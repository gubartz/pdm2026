package br.edu.ifsp.hto.htoipdm.filmes.feature.filme

import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.toDomain
import br.edu.ifsp.hto.htoipdm.filmes.feature.genero.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilmeRepository @Inject constructor(
    private val filmeService: FilmeService,
    private val filmeDao: FilmeDAO
) {
    val filmes: Flow<List<Filme>> =
        filmeDao.observarTodos().map { filmes ->
            filmes.map { filme ->
                filme.filme.toDomain(
                    generos = filme.generos.map { it.toDomain() }
                )
            }
        }

    suspend fun sync(): Result<Unit> = try {
        val filmes = filmeService.listar().dataResponse.orEmpty()

        val filmesEntity = filmes.map { it.toEntity() }

        val generosEntity = filmes
            .flatMap { it.generos }
            .distinctBy { it.id }
            .map { it.toEntity() }

        val relacionamentos = filmes
            .flatMap { it.toGeneroCrossRefs() }

        filmeDao.sincronizar(
            filmes = filmesEntity,
            generos = generosEntity,
            relacionamentos = relacionamentos
        )

        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun inserir(request: FilmeRequest): Result<Unit> = try {
        val filme = filmeService.inserir(request).dataResponse
            ?: return Result.failure(Exception("Resposta vazia"))

        val filmeEntity = filme.toEntity()

        val generosEntity = filme.generos
            .distinctBy { it.id }
            .map { it.toEntity() }

        val relacionamentos = filme.toGeneroCrossRefs()

        filmeDao.inserirCompleto(
            filme = filmeEntity,
            generos = generosEntity,
            relacionamentos = relacionamentos
        )

        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
package br.edu.ifsp.hto.htoipdm.filmes.feature.genero

import br.edu.ifsp.hto.htoipdm.filmes.feature.filme.toEntity
import br.edu.ifsp.hto.htoipdm.filmes.feature.filme.toGeneroCrossRefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.flatMap
import kotlin.collections.map
import kotlin.collections.orEmpty

class GeneroRepository @Inject constructor(
    private val generoService: GeneroService,
    private val generoDAO: GeneroDAO,
) {
    val generos: Flow<List<Genero>> =
        generoDAO.listar().map { generos ->
            generos.map { genero ->
                genero.toDomain()
            }
        }

    suspend fun sync(): Result<Unit> = try {
        val generos = generoService.listar().dataResponse.orEmpty()

        val generosEntity = generos.map { it.toEntity() }


        generoDAO.sincronizar(
            generos = generosEntity,
        )

        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
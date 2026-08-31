from flask import Blueprint, jsonify, request
from flask_jwt_extended import jwt_required

from app.db import get_db
from app.model.api_response import ApiResponse

bp = Blueprint("filme", __name__)


@bp.route("/filmes", methods=["GET"])
@jwt_required()
def listar_filmes():
    db = get_db()

    sql = """
    SELECT filme.id AS f_id
         , titulo AS f_titulo
         , data_lancamento AS f_data_lancamento
         , duracao AS f_duracao
         , titulo_original AS f_titulo_original
         , sinopse AS f_sinopse
         , genero.id AS g_id
         , genero.descricao AS g_descricao
    FROM filme
       , filme_genero
       , genero
    WHERE filme_genero.filme_id = filme.id
      ANd filme_genero.genero_id = genero.id"""

    filmes = db.execute(sql).fetchall()

    response = {}

    for filme in filmes:
        um_filme = response.setdefault(
            filme["f_id"],
            {
                "id": filme["f_id"],
                "titulo": filme["f_titulo"],
                "titulo_original": filme["f_titulo_original"],
                "data_lancamento": filme["f_data_lancamento"],
                "duracao": filme["f_duracao"],
                "sinopse": filme["f_sinopse"],
                "generos": [],
            },
        )

        um_filme["generos"].append(
            {
                "id": filme["g_id"],
                "descricao": filme["g_descricao"],
            }
        )

    return jsonify(
        ApiResponse(
            dataResponse=list(response.values()),
            message=None,
            response_code=200,
        ).to_dict()
    )


@bp.route("/filme/<int:id>", methods=["GET"])
def listar_filme(id: int):
    db = get_db()

    sql = """
    SELECT filme.id AS f_id
         , titulo AS f_titulo
         , data_lancamento AS f_data_lancamento
         , titulo_original AS f_titulo_original
         , duracao AS f_duracao
         , sinopse AS f_sinopse
         , genero.id AS g_id
         , genero.descricao AS g_descricao
    FROM filme
       , filme_genero
       , genero
    WHERE filme_genero.filme_id = filme.id
      ANd filme_genero.genero_id = genero.id
      AND filme.id = ?"""

    filmes = db.execute(sql, (id,)).fetchall()

    if not filmes:
        return (
            jsonify(
                ApiResponse(
                    dataResponse=None,
                    message=None,
                    response_code=404,
                ).to_dict()
            ),
            404,
        )

    response = {}

    for filme in filmes:
        um_filme = response.setdefault(
            filme["f_id"],
            {
                "id": filme["f_id"],
                "titulo": filme["f_titulo"],
                "data_lancamento": filme["f_data_lancamento"],
                "titulo_original": filme["f_titulo_original"],
                "duracao": filme["f_duracao"],
                "sinopse": filme["f_sinopse"],
                "generos": [],
            },
        )

        um_filme["generos"].append(
            {
                "id": filme["g_id"],
                "descricao": filme["g_descricao"],
            }
        )

    return jsonify(
        ApiResponse(
            dataResponse=list(response.values()),
            message=None,
            response_code=200,
        ).to_dict()
    )


@bp.route("/filme", methods=["POST"])
def inserir_filme():
    db = get_db()

    data = request.get_json()
    titulo = data["titulo"]
    titulo_original = data["titulo_original"]
    data_lancamento = data["data_lancamento"]
    duracao = data["duracao"]
    sinopse = data["sinopse"]
    generos = data.pop("generos")

    sql = """
        INSERT INTO filme(titulo, data_lancamento, titulo_original, duracao, sinopse)
        VALUES (:titulo, :data_lancamento, :titulo_original, :duracao, :sinopse)
    """

    cursor = db.execute(sql, data)
    cursor.lastrowid
    db.commit()

    return (
        jsonify(
            ApiResponse(
                dataResponse=list([cursor.lastrowid]),
                message=None,
                response_code=400,
            ).to_dict()
        ),
        200,
    )

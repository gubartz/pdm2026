from flask import Blueprint, jsonify, request

from app.db import get_db

bp = Blueprint("pessoa", __name__)


class ApiResponse:
    def __init__(self, dataResponse, message: str, response_code: int):
        self.message = message
        self.dataResponse = dataResponse
        self.response_code = response_code

    def to_dict(self):
        return {
            "message": self.message,
            "data_response": self.dataResponse,
            "response_code": self.response_code,
        }


@bp.route("/pessoas", methods=["GET"])
def index():
    db = get_db()
    pessoas = db.execute("SELECT * FROM pessoa").fetchall()
    return jsonify(
        ApiResponse(
            dataResponse=[dict(p) for p in pessoas],
            message="Pessoas listadas com sucesso!",
            response_code=200,
        ).to_dict()
    )


@bp.route("/pessoa", methods=["POST"])
def create():

    data = request.get_json()

    if not data:
        return jsonify({"error": "JSON body is required"}), 400

    id = data.get("id")
    nome = data.get("nome")
    data_nascimento = data.get("data_nascimento")

    if not nome or not data_nascimento:
        return jsonify({"error": "nome and data_nascimento are required"}), 400

    db = get_db()

    if id == 0:

        cursor = db.execute(
            """
            INSERT INTO pessoa (
                nome,
                data_nascimento
            )
            VALUES (?, ?)
            """,
            (nome, data_nascimento),
        )

        pessoa_id = cursor.lastrowid

    else:

        db.execute(
            """
            UPDATE pessoa
            SET nome = ?
              , data_nascimento = ?
            WHERE id = ?
            """,
            (
                nome,
                data_nascimento,
                id,
            ),
        )

        pessoa_id = id

    db.commit()

    pessoa = db.execute(
        """
        SELECT
            id,
            nome,
            data_nascimento
        FROM pessoa
        WHERE id = ?
        """,
        (pessoa_id,),
    ).fetchone()

    return (
        jsonify(
            ApiResponse(
                dataResponse=dict(pessoa),
                message="Pessoa salva com sucesso!",
                response_code=201,
            ).to_dict()
        ),
        201,
    )


@bp.route("/pessoa/<int:id>", methods=["DELETE"])
def delete(id: int):
    db = get_db()
    db.execute("DELETE FROM pessoa WHERE id = ?", (id,))
    db.commit()
    return (
        jsonify(
            ApiResponse(
                dataResponse=None,
                message="Pessoa removida com sucesso!",
                response_code=200,
            ).to_dict()
        ),
        200,
    )

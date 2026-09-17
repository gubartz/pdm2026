from flask import Blueprint, jsonify, request
from flask_jwt_extended import jwt_required

from app.db import get_db
from app.model.api_response import ApiResponse

bp = Blueprint("genero", __name__)


@bp.route("/generos", methods=["GET"])
# @jwt_required()
def listar_generos():
    db = get_db()

    sql = """
    SELECT *
    FROM genero"""

    generos = db.execute(sql).fetchall()

    return jsonify(
        ApiResponse(
            dataResponse=[dict(genero) for genero in generos],
            message=None,
            response_code=200,
        ).to_dict()
    )

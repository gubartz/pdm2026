from flask import Blueprint, jsonify, request
from flask_jwt_extended import create_access_token, decode_token

from app.db import get_db
from app.model.api_response import ApiResponse

bp = Blueprint("auth", __name__)


@bp.post("/auth/login")
def login():
    data = request.get_json()
    login = data["login"]
    senha = data["senha"]

    db = get_db()

    sql = """
    SELECT * FROM usuario
    WHERE email = :email
      AND senha = :senha
    """

    usuario = db.execute(sql, {"email": login, "senha": senha}).fetchone()
    if not usuario:
        return jsonify(
            ApiResponse(
                dataResponse=None,
                message=None,
                response_code=401,
            ).to_dict()
        ), 401

    access_token = create_access_token(identity=str(usuario["id"]))
    claims = decode_token(access_token)
    return {"token": access_token, "exp": claims["exp"]}

import os

from flask import Flask
from flask_jwt_extended import JWTManager

from .config import Config

jwt = JWTManager()


def create_app(test_config=None):
    app = Flask(__name__, instance_relative_config=True)

    app.config.from_object(Config)

    if test_config is not None:
        app.config.from_mapping(test_config)

    jwt.init_app(app)

    os.makedirs(app.instance_path, exist_ok=True)

    from . import db

    db.init_app(app)

    from .routes import filme, auth

    app.register_blueprint(filme.bp)
    app.register_blueprint(auth.bp)

    return app

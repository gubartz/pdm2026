from datetime import timedelta
import os

BASE_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))


class Config:
    # General
    SECRET_KEY = os.environ.get("SECRET_KEY", "dev")
    SSL_CONTEXT = "adhoc"

    # SQLite database
    DATABASE = os.path.join(BASE_DIR, "instance", "db.sqlite")
    # JWT
    JWT_SECRET_KEY = "0ac382a93fcc94e6111c5b6da4121d6fbd8d002f59561ed113bea2166dd0041b10603e8f21ac7162fed28f53207b2280d7edf809439bc01f6777e97e1f9c248a"
    JWT_ACCESS_TOKEN_EXPIRES = timedelta(days=365)

    # Other settings
    DEBUG = False
    TESTING = False

class ApiResponse:
    def __init__(self, dataResponse, message: str | None, response_code: int):
        self.message = message
        self.dataResponse = dataResponse
        self.response_code = response_code

    def to_dict(self):
        return {
            "message": self.message,
            "data_response": self.dataResponse,
            "response_code": self.response_code,
        }

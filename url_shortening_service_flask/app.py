from flask import Flask, jsonify
from controller.url_controller import url_controller  # Ensure correct import based on your directory structure
from exception.url_not_found_exception import UrlNotFoundException
from exception.invalid_url_input_exception import InvalidUrlInputException  # Ensure correct import based on your directory structure

app = Flask(__name__)

# Register the URL controller blueprint
app.register_blueprint(url_controller, url_prefix='/url')

# Global error handler for UrlNotFoundException
@app.errorhandler(UrlNotFoundException)
def handle_url_not_found(error):
    response = jsonify({"message": str(error)})
    response.status_code = 404
    return response

# Global error handler for InvalidUrlInputException
@app.errorhandler(InvalidUrlInputException)
def handle_invalid_url_input(error):
    response = jsonify({"message": str(error)})
    response.status_code = 400
    return response

# Global error handler for all unhandled exceptions
@app.errorhandler(Exception)
def handle_exception(error):
    response = jsonify({"message": "An unexpected error occurred."})
    response.status_code = 500
    return response

if __name__ == "__main__":
    app.run(debug=True, port=8080)

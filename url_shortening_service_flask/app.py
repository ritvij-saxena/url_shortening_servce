from flask import Flask, jsonify
from controllers.url_controller import url_controller
from exceptions.invalid_url_input_exception import InvalidUrlInputException
from exceptions.url_not_found_exception import UrlNotFoundException

app = Flask(__name__)
app.register_blueprint(url_controller, url_prefix='/url')

# Global exception handlers
@app.errorhandler(InvalidUrlInputException)
def handle_invalid_url_input(e):
    return jsonify(error=str(e)), 400

@app.errorhandler(UrlNotFoundException)
def handle_url_not_found(e):
    return jsonify(error=str(e)), 404

if __name__ == "__main__":
    app.run(debug=True)

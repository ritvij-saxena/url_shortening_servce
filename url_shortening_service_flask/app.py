from flask import Flask
from controller.url_controller import url_controller

app = Flask(__name__)

# Register the URL controller blueprint
app.register_blueprint(url_controller)

if __name__ == "__main__":
    app.run(debug=True)

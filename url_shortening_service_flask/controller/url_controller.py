from flask import Blueprint, request, jsonify, abort
from service.url_service import UrlService
from exception.invalid_url_input_exception import InvalidUrlInputException

url_controller = Blueprint('url_controller', __name__)
url_service = UrlService()

@url_controller.route("/shorten", methods=["POST"])
def create_short_url():
    original_url = request.args.get("url")
    data = request.get_json(silent=True)
    body_url = data.get("url") if data else None

    if original_url and body_url:
        raise InvalidUrlInputException("Provide 'url' as a query parameter or in the request body, not both.")
    elif not original_url and not body_url:
        raise InvalidUrlInputException("URL is required as a query parameter or in the request body.")

    url_to_shorten = original_url if original_url else body_url
    short_url = url_service.shorten_url(url_to_shorten)
    return short_url

@url_controller.route("/<string:short_url>", methods=["GET"])
def get_actual_url(short_url):
    actual_url = url_service.get_actual_url(short_url)
    return actual_url

@url_controller.route("/<string:short_url>/all", methods=["GET"])
def get_all_mapping(short_url):
    mapping_data = url_service.get_mapping_data(short_url)
    return jsonify(mapping_data)

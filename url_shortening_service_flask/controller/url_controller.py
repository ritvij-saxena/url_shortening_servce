from flask import Blueprint, request, jsonify, abort
from service.url_service import shorten_url, get_original_url

url_controller = Blueprint("url_controller", __name__)

@url_controller.route("/shorten", methods=["POST"])
def create_short_url():
    data = request.get_json()
    original_url = data.get("url")
    if not original_url:
        abort(400, description="URL is required")

    short_url = shorten_url(original_url)
    return jsonify({"short_url": short_url, "original_url": original_url})

@url_controller.route("/<short_id>", methods=["GET"])
def retrieve_original_url(short_id):
    original_url = get_original_url(short_id)
    if original_url is None:
        abort(404, description="URL not found")

    return jsonify({"original_url": original_url})

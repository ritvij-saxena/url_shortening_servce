from flask import Blueprint, request, jsonify, abort
from service.url_service import shorten_url, get_original_url

url_controller = Blueprint("url_controller", __name__)

@url_controller.route("/shorten", methods=["POST"])
def create_short_url():
    # Check for `url` in query parameters or in the JSON body
    original_url = request.args.get("url", None)
    data = request.get_json(silent=True)  # Avoid error if no JSON body
    body_url = data.get("url") if data else None

    # Validate: Only one of `url` query param or `url` in body should be present
    if original_url and body_url:
        abort(400, description="Provide 'url' as either a query parameter or in the request body, but not both.")
    elif not original_url and not body_url:
        abort(400, description="URL is required as a query parameter or in the request body.")

    # Use whichever `url` is provided
    url_to_shorten = original_url if original_url else body_url
    short_url = shorten_url(url_to_shorten)

    return jsonify({"short_url": short_url, "original_url": url_to_shorten})

@url_controller.route("/<short_id>", methods=["GET"])
def retrieve_original_url(short_id):
    original_url = get_original_url(short_id)
    if original_url is None:
        abort(404, description="URL not found")

    return jsonify({"original_url": original_url})

from model.url_store import url_store
from utils.base62_encoder import generate_short_id

def shorten_url(original_url):
    short_id = generate_short_id()
    url_store[short_id] = original_url
    return short_id

def get_original_url(short_id):
    return url_store.get(short_id)

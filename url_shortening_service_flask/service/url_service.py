import uuid
from utils.base62_encoder import Base62Encoder
from repositories.user_repository import UserRepository
from model.url_mapping import UrlMapping
from exception.url_not_found_exception import UrlNotFoundException

class UrlService:
    def __init__(self):
        self.user_repository = UserRepository()

    def shorten_url(self, url_to_shorten):
        unique_id = uuid.uuid4().hex[:13]  # Generate 13 characters from UUID
        short_id = Base62Encoder.get_encoded_id(unique_id)
        url_mapping = UrlMapping(short_id, f"short.ly/{short_id}", url_to_shorten)
        self.user_repository.add_url_mapping(url_mapping)
        return url_mapping.url

    def get_actual_url(self, short_url):
        url_mapping = self.user_repository.get_url_mapping(short_url)
        if not url_mapping:
            raise UrlNotFoundException(f"URL not found for the given ID: {short_url}")
        return url_mapping["actual_url"]

    def get_mapping_data(self, short_url):
        mapping = self.user_repository.get_url_mapping(short_url)
        if not mapping:
            raise UrlNotFoundException(f"URL not found for the given ID: {short_url}")
        return mapping

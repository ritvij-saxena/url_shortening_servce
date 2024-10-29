class UrlMapping:
    def __init__(self, id, url, actual_url):
        self.id = id
        self.url = url
        self.actual_url = actual_url

    def to_dict(self):
        return {
            "id": self.id,
            "url": self.url,
            "actual_url": self.actual_url
        }

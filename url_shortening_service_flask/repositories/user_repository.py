class UserRepository:
    def __init__(self):
        self.db = {}

    def add_url_mapping(self, url_mapping):
        self.db[url_mapping.id] = url_mapping.to_dict()
        print(self.db[url_mapping.id])

    def get_url_mapping(self, short_url):
        return self.db.get(short_url)

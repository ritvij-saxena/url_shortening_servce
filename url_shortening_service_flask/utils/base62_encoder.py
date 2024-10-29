import uuid

BASE62_CHARSET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

def generate_short_id():
    # Generate a random UUID, take the first 13 characters, convert to decimal, and encode to Base62
    uuid_part = uuid.uuid4().hex[:13]  # Get the first 13 characters
    decimal_id = int(uuid_part, 16)    # Convert hex to decimal
    return base62_encode(decimal_id)

def base62_encode(number):
    # Convert a decimal number to a Base62 string
    if number == 0:
        return BASE62_CHARSET[0]

    encoded = []
    base = len(BASE62_CHARSET)

    while number > 0:
        remainder = number % base
        encoded.append(BASE62_CHARSET[remainder])
        number //= base

    return ''.join(reversed(encoded))

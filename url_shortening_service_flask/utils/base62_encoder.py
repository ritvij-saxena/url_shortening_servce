class Base62Encoder:
    BASE62_ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    @staticmethod
    def get_encoded_id(hex_string):
        # Convert hex string to decimal
        decimal_number = int(hex_string, 16)
        # Encode decimal number to Base62
        base62 = []
        while decimal_number > 0:
            decimal_number, remainder = divmod(decimal_number, 62)
            base62.append(Base62Encoder.BASE62_ALPHABET[remainder])

        # Join characters in reverse order for correct Base62 encoding
        return ''.join(reversed(base62)) if base62 else Base62Encoder.BASE62_ALPHABET[0]

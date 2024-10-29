# URL Shortening Service

This repository contains URL shortening services implemented in two different frameworks:
- **Java (Spring Boot)**
- **Python (Flask)**

This project was inspired by a thought experiment in system design. The services use Base62 encoding to generate unique, shortened URLs from full-length URLs. 

## Project Structure

- `url-shortening-service/` - Contains the Spring Boot implementation.
- `url_shortening_service_flask/` - Contains the Flask implementation.

## URL Shortening Logic: Base62 Encoding

### How Base62 Encoding Works

Base62 encoding is a way to represent large decimal numbers in a compressed format using a set of 62 alphanumeric characters (0-9, a-z, and A-Z). This encoding is particularly useful in URL shortening services because it creates compact, human-readable IDs that can be embedded directly in URLs.

### ID Uniqueness and Generation Process

1. **UUID Generation**: 
   - A UUID (Universally Unique Identifier) is generated randomly for each URL.
   - To reduce the length, the first 13 characters are taken, giving a 52-bit hexadecimal number.

2. **Hexadecimal to Decimal Conversion**: 
   - The 13-character hexadecimal is then converted to a decimal number. 
   - This step results in a very large decimal value (up to approximately $2^{52}$).

3. **Base62 Encoding**: 
   - The large decimal number is then converted into Base62. 
   - Base62 encoding ensures that even large numbers can be represented compactly, producing a much shorter, URL-friendly identifier.

### Why IDs are Unique

Each UUID generated is highly unlikely to be duplicated due to its random nature and vast range of possible values. Since UUIDs have $2^{128}$ possible combinations, taking the first 13 characters alone yields $2^{52}$ combinations, which provides a sufficient level of uniqueness for most URL shortening applications.

### Expected Length of Encoded Strings

For a 13-character hexadecimal string:

1. **Decimal Equivalent**: The maximum value of a 13-character hexadecimal string (e.g., `fffffffffffff`) is approximately $2^{52} - 1$.
2. **Base62 Representation**: To find the length of the encoded Base62 string, we can use the formula:
   $$
   \text{Length} = \lceil \log_{62}(2^{52}) \rceil
   $$
   - Calculating this, $\log_{62}(2^{52}) \approx 8.39$, so the ceiling of this is **9**.

Thus, each shortened URL will be a **9-character Base62 string**, which is compact enough to be included in URLs and still provides a vast number of unique identifiers.

### Why Base62 Encoding is Useful in This Service

Base62 encoding offers several benefits:
- **Compact Representation**: Reduces the length of the identifier from a large number to around 9 characters.
- **Readability**: Uses alphanumeric characters, making the shortened URL more readable and URL-friendly.
- **Efficiency**: Minimizes the chance of collisions due to the vast number of possible Base62 combinations (over 56 billion for 9-character strings).

### Collision Probability

- With a 52-bit space, there are approximately $4.5 \times 10^{15}$ unique values, which reduces the likelihood of collisions. However, as the service scales, collision detection mechanisms may need to be introduced if billions of URLs are stored.
- Another second solution could be to extend the UUID substring from 13 characters to a longer length. However, this would also result in longer encoded strings, which may not be desirable for the URL shortening service.

## Future Improvements

To scale this system, consider implementing:
- **Replication and Sharding**: For database resilience and handling increased traffic.
- **Caching**: Store popular URLs in cache to reduce database hits with TTLs (time-to-live) if required.
- **Asynchronous Processing**: For higher write scalability and efficient logging.
- **Load Balancing**: With multiple read instances and fewer write instances to handle high traffic.

This project demonstrates the basics of a URL shortening service, with room to evolve into a more robust, production-ready system.

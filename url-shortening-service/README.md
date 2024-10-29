# URL Shortening Service

A simple URL shortening service that allows users to shorten long URLs and retrieve the original URLs using shortened identifiers. The service is built using Spring Boot and leverages validation annotations for input validation.

## Features

- Shorten a given URL
- Retrieve the original URL using a shortened identifier
- Input validation for URLs
- In-memory storage using a ConcurrentHashMap

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Jakarta Validation
- Lombok

## Endpoints

### 1. Shorten URL

**POST** `/url/shorten`

#### Request

- **Parameters**:
  - `url` (optional query parameter): The URL to be shortened.
  - `ShortenUrlRequest` (optional JSON body):
    ```json
    {
      "url": "https://example.com"
    }
    ```

#### Response

- **Success**: Returns the shortened URL as a string.
- **Error**: Returns error messages for invalid inputs.

### 2. Retrieve Original URL

**GET** `/url/{shorten_url}`

#### Request

- **Path Variable**:
  - `shorten_url`: The shortened URL identifier.

#### Response

- **Success**: Returns the original URL.
- **Error**: Returns a 404 error if the URL is not found.

### 3. Get All URL Mappings

**GET** `/url/{shorten_url}/all`

#### Request

- **Path Variable**:
  - `shorten_url`: The shortened URL identifier.

#### Response

- **Success**: Returns all mappings related to the shortened URL.
- **Error**: Returns a 404 error if the URL is not found.

## Validation

The service validates URLs using a custom annotation `@ValidHttpLink`, ensuring that only valid HTTP or HTTPS links are accepted.

## Base62 Encoding

The service utilizes **Base62 encoding** to generate compact representations of the original URLs. Base62 uses a character set of 62 unique symbols (0-9, a-z, A-Z) to convert numeric identifiers into shorter strings.

### Benefits of Base62 Encoding:

- **Compact Representation**: Generates shorter URLs compared to standard numeric IDs, making them easier to share and use.
- **URL Friendly**: The characters used are safe for URLs, eliminating the need for additional encoding and ensuring compatibility across different platforms.
- **Reduced Collisions**: The larger range of unique values minimizes the risk of generating the same shortened link for different URLs, thus enhancing the reliability of the service.
- **Human Readable**: Easier to share verbally or in writing, which improves user experience and accessibility.

### Why Choose Base62 Encoding?

The choice of Base62 encoding was made for several reasons:

1. **Efficiency**: Base62 encoding significantly reduces the length of the identifier, which is crucial in a URL-shortening service. Shortened URLs take up less space, making them more manageable in contexts like social media, emails, or printed materials.

2. **Diverse Character Set**: The use of a mix of numbers and both lowercase and uppercase letters allows for a vast number of unique combinations. This diversity is essential to ensure that the system can handle a large volume of shortened URLs without running into collisions.

3. **Ease of Use**: Base62-encoded strings are generally easier for users to remember and share. They do not include special characters that can complicate sharing, especially in situations where URLs are typed out manually.

4. **Implementation Simplicity**: Implementing Base62 encoding is straightforward, allowing for quick and efficient encoding and decoding processes, which is vital for a responsive user experience.

### How Base62 Encoding Works:

1. Convert a numeric ID to a Base62 string by repeatedly dividing by 62 and collecting remainders.
2. Each remainder maps to a character in the Base62 character set.
3. Construct the Base62 string by reversing the order of the collected characters.

This approach enhances user experience by creating shorter, memorable links.

## Design Inspiration

This URL shortening service was inspired by systems like "TinyURL". It aims to replicate the fundamental features of such services while allowing for scalability and efficiency.

## Final Thoughts and Improvements

While the URL Shortening Service is functional and demonstrates core features, there are several potential improvements that could enhance its capabilities:

1. **Database Integration**: Currently, the service uses an in-memory `ConcurrentHashMap` for storage, which limits persistence. Integrating a database (like MySQL or MongoDB) could allow for data persistence and improved scalability.

2. **Multiple Read Instances**: To handle high traffic and improve availability, implementing multiple read instances can help balance load and ensure faster response times for users retrieving URLs.

3. **Fewer Write Instances**: Limiting write instances could help manage data consistency and reduce the chances of write conflicts, especially when many users are shortening URLs simultaneously.

4. **Caching Popular Links**: Implementing a caching mechanism for "hot" or frequently accessed links can significantly improve performance, reducing the load on the database and speeding up response times.

5. **Replication to Databases**: Introducing replication strategies for databases could enhance data availability and fault tolerance. This would ensure that even in case of failures, the data remains accessible.

6. **Asynchronous Writes**: Allowing asynchronous writes could improve the user experience by making the system more responsive, as users would not have to wait for write operations to complete.

7. **User Authentication**: Implementing user accounts would enable users to manage their shortened URLs, track usage statistics, and provide additional security.

8. **Analytics Dashboard**: Adding an analytics feature could provide insights into how often links are accessed, geographical location of users, and other relevant metrics.

9. **Rate Limiting**: Implementing rate limiting could prevent abuse of the service by restricting the number of URLs a user can shorten within a certain time frame.

10. **Custom Shortened URLs**: Allowing users to choose their custom shortened URLs could enhance user experience and brand engagement.

11. **Error Handling and Logging**: Improving error handling and logging mechanisms could make it easier to diagnose issues and enhance the robustness of the service.

These improvements could help scale the application and offer a more comprehensive service to users.

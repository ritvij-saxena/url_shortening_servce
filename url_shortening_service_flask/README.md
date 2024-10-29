# URL Shortening Service (flask)

This project is a URL shortening service implemented in Python using Flask. It allows users to shorten long URLs and retrieve the original URLs using the shortened version. The service is designed with a clean architecture and follows the Model-View-Controller (MVC) pattern.

## Project Structure

- **app.py**: The main entry point for the Flask application.
- **controller/**: Contains the URL controller that handles incoming requests related to URL shortening.
- **exception/**: Custom exceptions for handling various error scenarios, such as invalid URL input and URL not found.
- **model/**: Data models representing the structure of URL mappings.
- **repository/**: The data repository layer for managing URL mappings in memory.
- **service/**: Business logic for shortening and retrieving URLs.
- **util/**: Utility functions, including the Base62 encoder for generating short URLs.
- **requirements.txt**: Python package dependencies required to run the application.



```
url_shortening_service_flask/
│
├── app.py                        # Entry point for the Flask application
├── controller/                   # Contains all the controllers (routes)
│   └── url_controller.py         # URL shortening controller
├── exception/                    # Custom exceptions for error handling
│   ├── invalid_url_input_exception.py  # Invalid URL input exception
│   └── url_not_found_exception.py      # URL not found exception
├── model/                        # Data models
│   └── url_mapping.py            # URL mapping model
├── repository/                   # Data repository layer
│   └── user_repository.py        # Repository for managing URL mappings
├── service/                     # Business logic
│   └── url_service.py            # URL service for shortening and retrieving URLs
├── util/                         # Utility functions
│   └── base62_encoder.py          # Base62 encoder for generating short URLs
└── requirements.txt              # Python package dependencies
```


## How It Works

The service accepts a long URL and generates a shortened version. The shortening algorithm uses the following steps:

1. Generate a random UUID.
2. Take the first 13 characters of the UUID.
3. Convert this substring from hexadecimal to decimal.
4. Encode the decimal number into a Base62 string to create the shortened URL.

### Base62 Encoding

Base62 encoding is a method of encoding data into a string of alphanumeric characters, which makes URLs shorter and more user-friendly. It uses a set of 62 characters: 0-9, a-z, and A-Z.

The length of the encoded Base62 string can be calculated using the formula:

$$
\text{Length} = \lceil \log_{62}(2^{52}) \rceil
$$

Calculating this, $\log_{62}(2^{52}) \approx 8.39$, so the ceiling of this is **9**. This means that we can generate a unique short URL that is always 9 characters long.

### Uniqueness of Shortened URLs

The use of UUIDs ensures that the shortened URLs are unique. By taking a substring of the UUID and encoding it, the likelihood of collisions is minimized. As the number of generated URLs increases, the risk of collisions can increase, but with a properly implemented Base62 encoder, the system can handle a vast number of unique short URLs without conflict.

## Usage

To shorten a URL, make a POST request to `/url/shorten` with the following JSON body:

```json
{
    "url": "https://example.com"
}
```

Alternatively, you can pass the URL as a query parameter:

```
/url/shorten?url=https://example.com
```

## Requirements

Make sure to install the required packages using:

```bash
pip install -r requirements.txt
```

## Running the Application

To run the application, use the following command:

```bash
python3 app.py
```

The application will start on `http://127.0.0.1:8080`.



### Docker Setup

This project can be easily set up and run using Docker. Follow the steps below to build and run the Flask URL shortening service in a Docker container.

#### Prerequisites

Make sure you have Docker and Docker Compose installed on your machine. You can download them from the following links:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

#### Building the Docker Image

1. **Clone the Repository**

   If you haven't already cloned the repository, do so using:

   ```bash
   git clone <repository-url>
   cd url_shortening_service_flask
   ```
2. **Build the Docker Image**

    In the root directory of your project, run the following command to build the Docker image:

    ```bash
    docker-compose build
    ```


#### Running the Docker Container

Once the image is built, you can run the container using:

   ```bash
   docker-compose up
   ```

This command starts your Flask application and exposes it on port `8080`.

#### Accessing the Application

You can access the URL shortening service by navigating to `http://localhost:5000` in your web browser or using a tool like Postman or curl.

#### Stopping the Docker Container

To stop the running container, you can use `CTRL + C` in the terminal where the Docker container is running, or run:

   ```bash
   docker-compose down
   ```

#### Notes

- The application runs in development mode by default. To change the environment to production, modify the `FLASK_ENV` variable in the `docker-compose.yml` file.
- If you make changes to the application code, the changes will reflect in the running container due to the volume binding in the `docker-compose.yml` file.
- For a production environment, consider using a WSGI server like Gunicorn or uWSGI and adjust the `Dockerfile` accordingly.
- **DO NOT USE IN PRODUCTION**. This is for educational purposes only.


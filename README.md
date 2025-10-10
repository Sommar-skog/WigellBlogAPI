# WigellBlogAPI

WigellBlogAPI is a **secured REST microservice** developed by **Amanda Aronsson** as part of the **Java Web Services course**.  
It demonstrates authentication and authorization with **Keycloak** and **OAuth 2.0 (JWT tokens)**, and follows a clean layered architecture with **DTOs, Services, and Repositories**.  
The API was tested and verified using **Postman** with **OAuth 2.0 authorization flow**.

---

## Overview

- **Language:** Java 21
- **Framework:** Spring Boot 3.5.5
- **Database:** H2 (in-memory)
- **Security:** Spring Security + Keycloak (OAuth2 Resource Server, JWT)
- **Ports:**
    - API → `8081`
    - Keycloak → `8080`

---

## Features

- Authentication and authorization via **Keycloak Realm “wigell-blog”**
- CRUD operations for blog posts
- DTO-based design for request and response separation
- Layered structure (`controllers`, `services`, `repositories`, `dtos`, `entities`, `utils`)
- Utility class (`UserInfoUtil`) for extracting user data from JWT
- In-memory H2 database for quick testing
- Fully testable endpoints via Postman using Bearer tokens from Keycloak

---

## Keycloak Integration

- **Realm:** `wigell-blog`
- **Client ID:** `wigellblog-client`
- **Token Endpoint:**  http://localhost:8080/realms/wigell-blog/protocol/openid-connect/token
- Access tokens are obtained via **Postman OAuth 2.0** configuration (Authorization Code or Client Credentials flow).
- The token is automatically attached to API requests when using Postman’s OAuth 2.0 setup.

---

## Example Endpoints

All endpoints require a valid **OAuth 2.0 access token** issued by **Keycloak**.  
Access control is based on roles defined in the realm `wigell-blog`.

| Method   | Endpoint                         | Description                                      | Required Role                           |
|----------|----------------------------------|--------------------------------------------------|-----------------------------------------|
| `GET`    | `/api/blogposts/posts`           | Retrieve all blog posts                          | Authenticated users (any role)          |
| `GET`    | `/api/blogposts/post/{id}`       | Retrieve a single blog post by ID                | Authenticated users (any role)          |
| `POST`   | `/api/blogposts/newpost`         | Create a new blog post                           | `wigellblog-user`                       |
| `PUT`    | `/api/blogposts/updatepost`      | Update an existing blog post                     | `wigellblog-user` or `wigellblog-admin` |
| `DELETE` | `/api/blogposts/deletepost/{id}` | Delete a blog post                               | `wigellblog-user` or `wigellblog-admin` |
| `GET`    | `/api/blogposts/count`           | Get the total number of blog posts in the system | `wigellblog-admin`                      |


---

## Running the Project

Run the application using Maven or your IDE:
mvn spring-boot:run

Access the API at:
http://localhost:8081

The H2 console is available at:  
http://localhost:8081/h2-console


---

## Testing with Postman (OAuth 2.0)

1. Start **Keycloak** on port `8080`.
2. In Postman, go to **Authorization → Type: OAuth 2.0**.
3. Use the following configuration:
    - Token Name: `WigellBlog Token`
    - Grant Type: `Client Credentials`
    - Access Token URL: `http://localhost:8080/realms/wigell-blog/protocol/openid-connect/token`
    - Client ID: `wigellblog-client`
4. Click **Get New Access Token**, then **Use Token**.
5. Execute API requests — authentication will be handled automatically.

---

## Result

Graded VG (Highest grade) in the Java Web Services course.







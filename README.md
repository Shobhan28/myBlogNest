# Empower Your Blog with the Spring Boot Backend

Welcome to the Spring Boot Blog Backend, a versatile Java-based application that provides a RESTful API for managing blog posts and comments. This documentation will guide you through its features, setup, and usage.

## Features
### Blog Management:

- Create, Read, Update, and Delete blog posts.
- View all blog posts and individual posts by their IDs.
- Pagination and sorting options for blog posts.

### Comment System:

- Create, Read, Update, and Delete comments on blog posts.
- Retrieve all comments for a specific blog post.
- Access individual comments by their IDs.

### Authentication and Authorization:
- Secure your API using JWT tokens.
- Generate JWT tokens through the /auth/login endpoint.

## Requirements
To get started, ensure you have the following tools installed:
- Java 8 or above
- Maven


The REST API for the blog backend is available at:
 `http://localhost:8080/api/blogs` 

### Here are the available endpoints and their methods:


| Post Endpoints                         | Method | Description                                |
| ------------------------------------ | ------ | ------------------------------------------ |
| `http://localhost:8080/api/posts`    | GET    | Get all blog posts                         |
| `http://localhost:8080/api/posts/{id}` | GET    | Get a single blog post by ID               |
| `http://localhost:8080/api/posts`    | POST   | Create a new blog post                     |
| `http://localhost:8080/api/posts/{id}` | PUT    | Update an existing blog post               |
| `http://localhost:8080/api/posts/{id}` | DELETE | Delete a blog post                         |

| Comment Endpoints                     | Method | Description                                |
| ------------------------------------ | ------ | ------------------------------------------ |
| `/blogs/{id}/comments`               | GET    | Get all comments on a blog post            |
| `/blogs/{id}/comments`               | POST   | Create a new comment on a blog post        |
| `/blogs/{id}/comments/{commentId}`   | GET    | Get a single comment on a blog post by ID |
| `/blogs/{id}/comments/{commentId}`   | PUT    | Update an existing comment on a blog post |
| `/blogs/{id}/comments/{commentId}`   | DELETE | Delete a comment on a blog post           |





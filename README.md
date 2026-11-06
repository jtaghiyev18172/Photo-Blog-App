# Photo-Blog-App
Photo blog web application where users can sign up, log in and publish posts. Anyone who opens the site can browse posts from all users.

# Properties 
* **Backend**: Java Spring boot
* **Frontend**: Flask

## Backend

### Data layer

- **Entities:** `User`, `Post` and `PostImage`. A user can have many posts, and a post can have many images.
- **Repositories:** Spring Data JPA repositories for all three entities. `UserRepository` can find a user by email or username and check if one already exists.
- **DTOs:** Separate request and response classes, so the API never returns database entities. This keeps the password hash from leaking.
- **Database:** H2 in-memory database (data is reset on every restart).

### Authentication

- **Register** (`POST /api/auth/register`): validates the input, rejects duplicate emails and usernames (409), hashes the password with BCrypt and saves the user.
- **Login** (`POST /api/auth/login`): checks the password against the stored hash and returns a JWT. A wrong password and an unknown email return the same 401 message, so attackers cannot find out which emails are registered.
- **JWT:** signed token with the user's email as subject, valid for 1 hour. The signing secret is read from the `JWT_SECRET` environment variable (minimum 32 characters) and is never stored in the code.
- **JWT filter:** on every request it reads the `Authorization: Bearer ...` header, validates the token and sets the logged-in user. It never blocks a request itself, the security rules decide that.
- **Current user** (`GET /api/auth/me`): returns the logged-in user's data.

### Security rules

- Stateless sessions (no server-side session, every request carries its own token).
- CSRF is disabled, because the API uses tokens in a header instead of cookies.
- Public endpoints: register, login and `GET` requests for posts and uploaded images. Everything else needs a valid token.
- **401** means "not logged in or invalid token". **403** means "logged in, but not allowed" (used later, for example when deleting someone else's post).
- Both return a JSON error instead of the default Spring page.

### Error handling

A global exception handler returns errors in one JSON format: `status`, `message`, `fieldErrors` (for validation problems) and `timestamp`.

### Environment variables

| Variable | Purpose |
| --- | --- |
| `JWT_SECRET` | Secret key for signing tokens (at least 32 characters) |

### Known limitations (so far)

- The H2 console is open for development and must be disabled in production.
- Tokens expire after 1 hour and there is no refresh token.
- The H2 database is in memory, so all data is lost on restart.
# Treasure Hunty API Documentation

## Authentication

The authentication system is implemented using **JSON Web Tokens (JWT)**, providing a stateless and secure way to verify users' identities and protect API endpoints.

### 1. User Signup

**Endpoint:**
```
POST http://localhost:8080/auth/signup
```

**Request Body:**
```json
{
  "email": "chouchou@inpt.com",
  "password": "1234xs5670",
  "fullName": "ChouChou"
}
```

**Response:**
```json
{
  "id": 5,
  "fullName": "ChouChou",
  "email": "chouchou@inpt.com",
  "password": "$2a$10$hashedpassword",
  "createdAt": "2025-02-04T11:48:51.912+00:00",
  "updatedAt": "2025-02-04T11:48:51.912+00:00",
  "score": 0,
  "gameId": -1,
  "enabled": true,
  "authorities": [],
  "username": "chouchou@inpt.com",
  "credentialsNonExpired": true,
  "accountNonExpired": true,
  "accountNonLocked": true
}
```

### 2. User Login

**Endpoint:**
```
POST http://localhost:8080/auth/login
```

**Request Body:**
```json
{
  "email": "chouchou@inpt.com",
  "password": "1234xs5670"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "expiresIn": 3600000
}
```

**Note:** The returned token should be passed as a **Bearer Token** in all subsequent API requests.

### 3. User Logout

**Endpoint:**
```
POST http://localhost:8080/auth/logout
```

**Description:**
Logs out the user by invalidating their authentication session.

**Headers:**
```
Authorization: Bearer <JWT_TOKEN>
```

**Response:**
```json
{
    "message": "User successfully logged out."
}
```

---

## User Information

### 4. Get Logged-in User Details

**Endpoint:**
```
GET http://localhost:8080/users/me
```

**Headers:**
```
Authorization: Bearer <JWT_TOKEN>
```

**Response:**
```json
{
    "id": 5,
    "fullName": "ChouChou",
    "email": "chouchou@inpt.com",
    "score": 0,
    "gameId": -1,
    "enabled": true
}
```

---

## Game Management

### 5. Start a New Game

**Endpoint:**
```
POST http://localhost:8080/games
```

**Request Body:**
```json
{
    "id": 105
}
```

---

## Riddle Management

### 6. Get All Riddles Associated with the Game

**Endpoint:**
```
GET http://localhost:8080/riddles
```

**Response:**
```json
[
  { "id": 277, "name": "The Artful Gallery", "solved": false },
  { "id": 281, "name": "The Eternal Obelisk", "solved": false },
  { "id": 278, "name": "The Gothic Jewel", "solved": false }
]
```

### 7. Get a Single Riddle

**Endpoint:**
```
GET http://localhost:8080/riddles/277
```

**Response:**
```json
{
  "id": 277,
  "name": "The Artful Gallery",
  "text": "I house paintings from every age...",
  "answer": null,
  "solved": false,
  "latitude": null,
  "longitude": null
}
```

### 8. Solve a Riddle

**Endpoint:**
```
POST http://localhost:8080/riddles/solve/277
```

**Request Body:**
```json
{
    "latitude": 48.89285425436402,
    "longitude": 2.3724074133390576
}
```

#### Incorrect Attempt Response:
```json
{
    "message": "Incorrect location. You are 5267.406668513984 meters away.",
    "answer": null,
    "correctLocation": null,
    "distance": 5267.406668513984,
    "solved": false
}
```

#### Correct Attempt Response:
```json
{
    "message": "Correct location! Riddle solved 🎉",
    "answer": "Louvre Museum",
    "correctLocation": [2.3376, 48.8606],
    "distance": 0,
    "solved": true
}
```

### 9. View Solved Riddle

**Endpoint:**
```
GET http://localhost:8080/riddles/277
```

**Response:**
```json
{
  "id": 277,
  "name": "The Artful Gallery",
  "text": "I house paintings from every age...",
  "answer": "Louvre Museum",
  "solved": true,
  "latitude": 48.8606,
  "longitude": 2.3376
}
```

---

## Notes
- Ensure that all authenticated requests include the `Authorization: Bearer <JWT_TOKEN>` header.
- Riddle coordinates are initially `null` until solved.
- If a solution attempt is incorrect, the response provides the distance to the correct location.

---

📌 **Treasure Hunty API** - Happy Hunting! 🏆

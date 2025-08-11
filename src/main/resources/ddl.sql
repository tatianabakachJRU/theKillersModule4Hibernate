CREATE TABLE users
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE profiles
(
    id         SERIAL PRIMARY KEY,
    user_id    INT UNIQUE NOT NULL,
    bio        TEXT,
    birth_date DATE,
    create_at  TIMESTAMP DEFAULT NOW(),
    is_active  BOOLEAN   DEFAULT TRUE,
    height     DECIMAL(5, 2),
    avatar     BYTEA,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
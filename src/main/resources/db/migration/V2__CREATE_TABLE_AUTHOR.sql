CREATE TABLE users
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(120) NOT NULL,
    CONSTRAINT PK_USERS PRIMARY KEY (id)
);

INSERT INTO
    users
VALUES
    (1, 'Jorginho', 'Jorginhohtinho@gmail.com')
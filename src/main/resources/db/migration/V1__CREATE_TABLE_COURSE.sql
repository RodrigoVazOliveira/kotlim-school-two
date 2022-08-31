
CREATE TABLE courses
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    category VARCHAR(50),
    CONSTRAINT PK_COURSE PRIMARY KEY (id)
);

INSERT INTO
    courses
VALUES
    (1, 'Kotlim', 'Programacao');
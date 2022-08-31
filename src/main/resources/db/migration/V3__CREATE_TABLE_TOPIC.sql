CREATE TABLE topics
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(60) NOT NULL,
    message TEXT NOT NULL,
    date_time_created DATETIME NOT NULL,
    course_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT PK_TOPIC PRIMARY KEY (id),
    CONSTRAINT FK_COURSE FOREIGN KEY (course_id)
        REFERENCES courses (id),
    CONSTRAINT FK_AUTHOR FOREIGN KEY (author_id)
            REFERENCES users (id)
);
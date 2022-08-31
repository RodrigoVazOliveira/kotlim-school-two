CREATE TABLE responses_topic
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    message TEXT NOT NULL,
    date_time_created DATETIME NOT NULL,
    author_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,
    soluction BOOL NOT NULL,
    CONSTRAINT PK_RESPONSES_TOPIC PRIMARY KEY (id),
    CONSTRAINT FK_RESPONSE_TOPIC_AUTHOR FOREIGN KEY (author_id)
                REFERENCES users (id),
    CONSTRAINT FK_RESPONSE_TOPIC_TOPIC FOREIGN KEY (topic_id)
                    REFERENCES topics (id)
);
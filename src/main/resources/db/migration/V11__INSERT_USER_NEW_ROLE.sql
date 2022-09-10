INSERT INTO
    roles (name)
VALUES
    ('ADMIN');

INSERT INTO
    users (name, email, password)
VALUES
    ('Sou o admin','admin@gmail.com', '$2a$10$2KhnBYUCme7PSr49GsoJneVrLjX8vnNlh3MarF18PBRzVJo32MWUS');

INSERT INTO
    user_roles (user_id, role_id)
SELECT
    (SELECT id FROM users WHERE email = 'admin@gmail.com'),
    id
 FROM
    roles
WHERE
    name = 'ADMIN'

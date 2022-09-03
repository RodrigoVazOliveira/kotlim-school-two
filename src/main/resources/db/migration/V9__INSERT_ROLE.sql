INSERT INTO
    roles (name) VALUES ('LEITURA_ESCRITA');

INSERT INTO
    user_roles (user_id, role_id)
SELECT
    (SELECT id FROM users WHERE email = 'raimundo@gmail.com'),
    id
FROM
    roles
WHERE
    name = 'LEITURA_ESCRITA';


-- inserir usaurio sem role
INSERT INTO
    users
VALUES
(NULL, 'geraldo', 'geraldo@gmail.com', '$2a$12$YGdTQNw9Sudn9kr6RxPLpOt08R55XbD4IBogRQgVwKn5i92e5Zu3W')
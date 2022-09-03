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

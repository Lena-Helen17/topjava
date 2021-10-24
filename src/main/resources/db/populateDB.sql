DELETE
FROM user_roles;
DELETE
FROM users;
DELETE  FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id)
VALUES ('2021-10-23 10:28:08', 'Завтрак', 500, 100000),
       ('2021-10-23 13:32:00', 'Обед', 730, 100000),
       ('2021-10-23 13:32:00', 'Обед', 730, 100001),
       ('2021-10-25 20:00:00', 'Ужин', 600, 100000);
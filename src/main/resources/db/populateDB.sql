DELETE
FROM meals;
DELETE
FROM user_roles;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('User2', 'user2@yandex.ru', 'password2'),
       ('User3', 'user3@yandex.ru', 'password3'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER2', 100001),
       ('USER3', 100002),
       ('ADMIN', 100003);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2021-10-23 10:28:08', 'Завтрак', 500, 100000),
       ('2021-10-24 10:28:08', 'Завтрак', 500, 100000),
       ('2021-10-24 10:28:08', 'Завтрак', 500, 100002),
       ('2021-10-23 13:32:00', 'Обед', 730, 100000),
       ('2021-10-23 13:32:00', 'Обед', 730, 100002),
       ('2021-10-25 13:32:00', 'Обед', 730, 100000),
       ('2021-10-23 13:32:00', 'Обед', 730, 100001),
       ('2021-10-25 20:00:00', 'Ужин', 600, 100000),
       ('2021-10-24 20:00:00', 'Ужин', 900, 100000),
       ('2021-10-24 13:32:00', 'Обед', 930, 100000);
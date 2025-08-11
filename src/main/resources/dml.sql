INSERT INTO users(name, email)
VALUES ('Anna', 'anna@example.com'),
       ('Petr', 'petr@example.com'),
       ('Charlie', 'charlie@example.com'),
       ('Maria', 'maria@example.com');

INSERT INTO profiles(user_id, bio, birth_date, height, avatar)
VALUES (1, 'Java-разработчик, который любит котиков', '1990-05-15', 180.5, NULL),
       (2, 'Тестировщик, который любит прогулки', '1992-04-18', 158.00, NULL),
       (3, 'UI дизайнер, который любит рисовать', '2002-01-04', 190.5, NULL);
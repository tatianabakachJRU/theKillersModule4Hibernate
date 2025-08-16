-- Очищаем таблицы (если нужно начать с чистого листа)
DELETE FROM profiles;
DELETE FROM users;

-- Сбрасываем счётчики SERIAL
ALTER SEQUENCE users_id_seq RESTART WITH 1;
ALTER SEQUENCE profiles_id_seq RESTART WITH 1;

-- Вставляем пользователей
INSERT INTO users (name, email) VALUES
                                    ('Anna Petrova', 'anna.petrova@example.com'),
                                    ('Petr Ivanov', 'petr.ivanov@example.com'),
                                    ('Charlie Brown', 'charlie.brown@example.com'),
                                    ('Maria Garcia', 'maria.garcia@example.com'),
                                    ('David Kim', 'david.kim@example.com'),
                                    ('Lena Müller', 'lena.mueller@example.com'),
                                    ('James Wilson', 'james.wilson@example.com'),
                                    ('Sofia Chen', 'sofia.chen@example.com'),
                                    ('Alexei Volkov', 'alexei.volkov@example.com'),
                                    ('Emma Dubois', 'emma.dubois@example.com');

-- Вставляем профили
INSERT INTO profiles (user_id, bio, birth_date, create_at, is_active, height, avatar) VALUES
                                                                                          (1, 'Java-разработчик, люблю котиков и утренний кофе', '1990-05-15', NOW(), TRUE, 165.5, NULL),
                                                                                          (2, 'Тестировщик, обожаю настольные игры', '1992-04-18', NOW(), TRUE, 178.0, NULL),
                                                                                          (3, 'UI/UX дизайнер, рисую каждый день', '2002-01-04', NOW(), TRUE, 182.5, NULL),
                                                                                          (4, 'Frontend-разработчик, фанат React', '1995-07-22', NOW(), TRUE, 160.0, NULL),
                                                                                          (5, 'Android-разработчик, бегаю по утрам', '1988-11-30', NOW(), FALSE, 175.0, NULL),
                                                                                          (6, 'Data Analyst, люблю визуализацию данных', '1993-03-10', NOW(), TRUE, 168.5, NULL),
                                                                                          (7, 'DevOps инженер, люблю автоматизацию', '1985-09-12', NOW(), TRUE, 185.0, NULL),
                                                                                          (8, 'Python-разработчик, участвую в open-source', '2000-06-18', NOW(), TRUE, 158.0, NULL),
                                                                                          (9, 'Fullstack, люблю сложные задачи', '1987-12-05', NOW(), TRUE, 172.5, NULL),
                                                                                          (10, 'QA Lead, пишу тесты на Java и Selenium', '1991-08-25', NOW(), TRUE, 163.0, NULL);
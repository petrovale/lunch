DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (name, vote) VALUES ('Black Thai', 0);
INSERT INTO restaurants (name, vote) VALUES ('White Rabbit', 0);
INSERT INTO restaurants (name, vote) VALUES ('Уголек', 0);

INSERT INTO meals (date_time, description, price, restaurant_id) VALUES
  ('2017-05-30 13:31:00', 'борщ', 500.00, 100002),
  ('2017-05-30 13:32:00', 'каша гречневая', 300.00, 100002),
  ('2017-05-30 13:33:00', 'котлета кур', 700.00, 100002);

INSERT INTO meals (date_time, description, price, restaurant_id) VALUES
  ('2017-05-30 13:34:00', 'омлет со спаржей', 500.00, 100003),
  ('2017-05-30 13:35:00', 'салат по сезону', 100.00, 100003),
  ('2017-05-30 13:36:00', 'каша молочная', 500.00, 100003);

INSERT INTO meals (date_time, description, price, restaurant_id) VALUES
  ('2017-05-30 13:37:00', 'суп фасолевый', 200.00, 100004),
  ('2017-05-30 13:38:00', 'рыба запеченная', 1000.00, 100004),
  ('2017-05-30 13:39:00', 'суп -харчо', 300.00, 100004);

INSERT INTO voices (restaurant_id, user_id) VALUES
  (100004, 100000),
  (100003, 100000),
  (100003, 100001);

INSERT INTO voices (restaurant_id, user_id, date_voice, registered) VALUES
  (100004, 100000, '2017-06-17' ,'2017-06-17 10:37:00'),
  (100002, 100000, '2017-06-17' ,'2017-06-17 13:37:00'),
  (100003, 100001, '2017-06-17' ,'2017-06-17 09:37:00')
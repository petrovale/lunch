DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User11', 'user@yandex.ru', 'password');

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
  ('2017-05-30 13:00:00', 'борщ', 500, 100002),
  ('2017-05-30 13:00:00', 'каша гречневая', 300, 100002),
  ('2017-05-30 13:00:00', 'котлета кур', 700, 100002);

INSERT INTO meals (date_time, description, price, restaurant_id) VALUES
  ('2017-05-30 13:00:00', 'омлет со спаржей', 500, 100003),
  ('2017-05-30 13:00:00', 'салат по сезону', 100, 100003),
  ('2017-05-30 13:00:00', 'каша молочная', 500, 100003);

INSERT INTO meals (date_time, description, price, restaurant_id) VALUES
  ('2017-05-30 13:00:00', 'суп фасолевый', 200, 100004),
  ('2017-05-30 13:00:00', 'рыба запеченная', 1000, 100004),
  ('2017-05-30 13:00:00', 'суп -харчо', 300, 100004);

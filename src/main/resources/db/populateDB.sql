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

INSERT INTO restaurants (name) VALUES ('Black Thai');
INSERT INTO restaurants (name) VALUES ('White Rabbit');
INSERT INTO restaurants (name) VALUES ('Уголек');

INSERT INTO meals (date, description, price, restaurant_id) VALUES
  ('2017-05-30', 'борщ', 500.00, 100002),
  ('2017-05-30', 'каша гречневая', 300.00, 100002),
  ('2017-05-30', 'котлета кур', 700.00, 100002);

INSERT INTO meals (date, description, price, restaurant_id) VALUES
  ('2017-05-30', 'омлет со спаржей', 500.00, 100003),
  ('2017-05-30', 'салат по сезону', 100.00, 100003),
  ('2017-05-30', 'каша молочная', 500.00, 100003);

INSERT INTO meals (date, description, price, restaurant_id) VALUES
  ('2017-05-30', 'суп фасолевый', 200.00, 100004),
  ('2017-06-29', 'рыба запеченная', 1000.00, 100004),
  ('2017-05-30', 'суп -харчо', 300.00, 100004);

INSERT INTO votes (restaurant_id, user_id, date) VALUES
  (100004, 100000, '2017-06-18'),
  (100003, 100001, '2017-06-18');

INSERT INTO votes (restaurant_id, user_id, date) VALUES
  (100004, 100001, '2017-06-20');

INSERT INTO votes (restaurant_id, user_id, date) VALUES
  (100003, 100001, '2017-05-30'),
  (100002, 100000, '2017-05-30');
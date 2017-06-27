DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS voices;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL,
  registered TIMESTAMP DEFAULT now(),
  enabled    BOOL DEFAULT TRUE
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL,
  registered TIMESTAMP DEFAULT now()
);
CREATE UNIQUE INDEX restaurants_unique_name_idx ON restaurants (name);

CREATE TABLE meals (
  id             INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id  INTEGER NOT NULL,
  date           DATE DEFAULT now(),
  description    TEXT NOT NULL,
  price          DECIMAL(12,2) NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE voices
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  date DATE DEFAULT now(),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX voices_unique_restaurant_user_date_idx ON voices(restaurant_id, user_id, date);
DELETE FROM menu;
DELETE FROM vote;
DELETE FROM restaurant;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, password) VALUES
  ('Admin', 'admin'),
  ('User1', 'user1'),
  ('User2', 'user2');

INSERT INTO user_roles (user_id, role) VALUES
  (100000, 'ROLE_ADMIN'),
  (100000, 'ROLE_USER'),
  (100001, 'ROLE_USER'),
  (100002, 'ROLE_USER');

INSERT INTO restaurant (name, address) VALUES
  ('rest1', 'street One'),
  ('rest2', 'street Two');

INSERT INTO menu (name, price, restaurant_id) VALUES
  ('fish', 10, 100003),
  ('meat', 20, 100003),
  ('tea', 2, 100003),
  ('snack', 5, 100004),
  ('cake', 6, 100004),
  ('coffee', 7, 100004);

INSERT INTO vote (restaurant_id, user_id, time) VALUES
  (100003, 100000, '2018-01-14 12:0:0'),
  (100003, 100001, '2018-01-10 10:0:0'),
  (100004, 100002, '2018-01-11 11:0:0');

INSERT INTO restaurant_user (restaurant_id, user_id) VALUES
(100003, 100000);
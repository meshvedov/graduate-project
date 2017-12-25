DELETE FROM menu;
DELETE FROM restaurant;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, password) VALUES
  ('User1', 'user1'),
  ('User2', 'user2'),
  ('Admin', 'admin');

INSERT INTO user_roles (user_id, role) VALUES
  (100000, 'ROLE_USER'),
  (100001, 'ROLE_USER'),
  (100002, 'ROLE_ADMIN');

INSERT INTO restaurant (name, address, votes) VALUES
  ('rest1', 'street One', 10),
  ('rest2', 'street Two', 20);

INSERT INTO menu (name, price, restaurant_id) VALUES
  ('fish', 10, 1003),
  ('meat', 20, 1003),
  ('tea', 2, 1003),
  ('snake', 5, 1004),
  ('cake', 6, 1004),
  ('coffee', 7, 1004);
INSERT INTO clients (name) VALUES
('Star-Lord'),
('Gamora'),
('Drax the Destroyer'),
('Rocket'),
('Groot'),
('Yondu'),
('Nebula'),
('Mantis'),
('Taserface'),
('Ronan the Accuser');

INSERT INTO planets (id, name) VALUES
  ('NOW', 'Knowhere'),
  ('XN72', 'Xandar'),
  ('BK9', 'Berhert'),
  ('SVD7', 'Sovereign'),
  ('CTXX', 'Contraxia');

INSERT INTO tickets (created_at, client_id, from_planet_id, to_planet_id) VALUES
   ('2023-10-23 12:00:00', 1, 'NOW', 'XN72'),
   ('2023-10-23 12:15:00', 2, 'BK9', 'SVD7'),
   ('2023-10-23 12:30:00', 3, 'CTXX', 'BK9'),
   ('2023-10-23 12:45:00', 4, 'SVD7', 'NOW'),
   ('2023-10-23 13:00:00', 5, 'XN72', 'BK9'),
   ('2023-10-23 13:15:00', 6, 'SVD7', 'CTXX'),
   ('2023-10-23 13:30:00', 7, 'BK9', 'XN72'),
   ('2023-10-23 13:45:00', 8, 'CTXX', 'NOW'),
   ('2023-10-23 14:00:00', 9, 'NOW', 'XN72'),
   ('2023-10-23 14:15:00', 10, 'XN72', 'BK9');


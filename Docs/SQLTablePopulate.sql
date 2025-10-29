INSERT INTO `User` (name, password, email) VALUES
    ('Jeppe K', 'Jeppe123', 'Nytnytnyt@pagne.dk');

INSERT INTO `Wish` (user_id, name, description, link, price, isReserved) VALUES
    (1, 'BMW M5', 'Ny dyt til far', 'https://www.bmw.dk/', 1000000, FALSE);

INSERT INTO `Wish` (user_id, name, description, link, price, isReserved) VALUES
    (1,  'bums', 'Kom NU', 'https://www.bmw.dk/', 250, FALSE);
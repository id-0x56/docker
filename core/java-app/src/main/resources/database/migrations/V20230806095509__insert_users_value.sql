INSERT IGNORE users (name, email, password) VALUES
    ('Kathleen', 'kathleen@mail.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
    ('Marie', 'marie@mail.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
    ('Louise', 'louise@mail.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
    ('Sharon', 'sharon@mail.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
    ('Danny', 'danny@mail.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
    ('Roxie', 'roxie@mail.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO');

INSERT IGNORE roles (name) VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER');

REPLACE INTO users_roles (user_id, role_id) VALUES
    (1, 1),
    (2, 2),
    (3, 2),
    (4, 2),
    (5, 2),
    (6, 2);

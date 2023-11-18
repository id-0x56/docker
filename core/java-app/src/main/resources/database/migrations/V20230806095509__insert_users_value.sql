INSERT IGNORE users (name, email, password, last_login_at, created_at) VALUES
    ('admin', 'admin@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO', now(), now()),
    ('Kathleen', 'kathleen@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO', now(), now()),
    ('Marie', 'marie@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO', now(), now()),
    ('Louise', 'louise@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO', now(), now()),
    ('Sharon', 'sharon@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO', now(), now()),
    ('Danny', 'danny@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO', now(), now()),
    ('Roxie', 'roxie@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO', now(), now());

INSERT IGNORE roles (name) VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER');

REPLACE INTO users_roles (user_id, role_id) VALUES
    (1, 1),
    (2, 2),
    (3, 2),
    (4, 2),
    (5, 2),
    (6, 2),
    (7, 2);

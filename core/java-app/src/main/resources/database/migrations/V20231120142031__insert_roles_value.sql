-- h2
-- INSERT INTO roles (name) VALUES
--     ('ROLE_ADMIN'),
--     ('ROLE_USER');

-- h2
-- INSERT INTO users_roles (user_id, role_id) VALUES
--     (1, 1),
--     (2, 1),
--     (3, 1),
--     (4, 2),
--     (5, 2),
--     (6, 2),
--     (7, 2);

-- mysql
-- INSERT IGNORE roles (name) VALUES
--     ('ROLE_ADMIN'),
--     ('ROLE_USER');

-- mysql
-- REPLACE INTO users_roles (user_id, role_id) VALUES
--     (1, 1),
--     (2, 1),
--     (3, 1),
--     (4, 2),
--     (5, 2),
--     (6, 2),
--     (7, 2);

-- postgres
INSERT INTO roles (name) VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER')
ON CONFLICT (name) DO NOTHING;

-- postgres
INSERT INTO users_roles (user_id, role_id) VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 2),
    (5, 2),
    (6, 2),
    (7, 2)
ON CONFLICT (user_id, role_id) DO NOTHING;

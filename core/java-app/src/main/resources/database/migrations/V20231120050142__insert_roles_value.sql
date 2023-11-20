-- INSERT INTO roles (name) VALUES
--     ('ROLE_ADMIN'),
--     ('ROLE_USER')
-- ON CONFLICT (name) DO NOTHING;

-- INSERT INTO users_roles (user_id, role_id) VALUES
--     (1, 1),
--     (2, 1),
--     (3, 1),
--     (4, 2),
--     (5, 2),
--     (6, 2),
--     (7, 2)
-- ON CONFLICT (user_id, role_id) DO NOTHING;

INSERT IGNORE roles (name) VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER');

REPLACE INTO users_roles (user_id, role_id) VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 2),
    (5, 2),
    (6, 2),
    (7, 2);

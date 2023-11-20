-- INSERT INTO activities (user_id, last_login_ip, last_login_at, created_at) VALUES
--     (1, '127.0.0.1', now(), now()),
--     (2, '127.0.0.1', now(), now()),
--     (3, '127.0.0.1', now(), now()),
--     (4, '127.0.0.1', now(), now()),
--     (5, '127.0.0.1', now(), now()),
--     (6, '127.0.0.1', now(), now()),
--     (7, '127.0.0.1', now(), now())
-- ON CONFLICT (user_id) DO NOTHING;

INSERT IGNORE activities (user_id, last_login_ip, last_login_at, created_at) VALUES
    (1, '127.0.0.1', now(), now()),
    (2, '127.0.0.1', now(), now()),
    (3, '127.0.0.1', now(), now()),
    (4, '127.0.0.1', now(), now()),
    (5, '127.0.0.1', now(), now()),
    (6, '127.0.0.1', now(), now()),
    (7, '127.0.0.1', now(), now());

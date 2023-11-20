CREATE TABLE IF NOT EXISTS activities (
    user_id BIGINT NOT NULL,
    last_login_ip VARCHAR(64) NULL,
    last_login_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NULL,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,

    PRIMARY KEY (user_id)
);

-- CREATE TABLE IF NOT EXISTS `activities` (
--     `user_id` BIGINT NOT NULL,
--     `last_login_ip` VARCHAR(64) NULL,
--     `last_login_at` TIMESTAMP NOT NULL,
--     `created_at` TIMESTAMP NOT NULL,
--     `updated_at` TIMESTAMP NULL,

--     FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,

--     PRIMARY KEY (`user_id`)
-- );

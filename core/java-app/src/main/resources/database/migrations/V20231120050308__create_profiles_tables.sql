-- h2, mysql
-- CREATE TABLE IF NOT EXISTS `profiles` (
--     `user_id` BIGINT NOT NULL,
--     `verify` BOOLEAN NOT NULL DEFAULT 0,
--     `first_name` VARCHAR(32) NOT NULL,
--     `last_name` VARCHAR(32) NOT NULL,

--     FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,

--     PRIMARY KEY (`user_id`)
-- );

-- postgres
CREATE TABLE IF NOT EXISTS profiles (
    user_id BIGINT NOT NULL,
    verify BOOLEAN NOT NULL DEFAULT FALSE,
    first_name VARCHAR(32) NOT NULL,
    last_name VARCHAR(32) NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,

    PRIMARY KEY (user_id)
);

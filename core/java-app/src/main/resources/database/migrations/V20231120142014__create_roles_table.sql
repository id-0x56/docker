CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id BIGINT NOT NULL,
    role_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,

    PRIMARY KEY (user_id, role_id)
);

-- CREATE TABLE IF NOT EXISTS `roles` (
--     `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
--     `name` VARCHAR(32) NOT NULL UNIQUE
-- );

-- CREATE TABLE IF NOT EXISTS `users_roles` (
--     `user_id` BIGINT NOT NULL,
--     `role_id` INT NOT NULL,

--     FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
--     FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE,

--     PRIMARY KEY (`user_id`, `role_id`)
-- );

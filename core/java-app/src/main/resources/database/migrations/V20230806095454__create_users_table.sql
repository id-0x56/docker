-- h2, mysql
-- CREATE TABLE IF NOT EXISTS `users` (
--     `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
--     `email` VARCHAR(64) NOT NULL UNIQUE,
--     `password` VARCHAR(64) NOT NULL
-- );

-- postgres
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(64) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL
);

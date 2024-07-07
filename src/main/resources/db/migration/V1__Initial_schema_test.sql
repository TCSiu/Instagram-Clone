CREATE TABLE IF NOT EXISTS "users" (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `uuid` VARCHAR(36) NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` INT NOT NULL,
    `updated_at` TIMESTAMP NOT NULL,
    `updated_by` INT NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
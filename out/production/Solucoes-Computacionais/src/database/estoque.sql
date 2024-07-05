CREATE TABLE `estoque`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(40) NOT NULL,
    `quantidade` INT NOT NULL,
    `und_medida` VARCHAR(40) NOT NULL,
    `descricao` TEXT NOT NULL,
    `created_at` DATE NOT NULL,
    `updated_at` DATE NOT NULL,
    `valor` DOUBLE(8, 2) NOT NULL
);
DROP TABLE IF EXISTS `clientes`;
DROP TABLE IF EXISTS estoque;
DROP TABLE IF EXISTS compras;
DROP TABLE IF EXISTS ordem_servico;
DROP TABLE IF EXISTS pecas_os;
DROP TABLE IF EXISTS faturamento;


CREATE TABLE clientes (
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nome VARCHAR(40) NOT NULL,
endereco VARCHAR(255),
telefone INT,
cidade VARCHAR(255) NOT NULL,
cpf VARCHAR(255) NOT NULL,
estado VARCHAR(2) NOT NULL,
created_at DATETIME DEFAULT now(),
updated_at DATETIME);

CREATE TABLE estoque (
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nome VARCHAR(40) NOT NULL,
quantidade INT NOT NULL,
valor FLOAT NOT NULL,
und_medida VARCHAR(10),
descricao TEXT,
created_at DATETIME DEFAULT now(),
updated_at DATETIME);

CREATE TABLE compras (
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
cliente_id INT NOT NULL,
product_id INT NOT NULL,
quantity INT NOT NULL,
finalizado BOOLEAN DEFAULT false,
valor INT DEFAULT 0,
created_at DATETIME DEFAULT now(),
updated_at DATETIME);

CREATE TABLE ordem_servico (
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
cliente_id INT NOT NULL,
descricao TEXT NOT NULL,
finalizado BOOLEAN DEFAULT false,
valor FLOAT DEFAULT 0,
placa_veiculo VARCHAR(10),
created_at DATETIME DEFAULT now(),
updated_at DATETIME);

CREATE TABLE pecas_os (
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
peca_id INT NOT NULL,
quantidade INT NOT NULL DEFAULT 0,
os_id INT NOT NULL,
created_at DATETIME DEFAULT now(),
updated_at DATETIME);

CREATE TABLE faturamento (
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
os_id INT,
compra_id INT,
created_at DATETIME DEFAULT now(),
updated_at DATETIME);

ALTER TABLE compras ADD CONSTRAINT compras_cliente_id_clientes_id FOREIGN KEY (cliente_id) REFERENCES clientes(id);
ALTER TABLE compras ADD CONSTRAINT compras_product_id_estoque_id FOREIGN KEY (product_id) REFERENCES estoque(id);
ALTER TABLE ordem_servico ADD CONSTRAINT ordem_servico_cliente_id_clientes_id FOREIGN KEY (cliente_id) REFERENCES clientes(id);
ALTER TABLE pecas_os ADD CONSTRAINT pecas_os_peca_id_estoque_id FOREIGN KEY (peca_id) REFERENCES estoque(id);
ALTER TABLE pecas_os ADD CONSTRAINT pecas_os_os_id_ordem_servico_id FOREIGN KEY (os_id) REFERENCES ordem_servico(id);
ALTER TABLE faturamento ADD CONSTRAINT faturamento_os_id_ordem_servico_id FOREIGN KEY (os_id) REFERENCES ordem_servico(id);
ALTER TABLE faturamento ADD CONSTRAINT faturamento_compra_id_compras_id FOREIGN KEY (compra_id) REFERENCES compras(id);



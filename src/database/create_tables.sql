DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS purchases;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(40) NOT NULL,
    phone INT,
    document INT,
    address VARCHAR(255),
    city VARCHAR(255) NOT NULL,
    state VARCHAR(2) NOT NULL,
    created_at DATETIME DEFAULT now(),
    updated_at DATETIME
);

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(40) NOT NULL,
    quantity INT NOT NULL,
    price FLOAT DEFAULT 0,
    measurement_unit VARCHAR(10),
    description TEXT,
    created_at DATETIME DEFAULT now(),
    updated_at DATETIME
);

CREATE TABLE purchases (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    customer_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    finished BOOLEAN DEFAULT false,
    price FLOAT DEFAULT 0,
    created_at DATETIME DEFAULT now(),
    updated_at DATETIME
);

CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    customer_id INT NOT NULL,
    description TEXT NOT NULL,
    finished BOOLEAN DEFAULT false,
    price FLOAT DEFAULT 0,
    created_at DATETIME DEFAULT now(),
    updated_at DATETIME
);

ALTER TABLE purchases
    ADD CONSTRAINT purchases_customer_id_customer_id
    FOREIGN KEY (customer_id)
    REFERENCES customers(id);

ALTER TABLE purchases
    ADD CONSTRAINT purchases_product_id_product_id
    FOREIGN KEY (product_id)
    REFERENCES products(id);

ALTER TABLE orders
    ADD CONSTRAINT order_customer_id_customer_id
    FOREIGN KEY (customer_id)
    REFERENCES customers(id);
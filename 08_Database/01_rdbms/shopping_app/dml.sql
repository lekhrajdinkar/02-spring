INSERT INTO customer (name, email, address) VALUES
('John Doe', 'john.doe@example.com', '123 Elm Street'),
('Jane Smith', 'jane.smith@example.com', '456 Oak Avenue');


INSERT INTO product (product_name, price) VALUES
('Laptop', 1200.00),
('Smartphone', 800.00),
('Headphones', 150.00);

INSERT INTO "order" (customer_id) VALUES
(1),
(2);

INSERT INTO order_detail (order_id, product_name, quantity, price) VALUES
(1, 'Laptop', 1, 1200.00),
(1, 'Headphones', 2, 300.00),
(2, 'Smartphone', 1, 800.00);

INSERT INTO cart (customer_id, product_id, quantity) VALUES
(1, 1, 1), -- John adds Laptop to cart
(1, 3, 1), -- John adds Headphones to cart
(2, 2, 1); -- Jane adds Smartphone to cart

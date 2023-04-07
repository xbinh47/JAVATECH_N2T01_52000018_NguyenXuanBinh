-- Insert Users
INSERT INTO account (username,password) VALUES
('account','$2a$12$9RKOMZNemzycgtroFGy1nuztBrFVtEWYW8q52uLgiO0.m6HsuOdrK'),
('xbinh47','$2a$12$Dc6F4XxjJqpHG2L/CIi77OgWgXsqqfBi/yRcoScSJzOQi4bwU57kC');
-- Insert Categories
INSERT INTO category (name) VALUES
('Phones'),
('Laptops'),
('Tablets'),
('Watches');
-- Insert Products
INSERT INTO product (name, price, description, color, image, brand, category_id) VALUES
-- Phones
('Samsung Galaxy S21', 799, 'The latest and greatest Samsung phone', 'Phantom Black', '/img/products/samsungs21.png','Samsung', 1),
('Apple iPhone 13', 999, 'The latest and greatest iPhone', 'Space Gray', '/img/products/iphone13.png','Apple', 1),
('Xiaomi Mi 12', 899, 'The most affordable flagship phone', 'Ocean Blue', '/img/products/xiaomimi12.png','Xiaomi', 1),
-- ('Samsung Galaxy Z Flip3', 999, 'The stylish and foldable Samsung phone', 'Lavender', '/img/products/samsungzflip3.png','Samsung', 1),
-- ('Apple iPhone SE', 399, 'The most affordable iPhone', 'Product Red', '/img/products/iphonese.png','Apple', 1),

-- Laptops
('Samsung Galaxy Book Pro', 1299, 'The thinnest and lightest Samsung laptop', 'Mystic Navy', '/img/products/samsungbookpro360.png','Samsung', 2),
('Apple MacBook Pro 16-inch', 2399, 'The most powerful MacBook ever', 'Space Gray', '/img/products/macbookpro16.png','Apple', 2),
('Xiaomi Mi Notebook Pro', 1299, 'The best value premium laptop', 'Deep Space Gray', '/img/products/xiaomiminotebookpro.png','Xiaomi', 2),
-- ('Samsung Galaxy Chromebook', 999, 'The premium Chromebook with 4K display', 'Fiesta Red', '/img/products/samsungchromebook.png','Samsung', 2),
-- ('Apple MacBook Air', 999, 'The most affordable MacBook', 'Gold', '/img/products/macbookair.png','Apple', 2),

-- Tablets
('Samsung Galaxy Tab S8', 649, 'The latest and greatest Samsung tablet', 'Mystic Black', '/img/products/samsungtabs8.png','Samsung', 3),
('Apple iPad Pro', 799, 'The most advanced iPad yet', 'Silver', '/img/products/ipadpro.png','Apple', 3),
('Xiaomi Mi Pad 5', 499, 'The most affordable high-end tablet', 'Cosmic Gray', '/img/products/xiaomimipad5.png','Xiaomi', 3),
-- ('Samsung Galaxy Tab S7 FE', 529, 'The affordable and feature-packed Samsung tablet', 'Mystic Silver', '/img/products/samsungtabs7fe.png','Samsung', 3),
-- ('Apple iPad Mini', 499, 'The most portable iPad', 'Sky Blue', '/img/products/ipadmini.png','Apple', 3),

-- Watches
('Samsung Galaxy Watch 4', 249, 'The ultimate fitness and wellness companion', 'Phantom Black', '/img/products/samsungwatch4.png','Samsung', 4),
('Apple Watch Series 7', 399, 'The ultimate fitness and wellness companion', 'Midnight', '/img/products/applewatch7.png','Apple', 4),
('Xiaomi Mi Watch Revolve', 129, 'The most affordable smartwatch', 'Midnight Black', '/img/products/xiaomiwatch.png','Xiaomi', 4);
-- ('Samsung Gear S3 Frontier', 299, 'The rugged Galaxy smartwatch', 'Dark Gray', '/img/products/samsunggears3frontier.png','Samsung', 4),
-- ('Apple Watch SE', 279, 'The most affordable Apple Watch', 'Gold Aluminum Case with Pink Sand Sport Band', '/img/products/applewatchse.png','Apple', 4);

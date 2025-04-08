-- Delete any existing tables (to reset the database from scratch)
DROP TABLE IF EXISTS OrderItem;
DROP TABLE IF EXISTS `Order`;
DROP TABLE IF EXISTS ShoppingCartItems;
DROP TABLE IF EXISTS ShoppingCart;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS User;

-- Create User table
CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_no VARCHAR(20),
    address VARCHAR(255),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50),
    cart_id INT UNIQUE -- Foreign key to ShoppingCart
);

-- Create Product table
CREATE TABLE Product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(100),
    stock_int INT NOT NULL,
    search_product TEXT
);

-- Create Order table
CREATE TABLE `Order` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    fulfillment_date DATETIME,
    status VARCHAR(50),
    payment_method VARCHAR(50),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES User(id)
);

-- Create OrderItem table (junction table for Order and Product)
CREATE TABLE OrderItem (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    product_id INT,
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL, -- Store price at the time of order
    FOREIGN KEY (order_id) REFERENCES `Order`(id),
    FOREIGN KEY (product_id) REFERENCES Product(id)
);

-- Create ShoppingCart table
CREATE TABLE ShoppingCart (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT UNIQUE, -- One user has one shopping cart
    session_id VARCHAR(255),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES User(id)
);

-- Create ShoppingCartItems table (junction table for ShoppingCart and Product)
CREATE TABLE ShoppingCartItems (
    shopping_cart_id INT,
    product_id INT,
    quantity INT NOT NULL,
    PRIMARY KEY (shopping_cart_id, product_id),
    FOREIGN KEY (shopping_cart_id) REFERENCES ShoppingCart(id),
    FOREIGN KEY (product_id) REFERENCES Product(id)
);

-- Add foreign key constraint for cart_id in User table after ShoppingCart is created
ALTER TABLE User
ADD CONSTRAINT FK_User_ShoppingCart
FOREIGN KEY (cart_id) REFERENCES ShoppingCart(id);

-- Sample Data (Optional)
INSERT INTO User (email, phone_no, address, first_name, last_name, password, role) VALUES
('john.doe@example.com', '123-456-7890', '123 Main St', 'John', 'Doe', 'password123', 'customer'),
('jane.smith@example.com', '987-654-3210', '456 Oak Ave', 'Jane', 'Smith', 'securepass', 'customer');

INSERT INTO Product (name, unit_price, category, stock_int, search_product) VALUES
('Laptop', 1200.00, 'Electronics', 50, 'powerful laptop for work and gaming'),
('Mouse', 25.00, 'Electronics', 100, 'ergonomic wireless mouse'),
('T-Shirt', 20.00, 'Apparel', 200, 'cotton casual t-shirt'),
('Organic HK Choy Sum', 3.30, 'Fresh Vegetables', 100, 'organic leafy green vegetable choy sum'),
('Organic Japanese Cucumber Size M Thailand', 3.50, 'Fresh Vegetables', 80, 'organic japanese cucumber medium size thailand'),
('Organic Australia Carrot', 5.80, 'Fresh Vegetables', 60, 'organic australia carrot'),
('Organic Garlic Thailand', 5.30, 'Fresh Vegetables', 120, 'organic garlic thailand'),
('Organic Baby Peeled Carrot USA', 7.80, 'Fresh Vegetables', 70, 'organic baby peeled carrot usa'),
('Zenxin Organic Baby Spinach', 6.95, 'Fresh Vegetables', 90, 'zenxin organic baby spinach'),
('Organic Celery', 10.50, 'Fresh Vegetables', 50, 'organic celery'),
('Organic Sweet Corn Thailand', 5.80, 'Fresh Vegetables', 85, 'organic sweet corn thailand'),
('Organic Blueberries', 6.90, 'Fruits', 75, 'organic blueberries'),
('Organic Long Purple Brinjal Thailand', 2.20, 'Fresh Vegetables', 110, 'organic long purple brinjal eggplant thailand'),
('Organic Ginger Thailand', 3.75, 'Fresh Vegetables', 105, 'organic ginger thailand'),
('Organic French Bean', 4.10, 'Fresh Vegetables', 80, 'organic french bean'),
('Organic Lemon', 6.30, 'Fruits', 65, 'organic lemon'),
('Organic Japanese Cucumber Malaysia Pack', 4.50, 'Fresh Vegetables', 70, 'organic japanese cucumber malaysia pack'),
('Organic Cherry Tomato Thailand', 4.40, 'Fruits', 90, 'organic cherry tomato thailand'),
('Organic Banana', 7.90, 'Fruits', 150, 'organic banana'),
('Organic Baby Corn Thanland', 3.00, 'Fresh Vegetables', 115, 'organic baby corn thailand'),
('Organic Cherry Tomato Malaysia Pack', 4.10, 'Fruits', 85, 'organic cherry tomato malaysia pack'),
('Spure Skinless Chicken Breast', 8.90, 'Chicken & Duck', 70, 'spure skinless chicken breast 400g'),
('Spure Chicken Fillet', 8.90, 'Chicken & Duck', 65, 'spure chicken fillet 400g'),
('Diamantina Wagyu Beef Burger Patties', 5.00, 'Beef & Lamb', 50, 'diamantina wagyu beef burger patties 150g'),
('Pork Lean Mince', 6.24, 'Pork', 80, 'pork lean mince 400g'),
('Weight Watcher Angus Grain Minced Beef', 18.50, 'Beef & Lamb', 40, 'weight watcher angus grain minced beef 500g'),
('Spure Chicken Mid Joint Wing', 7.65, 'Chicken & Duck', 75, 'spure chicken mid joint wing 280g'),
('Australia Pork Collar', 9.68, 'Pork', 60, 'australia pork collar 400g'),
('Diamantina Wagyu Chuck Steak Australia', 20.50, 'Beef & Lamb', 30, 'diamantina wagyu chuck steak australia 265g'),
('Savour. American Pork Sausages Singapore Tray, 10s', 10.10, 'Pork', 55, 'savour american pork sausages singapore tray 10 sausages'),
('Spure Bone-In Chicken Thigh', 10.00, 'Chicken & Duck', 60, 'spure bone-in chicken thigh 450g'),
('Halal Dia Wagyu Rib-Eye Steak Australia', 34.90, 'Beef & Lamb', 25, 'halal dia wagyu rib-eye steak australia 200g'),
('Diamantina Wagyu Beef Stir Fry Australia', 15.90, 'Beef & Lamb', 45, 'diamantina wagyu beef stir fry australia 250g'),
('Australia Pork Mini Belly', 8.60, 'Pork', 70, 'australia pork mini belly 400g'),
('Diamantina Wagyu Diced Beef Australia', 15.90, 'Beef & Lamb', 35, 'diamantina wagyu diced beef australia 250g'),
('Savour. Chipolata Sausages Singapore Tray, 10s', 10.10, 'Pork', 65, 'savour chipolata sausages singapore tray 10 sausages'),
('Savour. Marinated Sweet BBQ Chicken Breast Singapore Pack', 9.50, 'Marinated Meat', 50, 'savour marinated sweet bbq chicken breast singapore pack 400g'),
('Savour. Italian Spicy Sausages Singapore Tray, 6s', 10.10, 'Pork', 55, 'savour italian spicy sausages singapore tray 6 sausages'),
('Savour Marinated Sweet BBQ Chicken Steak', 8.50, 'Marinated Meat', 60, 'savour marinated sweet bbq chicken steak 400g'),
('Savour. Garlic & Herb Sausages Singapore Tray, 18s', 10.10, 'Pork', 45, 'savour garlic & herb sausages singapore tray 18 sausages'),
('Savour. Crushed Pepper Chicken Breast', 9.50, 'Marinated Meat', 55, 'savour crushed pepper chicken breast');

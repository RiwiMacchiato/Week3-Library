-- USER TABLE
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

-- ITEM TABLE (superclass)
CREATE TABLE items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    published_year INT,
    -- who owns or borrowed it (optional relation)
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- BOOK TABLE (subclass of item)
CREATE TABLE books (
    book_id INT PRIMARY KEY,
    author VARCHAR(150) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    pages INT,
    FOREIGN KEY (book_id) REFERENCES items(item_id)
);

-- MAGAZINE TABLE (subclass of item)
CREATE TABLE magazines (
    magazine_id INT PRIMARY KEY,
    issue_number INT NOT NULL,
    publisher VARCHAR(150),
    FOREIGN KEY (magazine_id) REFERENCES items(item_id)
);

DROP DATABASE IF EXISTS WishList;
CREATE DATABASE WishList
DEFAULT CHARACTER SET utf8mb4;
USE Wishlist;

CREATE TABLE User (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      password VARCHAR(50) NOT NULL,
                      email VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Wish (
                      wish_id INT AUTO_INCREMENT PRIMARY KEY,
                      user_id int not null,
                      name VARCHAR(150) NOT NULL,
                      description VARCHAR(150),
                      link VARCHAR(150),
                      price INTEGER,
                      isReserved BOOLEAN,
                      FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE
);

Create TABLE Wishlists
(
                      wishlist_id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(150) NOT NULL,
                      user_id int not null,
                      FOREIGN KEY (user_id) REFERENCES User (user_id) ON DELETE CASCADE
);
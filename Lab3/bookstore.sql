-- DROP DATABASE IF EXISTS `BookStore`;
-- CREATE DATABASE `BookStore`;

-- CREATE TABLE `BookStore`.`Customer` (
--   `Name` varchar(50) NOT NULL,
--   `Mobile` varchar(50) DEFAULT NULL, 
--   PRIMARY KEY (`Name`));
--   
-- CREATE TABLE `BookStore`.`Book` (
--   `ISBN` varchar(10) NOT NULL,
--   `title` varchar(50) NOT NULL,
--   PRIMARY KEY (`ISBN`));
--   
-- CREATE TABLE `BookStore`.`Feature` (
--   `name` varchar(50) NOT NULL,
--   `description` varchar(255) NOT NULL,
--   PRIMARY KEY (`name`));


-- CREATE TABLE `BookStore`.`Book_Has_Features`;
--    
-- CREATE TABLE `BookStore`.`Customer_Interests`;

-- CREATE TABLE `BookStore`.`Customer_Purchase`;

-- -- Database -> Reverse Engineer to render Entity Relation Model and create necessary relations

-- INSERT INTO e_Book (ISBN, title)
-- VALUES ('isbn1','title1'), ('isbn2','title2');

-- INSERT INTO Customer (Name, Mobile)
-- VALUES ('custA','mobA'), ('custB','mobB');

-- INSERT INTO Feature (name, description)
-- VALUES ('scifi',''), ('wifi','');

-- INSERT INTO Book_Has_Features (Book_ISBN, Feature_name)
-- VALUES ('isbn1','wifi'), ('isbn2','scifi');

-- INSERT INTO Customer_Interests (Customer_Name, Feature_name)
-- VALUES ('custA','wifi'), ('custB', 'scifi'), ('custA', 'scifi');

-- SELECT ISBN,title,Book_Has_Features.Feature_name FROM Book LEFT JOIN Book_Has_Features ON Book.ISBN = Book_Has_Features.Book_ISBN;
-- SELECT name,Customer_Interests.Feature_name FROM Customer LEFT JOIN Customer_Interests ON Customer.name = Customer_Interests.Customer_Name; 

-- SELECT Book_ISBN, Feature_name, Customer_Interests.Customer_Name FROM Book_Has_Features LEFT JOIN Customer_Interests ON Book_Has_Features.Book_ISBN = Customer_Interests.Customer_Name; 

-- INSERT INTO Feature (name, description)
-- VALUES ('biography','details and events of a person’s life span');


-- UPDATE Feature
-- SET description = 'space, time travel, aliens, or time-traveling aliens in space '
-- WHERE name = 'scifi';

-- UPDATE Feature
-- SET description = 'through engaging, sequential narrative art'
-- WHERE name = 'comic'

SELECT * FROM Feature
ORDER BY name DESC





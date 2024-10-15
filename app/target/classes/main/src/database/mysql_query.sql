CREATE TABLE IF NOT EXISTS users (
    userID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL
) AUTO_INCREMENT = 2001;

CREATE TABLE IF NOT EXISTS library (
    bookID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(50) NOT NULL,
    published YEAR NOT NULL,
    price DECIMAL (4,2) NOT NULL,
    link VARCHAR(255) NOT NULL
) AUTO_INCREMENT = 1001;

CREATE TABLE IF NOT EXISTS orders (
    orderID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userID INT NOT NULL,
    bookID INT NOT NULL,
    price DECIMAL (4,2) NOT NULL,
    purchaseDate DATE NOT NULL,
    FOREIGN KEY (userID) REFERENCES users(userID),
    FOREIGN KEY (bookID) REFERENCES library(bookID)
) AUTO_INCREMENT = 3001;

INSERT INTO users (username, password) VALUES
('alice1', SHA2('blueSky42!', 256)),
('bob2', SHA2('greenApple77@', 256)),
('charlie3', SHA2('redDragon13#', 256)),
('dave4', SHA2('purpleRain88$', 256)),
('eve5', SHA2('yellowStone21%', 256)),
('frank6', SHA2('orangeTree99^', 256)),
('grace7', SHA2('blackCoffee45&', 256)),
('heidi8', SHA2('whiteHorse88*', 256)),
('ivan9', SHA2('pinkFlower33!', 256)),
('judy10', SHA2('grayWolf76$', 256));

INSERT INTO library (title, author, published, price, link) VALUES
('Northanger Abbey', 'Jane Austen', '1817', '12.99', 'https://www.gutenberg.org/cache/epub/121/pg121-images.html'),
('War and Peace', 'graf Leo Tolstoy', '1867', '6.99', 'https://www.gutenberg.org/cache/epub/2600/pg2600-images.html'),
('Moby Dick', 'Herman Melville', '1851', '29.99', 'https://www.gutenberg.org/cache/epub/2701/pg2701-images.html'),
('Romeo and Juliet', 'William Shakespeare', '1597', '15.97', 'https://www.gutenberg.org/cache/epub/1513/pg1513-images.html'),
('A Room with a View', 'E. M. Forster', '1908', '10.99', 'https://www.gutenberg.org/cache/epub/2641/pg2641-images.html'),
('Adventures of Huckleberry Finn', 'Mark Twain', '1884', '14.99', 'https://www.gutenberg.org/cache/epub/76/pg76-images.html'),
('Bleak House', 'Charles Dickens', '1853', '13.99', 'https://www.gutenberg.org/cache/epub/1023/pg1023-images.html'),
('Adventures of Tom Sawyer', 'Mark Twain', '1876', '11.99', 'https://www.gutenberg.org/cache/epub/74/pg74-images.html'),
('Middlemarch', 'George Eliot', '1871', '18.99', 'https://www.gutenberg.org/cache/epub/145/pg145-images.html'),
('The Enchanted April', 'Elizabeth von Arnim', '1922', '9.99', 'https://www.gutenberg.org/cache/epub/16389/pg16389-images.html'),
('Pride and Prejudice', 'Jane Austen', '1813', '12.99', 'https://www.gutenberg.org/cache/epub/1342/pg1342-images.html'),
('Cranford', 'Elizabeth Gaskell', '1851', '8.99', 'https://www.gutenberg.org/cache/epub/394/pg394-images.html'),
('Dracula', 'Bram Stoker', '1897', '16.99', 'https://www.gutenberg.org/cache/epub/345/pg345-images.html'),
('The Great Gatsby', 'F. Scott Fitzgerald', '1925', '10.99', 'https://www.gutenberg.org/cache/epub/64317/pg64317-images.html'),
('The Yellow Wallpaper', 'Charlotte Perkins Gilman', '1892', '7.99', 'https://www.gutenberg.org/cache/epub/1952/pg1952-images.html'),
('Great Expectations', 'Charles Dickens', '1861', '13.99', 'https://www.gutenberg.org/cache/epub/1400/pg1400-images.html'),
('Ulysses', 'James Joyce', '1922', '15.99', 'https://www.gutenberg.org/cache/epub/4300/pg4300-images.html'),
('The Prophet', 'Kahlil Gibran', '1923', '10.99', 'https://www.gutenberg.org/cache/epub/58585/pg58585-images.html'),
('The Odyssey', 'Homer', '1488', '11.99', 'https://www.gutenberg.org/cache/epub/58585/pg58585-images.html'),
('Anne of Green Gables', 'Lucy Maud Montgomery', '1908', '9.99', 'https://www.gutenberg.org/cache/epub/45/pg45-images.html'),
('Don Quixote', 'Miguel de Cervantes', '1605', '17.99', 'https://www.gutenberg.org/cache/epub/996/pg996-images.html'),
('Dantes Inferno', 'Dante Alighieri', '1320', '14.99', 'https://www.gutenberg.org/cache/epub/41537/pg41537-images.html'),
('A Womans Soul', 'Willa Cather', '1900', '10.99', 'https://www.gutenberg.org/cache/epub/74103/pg74103-images.html'),
('Winnie the Pooh', 'A. A. Milne', '1926', '8.99', 'https://www.gutenberg.org/cache/epub/67098/pg67098-images.html'),
('Dubliners', 'James Joyce', '1914', '12.99', 'https://www.gutenberg.org/cache/epub/2814/pg2814-images.html'),
('The Time Machine', 'H. G. Wells', '1895', '11.99', 'https://www.gutenberg.org/cache/epub/35/pg35-images.html'),
('Sense and Sensibility', 'Jane Austen', '1811', '13.99', 'https://www.gutenberg.org/cache/epub/161/pg161-images.html'),
('South Sea Yarns', 'Basil Thomson', '1889', '12.99', 'https://www.gutenberg.org/cache/epub/66195/pg66195-images.html'),
('The War of the Worlds', 'H. G. Wells', '1898', '13.99', 'https://www.gutenberg.org/cache/epub/36/pg36-images.html'),
('The Elements of Style', 'William Strunk Jr.', '1918', '7.99', 'https://www.gutenberg.org/cache/epub/37134/pg37134-images.html'),
('Siddhartha: An Indian Tale', 'Hermann Hesse', '1922', '11.99', 'https://www.gutenberg.org/cache/epub/2500/pg2500-images.html'),
('Persuasion', 'Jane Austen', '1817', '10.99', 'https://www.gutenberg.org/cache/epub/105/pg105-images.html'),
('Pickwick Papers', 'Charles Dickens', '1837', '15.99', 'https://www.gutenberg.org/cache/epub/580/pg580-images.html'),
('The Murder of Roger Ackroyd', 'Agatha Christie', '1926', '9.99', 'https://www.gutenberg.org/cache/epub/69087/pg69087-images.html'),
('The Jungle Book', 'Rudyard Kipling', '1894', '10.99', 'https://www.gutenberg.org/cache/epub/236/pg236-images.html'),
('The Call of the Wild', 'Jack London', '1903', '11.99', 'https://www.gutenberg.org/cache/epub/215/pg215-images.html');


INSERT INTO orders (userID, bookID, price, purchaseDate) VALUES
(2001, 1001, 12.99, '2022-09-15'),
(2001, 1003, 29.99, '2023-05-05'),
(2002, 1005, 13.99, '2023-06-22'),
(2002, 1007, 10.99, '2023-03-30'),
(2003, 1009, 14.99, '2022-12-12'),
(2003, 1011, 8.99, '2023-07-15'),
(2004, 1013, 16.99, '2023-08-02'),
(2004, 1015, 11.99, '2023-01-10'),
(2005, 1017, 9.99, '2023-09-14'),
(2005, 1019, 18.99, '2023-02-28'),
(2006, 1020, 15.99, '2022-11-21'),
(2006, 1021, 7.99, '2023-08-30'),
(2007, 1022, 10.99, '2023-03-05'),
(2007, 1023, 14.99, '2023-06-10'),
(2008, 1024, 13.99, '2023-04-18'),
(2008, 1002, 12.99, '2022-05-10'),
(2009, 1004, 19.99, '2023-01-20'),
(2009, 1006, 22.99, '2023-05-15'),
(2010, 1008, 17.99, '2022-10-11'),
(2010, 1010, 11.99, '2023-07-25'),
(2001, 1025, 15.99, '2023-06-05'),
(2002, 1026, 20.99, '2022-08-20'),
(2003, 1027, 13.99, '2023-04-15'),
(2004, 1028, 9.99, '2023-05-30'),
(2005, 1029, 10.99, '2022-12-30'),
(2006, 1030, 16.99, '2023-03-25'),
(2007, 1031, 12.99, '2023-02-05'),
(2008, 1032, 14.99, '2022-11-15'),
(2009, 1033, 18.99, '2023-04-22'),
(2010, 1034, 8.99, '2023-07-10'),
(2001, 1035, 21.99, '2023-09-01'),
(2002, 1036, 15.99, '2023-03-01');





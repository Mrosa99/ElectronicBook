DROP TABLE library; 

CREATE TABLE IF NOT EXISTS users (
    userID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(64) NOT NULL
) AUTO_INCREMENT = 2001;

CREATE TABLE IF NOT EXISTS library (
    bookID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    published INT(4) NOT NULL,
    price INT(4) NOT NULL,
    link VARCHAR(100) NOT NULL
) AUTO_INCREMENT = 1001;

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
('The Enchanted April', 'Elizabeth von Arnim', '1922', '9.99', 'INSERT'),
('Pride and Prejudice', 'Jane Austen', '1813', '12.99', 'INSERT'),
('Cranford', 'Elizabeth Gaskell', '1851', '8.99', 'INSERT'),
('Dracula', 'Bram Stoker', '1897', '16.99', 'INSERT'),
('The Great Gatsby', 'F. Scott Fitzgerald', '1925', '10.99', 'INSERT'),
('The Yellow Wallpaper', 'Charlotte Perkins Gilman', '1892', '7.99', 'INSERT'),
('Great Expectations', 'Charles Dickens', '1861', '13.99', 'INSERT'),
('Ulysses', 'James Joyce', '1922', '15.99', 'INSERT'),
('The Prophet', 'Kahlil Gibran', '1923', '10.99', 'INSERT'),
('The Odyssey', 'Homer', '1488', '11.99', 'INSERT'),
('Anne of Green Gables', 'Lucy Maud Montgomery', '1908', '9.99', 'INSERT'),
('Don Quixote', 'Miguel de Cervantes', '1605', '17.99', 'INSERT'),
('Dantes Inferno', 'Dante Alighieri', '1320', '14.99', 'INSERT'),
('A Womans Soul', 'Willa Cather', '1900', '10.99', 'INSERT'),
('Winnie the Pooh', 'A. A. Milne', '1926', '8.99', 'INSERT'),
('Dubliners', 'James Joyce', '1914', '12.99', 'INSERT'),
('The Time Machine', 'H. G. Wells', '1895', '11.99', 'INSERT'),
('Sense and Sensibility', 'Jane Austen', '1811', '13.99', 'INSERT'),
('South Sea Yarns', 'Robert Louis Stevenson', '1889', '12.99', 'INSERT'),
('The War of the Worlds', 'H. G. Wells', '1898', '13.99', 'INSERT'),
('The Elements of Style', 'William Strunk Jr.', '1918', '7.99', 'INSERT'),
('Siddhartha: An Indian Tale', 'Hermann Hesse', '1922', '11.99', 'INSERT'),
('Persuasion', 'Jane Austen', '1817', '10.99', 'INSERT'),
('Pickwick Papers', 'Charles Dickens', '1837', '15.99', 'INSERT'),
('The Murder of Roger Ackroyd', 'Agatha Christie', '1926', '9.99', 'INSERT'),
('The Jungle Book', 'Rudyard Kipling', '1894', '10.99', 'INSERT'),
('The Call of the Wild', 'Jack London', '1903', '11.99', 'INSERT');





SHOW TABLES;




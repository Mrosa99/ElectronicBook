CREATE TABLE IF NOT EXISTS users (
    userID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(64) NOT NULL
) AUTO_INCREMENT = 1001;

CREATE TABLE IF NOT EXISTS books (
    bookID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    Author VARCHAR(50) NOT NULL,
    Published VARCHAR(50) NOT NULL,
    Price VARCHAR(50) NOT NULL
) AUTO_INCREMENT = 2001;

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




SHOW TABLES;




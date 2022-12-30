CREATE TABLE players(
player_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
first_name VARCHAR(20),
last_name VARCHAR(20),
nickname VARCHAR(20),
street_address VARCHAR(100),
postal_code VARCHAR(10),
country VARCHAR(20),
email VARCHAR(45),
team_id INT
);


DROP TABLE players;
TRUNCATE table players;
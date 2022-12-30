DROP TABLE teams;
DESCRIBE teams;
SELECT * FROM teams;

USE tournament;

CREATE TABLE teams(
                      team_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                      team_name VARCHAR(20),
                      game_id INT
);



INSERT INTO teams (team_name, game_id) VALUES
    ('Teams',2);
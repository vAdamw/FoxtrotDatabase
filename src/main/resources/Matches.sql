--Team matches

USE tournament;
-- SET SQL_SAFE_UPDATES = 0;
DROP TABLE team_matches;

--Creating a table with match_id as primary key. Creating connections to other tables through team1_id, team2_id and game_id
CREATE TABLE team_matches(
                             match_id INT AUTO_INCREMENT,
                             team1_id INT,
                             team2_id INT,
                             match_date VARCHAR(45) NOT NULL,
                             score_team1 INT,
                             score_team2 INT,
                             game_id INT,
                             finished BOOLEAN,
                             PRIMARY KEY(match_id),
                             FOREIGN KEY (team1_id) REFERENCES teams(team_id),
                             FOREIGN KEY (team2_id) REFERENCES teams(team_id),
                             FOREIGN KEY (game_id) REFERENCES games(game_id)
);

--Adding values to the table
INSERT INTO team_matches (match_id, team1_id, team2_id, match_date, score_team1, score_team2, game_id, finished)VALUES
(3,1,2,'01.01.01',100,200,1, true);

--Select everyting
SELECT * FROM team_matches;


-- Player matches

USE tournament;
-- SET SQL_SAFE_UPDATES = 0;
DROP TABLE player_matches;

--Creating a table with match_id as primary key. Creating connections to other tables through team1_id, team2_id and game_id*/
CREATE TABLE player_matches(
                               match_id INT AUTO_INCREMENT,
                               player1_id INT,
                               player2_id INT,
                               match_date VARCHAR(45) NOT NULL,
                               score_player1 INT,
                               score_player2 INT,
                               game_id INT,
                               finished BOOLEAN,
                               PRIMARY KEY(match_id),
                               FOREIGN KEY (player1_id) REFERENCES players(player_id),
                               FOREIGN KEY (player2_id) REFERENCES players(player_id),
                               FOREIGN KEY (game_id) REFERENCES games(game_id)
);

--Adding values to the table
INSERT INTO player_matches (match_id, player1_id, player2_id, match_date, score_player1, score_player2, game_id)VALUES
(1,1,2,'01.01.01',100,200,1);

--Select everyting
SELECT * FROM player_matches;


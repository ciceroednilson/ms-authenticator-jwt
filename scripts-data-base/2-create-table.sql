
/*TABLE*/
CREATE TABLE IF NOT EXISTS tb_user(
   `id` 	   INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   `name`      VARCHAR(250) NOT NULL,
   `email`     VARCHAR(250) NOT NULL,
   `password`  VARCHAR(250) NOT NULL,
   `enable`    CHAR(1)      NOT NULL,
   `register_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


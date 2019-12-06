CREATE DATABASE `hitcher_db` DEFAULT CHARACTER SET utf8;
SET global time_zone = '+3:00';

CREATE USER hitcher_user1 IDENTIFIED BY 'password';

GRANT INSERT, SELECT, DELETE, UPDATE ON *.* TO 'hitcher_user1'@'%';

CREATE DATABASE `hitcher_db_test` DEFAULT CHARACTER SET utf8;
SET global time_zone = '+3:00';

CREATE USER hitcher_user_test IDENTIFIED BY 'password';

GRANT INSERT, SELECT, DELETE, UPDATE ON *.* TO 'hitcher_user_test'@'%';

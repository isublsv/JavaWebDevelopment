USE `hitcher_db_test`;

/*password smith*/
INSERT INTO users (login, email, password, salt, role, status, registration_date)
VALUES ('smith', 'smith@gmail.com', 'AyAurJOVwkp75h4/vlw1Rni/jI+km5uw+c9b9rHi1Ws=', '3Hfzs5SzuDLg3uD12X9CnyFD4rb5jB', 1, 1, '2018-07-08 21:34:13');
/*password anna*/
INSERT INTO users (login, email, password, salt, role, status, registration_date)
VALUES ('anna', 'solovei@gmail.com', '4BL8sAw1ZR81rkQihuIex6QDA5RD8eyz+Aa46q7MD4Y=', 'vggBdm1I60lE7YpK88Ywf5X0EsAGrR', 1, 1, '2018-07-15 21:35:54');
/*password petrov*/
INSERT INTO users (login, email, password, salt, role, status, registration_date)
VALUES ('petrov', 'petrov@gmail.com', 'cYkA4YVx2kMbcfEr+kcc+GpBKyBvK0nwzTU/jZi6Vb4=', '01z8BuDKet8dpW7HTCB34FgEUWzlQH', 1, 1, '2017-08-22 17:31:58');

INSERT INTO music (description) VALUE ('music.negative');
INSERT INTO music (description) VALUE ('music.neutral');
INSERT INTO music (description) VALUE ('music.positive');

INSERT INTO communication (description) VALUES ('com.negative');
INSERT INTO communication (description) VALUES ('com.neutral');
INSERT INTO communication (description) VALUES ('com.positive');

INSERT INTO hitchers (user_id, surname, name, patronymic, phone, address, music_id, communication_id)
VALUES (2, 'Smith', 'John', null,  '+375 (29) 123-45-67', 'Sovetskaya st., 13, 1', 1, 3);
INSERT INTO hitchers (user_id, surname, name, patronymic, phone, address, music_id, communication_id)
VALUES (3, 'Solovei', 'Anna', 'Alexeevna', '+375 (29) 222-55-55', 'Slobodskoi pr., 122', 3, 2);
INSERT INTO hitchers (user_id, surname, name, patronymic, phone, address, music_id, communication_id)
VALUES (4, 'Petrov', 'Vasya', 'Vasilievich', '+375 (25) 710-21-99', 'Pushkina pr., 14', 2, 3);

INSERT INTO driver_info (user_id, driving_licence_number, car_model, car_color)
VALUES (3, '1AA 123456', 'LADA Calina', 'BLUE');

INSERT INTO reviews (about_id, who_id, text, rating) VALUES (2, 3, 'Приятный человек!', 5);
INSERT INTO reviews (about_id, who_id, text, rating) VALUES (3, 2, 'Все тип-топ, но есть ньюансы!', 4);

INSERT INTO country (id, name) VALUES (1, 'Russia');

INSERT INTO city (id, name, country_id) VALUES (1, 'Moscow', 1);
INSERT INTO city (id, name, country_id) VALUES (2, 'Saint Petersburg', 1);

INSERT INTO trips (driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) 
VALUES (3, 1, 2, '2019-12-23 17:00:00', '2019-12-24 05:00:00');

INSERT INTO trip_users (trip_id, passenger_id) VALUES (1, 2);

INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (1, 3, 50.00, 1, 1);



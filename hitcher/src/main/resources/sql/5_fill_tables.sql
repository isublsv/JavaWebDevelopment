USE `hitcher_db`;

INSERT INTO hitcher_db.users (id, login, password, salt, role)
VALUES (2, 'smith', '6KZMQEXNWK2V3R499BT3OVA5JRT3HE7O4Y3B8Y6AJZG=', 'AHQ04VGUJF7G8XKE2L926GFTFQ2MYQ', 1);
INSERT INTO hitcher_db.users (id, login, password, salt, role)
VALUES (3, 'anna', 'YY9NSZLEVNTHAVEV8V8EYIFCKQ9XUNXF/LMXTDCQJBM=', 'OIK38RS5GHMPOR41DK4OO4QNU6IKFC', 1);
INSERT INTO hitcher_db.users (id, login, password, salt, role)
VALUES (4, 'petrov', 'IIWTCDUFK+Q9NWOOYXXK8IKXMTFNH1FMARBMOF5NNNQ=', 'K1BP6CTVQOTHYJI7FZKAMCKLGQNGVV', 1);
INSERT INTO hitcher_db.users (id, login, password, salt, role)
VALUES (5, 'kamilla', 'MY0/1EGUWNIPRA591JTQV/73GKMQVDZTR4QX8MQ71S0=', '372ERBB7L49G6BONNDQAXLECYIEYMA', 1);
INSERT INTO hitcher_db.users (id, login, password, salt, role)
VALUES (6, 'tolik', 'TYCLTFBMWDOZSRMOE12XZ9N5MRIUEQFGSMHZHUSQLQK=', 'LWEFQYQADJVTSJR0DLYA0YZOSXNSMP', 1);
INSERT INTO hitcher_db.users (id, login, password, salt, role)
VALUES (7, 'boi', 'EQIQQLWO1SGMWJU4CX1JPAQZTISQBYAM4PKXIP4JGMY=', 'TF99LUSNGFJKUB9MAPCW5MOV2HAW0X', 1);
INSERT INTO hitcher_db.users (id, login, password, salt, role)
VALUES (8, 'bird', 'LYX7XR3QADBZF69PBDZ+Y1ZKXHI69BLWDGMRZPIQ66W=', 'TDB75PU7BLOEKAIPYF2J6QCWU365FT', 1);
INSERT INTO hitcher_db.users (id, login, password, salt, role)
VALUES (9, 'xtasy', '2ZP9O1L7/VLD5CTVDLVZGGUKLOVUYYO/FR4GDCBFXG4=', 'CNKV9T06ZKIEWP8NRCJVQYBFPIRJ00', 1);
INSERT INTO hitcher_db.users (id, login, password, salt, role)
VALUES (10, 'carl', 'OOGCRVRQQU+HOJJO/UHVCKHJBLN5SBZR2XUCUIZ84AE=', 'CIERROJQU1GFKPLWHIB8NVYWOAPRPQ', 1);

INSERT INTO hitcher_db.hitchers (user_id, surname, name, patronymic, email, phone, registration_date)
VALUES (2, 'Smith', 'John', null, 'smith@gmail.com', '+375 (29) 123-45-67', '2018-07-08 21:34:13');
INSERT INTO hitcher_db.hitchers (user_id, surname, name, patronymic, email, phone, registration_date)
VALUES (3, 'Solovei', 'Anna', 'Alexeevna', 'solovei@gmail.com', '+375 (29) 222-55-55', '2018-07-15 21:35:54');
INSERT INTO hitcher_db.hitchers (user_id, surname, name, patronymic, email, phone, registration_date)
VALUES (4, 'Petrov', 'Vasya', 'Vasilievich', 'petrov@gmail.com', '+375 (25) 710-21-99', '2017-08-22 17:31:58');
INSERT INTO hitcher_db.hitchers (user_id, surname, name, patronymic, email, phone, registration_date)
VALUES (5, 'Познева', 'Наталья', 'Петровна', 'pozn_@gmail.com', '+375 (25) 510-28-44', '2016-01-20 23:50:28');
INSERT INTO hitcher_db.hitchers (user_id, surname, name, patronymic, email, phone, registration_date)
VALUES (6, 'Осипов', 'Олег', null, 'osipov@mail.by', '+375 (25) 192-21-39', '2017-09-13 23:51:50');
INSERT INTO hitcher_db.hitchers (user_id, surname, name, patronymic, email, phone, registration_date)
VALUES (7, 'Ivanov', 'Ivan', 'Ivanovich', 'ivan@tut.by', '+375 (27) 232-33-33', '2018-12-02 23:52:52');
INSERT INTO hitcher_db.hitchers (user_id, surname, name, patronymic, email, phone, registration_date)
VALUES (8, 'Сидоров', 'Сидор', 'Сидорович', 'sidor@tut.by', '+375 (25) 788-28-94', '2016-10-11 23:53:57');
INSERT INTO hitcher_db.hitchers (user_id, surname, name, patronymic, email, phone, registration_date)
VALUES (9, 'Selezneva', 'Olga', null, 'sell@gmail.com', '+375 (25) 577-28-74', '2017-03-14 23:54:59');
INSERT INTO hitcher_db.hitchers (user_id, surname, name, patronymic, email, phone, registration_date)
VALUES (10, 'Carlson', 'Carl', null, 'carlson@yahoo.com', '+375 (25) 520-44-44', '2015-04-19 23:56:36');

INSERT INTO hitcher_db.driver_info (user_id, driving_licence_number, car_model, color)
VALUES (2, 'AA 12345678', 'Mazda CRV', 'RED');
INSERT INTO hitcher_db.driver_info (user_id, driving_licence_number, car_model, color)
VALUES (4, 'AA 98765432', 'Volvo XC90', 'BLUE');
INSERT INTO hitcher_db.driver_info (user_id, driving_licence_number, car_model, color)
VALUES (5, 'AA 11223344', 'Nissan Qashqai', 'METALLIC');
INSERT INTO hitcher_db.driver_info (user_id, driving_licence_number, car_model, color)
VALUES (8, 'AA 55669977', 'LADA Sedan', 'YELLOW');

INSERT INTO hitcher_db.reviews (about_id, who_id, text, rating)
VALUES (2, 3, 'Приятный человек!', 5);
INSERT INTO hitcher_db.reviews (about_id, who_id, text, rating)
VALUES (2, 4, 'Все тип-топ, но есть ньюансы!', 4);
INSERT INTO hitcher_db.reviews (about_id, who_id, text, rating)
VALUES (3, 2, 'Все тип-топ, но есть ньюансы!', 4);
INSERT INTO hitcher_db.reviews (about_id, who_id, text, rating)
VALUES (3, 6, 'Best!', 5);
INSERT INTO hitcher_db.reviews (about_id, who_id, text, rating)
VALUES (3, 7, 'Опоздал на полчаса!', 3);
INSERT INTO hitcher_db.reviews (about_id, who_id, text, rating)
VALUES (4, 8, 'Проблема в общении!', 4);
INSERT INTO hitcher_db.reviews (about_id, who_id, text, rating)
VALUES (4, 9, 'Лучший!', 5);
INSERT INTO hitcher_db.reviews (about_id, who_id, text, rating)
VALUES (6, 3, 'Слишком много курит!', 2);
INSERT INTO hitcher_db.reviews (about_id, who_id, text, rating)
VALUES (8, 4, 'Ужасный человек!', 1);
INSERT INTO hitcher_db.reviews (about_id, who_id, text, rating)
VALUES (8, 5, 'Я хочу от него детей!', 5);

INSERT INTO hitcher_db.trips (id, `from`, `to`, departure_datetime, arrival_datetime)
VALUES (1, 'Минск', 'Москва', '2019-11-09 17:00:00', '2019-11-10 05:00:00');
INSERT INTO hitcher_db.trips (id, `from`, `to`, departure_datetime, arrival_datetime)
VALUES (2, 'Брест', 'Витебск', '2019-11-11 13:00:00', '2019-11-11 22:00:00');
INSERT INTO hitcher_db.trips (id, `from`, `to`, departure_datetime, arrival_datetime)
VALUES (3, 'Могилев', 'Минск', '2019-11-09 09:00:00', '2019-11-09 13:00:00');
INSERT INTO hitcher_db.trips (id, `from`, `to`, departure_datetime, arrival_datetime)
VALUES (4, 'Минск ', 'Москва', '2019-11-09 12:00:00', '2019-11-09 22:00:00');
INSERT INTO hitcher_db.trips (id, `from`, `to`, departure_datetime, arrival_datetime)
VALUES (5, 'Варшава', 'Берлин', '2019-11-15 15:00:00', '2019-11-15 21:00:00');
INSERT INTO hitcher_db.trips (id, `from`, `to`, departure_datetime, arrival_datetime)
VALUES (6, 'Париж', 'Мадрид', '2019-11-12 15:00:00', '2019-11-13 15:00:00');
INSERT INTO hitcher_db.trips (id, `from`, `to`, departure_datetime, arrival_datetime)
VALUES (7, 'Таллин', 'Вильнюс', '2019-11-20 09:00:00', '2019-11-20 11:00:00');
INSERT INTO hitcher_db.trips (id, `from`, `to`, departure_datetime, arrival_datetime)
VALUES (8, 'Минск', 'Вильнюс', '2019-11-21 09:00:00', '2019-11-20 13:00:00');
INSERT INTO hitcher_db.trips (id, `from`, `to`, departure_datetime, arrival_datetime)
VALUES (9, 'Москва', 'Смоленск', '2019-11-13 18:00:00', '2019-11-19 18:00:00');
INSERT INTO hitcher_db.trips (id, `from`, `to`, departure_datetime, arrival_datetime)
VALUES (10, 'Москва', 'Челябинск', '2019-11-14 09:00:00', '2019-11-17 18:00:00');

INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (1, 2, 1);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (1, 2, 3);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (1, 2, 5);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (2, 4, 2);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (2, 4, 5);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (2, 4, 7);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (3, 5, 10);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (3, 5, 2);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (3, 5, 3);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (4, 8, 1);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (4, 8, 5);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (5, 8, 3);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (5, 4, 8);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (6, 2, 6);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (7, 8, 4);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (8, 2, 1);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (9, 4, 9);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (9, 4, 10);
INSERT INTO hitcher_db.trip_users (trip_id, driver_id, passenger_id)
VALUES (10, 5, 3);

INSERT INTO hitcher_db.trip_options (trip_id, free_seats, price, smoking, pets)
VALUES (1, 2, 50, 1, 1);
INSERT INTO hitcher_db.trip_options (trip_id, free_seats, price, smoking, pets)
VALUES (2, 3, 70, 0, 1);
INSERT INTO hitcher_db.trip_options (trip_id, free_seats, price, smoking, pets)
VALUES (3, 1, 55, 0, 1);
INSERT INTO hitcher_db.trip_options (trip_id, free_seats, price, smoking, pets)
VALUES (4, 2, 45, 1, 1);
INSERT INTO hitcher_db.trip_options (trip_id, free_seats, price, smoking, pets)
VALUES (5, 3, 25, 1, 1);
INSERT INTO hitcher_db.trip_options (trip_id, free_seats, price, smoking, pets)
VALUES (6, 4, 44, 1, 1);
INSERT INTO hitcher_db.trip_options (trip_id, free_seats, price, smoking, pets)
VALUES (7, 2, 30, 1, 1);
INSERT INTO hitcher_db.trip_options (trip_id, free_seats, price, smoking, pets)
VALUES (8, 3, 62, 1, 1);
INSERT INTO hitcher_db.trip_options (trip_id, free_seats, price, smoking, pets)
VALUES (9, 4, 88, 0, 1);
INSERT INTO hitcher_db.trip_options (trip_id, free_seats, price, smoking, pets)
VALUES (10, 1, 100, 0, 1);

INSERT INTO hitcher_db.hitcher_info (user_id, address, music, conversation)
VALUES (1, null, 'Depends on playlist', 'Depends on mood');
INSERT INTO hitcher_db.hitcher_info (user_id, address, music, conversation)
VALUES (2, 'Sovetskaya st., 13, 1', 'Electronic', 'Depends on mood');
INSERT INTO hitcher_db.hitcher_info (user_id, address, music, conversation)
VALUES (3, 'Slobodskoi pr., 122', 'Radio', 'Dont want to talk');
INSERT INTO hitcher_db.hitcher_info (user_id, address, music, conversation)
VALUES (4, 'Pushkina pr., 14', 'Metal', 'Depends on mood');
INSERT INTO hitcher_db.hitcher_info (user_id, address, music, conversation)
VALUES (5, 'Козлова, 22', 'Depends on playlist', 'Dont want to talk');
INSERT INTO hitcher_db.hitcher_info (user_id, address, music, conversation)
VALUES (6, null, 'Electronic', 'Always ready to talk');
INSERT INTO hitcher_db.hitcher_info (user_id, address, music, conversation)
VALUES (7, 'Odincova st, 15', 'Metal', 'Depends on mood');
INSERT INTO hitcher_db.hitcher_info (user_id, address, music, conversation)
VALUES (8, 'пер. Пешеходов, 2', 'Depends on playlist', 'Dont want to talk');
INSERT INTO hitcher_db.hitcher_info (user_id, address, music, conversation)
VALUES (9, null, 'Radio', 'Depends on mood');
INSERT INTO hitcher_db.hitcher_info (user_id, address, music, conversation)
VALUES (10, null, 'Depends on playlist', 'Always ready to talk');

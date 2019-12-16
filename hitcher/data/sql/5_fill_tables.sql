USE `hitcher_db`;

/*password smith*/
INSERT INTO users (login, email, password, salt, role, status, registration_date)
VALUES ('smith', 'smith@gmail.com', 'AyAurJOVwkp75h4/vlw1Rni/jI+km5uw+c9b9rHi1Ws=', '3Hfzs5SzuDLg3uD12X9CnyFD4rb5jB', 1, 1, '2018-07-08 21:34:13');
/*password anna*/
INSERT INTO users (login, email, password, salt, role, status, registration_date)
VALUES ('anna', 'solovei@gmail.com', '4BL8sAw1ZR81rkQihuIex6QDA5RD8eyz+Aa46q7MD4Y=', 'vggBdm1I60lE7YpK88Ywf5X0EsAGrR', 1, 1, '2018-07-15 21:35:54');
/*password petrov*/
INSERT INTO users (login, email, password, salt, role, status, registration_date)
VALUES ('petrov', 'petrov@gmail.com', 'cYkA4YVx2kMbcfEr+kcc+GpBKyBvK0nwzTU/jZi6Vb4=', '01z8BuDKet8dpW7HTCB34FgEUWzlQH', 1, 1, '2017-08-22 17:31:58');
/*password kamilla*/
INSERT INTO users (login, email, password, salt, role, status, registration_date)
VALUES ('kamilla', 'pozn_@gmail.com', 'wfPQvpSRTMpNYyq4TP2sPiuy+/QdwzeWAXtohmJoXTI=', 'zMB9NTw7NeOAdFQjwNna87TuF6C0fB', 1, 1, '2016-01-20 23:50:28');
/*password tolik*/
INSERT INTO users (login, email, password, salt, role, status, registration_date)
VALUES ('tolik', 'osipov@mail.by', 'WQPload5K1lBRGE9uZIwgBQ3fuuv6ENqxwCbRprtN2c=', '5eIaelsUQ3tYpm2ygqYsJykgJCeUjq', 1, 1, '2017-09-13 23:51:50');
/*password boi*/
INSERT INTO users (login, email, password, salt, role, status, registration_date)
VALUES ('boi', 'ivan@tut.by', 'Ls4rE7U2lXoLrbemVlnYSUW4/8WVemw603KLkF62Ht8=', 'iSIZRG4Yj4iv0DK2wKiUkCyZUt0yAY', 1, 1, '2018-12-02 23:52:52');
/*password bird*/
INSERT INTO users (login, email, password, salt, role, status, registration_date)
VALUES ('bird', 'sidor@tut.by', 'E/yg/B/ejZUe7VvpYQtLVs1B3kZiu7TwXPtln2enMVI=', 'YONpG7SKxNUgoEhIVRWRoXzmeFUEs1', 1, 1, '2016-10-11 23:53:57');
/*password xtasy*/
INSERT INTO users (login, email, password, salt, role, status, registration_date)
VALUES ('xtasy', 'sell@gmail.com', 'jVv9sQyBOMyUrOrheOOU8vzkSV4sBv/Ky8N20lfxh/Q=', 'ykrYanEkiEMTCJHaoGkHmokg3CYwdt', 1, 1, '2017-03-14 23:54:59');
/*password carl*/
INSERT INTO users (login, email, password, salt, role, status, registration_date)
VALUES ('carl', 'carlson@yahoo.com', '4mcBWw15Btu6wu/zdwNKNHtc90UEPf1zaqm+k09mMHE=', 'BQTgkFqXwp09pFjVukoX5RbLikQgrZ', 1, 1, '2015-04-19 23:56:36');

INSERT INTO music (description)
    VALUE ('music.negative');
INSERT INTO music (description)
    VALUE ('music.neutral');
INSERT INTO music (description)
    VALUE ('music.positive');

INSERT INTO communication (description)
VALUES ('com.negative');
INSERT INTO communication (description)
VALUES ('com.neutral');
INSERT INTO communication (description)
VALUES ('com.positive');

INSERT INTO hitchers (user_id, surname, name, patronymic, phone, address, music_id, communication_id)
VALUES (2, 'Smith', 'John', null,  '+375 (29) 123-45-67', 'Sovetskaya st., 13, 1', 1, 3);
INSERT INTO hitchers (user_id, surname, name, patronymic, phone, address, music_id, communication_id)
VALUES (3, 'Solovei', 'Anna', 'Alexeevna', '+375 (29) 222-55-55', 'Slobodskoi pr., 122', 3, 2);
INSERT INTO hitchers (user_id, surname, name, patronymic, phone, address, music_id, communication_id)
VALUES (4, 'Petrov', 'Vasya', 'Vasilievich', '+375 (25) 710-21-99', 'Pushkina pr., 14', 2, 3);
INSERT INTO hitchers (user_id, surname, name, patronymic, phone, address, music_id, communication_id)
VALUES (5, 'Познева', 'Наталья', 'Петровна', '+375 (25) 510-28-44', 'Козлова, 22', 2, 1);
INSERT INTO hitchers (user_id, surname, name, patronymic, phone, address, music_id, communication_id)
VALUES (6, 'Осипов', 'Олег', null, '+375 (25) 192-21-39', null, 2, 2);
INSERT INTO hitchers (user_id, surname, name, patronymic, phone, address, music_id, communication_id)
VALUES (7, 'Ivanov', 'Ivan', 'Ivanovich', '+375 (27) 232-33-33', 'Odincova st, 15', 2, 3);
INSERT INTO hitchers (user_id, surname, name, patronymic, phone, address, music_id, communication_id)
VALUES (8, 'Сидоров', 'Сидор', 'Сидорович', '+375 (25) 788-28-94', 'пер. Пешеходов, 2', 3, 2);
INSERT INTO hitchers (user_id, surname, name, patronymic, phone, address, music_id, communication_id)
VALUES (9, 'Selezneva', 'Olga', null, '+375 (25) 577-28-74', null, 2, 1);
INSERT INTO hitchers (user_id, surname, name, patronymic, phone, address, music_id, communication_id)
VALUES (10, 'Carlson', 'Carl', null, '+375 (25) 520-44-44', null, 1, 2);

INSERT INTO driver_info (user_id, driving_licence_number, car_model, car_color)
VALUES (2, '1AA 987654', 'Mazda CRV', 'RED');
INSERT INTO driver_info (user_id, driving_licence_number, car_model, car_color)
VALUES (4, '2AA 123456', 'Volvo XC90', 'BLUE');
INSERT INTO driver_info (user_id, driving_licence_number, car_model, car_color)
VALUES (5, '5AA 112233', 'Nissan Qashqai', 'METALLIC');
INSERT INTO driver_info (user_id, driving_licence_number, car_model, car_color)
VALUES (8, '3AA 556699', 'LADA Sedan', 'YELLOW');
INSERT INTO driver_info (user_id, driving_licence_number, car_model, car_color)
VALUES (3, '1AA 123456', 'LADA Calina', 'BLUE');

INSERT INTO reviews (about_id, who_id, text, rating)
VALUES (2, 3, 'Приятный человек!', 5);
INSERT INTO reviews (about_id, who_id, text, rating)
VALUES (2, 4, 'Все тип-топ, но есть ньюансы!', 4);
INSERT INTO reviews (about_id, who_id, text, rating)
VALUES (3, 2, 'Все тип-топ, но есть ньюансы!', 4);
INSERT INTO reviews (about_id, who_id, text, rating)
VALUES (3, 6, 'Best!', 5);
INSERT INTO reviews (about_id, who_id, text, rating)
VALUES (3, 7, 'Опоздание на полчаса!', 3);
INSERT INTO reviews (about_id, who_id, text, rating)
VALUES (4, 8, 'Проблема в общении!', 4);
INSERT INTO reviews (about_id, who_id, text, rating)
VALUES (4, 9, 'Лучший!', 5);
INSERT INTO reviews (about_id, who_id, text, rating)
VALUES (6, 3, 'Слишком много курит!', 2);
INSERT INTO reviews (about_id, who_id, text, rating)
VALUES (8, 4, 'Ужасный человек!', 1);
INSERT INTO reviews (about_id, who_id, text, rating)
VALUES (8, 5, 'Я хочу от него детей!', 5);

INSERT INTO country (id, name) VALUES (1, 'Russia');
INSERT INTO country (id, name) VALUES (2, 'Belarus');
INSERT INTO country (id, name) VALUES (3, 'Ukraine');
INSERT INTO country (id, name) VALUES (4, 'Poland');

INSERT INTO city (id, name, country_id) VALUES (1, 'Moscow', 1);
INSERT INTO city (id, name, country_id) VALUES (2, 'Saint Petersburg', 1);
INSERT INTO city (id, name, country_id) VALUES (3, 'Novosibirsk', 1);
INSERT INTO city (id, name, country_id) VALUES (4, 'Yekaterinburg', 1);
INSERT INTO city (id, name, country_id) VALUES (5, 'Nizhny Novgorod', 1);
INSERT INTO city (id, name, country_id) VALUES (6, 'Kazan', 1);
INSERT INTO city (id, name, country_id) VALUES (7, 'Chelyabinsk', 1);
INSERT INTO city (id, name, country_id) VALUES (8, 'Omsk', 1);
INSERT INTO city (id, name, country_id) VALUES (9, 'Samara', 1);
INSERT INTO city (id, name, country_id) VALUES (10, 'Ufa', 1);
INSERT INTO city (id, name, country_id) VALUES (11, 'Perm', 1);
INSERT INTO city (id, name, country_id) VALUES (12, 'Minsk', 2);
INSERT INTO city (id, name, country_id) VALUES (13, 'Barysaw', 2);
INSERT INTO city (id, name, country_id) VALUES (14, 'Salihorsk', 2);
INSERT INTO city (id, name, country_id) VALUES (15, 'Maladzyechna', 2);
INSERT INTO city (id, name, country_id) VALUES (16, 'Zhodzina', 2);
INSERT INTO city (id, name, country_id) VALUES (17, 'Slutsk', 2);
INSERT INTO city (id, name, country_id) VALUES (18, 'Vitebsk', 2);
INSERT INTO city (id, name, country_id) VALUES (19, 'Orsha', 2);
INSERT INTO city (id, name, country_id) VALUES (20, 'Mogilev', 2);
INSERT INTO city (id, name, country_id) VALUES (21, 'Babruysk', 2);
INSERT INTO city (id, name, country_id) VALUES (22, 'Gomel', 2);
INSERT INTO city (id, name, country_id) VALUES (23, 'Mazyr', 2);
INSERT INTO city (id, name, country_id) VALUES (24, 'Brest', 2);
INSERT INTO city (id, name, country_id) VALUES (25, 'Pinsk', 2);
INSERT INTO city (id, name, country_id) VALUES (26, 'Grodno', 2);
INSERT INTO city (id, name, country_id) VALUES (27, 'Kiev', 3);
INSERT INTO city (id, name, country_id) VALUES (28, 'Kharkiv', 3);
INSERT INTO city (id, name, country_id) VALUES (29, 'Odessa', 3);
INSERT INTO city (id, name, country_id) VALUES (30, 'Dnipropetrovsk', 3);
INSERT INTO city (id, name, country_id) VALUES (31, 'Donetsk', 3);
INSERT INTO city (id, name, country_id) VALUES (32, 'Zaporizhia', 3);
INSERT INTO city (id, name, country_id) VALUES (33, 'Lviv', 3);
INSERT INTO city (id, name, country_id) VALUES (34, 'Mykolaiv', 3);
INSERT INTO city (id, name, country_id) VALUES (35, 'Donetsk', 3);
INSERT INTO city (id, name, country_id) VALUES (36, 'Warsaw', 4);
INSERT INTO city (id, name, country_id) VALUES (37, 'Legnica', 4);
INSERT INTO city (id, name, country_id) VALUES (38, 'Olsztyn', 4);
INSERT INTO city (id, name, country_id) VALUES (39, 'Opole', 4);
INSERT INTO city (id, name, country_id) VALUES (40, 'Rybnik', 4);
INSERT INTO city (id, name, country_id) VALUES (41, 'Szczecin', 4);
INSERT INTO city (id, name, country_id) VALUES (42, 'Bydgoszcz', 4);
INSERT INTO city (id, name, country_id) VALUES (43, 'Lublin', 4);
INSERT INTO city (id, name, country_id) VALUES (44, 'Katowice', 4);
INSERT INTO city (id, name, country_id) VALUES (45, 'Kielce', 4);

INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (1, 2, 1, 2, '2019-11-09 17:00:00', '2019-11-10 05:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (2, 4, 5, 2, '2019-11-11 13:00:00', '2019-11-11 22:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (3, 5, 12, 19, '2019-11-09 09:00:00', '2019-11-09 13:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (4, 8, 1, 27, '2019-11-09 12:00:00', '2019-11-09 22:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (5, 4, 29, 30, '2019-11-15 15:00:00', '2019-11-15 21:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (6, 2, 38, 17, '2019-11-12 15:00:00', '2019-11-13 15:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (7, 8, 15, 42, '2019-11-20 09:00:00', '2019-11-20 11:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (8, 2, 3, 10, '2019-11-21 09:00:00', '2019-11-20 13:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (9, 4, 22, 1, '2019-11-13 18:00:00', '2019-11-19 18:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (10, 5, 37, 25, '2019-11-14 09:00:00', '2019-11-17 18:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (11, 2, 2, 15, '2019-12-05 23:00:00', '2019-12-15 23:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (12, 2, 22, 24, '2019-12-12 23:00:00', '2019-12-15 23:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (13, 3, 2, 8, '2019-12-12 23:00:00', '2019-12-14 23:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (14, 2, 40, 44, '2019-12-12 23:00:00', '2019-12-14 23:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (15, 3, 2, 8, '2019-12-13 23:00:00', '2019-12-14 23:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (16, 3, 2, 8, '2019-12-14 23:00:00', '2019-12-15 23:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (17, 3, 2, 8, '2019-12-15 23:00:00', '2019-12-16 23:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (18, 3, 2, 8, '2019-12-16 23:00:00', '2019-12-17 23:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (19, 3, 2, 8, '2019-12-17 23:00:00', '2019-12-18 23:00:00');
INSERT INTO trips (id, driver_id, from_city_id, to_city_id, departure_datetime, arrival_datetime) VALUES (20, 3, 2, 8, '2019-12-18 23:00:00', '2019-12-22 23:00:00');

INSERT INTO trip_users (trip_id, passenger_id) VALUES (1, 1);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (1, 3);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (1, 5);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (2, 2);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (2, 5);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (2, 7);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (3, 10);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (3, 2);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (3, 3);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (4, 1);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (4, 5);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (5, 3);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (5, 8);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (6, 6);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (7, 4);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (8, 1);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (9, 9);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (9, 10);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (10, 3);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (18, 1);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (18, 5);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (18, 6);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (19, 8);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (19, 9);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (20, 4);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (20, 5);
INSERT INTO trip_users (trip_id, passenger_id) VALUES (20, 6);

INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (1, 3, 50.00, 1, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (2, 3, 70.00, 0, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (3, 3, 55.00, 0, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (4, 2, 45.00, 1, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (5, 3, 25.00, 1, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (6, 4, 44.00, 1, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (7, 2, 30.00, 1, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (8, 3, 62.00, 1, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (9, 4, 88.00, 0, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (10, 1, 100.00, 0, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (11, 1, 20.00, 1, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (12, 2, 10.00, 0, 0);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (13, 2, 20.00, 1, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (14, 3, 15.00, 0, 0);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (15, 4, 50.00, 1, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (16, 5, 11.00, 0, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (17, 1, 22.00, 1, 0);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (18, 2, 30.00, 0, 1);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (19, 3, 45.00, 1, 0);
INSERT INTO trip_options (trip_id, free_seats, price, smoking, pets) VALUES (20, 4, 22.00, 0, 1);


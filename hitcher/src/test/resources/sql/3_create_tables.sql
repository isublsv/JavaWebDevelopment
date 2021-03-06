USE `hitcher_db_test`;

CREATE TABLE `users`
(
    `id`       INTEGER      NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(255) NOT NULL UNIQUE,
    `email`    VARCHAR(255) NOT NULL UNIQUE,
    `password` CHAR(44)     NOT NULL,
    `salt`     CHAR(30)     NOT NULL,
    /*
     * 0 - Role.ADMIN
     * 1 - Role.USER
     */
    `role`     TINYINT      NOT NULL CHECK (`role` IN (0, 1)),
    /*
     * 0 - Status.BANNED
     * 1 - Status.ACTIVE
     */
    `status`   TINYINT      NOT NULL DEFAULT 1 CHECK ( `status` IN (0, 1)),
    `registration_date` DATETIME     DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `music`
(
    `id`          INTEGER      NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `communication`
(
    `id`          INTEGER      NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `hitchers`
(
    `user_id`          INTEGER      NOT NULL,
    `surname`          VARCHAR(255) NOT NULL,
    `name`             VARCHAR(255) NOT NULL,
    `patronymic`       VARCHAR(255),
    `phone`            VARCHAR(255) NOT NULL,
    `address`          VARCHAR(255),
    `music_id`         INTEGER,
    `communication_id` INTEGER,
    CONSTRAINT FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (`music_id`)
        REFERENCES `music` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (`communication_id`)
        REFERENCES `communication` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `driver_info`
(
    `user_id`                INTEGER NOT NULL,
    `driving_licence_number` VARCHAR(255) UNIQUE,
    `car_model`              VARCHAR(255),
    `car_color`              VARCHAR(255),
    CONSTRAINT FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `reviews`
(
    `id`       INTEGER      NOT NULL AUTO_INCREMENT,
    `about_id` INTEGER      NOT NULL,
    `who_id`   INTEGER      NOT NULL,
    `text`     VARCHAR(255) NOT NULL,
    `rating`   INTEGER      NOT NULL CHECK ( `rating` IN (1, 2, 3, 4, 5)),
    PRIMARY KEY (`id`),
    CONSTRAINT FOREIGN KEY (`about_id`)
        REFERENCES `users` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (`who_id`)
        REFERENCES `users` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE country
(
    `id`   INTEGER      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE city
(
    `id`         INTEGER      NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255) NOT NULL,
    `country_id` INTEGER      NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT FOREIGN KEY (`country_id`)
        REFERENCES `country` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE INDEX city_name_index
    ON city (`name`);

CREATE TABLE trips
(
    `id`                 INTEGER  NOT NULL AUTO_INCREMENT,
    `driver_id`          INTEGER  NOT NULL,
    `from_city_id`       INTEGER  NOT NULL,
    `to_city_id`         INTEGER  NOT NULL,
    `departure_datetime` DATETIME NOT NULL,
    `arrival_datetime`   DATETIME NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT FOREIGN KEY (`driver_id`)
        REFERENCES `users` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (`from_city_id`)
        REFERENCES `city` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (`to_city_id`)
        REFERENCES `city` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE trip_users
(
    `trip_id`      INTEGER NOT NULL,
    `passenger_id` INTEGER NOT NULL,
    CONSTRAINT FOREIGN KEY (`trip_id`)
        REFERENCES `trips` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (`passenger_id`)
        REFERENCES `users` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `trip_options`
(
    `trip_id`    INTEGER       NOT NULL,
    `free_seats` INTEGER       NOT NULL,
    `price`      DECIMAL(8, 2) NOT NULL,
    `smoking`    TINYINT(1)    NOT NULL,
    `pets`       TINYINT(1)    NOT NULL,
    CONSTRAINT FOREIGN KEY (`trip_id`)
        REFERENCES `trips` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TRIGGER `add_passenger`
    AFTER INSERT
    ON `trip_users`
    FOR EACH ROW
    UPDATE `trip_options`
    SET `free_seats` = free_seats - 1
    WHERE `trip_options`.trip_id = NEW.trip_id;

CREATE TRIGGER `remove_passenger`
    AFTER DELETE
    ON `trip_users`
    FOR EACH ROW
    UPDATE `trip_options`
    SET `free_seats` = free_seats + 1
    WHERE `trip_options`.trip_id = OLD.trip_id;

USE `hitcher_db`;

CREATE TABLE `users`
(
    `id`       INTEGER      NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(255) NOT NULL UNIQUE,
    `password` CHAR(44)     NOT NULL,
    `salt`     CHAR(30)     NOT NULL,
    /*
     * 0 - Role.ADMINISTRATOR
     * 1 - Role.USER
     */
    `role`     TINYINT      NOT NULL CHECK (`role` IN (0, 1)),
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `hitchers`
(
    `user_id`           INTEGER      NOT NULL,
    `surname`           VARCHAR(255) NOT NULL,
    `name`              VARCHAR(255) NOT NULL,
    `patronymic`        VARCHAR(255),
    `email`             VARCHAR(255) NOT NULL,
    `phone`             VARCHAR(255) NOT NULL,
    `registration_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `hitcher_info`
(
    `user_id`      INTEGER NOT NULL,
    `address`      VARCHAR(255),
    `music`        VARCHAR(255),
    `conversation` VARCHAR(255),
    CONSTRAINT FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `driver_info`
(
    `user_id`                INTEGER NOT NULL,
    `driving_licence_number` VARCHAR(255) UNIQUE,
    `car_model`              VARCHAR(255),
    `color`                  VARCHAR(255),
    CONSTRAINT FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `reviews`
(
    `about_id` INTEGER      NOT NULL,
    `who_id`   INTEGER      NOT NULL,
    `text`     VARCHAR(255) NOT NULL,
    `rating`   INTEGER      NOT NULL,
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

CREATE TABLE trips
(
    `id`                 INTEGER      NOT NULL AUTO_INCREMENT,
    `from`               VARCHAR(255) NOT NULL,
    `to`                 VARCHAR(255) NOT NULL,
    `departure_datetime` DATETIME     NOT NULL,
    `arrival_datetime`   DATETIME     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE trip_users
(
    `trip_id`      INTEGER NOT NULL,
    `driver_id`    INTEGER NOT NULL,
    `passenger_id` INTEGER NOT NULL,
    CONSTRAINT FOREIGN KEY (`trip_id`)
        REFERENCES `trips` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (`driver_id`)
        REFERENCES `users` (`id`)
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
    `trip_id`    INTEGER    NOT NULL,
    `free_seats` INTEGER    NOT NULL,
    `price`      INTEGER    NOT NULL,
    `smoking`    TINYINT(1) NOT NULL,
    `pets`       TINYINT(1) NOT NULL,
    CONSTRAINT FOREIGN KEY (`trip_id`)
        REFERENCES `trips` (`id`)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

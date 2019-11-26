USE `hitcher_db`;

INSERT INTO `users` (`id`,
                     `login`,
                     `password`,
                     `salt`,
                     `role`)
VALUES (1,
        "admin",
        "AZS4ST2SLGI/EGDRGCKQ4ISU3XV5VZ0OSUNMXPRPWKI=", /* PBKDF2WithHmacSHA1 hash пароля "password" */
        "PEMGI4IBOSMWIO98OIIT36F1IEEE85", /* salt of "password" */
        0);

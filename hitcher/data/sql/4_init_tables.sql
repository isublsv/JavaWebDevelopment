USE `hitcher_db`;

INSERT INTO `users` (`id`,
                     `login`,
                     `password`,
                     `salt`,
                     `role`)
VALUES (1,
        "admin",
        "fdGKFhv5EiPhcaRCILwuG0wfS6dBVN/PJH0QNqKrnCM=", /* PBKDF2WithHmacSHA1 hash пароля "password" */
        "z9BryTA8A2JSenyVBXQrduaAFq92m8", /* salt of "password" */
        0);

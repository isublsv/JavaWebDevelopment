USE `hitcher_db`;

INSERT INTO `users` (`id`,
                     `login`,
                     `password`,
                     `salt`,
                     `role`)
VALUES (1,
        "admin",
        "HJ0/0LX804NQ3L5YXH6BQRPLPQFWG3JXFSHITCCLWRI=", /* PBKDF2WithHmacSHA1 hash пароля "password" */
        "QNVSQFN2ZKIHYB9EMN28AGNMHC9KBN", /* salt of "password" */
        0);

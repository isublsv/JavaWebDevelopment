USE `hitcher_db`;

/*hash пароля "password" */
INSERT INTO `users` (`id`, `login`, `email`, `password`, `salt`, `role`, `status`)
VALUES (1, 'admin', 'admin@mail.by', 'fdGKFhv5EiPhcaRCILwuG0wfS6dBVN/PJH0QNqKrnCM=', 'z9BryTA8A2JSenyVBXQrduaAFq92m8', 0, 1);

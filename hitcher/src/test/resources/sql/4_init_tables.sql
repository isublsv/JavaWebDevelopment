USE `hitcher_db_test`;

/*hash пароля "password" */
INSERT INTO `users` (`id`, `login`, `email`, `password`, `salt`, `role`, `status`, `registration_date`)
VALUES (1, 'admin', 'admin@mail.by', 'fdGKFhv5EiPhcaRCILwuG0wfS6dBVN/PJH0QNqKrnCM=', 'z9BryTA8A2JSenyVBXQrduaAFq92m8', 0, 1, '2010-07-15 21:35:54');

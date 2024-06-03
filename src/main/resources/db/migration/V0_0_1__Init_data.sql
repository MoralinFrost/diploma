INSERT INTO roles (name)
VALUES ('USER'),
       ('ADMIN'),
       ('HEAD'),
       ('PROJECT_HEAD');

-- password is `admin` in bcrypt
INSERT INTO users(firstname, surname, email, password)
VALUES ('Admin', 'User', 'admin@example.com', '$2y$10$iJmloC3AbVgImmeW86c5z.WfNOHr40NeCSDNiCM.JsLFXSoRUSsca');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 2),
       (1, 1),
       (1, 3),
       (1, 4);
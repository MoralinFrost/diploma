INSERT INTO roles (name)
VALUES ('USER'),
       ('ROLE_ADMIN'),
       ('ROLE_HEAD'),
       ('ROLE_CONTROLLER'),
       ('ROLE_PROJECT_HEAD');

-- password is `admin` in bcrypt
INSERT INTO users(firstname, surname, email, password)
VALUES ('Admin', 'User', 'admin@example.com', '$2y$10$iJmloC3AbVgImmeW86c5z.WfNOHr40NeCSDNiCM.JsLFXSoRUSsca');
CREATE TABLE user_project
(
    roles      VARCHAR(255),
    user_id    BIGINT NOT NULL,
    project_id BIGINT NOT NULL,
    CONSTRAINT pk_user_project PRIMARY KEY (user_id, project_id)
);

CREATE TABLE users
(
    id       BIGINT       NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    active   BOOLEAN,
    roles    VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE user_project
    ADD CONSTRAINT FK_USER_PROJECT_ON_PROJECT FOREIGN KEY (project_id) REFERENCES projects (id);

ALTER TABLE user_project
    ADD CONSTRAINT FK_USER_PROJECT_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE client_project
    DROP CONSTRAINT fk_client_project_on_client;

ALTER TABLE client_project
    DROP CONSTRAINT fk_client_project_on_project;

ALTER TABLE clients
    DROP CONSTRAINT uc_clients_username;

DROP TABLE client_project CASCADE;

DROP TABLE clients CASCADE;
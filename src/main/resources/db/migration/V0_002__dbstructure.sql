CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE client_project
(
    roles      VARCHAR(255),
    client_id  BIGINT NOT NULL,
    project_id BIGINT NOT NULL,
    CONSTRAINT pk_client_project PRIMARY KEY (client_id, project_id)
);

CREATE TABLE clients
(
    id       BIGINT       NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    active   BOOLEAN,
    roles    VARCHAR(255),
    CONSTRAINT pk_clients PRIMARY KEY (id)
);

CREATE TABLE projects
(
    id          BIGINT       NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    CONSTRAINT pk_projects PRIMARY KEY (id)
);

ALTER TABLE clients
    ADD CONSTRAINT uc_clients_username UNIQUE (username);

ALTER TABLE client_project
    ADD CONSTRAINT FK_CLIENT_PROJECT_ON_CLIENT FOREIGN KEY (client_id) REFERENCES clients (id);

ALTER TABLE client_project
    ADD CONSTRAINT FK_CLIENT_PROJECT_ON_PROJECT FOREIGN KEY (project_id) REFERENCES projects (id);
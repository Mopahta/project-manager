ALTER TABLE projects
    ADD creation_date TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE user_project
    ADD date_joined TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE project_task
    ADD finished BOOLEAN;
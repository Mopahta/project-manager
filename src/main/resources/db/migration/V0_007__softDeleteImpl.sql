ALTER TABLE project_task
    ADD deleted BOOLEAN;

ALTER TABLE projects
    ADD deleted BOOLEAN;

ALTER TABLE user_project
    ADD deleted BOOLEAN;
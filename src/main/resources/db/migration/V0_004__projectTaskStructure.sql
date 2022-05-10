CREATE TABLE project_task
(
    task       VARCHAR(255),
    order_id   BIGINT NOT NULL,
    project_id BIGINT NOT NULL,
    CONSTRAINT pk_project_task PRIMARY KEY (order_id, project_id)
);

ALTER TABLE project_task
    ADD CONSTRAINT FK_PROJECT_TASK_ON_PROJECT FOREIGN KEY (project_id) REFERENCES projects (id);
ALTER TABLE project_task
    ADD CONSTRAINT uc_project_task_order UNIQUE (order_id);
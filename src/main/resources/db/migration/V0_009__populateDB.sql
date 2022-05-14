INSERT INTO users (id, username, password, active, roles) VALUES
    (143, 'hawtstone', '$2a$10$ZIedVtqdLDFxM0xxEnyJl.WCBc3Mqi00sq0iG/3wOK82rI8gPLZYK', true, 'USER');

INSERT INTO users (id, username, password, active, roles) VALUES
    (144, 'dendi', '$2a$10$KfDx61c/WqndflWYe5w0jOtluACGkAabIzcQmFsj5LDB5tptMZvDm', true, 'USER');

INSERT INTO users (id, username, password, active, roles) VALUES
    (145, 'admin', '$2a$10$a3sY8e7sa1jgizkd2BZk7Oe2pTcdLR8SV886c7jWiZdHiS/9Yw9nq',true,'ADMIN');


INSERT INTO projects (id, name, description, creation_date, deleted) VALUES
    (150, 'Project manager', 'Good to plan and edit projects', current_timestamp, false);

INSERT INTO projects (id, name, description, creation_date, deleted) VALUES
    (151, 'Dorm', 'Cockroaches investigating', current_timestamp, false);

INSERT INTO projects (id, name, description, creation_date, deleted) VALUES
    (149, 'Project X', 'Famous project', current_timestamp, false);


INSERT INTO user_project (roles, user_id, project_id, date_joined, deleted) VALUES
    ('Editor', 143, 149, current_timestamp, false);

INSERT INTO user_project (roles, user_id, project_id, date_joined, deleted) VALUES
    ('DB manager', 144, 149, current_timestamp, false);


INSERT INTO project_task (task, order_id, project_id, finished, deleted) VALUES
    ('Destroy ''em', 2, 151, false, false);

INSERT INTO project_task (task, order_id, project_id, finished, deleted) VALUES
    ('Get antipesticide spray', 1, 151, false, false);


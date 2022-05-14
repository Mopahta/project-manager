ALTER TABLE projects
    RENAME COLUMN creation_date TO creationDate;
ALTER TABLE user_project
    RENAME COLUMN date_joined TO dateJoined;

CREATE TABLE task (
                      task_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      task_title VARCHAR(255) NOT NULL,
                      task_description TEXT,
                      task_status VARCHAR(50),
                      project_id BIGINT
);

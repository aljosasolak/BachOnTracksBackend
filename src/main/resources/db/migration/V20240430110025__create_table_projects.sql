CREATE TABLE projects (
    id VARCHAR(50) NOT NULL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    starting_date DATE NOT NULL, -- Problem with data Type?
    ending_date DATE NOT NULL, -- Problem with data Type?
    description TEXT NULL,
    user_id VARCHAR(50) NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
) Engine=InnoDB DEFAULT CHARSET=utf8mb4;
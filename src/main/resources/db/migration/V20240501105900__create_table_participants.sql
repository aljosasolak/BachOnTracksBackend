CREATE TABLE participants (
	id VARCHAR(50) PRIMARY KEY,
	firstname VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	email VARCHAR(255) NULL,
	phone_number VARCHAR(50) NULL,
	project_id VARCHAR(50),
	FOREIGN KEY (project_id) REFERENCES projects(id)
) Engine=InnoDB DEFAULT CHARSET=utf8mb4;
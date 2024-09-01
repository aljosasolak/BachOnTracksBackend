CREATE TABLE appointments (
	id VARCHAR(50) PRIMARY KEY,
	date Date NOT NULL, -- Problem with data Type?
	starting_time TIME NOT NULL,
	ending_time TIME NOT NULL,
	status ENUM ('CONFIRMED', 'NOT_CONFIRMED') NOT NULL,
	project_id VARCHAR(50) NULL,
	location VARCHAR(250) NOT NULL,
	type ENUM ('PROBE', 'ANSPIELPROBE', 'GENERALPROBE', 'STIMMTERMINE', 'KONZERT', 'BESPRECHUNG') NOT NULL,
	FOREIGN KEY (project_id) REFERENCES projects(id)
) Engine=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE users (
	id VARCHAR(50) NOT NULL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NULL
) Engine=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO users VALUES (
'DUMMY_USER_ID', 'Aljoša', 'Šolak', 'aljosasolak@yahoo.com'
);
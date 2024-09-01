CREATE TABLE bach_on_tracks.files (
                            id VARCHAR(50) PRIMARY KEY,
                            file_name VARCHAR(500) NOT NULL,
                            pathname VARCHAR(500) NOT NULL,
                            mime_type VARCHAR(250) NOT NULL,
                            project_id VARCHAR(50),
                            FOREIGN KEY (project_id) REFERENCES bach_on_tracks.projects(id)
) ENGINE=InnoDB DEFAULT  CHARSET=utf8mb4;
DROP TABLE IF EXISTS to_do_list_entry CASCADE;

CREATE TABLE to_do_list_entry (
	id INT AUTO_INCREMENT,
	priority INT NOT NULL,
	title VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	complete BOOLEAN,
	PRIMARY KEY (id)
);
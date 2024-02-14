CREATE TABLE IF NOT EXISTS users (
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
surname VARCHAR(50) NOT NULL,
name VARCHAR(50) NOT NULL,
middle_name VARCHAR(50) NOT NULL);

CREATE TABLE IF NOT EXISTS user_qr_codes (
id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
user_id INT,
uuid UUID NOT NULL,
FOREIGN KEY (user_id) REFERENCES users (id));

INSERT INTO users (surname, name, middle_name)
VALUES ('Taylor', 'John', 'Smith');
INSERT INTO user_qr_codes (user_id, uuid)
VALUES ((SELECT id FROM users WHERE surname = 'Taylor'), 'd67d7d36-dc3f-4425-a9dc-172e13ce84e4');
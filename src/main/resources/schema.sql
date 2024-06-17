DROP TABLE IF EXISTS rentals CASCADE;
DROP TABLE IF EXISTS hosts CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS books CASCADE;


CREATE TABLE hosts
(
id SERIAL PRIMARY KEY,
name VARCHAR(16) NOT NULL,
password VARCHAR(16) NOT NULL,
created_at TIMESTAMP NOT NULL,
created_by INTEGER NOT NULL,
update_at TIMESTAMP,
update_by INTEGER,
version_no INTEGER NOT NULL,
delete_flag INTEGER NOT NULL
);


CREATE TABLE users
(
id SERIAL PRIMARY KEY,
name VARCHAR(16) NOT NULL,
email VARCHAR(32) NOT NULL UNIQUE,
password VARCHAR(16) NOT NULL,
created_at TIMESTAMP NOT NULL,
created_by SERIAL NOT NULL,
update_at TIMESTAMP,
update_by INTEGER  REFERENCES hosts(id),
version_no INTEGER NOT NULL,
delete_flag INTEGER NOT NULL
);


CREATE TABLE books
(
id SERIAL PRIMARY KEY,
title VARCHAR(100) NOT NULL,
author VARCHAR(100) NOT NULL,
created_at TIMESTAMP NOT NULL,
created_by INTEGER REFERENCES hosts(id) NOT NULL,
update_at TIMESTAMP,
update_by INTEGER REFERENCES hosts(id),
version_no INTEGER NOT NULL,
delete_flag INTEGER NOT NULL
);


CREATE TABLE rentals
(
id SERIAL PRIMARY KEY,
user_id INTEGER REFERENCES users(id) NOT NULL,
book_id INTEGER REFERENCES books(id) NOT NULL,
rental_date DATE NOT NULL,
limit_date DATE NOT NULL,
return_date DATE,
created_at TIMESTAMP NOT NULL,
created_by INTEGER REFERENCES hosts(id) NOT NULL,
update_at TIMESTAMP,
update_by INTEGER REFERENCES hosts(id),
version_no INTEGER NOT NULL,
delete_flag INTEGER NOT NULL
);



CREATE TABLE people (
    person_id BIGINT PRIMARY KEY auto_increment,
    id_number VARCHAR(32) NOT NULL UNIQUE,
    mobile_number VARCHAR(20) NOT NULL UNIQUE,
    firstname VARCHAR(32),
    lastname VARCHAR(32),
    physical_address VARCHAR(100)
);
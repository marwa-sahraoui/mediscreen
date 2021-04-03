CREATE SEQUENCE hibernate_sequence START 2;

create table Patient
(
    id  BIGINT NOT NULL PRIMARY KEY,
    family VARCHAR ( 50 ),
    given  VARCHAR ( 50 ),
    dob VARCHAR ( 50 ),
    sex VARCHAR ( 50 ),
    address VARCHAR ( 50 ),
    phone VARCHAR ( 50 )

);
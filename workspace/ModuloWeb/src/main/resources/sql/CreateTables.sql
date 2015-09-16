-- IDENTITY (1, 1) clave autonumerica
CREATE TABLE "dbo"."TEST"
(
PERSON_ID int IDENTITY (1, 1) NOT NULL,
LAST_NAME varchar(55),
FIRST_NAME varchar(55),
ADDRESS varchar(55),
CITY varchar(55),
CONSTRAINT TEST_PK PRIMARY KEY (PERSON_ID)
);




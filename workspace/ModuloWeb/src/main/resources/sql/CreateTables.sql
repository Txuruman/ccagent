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


-- CCAGENT_ADM
CREATE TABLE "dbo"."direct_access"
(
id int IDENTITY (1, 1) NOT NULL,
name varchar(255),
descripition varchar(255),
url varchar(255),
position int,
CONSTRAINT direct_access_PK PRIMARY KEY (id)
);

-- CCAGENT_AUD
CREATE TABLE "dbo"."audit"
(
date date,
actor varchar(255),
id int IDENTITY (1, 1) NOT NULL,
app varchar(255),
event varchar(255),
result varchar(255),
detail varchar(255),
CONSTRAINT audit_PK PRIMARY KEY (id)
);




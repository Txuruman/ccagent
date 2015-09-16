-- IDENTITY (1, 1) clave autonumerica
DROP TABLE "dbo"."TEST";
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
DROP TABLE "dbo"."direct_access";
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
DROP TABLE "dbo"."audit";
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

-- CCAGENT_AUD
DROP TABLE "dbo"."field_config";
CREATE TABLE "dbo"."field_config"
(
id int IDENTITY (1, 1) NOT NULL,
app varchar(255),
identifier varchar(255),
description varchar(255),
visible bit,
editable bit,
CONSTRAINT field_config_PK PRIMARY KEY (id)
)





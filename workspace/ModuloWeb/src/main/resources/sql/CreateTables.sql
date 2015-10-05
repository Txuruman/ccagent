-- CCAGENT_AUD
DROP TABLE "dbo"."audit";
CREATE TABLE "dbo"."audit"
(
id int IDENTITY (1, 1) NOT NULL,
audit_date datetime,
actor varchar(255),
app varchar(255),
event varchar(255),
result varchar(255),
detail varchar(255),
CONSTRAINT audit_PK PRIMARY KEY (id)
);



-- CCAGENT_ADM
DROP TABLE "dbo"."direct_access";
CREATE TABLE "dbo"."direct_access"
(
id int IDENTITY (1, 1) NOT NULL,
name varchar(255),
description varchar(255),
url varchar(255),
position int,
CONSTRAINT direct_access_PK PRIMARY KEY (id)
);



-- CCAGENT_ADM
DROP TABLE "dbo"."field_config";
CREATE TABLE "dbo"."field_config"
(
id int IDENTITY (1, 1) NOT NULL,
position int,
app varchar(255),
identifier varchar(255),
description varchar(255),
visible bit,
editable bit,
administrable bit,
CONSTRAINT field_config_PK PRIMARY KEY (id)
)

-- CCAGENT_ADM
DROP TABLE "dbo"."direct_access_params";
CREATE TABLE "dbo"."direct_access_params"
(
id int IDENTITY (1, 1) NOT NULL,
name varchar(255),
value varchar(255),
direct_access int,
CONSTRAINT direct_access_params_PK PRIMARY KEY (id)
)


-- CCAGENT_ADM
DROP TABLE "dbo"."combinations_keys";
CREATE TABLE "dbo"."combinations_keys"
(
id int IDENTITY (1, 1) NOT NULL,
key1 varchar(255),
key2 varchar(255),
key3 varchar(255),
tab varchar(255),
CONSTRAINT combinations_keys_PK PRIMARY KEY (id)
)

-- CCAGENT_ADM
DROP TABLE "dbo"."action_plan";
CREATE TABLE "dbo"."action_plan"
(
id int IDENTITY (1, 1) NOT NULL,
type varchar(255),
secuence int,
contact_name varchar(255),
phone1 varchar(255),
phone2 varchar(255),
phone3 varchar(255),
CONSTRAINT action_plan_PK PRIMARY KEY (id)
)


-- CCAGENT_ADM
--DROP TABLE "dbo"."cuote";
CREATE TABLE "dbo"."cuote"
(
id int IDENTITY (1, 1) NOT NULL,
installation_number int,
mes_actual numeric(30,2),
enero_actual numeric(30,2),
enero_pasado1 numeric(30,2),
CONSTRAINT cuote_PK PRIMARY KEY (id)
)

-- CCAGENT_ADM
DROP TABLE "dbo"."cycle_feeds";
CREATE TABLE "dbo"."cycle_feeds"
(
id int IDENTITY (1, 1) NOT NULL,
ins_no varchar(255),
from_date date,
to_date date,
fee numeric(30,2),
rev_tp varchar(255),
description varchar(255),
count int,
CONSTRAINT cycle_feeds_PK PRIMARY KEY (id)
)


-- CCAGENT_ADM
DROP TABLE "dbo"."installation_data";
CREATE TABLE "dbo"."installation_data"
(
      id int IDENTITY (1, 1) NOT NULL,
      panel varchar(255),
      version varchar(255),
      installation_number varchar(255),
      panel_phone varchar(255),
      customer_name varchar(255),
      email_monitoring varchar(255),
      email_update varchar(255),
      email_services varchar(255),
      camera varchar(255),
      aka varchar(255),
      address varchar(255),
      city varchar(255),
      monitoring_status varchar(255),
      subtype varchar(255),
      language varchar(255),
      customer_password varchar(255),
      securitas_password varchar(255),
      coercion_password varchar(255),
      CONSTRAINT installation_data_PK PRIMARY KEY (id)
)


-- CCAGENT_ADM
DROP TABLE "dbo"."invoice_data";
CREATE TABLE "dbo"."invoice_data"
(
  id int IDENTITY (1, 1) NOT NULL,
  invoice_number int,
	ext_invoice_no varchar(255),
	intallation_number int,
	invoice_type varchar(255),
	amount numeric(30,2),
	system_date date,
	transaction_date date,
	due_date date,
	CONSTRAINT invoice_data_PK PRIMARY KEY (id)
)


-- CCAGENT_ADM
DROP TABLE "dbo"."invoice_info";
CREATE TABLE "dbo"."invoice_info"
(
    id int IDENTITY (1, 1) NOT NULL,
    invoice_send bit,
    debt_amount numeric(30,2),
    financial_entity varchar(255),
    pay_mode varchar(255),
    discount bit,
    email_billing varchar(255),
    ccc varchar(255),
   CONSTRAINT invoice_info_PK PRIMARY KEY (id)
)



-- CCAGENT_ADM
DROP TABLE "dbo"."invoice_item";
CREATE TABLE "dbo"."invoice_item"
(
  id int IDENTITY (1, 1) NOT NULL,
  invoice_number int,
	description varchar(255),
	period int,
	amount numeric(30,2),
	tax numeric(30,2),
	CONSTRAINT invoice_item_PK PRIMARY KEY (id)
)



-- CCAGENT_ADM
DROP TABLE "dbo"."phone";
CREATE TABLE "dbo"."phone"
(
  id int IDENTITY (1, 1) NOT NULL,
	type varchar(255),
  number varchar(255),
	CONSTRAINT phone_PK PRIMARY KEY (id)
)



-- CCAGENT_ADM
DROP TABLE "dbo"."users";
CREATE TABLE "dbo"."users"
(
  id int IDENTITY (1, 1) NOT NULL,
  user_id varchar(255),
  name varchar(255),
  is_admin bit,
  CONSTRAINT users_PK PRIMARY KEY (id)
)






















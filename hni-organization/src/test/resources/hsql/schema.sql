CREATE TABLE organizations (
  id int NOT NULL IDENTITY,
  name varchar(64) NOT NULL
);

CREATE TABLE users (
  id int NOT NULL IDENTITY,
  firstName varchar(64) NOT NULL,
  lastName varchar(64) NOT NULL,
  genderCode varchar(1),
  mobilePhone varchar(16)  
);

CREATE TABLE user_organization_role (
	user_id INTEGER NOT NULL,
	org_id INTEGER NOT NULL,
	role_id INTEGER NOT NULL,
	PRIMARY KEY(user_id, org_id, role_id)

);

CREATE TABLE roles (
  id int NOT NULL IDENTITY,
  name varchar(64) NOT NULL
);
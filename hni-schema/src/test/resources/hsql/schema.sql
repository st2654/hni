
CREATE TABLE users (
  id int NOT NULL IDENTITY,
  firstName varchar(64) NOT NULL,
  lastName varchar(64) NOT NULL,
  genderCode varchar(1),
  mobilePhone varchar(16),
  email varchar(100),
  deleted int
);

CREATE TABLE organizations (
  id int NOT NULL IDENTITY,
  name varchar(64) NOT NULL,
  addressLine1 varchar(100),
  addressLine2 varchar(100),
  city varchar(100),
  state varchar(2),
  zip varchar(12)
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
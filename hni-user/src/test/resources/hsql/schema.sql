CREATE TABLE users (
  id int NOT NULL IDENTITY,
  firstName varchar(64) NOT NULL,
  lastName varchar(64) NOT NULL,
  genderCode varchar(1),
  mobilePhone varchar(16),
  deleted int
);


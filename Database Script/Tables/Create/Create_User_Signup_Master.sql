CREATE TABLE ACCOUNT_MASTER(
  Email varchar(100) primary key,
  UserId int default 0 unique,
  UserName varchar(100),
  CountryCode varchar(10),
  PhoneNumber varchar(20),
  Password int,
  isActive int default 1
);

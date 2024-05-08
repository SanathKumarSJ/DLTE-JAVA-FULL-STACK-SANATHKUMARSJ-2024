--Creating the table
create table Mobile_Banking(
username VARCHAR(255) not NULL,
upi VARCHAR(255) not NULL,
mobile_number NUMBER(10) not NULL,
eamil VARCHAR(255) not NULL,
wallet_type VARCHAR(255) not NULL,
recharged_date DATE not NULL,
recharged_provider VARCHAR(255) not NULL,
recharged_to VARCHAR(255) not NULL,
recharged_amount NUMBER(10) not NULL);

--Personal Details table
create table Personal_Details(
username VARCHAR(255) not NULL,
mobile_number NUMBER(10) Primary key not NULL,
email VARCHAR(255) not NULL);

-- wallet details table has mobile number of personal deatils
create table Wallet_Details(
mobile_number number(10) not NULL,
upi VARCHAR(255) primary key not NULL,
wallet_type VARCHAR(255) not NULL
)

--recharge details table has the upi id of the wallet and linked to personal details
create table Recharge_Details(
upi VARCHAR(255) primary key,
recharged_date DATE not NULL,
recharged_provider VARCHAR(255) not NULL,
recharged_to VARCHAR(255) not NULL,
recharged_amount NUMBER(10) not NULL
)


--wallet details has mobile nuber of personal details
alter table Wallet_Details add foreign key(mobile_number) references Personal_Details(mobile_number);

--recharge details has upi of wallet details
alter table Recharge_Details add foreign key(upi) references  Wallet_Details(upi);
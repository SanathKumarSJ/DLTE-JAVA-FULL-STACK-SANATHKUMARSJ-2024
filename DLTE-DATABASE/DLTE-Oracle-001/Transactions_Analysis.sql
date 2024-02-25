--Creating table Transactions_Analysis--
create table Transactions_Analysis(transaction_id number primary key, transaction_date date not null, transaction_to varchar(255) not null, transaction_amount number not null, transaction_remarks varchar(255) not null);


--Adding Sequence transaction_seque --
create sequence transaction_seque start with 2458200 increment by 1;


-- Inserting Records--
insert into Transactions_Analysis(transaction_id,transaction_amount,transaction_date,transaction_to,transaction_remarks) values(transaction_seque.nextval,15000,'24-feb-2024','Rohith','Friend');

--1 row created.--

insert into Transactions_Analysis(transaction_id,transaction_amount,transaction_date,transaction_to,transaction_remarks) values(transaction_seque.nextval,69000,'27-feb-2024','Virat','Relative');

--1 row created.--

insert into Transactions_Analysis(transaction_id,transaction_amount,transaction_date,transaction_to,transaction_remarks) values(transaction_seque.nextval,7840,'02-mar-2024','Rishab','UPI');

--1 row created.--

insert into Transactions_Analysis(transaction_id,transaction_amount,transaction_date,transaction_to,transaction_remarks) values(transaction_seque.nextval,98740,'05-mar-2024','Kumar','emergency');

--1 row created.--

-- Filtering Transaction details in the given transaction_date range--
select * from Transactions_Analysis where transaction_date between '21-feb-2024' and '03-mar-2024';


-- Finding Least Transaction Amount--
select min(transaction_amount) from Transactions_Analysis;

-- Finding Highest Transaction Amount--
select max(transaction_amount) from Transactions_Analysis;

--Number transaction made to particular beneficiary--
select count(transaction_to) from Transactions_Analysis where transaction_to='Rohith';

--Filtering Transaction Details based on remarks--
select * from Transactions_Analysis where transaction_remarks='emergency';
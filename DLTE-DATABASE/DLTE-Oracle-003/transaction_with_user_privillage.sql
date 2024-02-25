--User1 -> Select 
create user sanath identified by sksj;

--User created.

grant connect to sanath;

connect sanath/sksj;

--Connected.

--Grant succeeded.

grant select on TRANSACTIONS_ANALYSIS to sanath;

--Grant succeeded.

Enter user-name: sanath
Enter password:

--Connected to:
--Oracle Database 11g Express Edition Release 11.2.0.2.0 - Production

connect sanath/sksj;
--Connected.


alter session set current_schema=system;

--Session altered.



select * from Transactions_Analysis;


-- ......................................................
--User2 -> DELETE

create user rohitdelte identified by hitman;

--User created.

grant connect to rohitdelte;

--Grant succeeded.

SQL> grant delete on TRANSACTIONS_ANALYSIS to rohitdelte;

--Grant succeeded.


Enter user-name: rohitdelte
Enter password:

--Connected to:
--Oracle Database 11g Express Edition Release 11.2.0.2.0 - Production


SQL> connect rohitdelte/hitman;

--Connected.

SQL> alter session set current_schema=system;

--Session altered.

SQL> delete from TRANSACTIONS_ANALYSIS where TRANSACTION_ID=2458225;

--1 row deleted.

SQL> commit;

--Commit complete.

-- ..............................................
--User3 -> Select 
create user manoj identified by mkmj;

--User created.

grant connect to manoj;

--Grant succeeded.

grant select on TRANSACTIONS_ANALYSIS to manoj;

--Grant succeeded.

Enter user-name: manoj
Enter password:

--Connected to:
--Oracle Database 11g Express Edition Release 11.2.0.2.0 - Production


connect manoj/mkmj;
--Connected.

alter session set current_schema=system;

--Session altered.

select * from Transaction_2024;

-- ..........................................................

--User4 Inser
create user dhanush identified by koti;

--User created.

grant connect to dhanush;

--Grant succeeded.

grant insert on TRANSACTIONS_ANALYSIS to dhanush;

--Grant succeeded.

Enter user-name: dhanush
Enter password:

--Connected to:
--Oracle Database 11g Express Edition Release 11.2.0.2.0 - Production


connect dhanush/koti;
--Connected.

alter session set current_schema=system;

--Session altered.

insert into Transactions_Analysis(transaction_id,transaction_amount,transaction_date,transaction_to,transaction_remarks) values(transaction_seque.nextval,9888,'8-mar-2024','dhanusha','emergency');


-- .................................................................................
-- User 5
create user ram identified by god;

--User created.

grant connect to ram;

--Grant succeeded.

alter session set current_schema=system;

--Session altered.

 grant update on Transactions_Analysis to ram;

--Grant succeeded.

alter session set current_schema=system;

--Session altered.

SQL> quit

Enter user-name: ram
Enter password:


--Oracle Database 11g Express Edition Release 11.2.0.2.0 - Production

alter session set current_schema=system;

--Session altered.

--SQL> update Transactions_Analysis set TRANSACTION_AMOUNT=TRANSACTION_AMOUNT+10000 where TRANSACTION_ID=2458223;

--1 row updated.

SQL> commit;

--Commit complete.


















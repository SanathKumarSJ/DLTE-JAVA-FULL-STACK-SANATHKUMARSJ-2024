create table transactions(transaction_doneBy varchar(255), transaction_type varchar(255), transaction_amount number, transaction_date date);
alter table transactions add FOREIGN key(transaction_doneBy) references mybank_users(username);
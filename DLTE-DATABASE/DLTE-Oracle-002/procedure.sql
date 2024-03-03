--PROCEDURES INSERTION---
create or replace procedure insert_transactiondata(
	   transaction_amount number,
	   transaction_date date,
      transaction_id number,
      transaction_to varchar2,
      transaction_remark varchar2,
      proced_operation out varchar2
    )
as
begin 
--insering values
 insert into Transactions_Analysis(transaction_id,transaction_amount,transaction_to,transaction_date,transaction_remarks) values(transaction_id,transaction_amount,transaction_date,transaction_to,transaction_remark);
 proced_operation:='Done';
exception 
when OTHERS then 
proced_operation:='Not Done'||SQLERRM;
end;
/

variable proced_operation varchar2;
--execution
execute insert_transactiondata(TRANSACTION_SEQ.nextval,75000,'Rohith','12-FEB-2026','Family',:proced_operation);
print proced_operation;


---PROCEDURE TO DELETE TRANSACTION GIVEN TO----
create or replace procedure delete_to(
  input_to varchar2, --input transaction_to
  error_info out varchar
  )
  as
  begin 
   delete from Transactions_Analysis where transaction_to=input_to;
   error_info:='no error';
  exception
   when OTHERS then
   error_info:='error -'||SQLERRM;
end;
/

variable error_info varchar2;
execute delete_to('Rohith',:error_info);

--Filtering on Education remarks

create or replace procedure filter_remark(
   input_remark varchar2, -- input remarks i.e "Education"
   error2_info out varchar2   
)
as 
 begin select * from Transactions_Analysis where transaction_remarks=input_remark;
 error2_info:='Done';
 exception
  when no_data_found then
  error2_info:='No data found';
  when others then
  error2_info:='error --'||SQLERRM;
end;
/

variable error2_info varchar2;
execute filter_remark('Education',:error2_info);
print input_remark;
print error2_info;
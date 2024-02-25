trigger check_transaction
before insert on TRANSACTIONS_ANALYSIS
for each row
begin
  if:new.transaction_remarks is null or new.transaction_remarks='' then :new.transaction_remarks:='other';
  end if;
end;

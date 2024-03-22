package com.spring.account.jpa.remote;

import com.spring.account.jpa.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
@Repository
public interface AccountInterface extends CrudRepository<Account,Long> {


}

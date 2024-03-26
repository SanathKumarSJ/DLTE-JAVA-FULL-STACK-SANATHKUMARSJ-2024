package com.jpa.hqlsql.jpahqlsql.repository;

import com.jpa.hqlsql.jpahqlsql.entity.Transaction_JPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.sound.midi.Track;
import java.util.List;

@Repository
public interface MyJPARepo extends JpaRepository<Transaction_JPA,Long> {

    //SQL to find transaction based on given name and type range
    @Query(value ="select * from Transaction_JPA where transaction_By=:username and transaction_Type=:transactiontype",nativeQuery = true)
    List<Transaction_JPA> transactionType(String username,String transactiontype);


    //HQL to find transaction amount in the given range
    @Query("from Transaction_JPA where transactionAmount between :startAmount and :endAmount")
    List<Transaction_JPA> amountRange(Double startAmount, Double endAmount);

}

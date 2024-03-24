package com.jpa.hqlsql.jpahqlsql.repository;

import com.jpa.hqlsql.jpahqlsql.entity.Transaction_JPA;
import org.springframework.data.repository.CrudRepository;

public interface MyRepoNew extends CrudRepository<Transaction_JPA,Long> {
}

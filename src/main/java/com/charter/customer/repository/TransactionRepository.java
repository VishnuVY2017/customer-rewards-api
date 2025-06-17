package com.charter.customer.repository;

import com.charter.customer.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT t FROM Transaction t where t.customer.cid =?1 and t.dateTime between ?2 AND ?3")
    Set<Transaction> getMonthlyCustomerPoints(Long cid, LocalDateTime startDate, LocalDateTime endDate);

}

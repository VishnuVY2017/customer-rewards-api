package com.charter.customer.service;

import com.charter.customer.entity.Customer;
import com.charter.customer.entity.Transaction;
import com.charter.customer.repository.CustomerRepository;
import com.charter.customer.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Transaction addCustomerTransaction(Transaction transaction, Long cid) {
        Transaction trans = null;
        if (cid == null || cid == 0) {
            throw new RuntimeException("customer id not found.");
        } else {
            if (transaction != null && transaction.getTotal() > 0) {
                Optional<Customer> customer = customerRepository.findById(cid);
                if (customer.isPresent()) {
                    transaction.setCustomer(customer.get());
                    transaction.setDateTime(LocalDateTime.now());
                    trans = transactionRepository.save(transaction);
                } else {
                    throw new RuntimeException("Customer id not found");
                }
            }
        }

        return trans;
    }

}

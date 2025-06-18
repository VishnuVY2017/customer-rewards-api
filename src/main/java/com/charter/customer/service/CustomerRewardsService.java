package com.charter.customer.service;


import com.charter.customer.entity.Customer;
import com.charter.customer.entity.Transaction;
import com.charter.customer.repository.CustomerRepository;
import com.charter.customer.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class CustomerRewardsService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long cid) {
        if (cid == 0) {
            throw new RuntimeException("invalid customer id provided!");
        } else {
            Optional<Customer> customer = customerRepository.findById(cid);
            if (customer.isPresent()) {
                return customer.get();
            } else {
                throw new RuntimeException("customer data not found");
            }
        }
    }

    public Customer addCustomer(Customer customer){
        if(customer != null){
            if(!CollectionUtils.isEmpty(customer.getTransactions())){
                throw new RuntimeException("you can not add transactions while adding the customer.");
            }
            return customerRepository.save(customer);
        }else{
            throw new RuntimeException("invalid customer data provided.");
        }
    }

    public Customer getMonthlyCustomerPoints(Long cid, Integer byMonth) {
        if (cid == 0 && byMonth == 0) {
            throw new RuntimeException("invalid customer id and month number provided.");
        } else {
            Customer customerObject = null;
            Optional<Customer> customer = customerRepository.findById(cid);
            if(customer.isPresent()){
                Date dd = new Date();
                Calendar cc = Calendar.getInstance();
                cc.setTime(dd);
                cc.add(Calendar.MONTH, -byMonth);
                Date lastMonth = cc.getTime();
                LocalDateTime startDate = convertToLocalDateTimeViaInstant(lastMonth);
                LocalDateTime endDate = LocalDateTime.now();

                customerObject = customer.get();
                Set<Transaction> transactions = transactionRepository.getMonthlyCustomerPoints(customerObject.getCid(), startDate, endDate);
                customerObject.setTransactions(transactions);
            }else{
                throw new RuntimeException("customer data not found");
            }
            return customerObject;
        }

    }

    private LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}

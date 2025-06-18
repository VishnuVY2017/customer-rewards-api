package com.charter.customer.service;

import com.charter.customer.entity.Customer;
import com.charter.customer.entity.Transaction;
import com.charter.customer.repository.CustomerRepository;
import com.charter.customer.repository.TransactionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    TransactionRepository transactionRepository;

    @DisplayName("for test addCustomer Transaction when id is wrong")
    @Test
    void addCustomerTransaction_failed() {
        LocalDateTime ltd = LocalDateTime.now();
        Transaction transaction = new Transaction();
        transaction.setTotal(10d);
        transaction.setDateTime(ltd);
        transaction.setDescription("xyz");
        Set<Transaction> s = new HashSet<>();
        s.add(transaction);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            when(customerRepository.findById(0l)).thenThrow(RuntimeException.class);
            transactionService.addCustomerTransaction(transaction, 0L);
        });

        String expectedMessage = "customer id not found.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));

    }

    @DisplayName("for test addCustomer Transaction when id is valid")
    @Test
    void addCustomerTransaction_success() {
        LocalDateTime ltd = LocalDateTime.now();
        Transaction transaction = new Transaction();
        transaction.setTotal(90d);
        transaction.setDateTime(ltd);
        transaction.setDescription("xyz");
        Set<Transaction> s = new HashSet<>();
        s.add(transaction);

        Customer customer = new Customer(1L, "vishnu", null, 90L, 120d);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(transactionRepository.save(any())).thenReturn(transaction);
        Transaction transaction1 = transactionService.addCustomerTransaction(transaction, 1L);

        assertTrue(transaction1.getTotal() == 90);
    }
}
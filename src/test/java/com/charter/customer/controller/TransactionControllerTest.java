package com.charter.customer.controller;

import com.charter.customer.entity.Transaction;
import com.charter.customer.service.TransactionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionControllerTest {

    @InjectMocks
    TransactionController transactionController;

    @Mock
    TransactionService transactionService;

    @DisplayName("test for test addCustomerTrasaction method ")
    @Test
    public void test_addCustomerTrasaction() {

        LocalDateTime ltd = LocalDateTime.now();
        Transaction transaction = new Transaction();
        transaction.setTotal(10d);
        transaction.setDateTime(ltd);
        transaction.setDescription("xyz");

        when(transactionService.addCustomerTransaction(transaction, 1l)).thenReturn(transaction);

        ResponseEntity<Transaction> result =transactionController.addCustomerTransaction(transaction, 1l);

        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getBody().getTotal()).isEqualTo(10d);
    }
}
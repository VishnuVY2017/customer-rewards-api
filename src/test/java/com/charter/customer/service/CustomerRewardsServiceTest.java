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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerRewardsServiceTest {

    @InjectMocks
    CustomerRewardsService customerRewardsService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    TransactionRepository transactionRepository;

    @DisplayName("test for get all customer")
    @Test
    public void get_all_customer__success() throws Exception {

        Customer customer1 = new Customer(1L, "vishnu", null, 1L, 1d);
        Customer customer2 = new Customer(2L, "sharma", null, 2L, 5d);
        Customer customer3 = new Customer(3L, "mistry", null, 3L, 10d);

        List<Customer> customersList = Arrays.asList(customer1, customer2, customer3);

        when(customerRepository.findAll()).thenReturn(customersList);

        List<Customer> result = customerRewardsService.getAllCustomers();

        assertThat(result.get(1).getName()).isEqualTo("sharma");
    }

    @DisplayName("test for get all customer when you pass id")
    @Test
    public void get_customer_when_you_pass_valid_id_success() throws Exception {

        Customer customer = new Customer(1L, "vishnu", null, 1L, 1d);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();
        Set<Transaction> transactions = transactionRepository.getMonthlyCustomerPoints(1L, startDate, endDate );

        assertThat(transactions.size() == 1);
    }
}
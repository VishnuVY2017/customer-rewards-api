package com.charter.customer.controller;

import com.charter.customer.entity.Customer;
import com.charter.customer.entity.Transaction;
import com.charter.customer.service.CustomerRewardsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomersRewardControllerTest {


    @InjectMocks
    CustomerRewardsController customerRewardsController;

    @Mock
    CustomerRewardsService customerRewardsService;

    Customer customer1 = new Customer(1L, "vishnu", null, 1L, 1d);
    Customer customer2 = new Customer(2L, "sharma", null, 2L, 5d);
    Customer customer3 = new Customer(3L, "mistry", null, 3L, 10d);


    @DisplayName("test for getAllCustomers method ")
    @Test
    public void test_getAllCustomers_success() {

        List<Customer> customersList = Arrays.asList(customer1, customer2, customer3);

        when(customerRewardsService.getAllCustomers()).thenReturn(customersList);

        ResponseEntity<List<Customer>> result = customerRewardsController.getAllCustomers();

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().get(0).getName()).isEqualTo("vishnu");

    }

    @DisplayName("test for getCustomers by id ")
    @Test
    public void test_getCustomer_by_id_success() throws Exception  {

        LocalDateTime ltd = LocalDateTime.now();
        Transaction t = new Transaction( );
        t.setDescription("xyz");
        t.setTotal(2d);
        t.setDateTime(ltd);

        Long id = 1l;

        Set<Transaction> tlist = new HashSet<>();

        Customer customer1 = new Customer(1l, "vishnu", tlist, 1l, 1d);

        when(customerRewardsService.getCustomerById(id)).thenReturn(customer1);

        ResponseEntity<Customer> result = customerRewardsController.getAllCustomers(id);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo("vishnu");

    }

    @DisplayName("JUnit test for getAllEmployees method (negative scenario)")
    @Test
    public void getAllEmployees_fail(){

        when(customerRewardsService.getAllCustomers()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Customer>> customerList = customerRewardsController.getAllCustomers();

        assertThat(customerList).isNotNull();
        assertThat(customerList.getBody().size()).isEqualTo(0);
    }


    @DisplayName("JUnit test for getEmployees method (negative scenario)")
    @Test
    public void getEmployees_fail() throws Exception{

        Customer c = new Customer();
        when(customerRewardsService.getCustomerById(1l)).thenReturn(c);

        ResponseEntity<Customer> customer = customerRewardsController.getAllCustomers(1L);

        assertThat(customer).isNotNull();
        assertThat(customer.getBody()).isEqualTo(c);
    }

}
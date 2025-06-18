package com.charter.customer.controller;

import com.charter.customer.entity.Customer;
import com.charter.customer.service.CustomerRewardsService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/rewards")
public class CustomerRewardsController {

    @Autowired
    CustomerRewardsService customerRewardsService;

    @GetMapping("")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerRewardsService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{cid}")
    public ResponseEntity<Customer> getAllCustomers(@PathVariable Long cid) {
        return new ResponseEntity<>(customerRewardsService.getCustomerById(cid), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid Customer customer){
       return new ResponseEntity<>(customerRewardsService.addCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/month/{monthNumber}")
    public ResponseEntity<Customer> getMonthlyCustomerPoints(@Valid @NotNull @Range(min = 1, max = 999999999) @PathVariable Long id, @Valid @NotNull @Range(min = 1, max = 12) @PathVariable int monthNumber) {
        return new ResponseEntity<>(customerRewardsService.getMonthlyCustomerPoints(id, monthNumber), HttpStatus.OK);
    }

}

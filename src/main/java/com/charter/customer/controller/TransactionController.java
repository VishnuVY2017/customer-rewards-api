package com.charter.customer.controller;

import com.charter.customer.entity.Transaction;
import com.charter.customer.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("customer/{cid}")
    public ResponseEntity<Transaction> addCustomerTransaction(@RequestBody @Valid Transaction transaction, @PathVariable @Valid @NotNull Long cid) {

        return new ResponseEntity<>(transactionService.addCustomerTransaction(transaction, cid), HttpStatus.CREATED);
    }
}

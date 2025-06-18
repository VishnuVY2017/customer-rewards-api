package com.charter.customer.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CID")
    private Long cid;

    @NotBlank(message = "customer name should not be blank!")
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private Long rewardPoints;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private Double totalAmount;

    public Customer(){}
    public Customer(Long cid, String name, Set<Transaction> transactions, Long rewardPoints, Double totalAmount) {
        this.cid = cid;
        this.name = name;
        this.transactions = transactions;
        this.rewardPoints = rewardPoints;
        this.totalAmount = totalAmount;
    }

    public Long getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions != null ? transactions : Collections.emptySet();
        for (Transaction transaction : this.transactions) {
            transaction.setCustomer(this);
        }
    }

    public Long getRewardPoints() {
        if (transactions == null || transactions.isEmpty()) {
            return 0L;
        }
        return transactions.stream().map(Transaction::getPoints).reduce(0L, Long::sum);
    }

    public void setRewardPoints(Long rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public Double getTotalAmount() {
        if (transactions == null || transactions.isEmpty()) {
            return 0d;
        }
        return transactions.stream().map(Transaction::getTotal).reduce(0d, Double::sum);
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
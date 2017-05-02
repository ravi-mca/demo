package com.favendo.customer.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favendo.commons.domain.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Long> {

    @Query("select customer " +
            "from Customer customer join fetch customer.customerUsers as customerUsers " +
            "ORDER BY customer.name ASC ")
    List<Customer> findAll();
    
    @Query("select customer " +
            "from  Customer customer join fetch customer.customerUsers as customerUsers " +
            "where customer.customerId = :customerId ")
    Customer findById(@Param("customerId") Long customerId);
}

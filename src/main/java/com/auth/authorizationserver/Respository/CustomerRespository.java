package com.auth.authorizationserver.Respository;

import com.auth.authorizationserver.Models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRespository  extends JpaRepository<Customers,Integer> {


    @Query(value = "select * from customers  where  customer_email=?1", nativeQuery = true)
    public Optional<Customers> findCustomerBYEmail(String  email);
}

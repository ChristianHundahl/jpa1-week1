package com.example.jpa1week1.repository;

import com.example.jpa1week1.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //Annoterer
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.save(new Customer("a", "b", "c@c.dk"));
        customerRepository.save(new Customer("z", "y", "z@z.dk"));
        customerRepository.save(new Customer("å", "æ", "ø@ø.dk"));
    }
}
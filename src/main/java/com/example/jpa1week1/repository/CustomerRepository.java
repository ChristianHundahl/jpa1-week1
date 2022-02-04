package com.example.jpa1week1.repository;

import com.example.jpa1week1.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {//Parametre= objekt og primærnøgle



}

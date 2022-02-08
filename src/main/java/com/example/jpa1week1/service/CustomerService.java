package com.example.jpa1week1.service;

import com.example.jpa1week1.dto.CustomerResponseDto;
import com.example.jpa1week1.entity.Customer;
import com.example.jpa1week1.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {//Auto-generated via generate->constructor
        this.customerRepository = customerRepository;
    }

    public CustomerResponseDto getCustomer(int id) throws Exception{
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new Exception ("Not found"));
        return new CustomerResponseDto(customer);
    }

    public List<CustomerResponseDto> getCustomers() {
        //Two lines:
        List<Customer> customers = customerRepository.findAll();
        return CustomerResponseDto.getList(customers);
    }

    public CustomerResponseDto addCustomer(CustomerResponseDto body) {
        //'body' = response body, what is returned
        Customer customer = customerRepository.save(new Customer(body));
        return new CustomerResponseDto(customer);
    }

    public void deleteCustomer(int id) throws Exception {
        if(!customerRepository.existsById(id)) {
            throw new Exception("Not found");
        }
        customerRepository.deleteById(id);
    }
}

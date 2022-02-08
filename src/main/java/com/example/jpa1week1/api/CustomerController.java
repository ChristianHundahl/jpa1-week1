package com.example.jpa1week1.api;

import com.example.jpa1week1.dto.CustomerResponseDto;
import com.example.jpa1week1.entity.Customer;
import com.example.jpa1week1.repository.CustomerRepository;
import com.example.jpa1week1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer") //All in this class gets api/customer as part of URL
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping//Arrive here with GET request
    public List<CustomerResponseDto> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}") //Appended to end of api/customer, as defined by @RequestMapping
    public CustomerResponseDto getCustomer(@PathVariable int id) throws Exception {
        return customerService.getCustomer(id);
        //return customerRepository.findById(id).orElseThrow(() -> new Exception("Not found")); //fixme '()->' is a  lambda, look it up
        //Alternative: getById needs no exception thrown
    }

    @PostMapping//Arrive here with POST request
    public CustomerResponseDto addCustomer(@RequestBody CustomerResponseDto body) {
        //Takes JSON
        return customerService.addCustomer(body);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) throws Exception{
        customerService.deleteCustomer(id);
    }
}


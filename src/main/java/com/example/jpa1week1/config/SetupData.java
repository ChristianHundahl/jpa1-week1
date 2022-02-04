package com.example.jpa1week1.config;

import com.example.jpa1week1.entity.Customer;
import com.example.jpa1week1.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller //Spring annotation - kan undgå at køre 'run' metode hvis denne udkommenteres
public class SetupData implements CommandLineRunner {

    CustomerRepository customerRepository;

    public SetupData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        customerRepository.save(new Customer("Peter", "Olsen", "a@b.dk"));
        customerRepository.save(new Customer("Hanne", "Olsen", "a1@b.dk"));

        Customer c3 = new Customer("Hanne", "Olsen", "a2@b.dk");
        System.out.println(c3.getId());
        customerRepository.save(c3); //Managed objektet håndteret at Entity framework
        System.out.println(c3.getId());
    }
}

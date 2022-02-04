package com.example.jpa1week1.repository;

import com.example.jpa1week1.entity.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //Annoterer at default metode skal ændres, og kører automatisk tests i en in-memory database (transaktionelle tests)
    //Se: https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/orm/jpa/DataJpaTest.html
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    static int customerId1, customerId2, customerId3;

    @BeforeAll //Viser hvornår følgende kode skal genereres ('BeforeAll' = før nogen tests køres)
    //'BeforeAll' skal bruges en statisk metode
    static void setUp(@Autowired CustomerRepository customerRepository) {
        Customer c1 = customerRepository.save(new Customer("a", "b", "c@c.dk"));
        customerId1 = c1.getId();
        Customer c2 = customerRepository.save(new Customer("z", "y", "z@z.dk"));
        customerId2 = c2.getId();
        Customer c3 = customerRepository.save(new Customer("å", "æ", "ø@ø.dk"));
        customerId3 = c3.getId();
    }

    //Hvis bare én test fejler kan projektet ikke bygges
    @Test
    public void testAddCustomer() {
        Customer customerNew = customerRepository.save(new Customer("x", "q", "xx@xx.dk"));
        assertNotEquals(0, customerNew.getId());
    }

    @Test //Annoter for at fortælle J-unit at dette er en test så der f.eks. kan håndteres fejltest senere
    public void testCount() {
        assertEquals(3, customerRepository.count());
    }

    @Test
    public void testFindById(){
        Customer cFound = customerRepository.findById(customerId1).orElse(null);
        assertEquals("c@c.dk", cFound.getEmail());
    }

}
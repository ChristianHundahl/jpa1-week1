package com.example.jpa1week1.dto;

import com.example.jpa1week1.entity.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//These annotations auto-create getters+setters via Lombok
@Getter
@Setter
@NoArgsConstructor //Empty constructor included via Lombok
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponseDto {
    //These are the fields we want to include in the JSON output:
    int id;
    String firsName;
    String lastName;
    String email;
    String secret1;

    public CustomerResponseDto(Customer c) {
        //Controls what is returned as dto
        this.id = c.getId();
        this.lastName = c.getLastName();
        this.firsName = c.getFirstName();
        this.email = c.getEmail();
    }

    public static List<CustomerResponseDto> getList(List<Customer> customerEntities){
        //Shortened, modern version:
        return customerEntities.stream().map(CustomerResponseDto::new).collect(Collectors.toList());
        //Using lambda: return customerEntities.stream().map((customer)-> new CustomerResponseDto(customer)).collect(Collectors.toList());

        /*Old-school: Iterates over a list and adds to another list
        List<CustomerResponseDto> dtos = new ArrayList<>();
        for(Customer c : customerEntities){
            dtos.add(new CustomerResponseDto(c));
        }
        return dtos;*/
    }
}

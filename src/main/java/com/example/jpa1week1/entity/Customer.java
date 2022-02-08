package com.example.jpa1week1.entity;

import com.example.jpa1week1.dto.CustomerResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "kunde") //omdøber den fra koden genererede tabel fra 'Customer' til 'kunde'
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Kan også bruge 'AUTO' --> laver en ekstra tabel med næste id.
    // Da vi har db der kan håndtere dette uden ekstra tabel i MySQL er der ingen grund.
    // I Hibernate kan AUTO være en bedre løsning pga. hvordan hibernate er opbygget
    int id;

    @Column(name="fornavn", nullable = false) //omdøber vores kolonne 'firstName' til 'fornavn', sætter som not_null
    //Nullable er default i JPA framework
    private String firstName;

    @Column(name="efternavn",length = 60, nullable = false)
    private String lastName;

    @Column(name="mail",length = 120,nullable = false,unique = true)
    private String email;

    @Column(name="oprettet")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "rettet")
    @UpdateTimestamp
    private LocalDateTime dateEdited;

    @Column(name = "hemmelighed1")
    //@JsonIgnore - can be used to ignore a field when returned as JSON
    private String secret1;

    @Column(name = "hemmelighed2")
    private String secret2;

    public Customer(CustomerResponseDto body) {
        this.firstName = body.getFirsName();
        this.lastName = body.getLastName();
        this.email = body.getEmail();
        this.secret1 = "Hemmeligt1";
        this.secret2 = "Hemmeligt2";
    }

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.secret1 = "Hemmeligt1";
        this.secret2 = "Hemmeligt2";
    }

    public Customer() {}

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateEdited() {
        return dateEdited;
    }

    public String getSecret1() {
        return secret1;
    }

    public String getSecret2() {
        return secret2;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateEdited=" + dateEdited +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(email, customer.email) && Objects.equals(dateCreated, customer.dateCreated) && Objects.equals(dateEdited, customer.dateEdited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, dateCreated, dateEdited);
    }
}

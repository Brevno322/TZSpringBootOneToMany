package com.mukhaArturTZ.TZSpringBootOneToMany.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "name_phone_book", referencedColumnName = "name")
    @NotNull
    private PhoneBook namePhoneBook;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @NotEmpty(groups = Integer.class)
    @Column(name = "age")
    private int age;

    public Contact() {
    }

    public Contact(PhoneBook namePhoneBook, String name, String city, int age) {
        this.namePhoneBook = namePhoneBook;
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PhoneBook getNamePhoneBook() {
        return namePhoneBook;
    }

    public void setNamePhoneBook(PhoneBook namePhoneBook) {
        this.namePhoneBook = namePhoneBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

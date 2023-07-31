package com.mukhaArturTZ.TZSpringBootOneToMany.dto;

import com.mukhaArturTZ.TZSpringBootOneToMany.model.PhoneBook;
import jakarta.validation.constraints.NotEmpty;

public class ContactDTO {

    @NotEmpty
    private PhoneBook namePhoneBook;


    @NotEmpty
    private String name;


    @NotEmpty
    private String city;

    @NotEmpty
    private int age;

    public ContactDTO() {
    }

    public ContactDTO(PhoneBook namePhoneBook, String name, String city, int age) {
        this.namePhoneBook = namePhoneBook;
        this.name = name;
        this.city = city;
        this.age = age;
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

package com.mukhaArturTZ.TZSpringBootOneToMany.dto;

import com.mukhaArturTZ.TZSpringBootOneToMany.model.Person;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



public class PhoneBookDTO {

    @NotEmpty
    @Size(min = 2, max = 40, message = "Название телефонной книги должно быть не менее 2 и не более 40 символов")
    private String name;

    @NotNull
    private Person person;

    public PhoneBookDTO() {
    }

    public PhoneBookDTO(String name, Person person) {
        this.name = name;

        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

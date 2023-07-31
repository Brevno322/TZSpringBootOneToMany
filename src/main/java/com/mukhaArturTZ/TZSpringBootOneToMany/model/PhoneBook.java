package com.mukhaArturTZ.TZSpringBootOneToMany.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "phone_book")
public class PhoneBook implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty
    @Size(min = 2,max = 40,message = "Название телефонной книги должно быть не менее 2 и не более 40 символов")
    private String name;

    @Column(name = "create_data")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "name_person",referencedColumnName = "name")
    @NotNull
    private Person person;

    public PhoneBook() {
    }

    public PhoneBook(String name, LocalDate date, Person person) {
        this.name = name;
        this.date = date;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

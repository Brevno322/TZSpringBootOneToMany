package com.mukhaArturTZ.TZSpringBootOneToMany.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;


@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty
    @Size(min =2 ,max = 40,message ="Имя человека не должно быть менее 2 и не более 40 символов " )
    private String name;

    @NotEmpty(groups = Integer.class)
    @Column(name = "age")
    @Min(value = 1,message = "Возраст челоке не должен быть меньше 1 года")
    private int age;

    @NotEmpty
    @Column(name = "city")
    @Size(min = 4,max = 40,message = "Название городо должно быть не менее 4 и не более 40 символов")
    private String city;

    @Column(name = "phone_number")
    @NotEmpty
    @NumberFormat(pattern = "^\\+?\\d{1,3}[-.\\s]?\\(?\\d{1,3}\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$")
    private String phoneNumber;

    public Person() {
    }

    public Person(String name, String city, int age,String phoneNumber) {
        this.name = name;
        this.city = city;
        this.age = age;
        this.phoneNumber=phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

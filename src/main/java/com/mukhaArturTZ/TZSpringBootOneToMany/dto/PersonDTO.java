package com.mukhaArturTZ.TZSpringBootOneToMany.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class PersonDTO {


    @NotEmpty
    @Size(min =2 ,max = 40,message ="Имя человека не должно быть менее 2 и не более 40 символов " )
    private String name;

    @NotEmpty(groups = Integer.class)
    @Min(value = 1,message = "Возраст челоке не должен быть меньше 1 года")
    private int age;

    @NotEmpty
    @Size(min = 4,max = 40,message = "Название городо должно быть не менее 4 и не более 40 символов")
    private String city;

    @NotEmpty
    private String phoneNumber;

    public PersonDTO(String name, int age, String city, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public PersonDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

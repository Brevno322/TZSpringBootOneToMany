package com.mukhaArturTZ.TZSpringBootOneToMany.util.PersonValid;

import com.mukhaArturTZ.TZSpringBootOneToMany.model.Person;
import com.mukhaArturTZ.TZSpringBootOneToMany.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidate implements Validator {
    private final PersonService personService;

    @Autowired
    public PersonValidate(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (person.getName().length() > 40 || person.getName().length() < 2) {
            errors.rejectValue("name", "Имя должно состоять из 2 - 40 символов");
        }
        if (person.getAge() < 1) {
            errors.rejectValue("age", "Возраст челоке не должен быть меньше 1 года");
        }
        if (person.getCity().length() > 40 || person.getCity().length() < 2) {
            errors.rejectValue("city", "Название городо должно быть не менее 4 и не более 40 символов");
        }
    }
}

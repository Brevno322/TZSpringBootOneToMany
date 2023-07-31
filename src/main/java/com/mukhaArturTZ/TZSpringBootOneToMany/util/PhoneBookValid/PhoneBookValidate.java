package com.mukhaArturTZ.TZSpringBootOneToMany.util.PhoneBookValid;

import com.mukhaArturTZ.TZSpringBootOneToMany.model.PhoneBook;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PhoneBookValidate implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PhoneBook.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PhoneBook phoneBook = (PhoneBook) target;
        if (phoneBook.getName().length() < 2 || phoneBook.getName().length() > 40) {
            errors.rejectValue("name", "Название телефонной книги должно быть не менее 2 и не более 40 символов");
        }

    }
}

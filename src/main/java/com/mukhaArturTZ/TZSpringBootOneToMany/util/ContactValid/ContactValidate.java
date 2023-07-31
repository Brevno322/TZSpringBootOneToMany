package com.mukhaArturTZ.TZSpringBootOneToMany.util.ContactValid;

import com.mukhaArturTZ.TZSpringBootOneToMany.model.Contact;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ContactValidate implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Contact.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Contact contact = (Contact) target;
        if (contact.getName().length() > 40 || contact.getName().length() < 2) {
            errors.rejectValue("name", "Название контакта должно состоять от 2 до 40 символов");
        }
    }
}

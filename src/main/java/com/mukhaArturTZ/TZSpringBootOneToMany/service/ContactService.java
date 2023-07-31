package com.mukhaArturTZ.TZSpringBootOneToMany.service;

import com.mukhaArturTZ.TZSpringBootOneToMany.model.Contact;
import com.mukhaArturTZ.TZSpringBootOneToMany.repositories.ContactRepository;
import com.mukhaArturTZ.TZSpringBootOneToMany.repositories.PhoneBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ContactService {

    private final ContactRepository contactRepository;
    private final PhoneBookRepository phoneBookRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository, PhoneBookRepository phoneBookRepository) {
        this.contactRepository = contactRepository;
        this.phoneBookRepository = phoneBookRepository;
    }

    @Transactional
    public void save(Contact contact) {
        contact.setNamePhoneBook(phoneBookRepository.findPhoneBookByName(contact.getNamePhoneBook().getName()));
        contactRepository.save(contact);
    }

    public Contact getOneContact(int id){
        return contactRepository.findById(id).orElse(null);
    }

    public List<Contact> getAllContact(){
        return contactRepository.findAll();
    }
    @Transactional
    public void deleteContact(int id ){
        contactRepository.deleteById(id);
    }
    @Transactional
    public void updateContact(int id,Contact contact){
        Contact updateContact=contactRepository.findById(id).orElse(null);
        updateContact.setName(contact.getName());
        updateContact.setCity(contact.getCity());
        updateContact.setAge(contact.getAge());
        updateContact.setNamePhoneBook(phoneBookRepository.findPhoneBookByName(contact.getNamePhoneBook().getName()));
        contactRepository.save(updateContact);
    }


}

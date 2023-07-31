package com.mukhaArturTZ.TZSpringBootOneToMany.service;

import com.mukhaArturTZ.TZSpringBootOneToMany.model.PhoneBook;
import com.mukhaArturTZ.TZSpringBootOneToMany.repositories.PersonRepository;
import com.mukhaArturTZ.TZSpringBootOneToMany.repositories.PhoneBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PhoneBookService {

    private final PhoneBookRepository phoneBookRepository;
    private final PersonService personService;

    @Autowired
    public PhoneBookService(PhoneBookRepository phoneBookRepository, PersonRepository personRepository, PersonService personRepository1, PersonService personService) {
        this.phoneBookRepository = phoneBookRepository;

        this.personService = personService;
    }

    @Transactional
    public void save(PhoneBook phoneBook) {
        phoneBook.setDate(LocalDate.now());
        phoneBook.setPerson(personService.findByName(phoneBook.getPerson().getName()));
        phoneBookRepository.save(phoneBook);
    }

    public List<PhoneBook> getAll() {
        return phoneBookRepository.findAll();
    }

    public PhoneBook getOnePhoneBook(int id){
        return phoneBookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updatePhoneBook(int id,PhoneBook updatePhoneBook){
        updatePhoneBook.setPerson(personService.findByName(updatePhoneBook.getPerson().getName()));
        PhoneBook phoneBook=getOnePhoneBook(id);
        if (phoneBook!=null){
            phoneBook.setName(updatePhoneBook.getName());
            phoneBook.setPerson(updatePhoneBook.getPerson());
            phoneBook.setDate(LocalDate.now());
            phoneBookRepository.save(phoneBook);
        }
        else {
            return;
        }
    }

    @Transactional
    public void deletePhoneBook(int id){
        phoneBookRepository.deleteById(id);
    }
}

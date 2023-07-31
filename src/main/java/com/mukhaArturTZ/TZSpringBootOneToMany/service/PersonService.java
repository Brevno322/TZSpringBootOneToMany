package com.mukhaArturTZ.TZSpringBootOneToMany.service;

import com.mukhaArturTZ.TZSpringBootOneToMany.model.Person;
import com.mukhaArturTZ.TZSpringBootOneToMany.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void savePerson(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public Person getOnePerson(int id){
       Optional<Person> optional=personRepository.findById(id);
       return optional.orElse(null);
    }

    public List<Person> getAllPerson(){
      return personRepository.findAll();
    }

    @Transactional
    public void updatePerson(int id,Person updatePerson){
        Person person=getOnePerson(id);

        if (person!=null){
            person.setAge(updatePerson.getAge());
            person.setName(updatePerson.getName());
            person.setCity(updatePerson.getCity());
            person.setPhoneNumber(updatePerson.getPhoneNumber());
            personRepository.save(person);
        }
        else {
            return;
        }
    }

    public Person findByName(String name){
        return personRepository.findByName(name);
    }
}

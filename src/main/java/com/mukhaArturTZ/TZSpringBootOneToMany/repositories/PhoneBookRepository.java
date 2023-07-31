package com.mukhaArturTZ.TZSpringBootOneToMany.repositories;

import com.mukhaArturTZ.TZSpringBootOneToMany.model.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBook,Integer> {

    PhoneBook findPhoneBookByName(String name);
}

package com.mukhaArturTZ.TZSpringBootOneToMany.controllers;

import com.mukhaArturTZ.TZSpringBootOneToMany.dto.PersonDTO;
import com.mukhaArturTZ.TZSpringBootOneToMany.model.Person;
import com.mukhaArturTZ.TZSpringBootOneToMany.service.PersonService;
import com.mukhaArturTZ.TZSpringBootOneToMany.util.ErrorsUtil;
import com.mukhaArturTZ.TZSpringBootOneToMany.util.PersonValid.PersonErrorResponse;
import com.mukhaArturTZ.TZSpringBootOneToMany.util.PersonValid.PersonException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable int id) {
        PersonDTO personDTO = convertToPersonDTO(personService.getOnePerson(id));
        return ResponseEntity.ok(personDTO);
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAll() {
        List<PersonDTO> personDTOS = personService.getAllPerson().
                stream().
                map(this::convertToPersonDTO).
                collect(Collectors.toList());
        return ResponseEntity.ok(personDTOS);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }

        Person person = convertToPerson(personDTO);
        personService.savePerson(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable int id, @RequestBody @Valid PersonDTO personDTO,BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }
        personService.updatePerson(id, convertToPerson(personDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        personService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}

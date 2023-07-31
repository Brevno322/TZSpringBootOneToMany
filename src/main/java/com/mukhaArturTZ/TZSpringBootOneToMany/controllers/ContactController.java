package com.mukhaArturTZ.TZSpringBootOneToMany.controllers;

import com.mukhaArturTZ.TZSpringBootOneToMany.dto.ContactDTO;
import com.mukhaArturTZ.TZSpringBootOneToMany.model.Contact;
import com.mukhaArturTZ.TZSpringBootOneToMany.service.ContactService;
import com.mukhaArturTZ.TZSpringBootOneToMany.util.ContactValid.ContactErrorResponse;
import com.mukhaArturTZ.TZSpringBootOneToMany.util.ContactValid.ContactException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ModelMapper modelMapper;
    private final ContactService contactService;

    @Autowired
    public ContactController(ModelMapper modelMapper, ContactService contactService) {
        this.modelMapper = modelMapper;
        this.contactService = contactService;
    }

    private ContactDTO convertToContactDTO(Contact contact) {
        return modelMapper.map(contact, ContactDTO.class);
    }

    private Contact convertToContact(ContactDTO contactDTO) {
        return modelMapper.map(contactDTO, Contact.class);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody ContactDTO contactDTO) {
        contactService.save(convertToContact(contactDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAll() {
        return ResponseEntity.ok(contactService.getAllContact()
                .stream()
                .map(this::convertToContactDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ContactDTO getOne(@PathVariable int id) {
        return convertToContactDTO(contactService.getOneContact(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable int id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateContact(@PathVariable int id, @RequestBody ContactDTO contactDTO) {
        contactService.updateContact(id, convertToContact(contactDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ContactErrorResponse>handException(ContactException e){
        ContactErrorResponse contactErrorResponse=new ContactErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(contactErrorResponse,HttpStatus.BAD_REQUEST);
    }


}

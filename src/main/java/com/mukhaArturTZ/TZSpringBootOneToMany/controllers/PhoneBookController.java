package com.mukhaArturTZ.TZSpringBootOneToMany.controllers;

import com.mukhaArturTZ.TZSpringBootOneToMany.dto.PhoneBookDTO;
import com.mukhaArturTZ.TZSpringBootOneToMany.model.PhoneBook;
import com.mukhaArturTZ.TZSpringBootOneToMany.service.PhoneBookService;
import com.mukhaArturTZ.TZSpringBootOneToMany.util.ErrorsUtil;
import com.mukhaArturTZ.TZSpringBootOneToMany.util.PhoneBookValid.PhoneBookErrorResponse;
import com.mukhaArturTZ.TZSpringBootOneToMany.util.PhoneBookValid.PhoneBookException;
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
@RequestMapping("phoneBook")
public class PhoneBookController {

    private final ModelMapper modelMapper;
    private final PhoneBookService phoneBookService;

    @Autowired
    public PhoneBookController(ModelMapper modelMapper, PhoneBookService phoneBookService) {
        this.modelMapper = modelMapper;
        this.phoneBookService = phoneBookService;
    }

    private PhoneBook convertToPhoneBook(PhoneBookDTO phoneBookDTO) {
        return modelMapper.map(phoneBookDTO, PhoneBook.class);
    }

    private PhoneBookDTO convertToPhoneBookDTO(PhoneBook phoneBook) {
        return modelMapper.map(phoneBook, PhoneBookDTO.class);
    }


    @GetMapping
    public ResponseEntity<List<PhoneBookDTO>> getAll() {
        List<PhoneBookDTO> phoneBookDTOS = phoneBookService.getAll().stream()
                .map(this::convertToPhoneBookDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(phoneBookDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneBookDTO> getOnePhoneBook(@PathVariable int id) {
        PhoneBookDTO phoneBookDTO = convertToPhoneBookDTO(phoneBookService.getOnePhoneBook(id));
        return ResponseEntity.ok(phoneBookDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid PhoneBookDTO phoneBookDTO, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }
        phoneBookService.save(convertToPhoneBook(phoneBookDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable int id, @RequestBody @Valid PhoneBookDTO phoneBookDTO,BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            ErrorsUtil.returnErrorsToClient(bindingResult);
        }
        phoneBookService.updatePhoneBook(id, convertToPhoneBook(phoneBookDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        phoneBookService.deletePhoneBook(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PhoneBookErrorResponse>handException(PhoneBookException e){
        PhoneBookErrorResponse personErrorResponse=new PhoneBookErrorResponse(
                e.getMessage(),System.currentTimeMillis()
        );
        return new ResponseEntity<>(personErrorResponse,HttpStatus.BAD_REQUEST);
    }
}

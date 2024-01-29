package com.example.poc.zipoperator.controller;

import com.example.poc.zipoperator.model.PersonData;
import com.example.poc.zipoperator.services.PersonService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@AllArgsConstructor
public class PersonRestController {

    private PersonService personService;

    @GetMapping("/{personId}")
    public Single<ResponseEntity<PersonData>> searchPerson(@PathVariable String personId, @RequestParam List<String> extraFields) {
        return personService.findPersonById(personId, extraFields)
                .map(ResponseEntity::ok)
                .subscribeOn(Schedulers.io());
    }

}

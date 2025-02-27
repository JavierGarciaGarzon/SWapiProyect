package com.diverger.swapi.application.controller;

import com.diverger.swapi.application.dto.PersonInfoResponse;
import com.diverger.swapi.application.service.PersonApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private final PersonApplicationService personApplicationService;

    @Autowired
    public PersonController(PersonApplicationService personApplicationService) {
        this.personApplicationService = personApplicationService;
    }

    @GetMapping("/swapi-proxy/person-info")
    public ResponseEntity<List<PersonInfoResponse>> getPersonInfo(@RequestParam String name, @RequestParam(required = false) Integer page) {

        List<PersonInfoResponse> response = personApplicationService.getPersonInfoByName(name, page);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

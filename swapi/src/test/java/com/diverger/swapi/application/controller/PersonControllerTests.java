package com.diverger.swapi.application.controller;

import com.diverger.swapi.application.dto.PersonInfoResponse;
import com.diverger.swapi.application.exception.PersonNotFoundException;
import com.diverger.swapi.application.service.PersonApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.*;

@WebMvcTest(PersonController.class)
public class PersonControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonApplicationService personApplicationService;

    @Test
    public void testGetPersonInfoSuccess() throws Exception {
        PersonInfoResponse personInfo = new PersonInfoResponse();
        when(personApplicationService.getPersonInfoByName("Luke", 1))
                .thenReturn(Collections.singletonList(personInfo));

        mockMvc.perform(MockMvcRequestBuilders.get("/swapi-proxy/person-info")
                        .param("name", "Luke")
                        .param("page", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetPersonInfoNotFound() throws Exception {
        when(personApplicationService.getPersonInfoByName("Unknown", 1))
                .thenThrow(new PersonNotFoundException("No person found with name: Unknown"));

        mockMvc.perform(MockMvcRequestBuilders.get("/swapi-proxy/person-info")
                        .param("name", "Unknown")
                        .param("page", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
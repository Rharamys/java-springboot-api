package io.example.controllers;

import io.example.domain.DomainObject;
import io.example.service.AnyService;
import io.example.service.ApiCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
public class AnyController {

    @Autowired
    private AnyService anyService;

    @Autowired
    private ApiCallService apiCallService;

    @GetMapping("/api-call")
    public ResponseEntity<?> getApiCall (@RequestParam(name = "animal_type") String animalType) {
        try {
            return ResponseEntity.ok(apiCallService.callPublicApiList(animalType));
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/domain-objects")
    public List<DomainObject> getDomainObjects() {
        return anyService.getDomainObjects();
    }

    @PostMapping("/domain-objects")
    public DomainObject postDomainObjects (@RequestBody DomainObject domainObject) {
        return anyService.insertDomainObject(domainObject);
    }
}
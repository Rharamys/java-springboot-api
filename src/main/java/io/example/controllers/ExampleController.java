package io.example.controllers;

import io.example.domain.DomainExampleObject;
import io.example.service.DatabaseExampleService;
import io.example.service.ApiCallExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExampleController {

    @Autowired
    private DatabaseExampleService databaseExampleService;

    @Autowired
    private ApiCallExampleService apiCallExampleService;

    @GetMapping("/api-call")
    public ResponseEntity<?> getApiCall (@RequestParam(name = "name") String name) {
        try {
            return ResponseEntity.ok(apiCallExampleService.callPublicApi(name));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/domain-objects")
    public List<DomainExampleObject> getDomainObjects() {
        return databaseExampleService.getDomainObjects();
    }

    @PostMapping("/domain-objects")
    public DomainExampleObject postDomainObjects (@RequestBody DomainExampleObject domainExampleObject) {
        return databaseExampleService.insertDomainObject(domainExampleObject);
    }
}
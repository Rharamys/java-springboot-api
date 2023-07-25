package io.example.controllers;

import io.example.domain.DomainObject;
import io.example.service.AnyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnyController {

    @Autowired
    private AnyService anyService;

    @GetMapping("/domain-objects")
    public List<DomainObject> index() {
        return anyService.getDomainObjects();
    }

    @PostMapping("/domain-objects")
    public DomainObject index(@RequestBody DomainObject domainObject) {
        return anyService.insertDomainObject(domainObject);
    }
}
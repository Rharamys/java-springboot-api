package io.example.service;

import io.example.DAO.DomainExampleObjectDAO;
import io.example.domain.DomainExampleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseExampleService {

    @Autowired
    private DomainExampleObjectDAO domainExampleObjectDAO;

    public DomainExampleObject getDomainObject (String anyAttribute){
        return domainExampleObjectDAO.findByAnyAttribute(anyAttribute);
    }

    public DomainExampleObject insertDomainObject (DomainExampleObject domainExampleObject){
        return domainExampleObjectDAO.save(domainExampleObject);
    }

    public List<DomainExampleObject> getDomainObjects (){
        return domainExampleObjectDAO.findAll();
    }
}

package io.example.service;

import io.example.DAO.DomainObjectDAO;
import io.example.domain.DomainObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnyService {

    @Autowired
    private DomainObjectDAO domainObjectDAO;

    public DomainObject getDomainObject (String anyAttribute){
        return domainObjectDAO.findByAnyAttribute(anyAttribute);
    }

    public DomainObject insertDomainObject (DomainObject domainObject){
        return domainObjectDAO.save(domainObject);
    }

    public List<DomainObject> getDomainObjects (){
        return domainObjectDAO.findAll();
    }
}

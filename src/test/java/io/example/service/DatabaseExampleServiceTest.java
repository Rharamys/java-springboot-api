package io.example.service;

import io.example.DAO.DomainExampleObjectDAO;
import io.example.domain.DomainExampleObject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DatabaseExampleServiceTest {

    @Autowired
    private DatabaseExampleService databaseExampleService;

    @MockBean
    private DomainExampleObjectDAO domainExampleObjectDAO;

    @Test
    public void getDomainObjectByAttribute_thenRetrieveMockedData (){
        DomainExampleObject domainExampleObject = new DomainExampleObject();
        domainExampleObject.anyAttribute = "anyTestAttr";
        Mockito.when(domainExampleObjectDAO.findByAnyAttribute(Mockito.anyString())).thenReturn(domainExampleObject);
        Assertions.assertThat(databaseExampleService.getDomainObject("something")).isEqualTo(domainExampleObject);
    }

    @Test
    public void insertDomainObjectByAttribute_thenRetrieveMockedData (){
        DomainExampleObject domainExampleObject = new DomainExampleObject();
        domainExampleObject.anyAttribute = "anyTestAttr";
        Mockito.when(domainExampleObjectDAO.save(Mockito.any())).thenReturn(domainExampleObject);
        Assertions.assertThat(databaseExampleService.insertDomainObject(domainExampleObject)).isEqualTo(domainExampleObject);
        Mockito.verify(domainExampleObjectDAO, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void getDomainObjects_thenRetrieveMockedData (){
        List<DomainExampleObject> domainExampleObjectList = new ArrayList<>();
        DomainExampleObject domainExampleObject = new DomainExampleObject();
        domainExampleObject.anyAttribute = "anyTestAttr";
        domainExampleObjectList.add(domainExampleObject);
        Mockito.when(domainExampleObjectDAO.findAll()).thenReturn(domainExampleObjectList);
        Assertions.assertThat(databaseExampleService.getDomainObjects()).isEqualTo(domainExampleObjectList);
        Mockito.verify(domainExampleObjectDAO, Mockito.times(1)).findAll();
    }
}

package io.example.controllers;

import io.example.domain.DomainExampleObject;
import io.example.domain.PublicApiObject;
import io.example.service.ApiCallExampleService;
import io.example.service.DatabaseExampleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
@SpringBootTest
public class ExampleControllerTest {

    @Autowired
    private ExampleController exampleController;

    @MockBean
    private ApiCallExampleService apiCallExampleService;

    @MockBean
    private DatabaseExampleService databaseExampleService;

    @Test
    public void whenApiCalled_ThenReturnMockedData () throws Exception {
        PublicApiObject publicApiObject = new PublicApiObject();
        publicApiObject.age = 56;
        publicApiObject.name = "silvia";
        publicApiObject.count = 14;
        Mockito.when(apiCallExampleService.callPublicApi(Mockito.anyString()))
                .thenReturn(publicApiObject);
        Assertions.assertThat(exampleController.getApiCall("silvia")).isEqualTo(ResponseEntity.ok(publicApiObject));
    }

    @Test
    public void whenApiCalled_ThenReturnError () throws Exception {
        Mockito.when(apiCallExampleService.callPublicApi(Mockito.anyString()))
                .thenThrow(new Exception("Test Error"));
        Assertions.assertThat(exampleController.getApiCall("silvia")).isEqualTo(ResponseEntity.internalServerError().build());
    }

    @Test
    public void whenGetDomainObjectsCalled_ThenReturnMockedData () throws Exception {
        List<DomainExampleObject> domainExampleObjectList = new ArrayList<>();
        DomainExampleObject domainExampleObject1 = new DomainExampleObject();
        domainExampleObject1.anyAttribute = "anyAttr1";
        domainExampleObjectList.add(domainExampleObject1);
        DomainExampleObject domainExampleObject2 = new DomainExampleObject();
        domainExampleObject2.anyAttribute = "anyAttr2";
        domainExampleObjectList.add(domainExampleObject2);
        Mockito.when(databaseExampleService.getDomainObjects())
                .thenReturn(domainExampleObjectList);
        Assertions.assertThat(exampleController.getDomainObjects()).isEqualTo(domainExampleObjectList);
    }

    @Test
    public void whenPostDomainObjectsCalled_ThenReturnMockedData () throws Exception {
        DomainExampleObject domainExampleObject = new DomainExampleObject();
        domainExampleObject.anyAttribute = "anyAttr1";
        Mockito.when(databaseExampleService.insertDomainObject(Mockito.any()))
                .thenReturn(domainExampleObject);
        Assertions.assertThat(exampleController.postDomainObjects(domainExampleObject)).isEqualTo(domainExampleObject);
    }

}
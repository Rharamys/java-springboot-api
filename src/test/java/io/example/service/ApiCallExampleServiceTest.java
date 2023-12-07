package io.example.service;

import io.example.domain.PublicApiObject;
import io.example.utils.ExternalApiCaller;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ApiCallExampleServiceTest {

    @Autowired
    ApiCallExampleService apiCallExampleService;

    @MockBean
    ExternalApiCaller externalApiCaller;

    @Test
    public void shallCallApi_ThenReturnMockedData () throws Exception {
        PublicApiObject publicApiObject = new PublicApiObject();
        publicApiObject.count = 1872;
        publicApiObject.age = 35;
        publicApiObject.name = "silvia";
        Mockito.when(externalApiCaller.callApi(Mockito.anyString(),Mockito.anyMap(),Mockito.anyString(),Mockito.any())).thenReturn(publicApiObject);
        Assertions.assertThat(apiCallExampleService.callPublicApi("silvia")).isEqualTo(
            publicApiObject
        );
    }

    @Test
    public void shallParseApiResponse_ThenReturnRightInformation () throws Exception {
        PublicApiObject publicApiObject = new PublicApiObject();
        publicApiObject.count = 1100;
        publicApiObject.age = 50;
        publicApiObject.name = "silvia";
        Mockito.when(externalApiCaller.callApi(Mockito.anyString(),Mockito.anyMap(),Mockito.anyString(),Mockito.any())).thenReturn(publicApiObject);
        Assertions.assertThat(apiCallExampleService.responseParserToDomainObject.parseContent("{\"name\":\"silvia\",\"age\":50,\"count\":1100}")).isEqualToComparingFieldByField(
                publicApiObject
        );
    }

}

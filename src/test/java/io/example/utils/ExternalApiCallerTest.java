package io.example.utils;

import io.example.Application;
import io.example.domain.PublicApiObject;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.HashMap;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ExternalApiCallerTest {

    @Autowired
    ExternalApiCaller externalApiCaller;

    @Mock
    HttpsURLConnection connection;

    @Mock
    URL url;

    public class ResponseParserToDomainObject implements ExternalApiCaller.ResponseParser {
        public PublicApiObject parseContent (String apiResponse) throws Exception {
            JSONObject jsonObject = new JSONObject(apiResponse);
            PublicApiObject object = new PublicApiObject();
            if (jsonObject.get("name") instanceof String) {
                object.name = jsonObject.getString("name");
            }
            if (jsonObject.get("age") instanceof Number) {
                object.age = jsonObject.getInt("age");
            }
            if (jsonObject.get("count") instanceof Number) {
                object.count = jsonObject.getInt("count");
            }
            return object;
        }
    }

    @Test
    public void shouldCallApi_thenReturnMockedData () throws Exception {
        Mockito.doReturn(connection).when(url).openConnection();
        Mockito.doReturn(HttpsURLConnection.HTTP_OK).when(connection).getResponseCode();
        Mockito.doReturn(new ByteArrayInputStream(new String("{\"name\":\"silvia\",\"age\":50,\"count\":1100}").getBytes())).when(connection).getInputStream();

        ResponseParserToDomainObject responseParserToDomainObject = new ResponseParserToDomainObject();
        PublicApiObject publicApiObject = new PublicApiObject();
        publicApiObject.count = 1100;
        publicApiObject.age = 50;
        publicApiObject.name = "silvia";
        Assertions.assertThat(externalApiCaller.callApi("https://anyurl.com/",new HashMap<>(),"GET", responseParserToDomainObject)).isEqualToComparingFieldByField(publicApiObject);

        //TODO: throwing connection reset
    }

}

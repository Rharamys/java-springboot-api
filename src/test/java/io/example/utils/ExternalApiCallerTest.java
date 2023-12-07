package io.example.utils;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

@SpringBootTest
public class ExternalApiCallerTest {

    @Autowired
    ExternalApiCaller externalApiCaller;

    @Test
    public void shouldCallApi_thenReturnMockedData () throws Exception {
        URL url = Mockito.mock(URL.class);
        HttpsURLConnection huc = Mockito.mock(HttpsURLConnection.class);
        Mockito.when(url.openConnection()).thenReturn(huc);
        Mockito.when(huc.getResponseCode()).thenReturn(200);
        Mockito.when(huc.getInputStream()).thenReturn(new ByteArrayInputStream("{\"name\":\"silvia\",\"age\":50,\"count\":1100}".getBytes()));
    // TODO: still didnt finish
    }

}

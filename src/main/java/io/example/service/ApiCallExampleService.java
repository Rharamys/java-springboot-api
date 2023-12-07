package io.example.service;

import io.example.domain.PublicApiObject;
import io.example.utils.ExternalApiCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.util.*;

@Service
public class ApiCallExampleService {

    @Autowired
    ExternalApiCaller externalApiCaller;

    private static final String  API_BASE_URL = "https://api.agify.io/";

    @Autowired
    ResponseParserToDomainObject responseParserToDomainObject;

    @Component
    public static class ResponseParserToDomainObject implements ExternalApiCaller.ResponseParser {
        public PublicApiObject parseContent (String apiResponse) {
            System.out.println("apiResponse: " + apiResponse);
            JSONObject jsonObject = new JSONObject(apiResponse);
            PublicApiObject object = new PublicApiObject();
            if (jsonObject.get("name") instanceof String) {
                object.name = jsonObject.getString("name");
            }
            if (jsonObject.get("age") instanceof Number) {
                object.age = jsonObject.getNumber("age");
            }
            if (jsonObject.get("count") instanceof Number) {
                object.count = jsonObject.getNumber("count");
            }
            return object;
        }
    }

    public PublicApiObject callPublicApi (String name) throws Exception {
        //Create connection
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", name);
        return (PublicApiObject) externalApiCaller.callApi(API_BASE_URL, parameters, "GET", responseParserToDomainObject);
    }

}

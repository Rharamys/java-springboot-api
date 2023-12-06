package io.example.service;

import io.example.DAO.DomainObjectDAO;
import io.example.domain.DomainObject;
import io.example.domain.PublicApiObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiCallService {

    private static final String  API_BASE_URL = "http://cat-fact.herokuapp.com/facts";

    private class ParameterStringBuilder {
        public static String getParamsString(Map<String, String> params)
                throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                result.append("&");
            }
            String resultString = result.toString();
            return resultString.length() > 0
                    ? resultString.substring(0, resultString.length() - 1)
                    : resultString;
        }
    }

    private class ResponseParserToDomainObject {
        public static List<PublicApiObject> parseResponse (String apiResponse) {
            JSONArray array = new JSONArray(apiResponse);
            List<PublicApiObject> response = new ArrayList<>();
            for (int i =0; i<array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                PublicApiObject object = new PublicApiObject();
                object._id = jsonObject.getString("_id");
                object._v = jsonObject.getNumber("_v");
                JSONObject status = jsonObject.getJSONObject("status");
                object.status = new PublicApiObject.Status(status.getBoolean("verified"), status.getString("feedback"), status.getNumber("sentCount")) ;
                object.deleted = jsonObject.getBoolean("deleted");
                object.text = jsonObject.getString("text");
                object.source = jsonObject.getString("source");
                object.sendDate = jsonObject.getString("sendDate");
                object.updatedAt = jsonObject.getString("updatedAt");
                object.user = jsonObject.getString("user");
                response.add(object);
            }
            return response;
        }
    }

    public List<PublicApiObject> callPublicApiList (String animalType) throws Exception {
        //Create connection
        URL url = new URL(API_BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
        con.setRequestProperty("Connection", "keep-alive");

        //Setting query parameters
        Map<String, String> parameters = new HashMap<>();
        parameters.put("animal_type", animalType);
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();

        //Configure timeout
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        int status = con.getResponseCode();
        Reader streamReader = null;

        if (status > 299) {
            throw new Exception("Error: " + con.getResponseCode());
        } else {
            streamReader = new InputStreamReader(con.getInputStream());
            return ResponseParserToDomainObject.parseResponse(streamReader.toString());
        }
    }

}

package io.example.utils;

import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

@Service
public class ExternalApiCaller {

    public static interface ResponseParser {
        public Object parseContent (String body) throws Exception;
    }

    private static class ParameterStringBuilder {
        public static String getParamsString(Map<String, String> params)
                throws UnsupportedEncodingException {
            int i=0;
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (i==0) {
                    result.append("?");
                } else {
                    result.append("&");
                }
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                i++;
            }
            return result.toString();
        }
    }

    public Object callApi (String inputUrl, Map<String, String> parameters, String method, ResponseParser parser) throws Exception {
        URL url = new URL(inputUrl + ParameterStringBuilder.getParamsString(parameters));
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod(method);

        //Configure timeout
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        int status = con.getResponseCode();

        if (status > 299) {
            throw new Exception("Error: " + status);
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            return parser.parseContent(sb.toString());
        }
    }

}

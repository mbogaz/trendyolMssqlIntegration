package service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.apache.http.protocol.HTTP.USER_AGENT;
import static utils.Constants.*;

public class HttpApiRequest {

    private HttpClient client = HttpClientBuilder.create().build();

    public JSONObject getJsonResponse(String url) throws IOException {
        return new JSONObject(httpGet(url));
    }

    public String getStringResponse(String url) throws IOException {
        return httpGet(url);
    }

    private String httpGet(String url) throws IOException {

        HttpGet request = new HttpGet(url);

        request.setHeader(HEADER_USER_AGENT_KEY, USER_AGENT);
        request.setHeader(HEADER_ACCEPT_KEY, HEADER_ACCEPT_VALUE);
        request.setHeader(HEADER_ACCEPT_LANGUAGE_KEY, HEADER_ACCEPT_LANGUAGE_VALUE);

        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }

}

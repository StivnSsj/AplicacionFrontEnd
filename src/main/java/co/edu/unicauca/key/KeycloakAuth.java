package co.edu.unicauca.key;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class KeycloakAuth {
    private static final String TOKEN_URL = "http://localhost:8080/realms/myrealm/protocol/openid-connect/token";
    
    private static final String CLIENT_ID = "myclient";
    private static final String CLIENT_SECRET = "pCAKi9AfbOrbKJGh7MRGnLeEJKMQqTSu";

    public static String getToken(String username, String password) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(TOKEN_URL);

            String body = "grant_type=password&client_id=" + CLIENT_ID
                    + "&client_secret=" + CLIENT_SECRET
                    + "&username=" + username
                    + "&password=" + password;

            post.setEntity(new StringEntity(body));
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonResponse = mapper.readTree(response.getEntity().getContent());

                if (response.getStatusLine().getStatusCode() == 200 && jsonResponse.has("access_token")) {
                    return jsonResponse.get("access_token").asText();
                } else {
                    throw new IOException("Error en autenticación: " + jsonResponse.toString());
                }
            }
        }
    }
}

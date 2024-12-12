
package co.edu.unicauca.utilidades;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Authenticator {
    private final String clientId = "myclient";  // Configura tu Client ID
    private final String realm = "myrealm";     // Configura tu Realm
    private final String baseUrl = "http://localhost:8080"; // Configura tu Base URL
    private final String tokenUrl;

    public Authenticator() {
        this.tokenUrl = baseUrl + "/realms/" + realm + "/protocol/openid-connect/token";
    }

    public String authenticate(String username, String password) {
        try {
            // Configuración de la conexión
            URL url = new URL(tokenUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            // Datos para la autenticación
            String data = "client_id=" + clientId
                    + "&grant_type=password"
                    + "&username=" + username
                    + "&password=" + password;

            // Enviar datos
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(data);
            writer.flush();
            writer.close();

            // Leer la respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Scanner scanner = new Scanner(connection.getInputStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();
                return response.toString(); // Token obtenido
            } else {
                System.err.println("Error: Código de respuesta " + responseCode);
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
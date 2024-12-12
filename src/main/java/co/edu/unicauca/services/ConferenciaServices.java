package co.edu.unicauca.services;

import java.util.List;
import co.edu.unicauca.modelos.Conferencia;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Clase de servicio que proporciona métodos para gestionar conferencias
 * utilizando un repositorio de conferencias.
 */
public class ConferenciaServices {

    private static final String BACKEND_URL = "http://localhost:8222/api/conferencias";

    public boolean actualizarConferencia(String token, Integer idConferencia, Integer idArticulo) {
        try {
            // Construir la URL del endpoint con el ID de la conferencia
            URL url = new URL(BACKEND_URL + "/" + idConferencia + "/articulo");

            // Configurar la conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Convertir el ID del artículo a JSON
            String idArticuloJson = String.valueOf(idArticulo);

            // Enviar el JSON en el cuerpo de la solicitud
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = idArticuloJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Verificar la respuesta del servidor
            int responseCode = connection.getResponseCode();
            return responseCode == 200; // Retorna true si el código de respuesta es 200 (OK)

        } catch (Exception e) {
            // Manejo de errores
            System.err.println("Error al actualizar la conferencia: " + e.getMessage());
            return false; // Retorna false en caso de excepción
        }
    }

    public Conferencia obtenerConferenciaPorNombre(String token, String conferenciaNombre) throws Exception {
        // Codificar el nombre de la conferencia para manejar espacios y caracteres especiales
        String encodedNombre = URLEncoder.encode(conferenciaNombre, StandardCharsets.UTF_8.toString())
                .replace("+", "%20");
        String urlString = BACKEND_URL + "/nombre/" + encodedNombre;
        URL url = new URL(urlString);
        System.out.println("URL: " + urlString);

        // Configurar la conexión al endpoint
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Content-Type", "application/json");

        // Verificar la respuesta del servidor
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Error al obtener la conferencia: Código de respuesta " + responseCode);
        }

        // Leer la respuesta del servidor
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }

        // Convertir la respuesta JSON a un objeto Conferencia
        com.nimbusds.jose.shaded.gson.Gson gson = new com.nimbusds.jose.shaded.gson.Gson();
        return gson.fromJson(response.toString(), Conferencia.class);
    }

    public Conferencia obtenerConferenciaPorId(String token, int conferenciaId) throws Exception {
        // Construir la URL con el ID de la conferencia
        String urlString = BACKEND_URL + "/" + conferenciaId;
        URL url = new URL(urlString);

        // Configurar la conexión al endpoint
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Content-Type", "application/json");

        // Verificar la respuesta del servidor
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Error al obtener la conferencia: Código de respuesta " + responseCode);
        }

        // Leer la respuesta del servidor
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }

        // Convertir la respuesta JSON a un objeto ConferenciaDTO
        Gson gson = new Gson();
        return gson.fromJson(response.toString(), Conferencia.class);
    }

    public List<Conferencia> listarConferencias(String token) throws Exception {
        // Configurar la conexión al endpoint
        URL url = new URL(BACKEND_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Content-Type", "application/json");

        // Verificar la respuesta del servidor
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) { // Si la respuesta es OK
            // Leer la respuesta del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Convertir el JSON a una lista de ConferenciaDTO
            Gson gson = new Gson();
            List<Conferencia> conferencias = gson.fromJson(
                    response.toString(),
                    new TypeToken<List<Conferencia>>() {
                    }.getType()
            );
            return conferencias;

        } else {
            throw new RuntimeException("Error al obtener conferencias: Código de respuesta " + responseCode);
        }
    }

    public boolean guardarConferencia(Conferencia conferencia, String token) throws Exception {
        // Configurar la conexión al endpoint
        URL url = new URL(BACKEND_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true); // Para enviar datos en el cuerpo de la solicitud

        // Convertir la conferencia a JSON
        Gson gson = new Gson();
        String conferenciaJson = gson.toJson(conferencia);

        // Enviar la conferencia al servidor
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = conferenciaJson.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Verificar la respuesta del servidor
        int responseCode = connection.getResponseCode();
        if (responseCode == 201 || responseCode == 200) { // Si la respuesta indica éxito
            System.out.println("Conferencia guardada exitosamente.");
            return true;
        } else {
            throw new RuntimeException("Error al guardar la conferencia: Código de respuesta " + responseCode);
        }
    }

//    private String endPoint;
//    private Client objConferenciaPeticiones;
//
//    public ConferenciaServices() {
//        this.endPoint = "http://localhost:8070/api/conferencias";
//        this.objConferenciaPeticiones = ClientBuilder.newClient().register(new JacksonFeature());
//    }
//
//    public List<Conferencia> listarConferencias() {
//        Response response = objConferenciaPeticiones
//                .target(endPoint)
//                .request(MediaType.APPLICATION_JSON)
//                .get();
//
//        if (response.getStatus() == 200) {
//            // Lee la lista de conferencias desde la respuesta
//            return response.readEntity(new GenericType<List<Conferencia>>() {
//            });
//        } else {
//            System.out.println("Error al listar las conferencias. Código de respuesta: " + response.getStatus());
//            return null;
//        }
//    }
//
//    // Método para crear una conferencia
//    // Método para crear una conferencia con token de autorización
//    public boolean crearConferencia(Conferencia conferencia, Integer idUsuario, String token) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        // Convierte las fechas de LocalDate a String
//        String fechaInicioStr = LocalDate.parse(conferencia.getFechaInicio()).format(formatter);
//        String fechaFinStr = LocalDate.parse(conferencia.getFechaFin()).format(formatter);
//
//        // Asigna las fechas convertidas a la conferencia
//        conferencia.setFechaInicio(fechaInicioStr);
//        conferencia.setFechaFin(fechaFinStr);
//
//        // Incluye el token de autorización en la solicitud
//        Response response = objConferenciaPeticiones
//                .target(endPoint)
//                .queryParam("idUsuario", idUsuario)
//                .request(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Bearer " + token) // Añade el token aquí
//                .post(Entity.entity(conferencia, MediaType.APPLICATION_JSON));
//
//        if (response.getStatus() == 200 || response.getStatus() == 201) {
//            //this.notifyAllObserves();
//            return true;
//        } else {
//            System.out.println("Error al crear la conferencia. Código de respuesta: " + response.getStatus());
//            return false;
//        }
//    }
//
//    // Método para consultar conferencia por ID
//    public Conferencia consultarConferenciaPorId(Integer id) {
//        Response response = objConferenciaPeticiones
//                .target(endPoint + "/" + id)
//                .request(MediaType.APPLICATION_JSON)
//                .get();
//
//        if (response.getStatus() == 200) {
//            // Leer la conferencia desde la respuesta
//            return response.readEntity(Conferencia.class);
//        } else {
//            System.out.println("Error al consultar la conferencia. Código de respuesta: " + response.getStatus());
//            return null;
//        }
//    }
//
//    // Método para agregar un artículo a una conferencia
//    public boolean agregarArticulo(Integer idConferencia, Integer idArticulo) {
//        // Construir la URL con el ID de la conferencia
//        String url = endPoint + "/" + idConferencia + "/articulo";
//
//        // Realizar la solicitud PUT
//        Response response = objConferenciaPeticiones
//                .target(url)
//                .request(MediaType.APPLICATION_JSON)
//                .put(Entity.entity(idArticulo, MediaType.APPLICATION_JSON));
//
//        if (response.getStatus() == 200) {
//            // Leer la conferencia actualizada desde la respuesta
//            return true;
//        } else {
//            System.out.println("Error al agregar el artículo. Código de respuesta: " + response.getStatus());
//            return false;
//        }
//    }
//
//    public Conferencia consultarConferenciaPorNombre(String nombre) {
//        // Construir la URL con el nombre de la conferencia
//        WebTarget target = objConferenciaPeticiones.target(endPoint).path("nombre").path(nombre);
//
//        // Enviar la petición GET
//        Response response = target.request(MediaType.APPLICATION_JSON).get();
//
//        // Verificar si la respuesta fue exitosa
//        if (response.getStatus() == 200) {
//            // Leer el objeto Conferencia desde la respuesta
//            return response.readEntity(Conferencia.class);
//        } else {
//            System.out.println("Error al consultar la conferencia: " + response.getStatus());
//            return null;
//        }
//    }
}

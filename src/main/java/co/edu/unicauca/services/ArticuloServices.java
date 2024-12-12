package co.edu.unicauca.services;

import co.edu.unicauca.modelos.Articulo;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de servicio que proporciona métodos para gestionar artículos utilizando
 * un repositorio de artículos.
 */
public class ArticuloServices {

    private static final String BACKEND_URL = "http://localhost:8222/api/articulos";

    public boolean guardarArticulo(String token, Articulo articulo) {
        try {
            // Construir la URL del endpoint
            URL url = new URL(BACKEND_URL);

            // Configurar la conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Convertir el objeto ArticuloDTO a JSON
            Gson gson = new Gson();
            String articuloJson = gson.toJson(articulo);

            // Enviar el JSON en el cuerpo de la solicitud
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = articuloJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Verificar la respuesta del servidor
            int responseCode = connection.getResponseCode();
            return responseCode == 200; // Retorna true si el código de respuesta es 201 (Created)

        } catch (Exception e) {
            // Manejo de errores
            System.err.println("Error al guardar el artículo: " + e.getMessage());
            return false; // Retorna false en caso de excepción
        }
    }

    public List<Articulo> listarArticulos(String token) throws Exception {
        // Configurar la conexión al endpoint
        URL url = new URL(BACKEND_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Content-Type", "application/json");

        // Verificar la respuesta del servidor
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Error al obtener la lista de artículos: Código de respuesta " + responseCode);
        }

        // Leer la respuesta del servidor
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }

        // Convertir la respuesta JSON a una lista de ArticuloDTO
        Gson gson = new Gson();
        return gson.fromJson(response.toString(), new TypeToken<List<Articulo>>() {
        }.getType());
    }

//    private String endPoint;
//    private Client objArticuloPeticiones;
//
//    public ArticuloServices() {
//        this.endPoint = "http://localhost:8222/api/articulos";
//        this.objArticuloPeticiones = ClientBuilder.newClient().register(new JacksonFeature());
//    }
//    public List<Articulo> listarArticulos() {
//        try {
//            WebTarget target = objArticuloPeticiones.target(endPoint);
//            Invocation.Builder solicitud = target.request(MediaType.APPLICATION_JSON);
//
//            Response respuesta = solicitud.get();
//
//            if (respuesta.getStatus() == 200) { // Si la respuesta es exitosa
//                return respuesta.readEntity(new GenericType<List<Articulo>>() {
//                });
//            } else {
//                System.out.println("Error: No se pudieron obtener las conferencias. Código de respuesta: " + respuesta.getStatus());
//                return new ArrayList<>(); // Retorna una lista vacía en caso de error
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ArrayList<>(); // Retorna una lista vacía si ocurre una excepción
//        }
//    }
//
//    public Articulo consultarArticulo(Integer id) {
//        WebTarget target = this.objArticuloPeticiones.target(this.endPoint + "/" + id);
//        Invocation.Builder objPeticion = target.request(MediaType.APPLICATION_JSON_TYPE);
//        Articulo objArticulo = objPeticion.get(Articulo.class);
//        return objArticulo;
//    }
//    
//    public boolean crearArticulo(Articulo objArticulo, Integer idUsuario) {
//        Response respuesta = objArticuloPeticiones
//                .target(endPoint)
//                .queryParam("idUsuario", idUsuario)
//                .request(MediaType.APPLICATION_JSON)
//                .post(Entity.entity(objArticulo, MediaType.APPLICATION_JSON));
//
//         Verificamos si el estado de respuesta es exitoso (2xx)
//        int statusCode = respuesta.getStatus();
//        if (statusCode == Response.Status.CREATED.getStatusCode()
//                || statusCode == Response.Status.OK.getStatusCode()
//                || statusCode == Response.Status.NO_CONTENT.getStatusCode()) {
//            return true;
//        } else {
//            System.out.println("Error al crear el artículo. Código de estado: " + statusCode);
//            System.out.println("Detalles de respuesta: " + respuesta.readEntity(String.class));
//            return false;
//        }
//    }
//
//    public boolean actualizarArticulo(Articulo objArticulo) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public boolean asignarEvaluador(int idArticulo, ArrayList<Evaluador> listEvaluadores) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public void cambiarEstado(int idArticulo, int i) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public boolean almacenarArticulo(Articulo objArticulo) {
//        return true;
//    }
}

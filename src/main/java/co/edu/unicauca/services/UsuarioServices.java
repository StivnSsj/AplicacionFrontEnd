package co.edu.unicauca.services;

import co.edu.unicauca.modelos.Usuario;
import com.nimbusds.jose.shaded.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class UsuarioServices {

    private static final String API_URL = "http://localhost:8222/api/usuarios";

   
    public Usuario obtenerUsuarioDesdeToken(String token) throws Exception {
        // Configurar la conexión al endpoint
        URL url = new URL(API_URL+"/user-info");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

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

            // Convertir el JSON a un objeto Usuario
            Gson gson = new Gson();
            Usuario usuario = gson.fromJson(response.toString(), Usuario.class);
            return usuario;

        } else {
            throw new RuntimeException("Error al obtener usuario: Código de respuesta " + responseCode);
        }
    }

//    public Usuario obtenerUsuarioDesdeToken(String token) {
//        // Configurar el encabezado Authorization con el Bearer token
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + token);
//
//        // Crear la entidad HTTP con los encabezados
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        try {
//            // Realizar la llamada GET al endpoint /user-info
//            ResponseEntity<Usuario> response = restTemplate.exchange(
//                    API_URL + "/user-info", // URL de tu API
//                    HttpMethod.GET,
//                    entity,
//                    Usuario.class // Clase modelo a mapear
//            );
//
//            // Verificar la respuesta
//            if (response.getStatusCode().is2xxSuccessful()) {
//                return response.getBody(); // Retorna el objeto Usuario
//            } else {
//                throw new RuntimeException("Error al obtener usuario: Código HTTP " + response.getStatusCode());
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Error al obtener usuario desde el token: " + e.getMessage(), e);
//        }
//    }
//
//    public List<String> obtenerPermisosPorId(int userId) throws Exception {
//        URL url = new URL(API_URL + "/" + userId + "/permisos");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Accept", "application/json");
//
//        if (connection.getResponseCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
//        }
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        StringBuilder response = new StringBuilder();
//        String output;
//        while ((output = br.readLine()) != null) {
//            response.append(output);
//        }
//        connection.disconnect();
//
//        // Convertir JSON a lista de permisos
//        return parsePermisos(response.toString());
//    }
//
//    private List<String> parsePermisos(String json) {
//        // Usar una librería como Jackson o Gson para convertir JSON a lista
//        // Aquí asumiremos una conversión manual para simplicidad
//        List<String> permisos = new ArrayList<>();
//        json = json.replace("[", "").replace("]", "").replace("\"", "");
//        String[] permisosArray = json.split(",");
//        for (String permiso : permisosArray) {
//            permisos.add(permiso.trim());
//        }
//        return permisos;
//    }
//
////    private String endPoint;
////    private Client objUsuarioPeticiones;
////
////    public UsuarioServices() {
////        this.endPoint = "http://localhost:8050/api/usuarios";
////        this.objUsuarioPeticiones = ClientBuilder.newClient().register(new JacksonFeature());
////    }
////
////    public boolean crearUsuario(Usuario objUsuario) {
////        // Realizamos la solicitud POST enviando el objeto Usuario como JSON
////        Response respuesta = objUsuarioPeticiones
////                .target(endPoint)
////                .request(MediaType.APPLICATION_JSON)
////                .post(Entity.entity(objUsuario, MediaType.APPLICATION_JSON));
////
////        // Comprobamos si la respuesta tiene un estado exitoso (2xx)
////        int statusCode = respuesta.getStatus();
////        if (statusCode == Response.Status.CREATED.getStatusCode()
////                || statusCode == Response.Status.OK.getStatusCode()
////                || statusCode == Response.Status.NO_CONTENT.getStatusCode()) {
////            return true;
////        } else {
////            System.out.println("Error al crear el usuario. Código de estado: " + statusCode);
////            System.out.println("Detalles de respuesta: " + respuesta.readEntity(String.class));
////            return false;
////        }
////    }
////    
//    public boolean consultarUsuario(Integer id) {
//        // Realizamos la solicitud GET para obtener el usuario con el ID especificado
//        Response respuesta = objUsuarioPeticiones
//                .target(API_URL + "/" + id)
//                .request(MediaType.APPLICATION_JSON)
//                .get();
//
//        // Verificamos si el estado de respuesta indica éxito
//        if (respuesta.getStatus() == Response.Status.OK.getStatusCode()) {
//            // Usuario encontrado
//            return true;
//        } else if (respuesta.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
//            // Usuario no encontrado
//            System.out.println("Usuario no encontrado con ID: " + id);
//            return false;
//        } else {
//            // Otro tipo de error
//            System.out.println("Error al consultar el usuario. Código de estado: " + respuesta.getStatus());
//            System.out.println("Detalles de respuesta: " + respuesta.readEntity(String.class));
//            return false;
//        }
//    }
//
//    public String consultarRolUsuario(Integer idUsuario) {
//        // Realizamos la solicitud GET para obtener el rol del usuario con el ID especificado
//        Response respuesta = objUsuarioPeticiones
//                .target(API_URL + idUsuario + "/rol/")
//                .request(MediaType.APPLICATION_JSON)
//                .get();
//
//        // Verificamos si la respuesta tiene un estado exitoso
//        if (respuesta.getStatus() == Response.Status.OK.getStatusCode()) {
//            // Obtenemos el nombre del rol desde la respuesta como una cadena
//            String nombreRol = respuesta.readEntity(String.class);
//            return nombreRol;
//        } else if (respuesta.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
//            System.out.println("Usuario no encontrado con ID: " + idUsuario);
//            return null;
//        } else {
//            // Otro tipo de error
//            System.out.println("Error al consultar el rol del usuario. Código de estado: " + respuesta.getStatus());
//            System.out.println("Detalles de respuesta: " + respuesta.readEntity(String.class));
//            return null;
//        }
//    }
//
//    public List<Usuario> obtenerUsuariosPorRol(String rolNombre) {
//        // Realizamos la solicitud GET para obtener todos los usuarios
//        Response respuesta = objUsuarioPeticiones
//                .target(API_URL)
//                .request(MediaType.APPLICATION_JSON)
//                .get();
//
//        // Verificamos si la respuesta tiene un estado exitoso
//        if (respuesta.getStatus() == Response.Status.OK.getStatusCode()) {
//            // Deserializamos la respuesta en una lista de usuarios
//            List<Usuario> listaUsuarios = respuesta.readEntity(new GenericType<List<Usuario>>() {
//            });
//
//            // Filtramos la lista de usuarios por el rol especificado
//            return listaUsuarios.stream()
//                    .filter(usuario -> usuario.getRol().equalsIgnoreCase(rolNombre))
//                    .collect(Collectors.toList());
//        } else {
//            System.out.println("Error al consultar los usuarios. Código de estado: " + respuesta.getStatus());
//            return null;
//        }
//    }
//
//    public Integer obtenerIdPorNombre(String nombre) throws Exception {
//        String urlString = API_URL + "/nombre/" + nombre;
//        URL url = new URL(urlString);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Accept", "application/json");
//
//        // Verificar el código de respuesta
//        if (connection.getResponseCode() == 200) {
//            // Leer la respuesta
//            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            StringBuilder response = new StringBuilder();
//            String output;
//            while ((output = br.readLine()) != null) {
//                response.append(output);
//            }
//            connection.disconnect();
//
//            // Convertir el ID recibido (en formato JSON) a Integer
//            return Integer.parseInt(response.toString().trim());
//        } else if (connection.getResponseCode() == 404) {
//            System.out.println("Usuario no encontrado con nombre: " + nombre);
//            return null;
//        } else {
//            System.out.println("Error al consultar el usuario. Código de estado: " + connection.getResponseCode());
//            connection.disconnect();
//            return null;
//        }
//    }
}

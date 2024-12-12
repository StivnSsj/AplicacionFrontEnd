/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.services;

import co.edu.unicauca.modelos.Usuario;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;

public class KeycloakServices {

    // Keycloak connection details
    private static final String SERVER_URL = "http://localhost:8080";
    private static final String REALM = "myrealm";
    private static final String CLIENT_ID = "myclient";
    private static final String CLIENT_SECRET = "pCAKi9AfbOrbKJGh7MRGnLeEJKMQqTSu";
    private boolean registro = false;

    public Usuario getUserBySub(String sub) {
        try {
            Keycloak keycloak = KeycloakBuilder.builder()
                    .serverUrl(SERVER_URL)
                    .realm(REALM)
                    .grantType("client_credentials")
                    .clientId(CLIENT_ID)
                    .clientSecret(CLIENT_SECRET)
                    .build();

            // Obtener al usuario por su sub (ID)
            UserRepresentation userRepresentation = keycloak.realm(REALM).users().get(sub).toRepresentation();

            if (userRepresentation != null) {
                // Mapear los datos a un objeto Usuario
                Usuario usuario = new Usuario();
                usuario.setId(userRepresentation.getId());
                usuario.setNombre(userRepresentation.getFirstName() + " " + userRepresentation.getLastName());
                usuario.setUser(userRepresentation.getUsername());
                usuario.setCorreo(userRepresentation.getEmail());
                usuario.setRol("No disponible en este método"); // Si el rol no está en UserRepresentation
                return usuario;
            }
        } catch (Exception e) {
            System.err.println("Error fetching user by sub: " + e.getMessage());
        }
        return null; // Si ocurre un error o no se encuentra el usuario
    }

    public List<Usuario> buscarUsuariosXRol(String rol) {
        String selectedRole = rol;
        List<Usuario> usuariosFiltrados = new ArrayList<>();
        try {
            Keycloak keycloak = KeycloakBuilder.builder()
                    .serverUrl(SERVER_URL)
                    .realm(REALM)
                    .grantType("client_credentials")
                    .clientId(CLIENT_ID)
                    .clientSecret(CLIENT_SECRET)
                    .build();

            // Obtener todos los usuarios del realm
            List<UserRepresentation> users = keycloak.realm(REALM).users().list();

            // Filtrar usuarios por el rol seleccionado
            for (UserRepresentation user : users) {
                List<RoleRepresentation> roles = keycloak.realm(REALM).users().get(user.getId()).roles().realmLevel().listAll();
                for (RoleRepresentation role : roles) {
                    if (role.getName().equals(selectedRole)) {
                        // Crear una instancia del modelo Usuario y agregarla a la lista
                        Usuario usuario = new Usuario();
                        usuario.setId(user.getId());
                        usuario.setNombre(user.getFirstName() + " " + user.getLastName());
                        usuario.setUser(user.getUsername());
                        usuario.setCorreo(user.getEmail());
                        usuario.setRol(selectedRole); // Establecer el rol actual
                        usuariosFiltrados.add(usuario);
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener los usuarios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        return usuariosFiltrados;
    }

    public void createUser(String username, String password, String email, String nombre, String apellido, String rol) {

        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Keycloak keycloak = KeycloakBuilder.builder()
                    .serverUrl(SERVER_URL)
                    .realm(REALM)
                    .grantType("client_credentials")
                    .clientId(CLIENT_ID)
                    .clientSecret(CLIENT_SECRET)
                    .build();

            // Create the user representation
            UserRepresentation user = new UserRepresentation();
            user.setUsername(username);
            user.setEmail(email);
            user.setFirstName(nombre);  // Set First Name
            user.setLastName(apellido);    // Set Last Name
            user.setEnabled(true);

            // Send a POST request to create the user
            jakarta.ws.rs.core.Response response = keycloak.realm(REALM).users().create(user);
            if (response.getStatus() == 201) {
                String location = response.getHeaderString("Location");
                String userId = location.substring(location.lastIndexOf("/") + 1);  // Extract user ID from location header

                // Step 1: Get all roles assigned to the user
                List<RoleRepresentation> assignedRoles = keycloak.realm(REALM).users().get(userId).roles().realmLevel().listAll();

                // Step 2: Filter inherited roles and "default-roles-myrealm"
                List<RoleRepresentation> rolesToRemove = assignedRoles.stream()
                        .filter(role -> role.getName().equals("default-roles-" + REALM) || role.isComposite()) // Remove default and inherited roles
                        .toList();

                // Step 3: Remove the filtered roles
                if (!rolesToRemove.isEmpty()) {
                    keycloak.realm(REALM).users().get(userId).roles().realmLevel().remove(rolesToRemove);
                }

                // Step 4: Assign the selected role
                RoleRepresentation selectedRoleRep = keycloak.realm(REALM).roles().get(rol).toRepresentation();
                keycloak.realm(REALM).users().get(userId).roles().realmLevel().add(Collections.singletonList(selectedRoleRep));

                // Step 5: Set the password
                CredentialRepresentation passwordCred = new CredentialRepresentation();
                passwordCred.setType(CredentialRepresentation.PASSWORD);
                passwordCred.setValue(password);  // Set the password
                passwordCred.setTemporary(false); // Disable "Temporary" option

                keycloak.realm(REALM).users().get(userId).resetPassword(passwordCred);

                JOptionPane.showMessageDialog(null, "Usuario creado correctamente con el rol: " + rol +"\n       Inicie sesion", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.registro = true;
            } else {
                JOptionPane.showMessageDialog(null, "Error creando el usuario: " + response.getStatusInfo().getReasonPhrase(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            response.close(); // Close response to free resources
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error creando el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public boolean isRegistro() {
        return registro;
    }

}

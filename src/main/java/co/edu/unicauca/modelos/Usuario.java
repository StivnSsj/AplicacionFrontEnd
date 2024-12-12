package co.edu.unicauca.modelos;

public class Usuario {

    private String id;
    private String nombre;
    private String user;
    private String correo;
    private String rol;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String user, String correo, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.user = user;
        this.correo = correo;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
}

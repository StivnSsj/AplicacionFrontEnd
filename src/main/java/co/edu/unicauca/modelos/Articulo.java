package co.edu.unicauca.modelos;
/**
 *
 * @author Mary
 */
import java.util.ArrayList;
import java.util.List;
/**
 * Representa un artículo dentro del sistema de conferencias.
 * Contiene información sobre el título, resumen, palabras clave,
 * autores asociados, conferencia y evaluadores.
 */
public class Articulo {
    private Integer id;
    private String titulo;
    private String resumen;
    private String palabrasClave;
    private List<String> autores;
    private Conferencia conferencia;

    public Articulo() {
    }

    public Articulo(Integer id, String titulo, String resumen, String palabrasClave, List<String> autores, Conferencia conferencia) {
        this.id = id;
        this.titulo = titulo;
        this.resumen = resumen;
        this.palabrasClave = palabrasClave;
        this.autores = autores;
        this.conferencia = conferencia;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }


    public Conferencia getConferencia() {
        return conferencia;
    }

    public void setConferencia(Conferencia conferencia) {
        this.conferencia = conferencia;
    }

    
    

    public boolean addComentario(String comentario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
}

package co.edu.unicauca.modelos;

import java.util.Date;
import java.util.List;

/**
 * Representa una conferencia dentro del sistema de conferencias.
 * Contiene información sobre el nombre, fechas de inicio y fin, y ubicación de la conferencia.
 */

public class Conferencia {
    private Integer id;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private String ubicacion;
    private List<Integer> articulos;

    public Conferencia() {
    }

    public Conferencia(Integer id, String nombre, String fechaInicio, String fechaFin, String ubicacion, List<Integer> articulos) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ubicacion = ubicacion;
        this.articulos = articulos;
    }

    public int retornoId(){
        return this.articulos.size();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Integer> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Integer> articulos) {
        this.articulos = articulos;
    }

    
}

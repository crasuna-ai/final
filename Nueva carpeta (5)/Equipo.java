// Equipo.java
// Clase abstracta base para los diferentes tipos de equipos
public abstract class Equipo {
    // Atributos comunes
    private String serial;
    private String marca;
    private float tamano;
    private float precio;
    
    // Constructor
    public Equipo(String serial, String marca, float tamano, float precio) {
        this.serial = serial;
        this.marca = marca;
        this.tamano = tamano;
        this.precio = precio;
    }
    
    // Getters y setters
    public String getSerial() {
        return serial;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public float getTamano() {
        return tamano;
    }
    
    public void setTamano(float tamano) {
        this.tamano = tamano;
    }
    
    public float getPrecio() {
        return precio;
    }
    
    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
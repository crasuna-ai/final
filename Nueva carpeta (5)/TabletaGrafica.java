// TabletaGrafica.java
public class TabletaGrafica extends Equipo {
    // Atributos específicos
    private String almacenamiento;
    private float peso;
    
    // Constructor
    public TabletaGrafica(String serial, String marca, float tamano, float precio, 
                        String almacenamiento, float peso) {
        super(serial, marca, tamano, precio);
        this.almacenamiento = almacenamiento;
        this.peso = peso;
    }
    
    // Getters y setters
    public String getAlmacenamiento() {
        return almacenamiento;
    }
    
    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }
    
    public float getPeso() {
        return peso;
    }
    
    public void setPeso(float peso) {
        this.peso = peso;
    }
}

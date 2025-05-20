// ComputadorPortatil.java
public class ComputadorPortatil extends Equipo {
    // Atributos específicos
    private String sistemaOperativo;
    private String procesador;
    
    // Constructor
    public ComputadorPortatil(String serial, String marca, float tamano, float precio, 
                            String sistemaOperativo, String procesador) {
        super(serial, marca, tamano, precio);
        this.sistemaOperativo = sistemaOperativo;
        this.procesador = procesador;
    }
    
    // Getters y setters
    public String getSistemaOperativo() {
        return sistemaOperativo;
    }
    
    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }
    
    public String getProcesador() {
        return procesador;
    }
    
    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }
}

// EstudianteDiseno.java
public class EstudianteDiseño extends Estudiante {
    // Atributos específicos
    private String modalidadEstudio;
    private int cantidadAsignaturas;
    
    // Constructor
    public EstudianteDiseño(String cedula, String nombre, String apellido, String telefono, 
                          String modalidadEstudio, int cantidadAsignaturas, String serial) {
        super(cedula, nombre, apellido, telefono, serial);
        this.modalidadEstudio = modalidadEstudio;
        this.cantidadAsignaturas = cantidadAsignaturas;
    }
    
    // Getters y setters
    public String getModalidadEstudio() {
        return modalidadEstudio;
    }
    
    public void setModalidadEstudio(String modalidadEstudio) {
        this.modalidadEstudio = modalidadEstudio;
    }
    
    public int getCantidadAsignaturas() {
        return cantidadAsignaturas;
    }
    
    public void setCantidadAsignaturas(int cantidadAsignaturas) {
        this.cantidadAsignaturas = cantidadAsignaturas;
    }
}
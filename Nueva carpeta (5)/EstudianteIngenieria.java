// EstudianteIngenieria.java
public class EstudianteIngenieria extends Estudiante {
    // Atributos específicos
    private int semestre;
    private float promedio;
    
    // Constructor
    public EstudianteIngenieria(String cedula, String nombre, String apellido, String telefono, 
                               int semestre, float promedio, String serial) {
        super(cedula, nombre, apellido, telefono, serial);
        this.semestre = semestre;
        this.promedio = promedio;
    }
    
    // Getters y setters
    public int getSemestre() {
        return semestre;
    }
    
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    
    public float getPromedio() {
        return promedio;
    }
    
    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }
}

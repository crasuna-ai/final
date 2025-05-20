
// Estudiante.java
// Clase abstracta base para los diferentes tipos de estudiantes
public abstract class Estudiante {
    // Atributos comunes
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String serial;
    
    // Constructor
    public Estudiante(String cedula, String nombre, String apellido, String telefono, String serial) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.serial = serial;
    }
    
    // Getters y setters
    public String getCedula() {
        return cedula;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getSerial() {
        return serial;
    }
}

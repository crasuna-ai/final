// Vector.java
// Implementación de un vector dinámico genérico
public class Vector<T> {
    private Object[] elementos;
    private int tamano;
    
    // Constructor
    public Vector() {
        elementos = new Object[0];
        tamano = 0;
    }
    
    // Obtener el tamaño actual del vector
    public int getTamano() {
        return tamano;
    }
    
    // Agregar un elemento al final del vector
    public void agregar(T elemento) {
        Object[] nuevoArreglo = new Object[tamano + 1];
        
        // Copiar los elementos existentes
        for (int i = 0; i < tamano; i++) {
            nuevoArreglo[i] = elementos[i];
        }
        
        // Agregar el nuevo elemento
        nuevoArreglo[tamano] = elemento;
        
        // Actualizar el vector y su tamaño
        elementos = nuevoArreglo;
        tamano++;
    }
    
    // Eliminar un elemento en la posición indicada
    public void eliminar(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        
        Object[] nuevoArreglo = new Object[tamano - 1];
        
        // Copiar los elementos antes del índice
        for (int i = 0; i < indice; i++) {
            nuevoArreglo[i] = elementos[i];
        }
        
        // Copiar los elementos después del índice
        for (int i = indice + 1; i < tamano; i++) {
            nuevoArreglo[i - 1] = elementos[i];
        }
        
        // Actualizar el vector y su tamaño
        elementos = nuevoArreglo;
        tamano--;
    }
    
    // Obtener un elemento en la posición indicada
    @SuppressWarnings("unchecked")
    public T getElemento(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        
        return (T) elementos[indice];
    }
    
    // Método de sobrecarga para agregar múltiples elementos
    public void agregar(T... elementosNuevos) {
        for (T elemento : elementosNuevos) {
            agregar(elemento);
        }
    }
    
    // Verificar si el vector está vacío
    public boolean estaVacio() {
        return tamano == 0;
    }
    
    // Limpiar el vector (eliminar todos los elementos)
    public void limpiar() {
        elementos = new Object[0];
        tamano = 0;
    }
}

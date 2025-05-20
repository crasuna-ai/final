// SistemaGestion.java
import javax.swing.JOptionPane;

public class SistemaGestion {
    private Vector<EstudianteIngenieria> vectorIngenieros;
    private Vector<EstudianteDiseño> vectorDiseñadores;
    private Vector<ComputadorPortatil> vectorPortatiles;
    private Vector<TabletaGrafica> vectorTabletas;
    
    public SistemaGestion() {
        // Inicialización de los vectores dinámicos
        vectorIngenieros = new Vector<>();
        vectorDiseñadores = new Vector<>();
        vectorPortatiles = new Vector<>();
        vectorTabletas = new Vector<>();
    }
    
    public void iniciarSistema() {
        boolean continuar = true;
        
        while (continuar) {
            String[] opciones = {"Estudiantes de Ingeniería", "Estudiantes de Diseño", 
                               "Imprimir Inventario Total", "Salir del Programa"};
            
            int opcion = JOptionPane.showOptionDialog(
                null,
                "SISTEMA DE GESTIÓN DE PRÉSTAMO DE EQUIPOS\n" +
                "Universidad San Juan de Dios",
                "Menú Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );
            
            switch (opcion) {
                case 0: // Estudiantes de Ingeniería
                    menuIngenieria();
                    break;
                case 1: // Estudiantes de Diseño
                    menuDiseño();
                    break;
                case 2: // Imprimir Inventario Total
                    imprimirInventarioTotal();
                    break;
                case 3: // Salir del Programa
                case -1: // Si se cierra la ventana
                    continuar = false;
                    JOptionPane.showMessageDialog(null, "Gracias por utilizar el sistema.");
                    break;
            }
        }
    }
    
    private void menuIngenieria() {
        boolean volver = false;
        
        while (!volver) {
            String[] opciones = {"Registrar préstamo de equipo", 
                               "Modificar préstamo de equipo", 
                               "Devolución de equipo", 
                               "Buscar equipo", 
                               "Volver al menú principal"};
            
            int opcion = JOptionPane.showOptionDialog(
                null,
                "GESTIÓN DE ESTUDIANTES DE INGENIERÍA",
                "Menú Ingeniería",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );
            
            switch (opcion) {
                case 0: // Registrar préstamo
                    registrarPrestamoIngenieria();
                    break;
                case 1: // Modificar préstamo
                    modificarPrestamoIngenieria();
                    break;
                case 2: // Devolución de equipo
                    devolverEquipoIngenieria();
                    break;
                case 3: // Buscar equipo
                    buscarEquipoIngenieria();
                    break;
                case 4: // Volver al menú principal
                case -1: // Si se cierra la ventana
                    volver = true;
                    break;
            }
        }
    }
    
    private void menuDiseño() {
        boolean volver = false;
        
        while (!volver) {
            String[] opciones = {"Registrar préstamo de equipo", 
                               "Modificar préstamo de equipo", 
                               "Devolución de equipo", 
                               "Buscar equipo", 
                               "Volver al menú principal"};
            
            int opcion = JOptionPane.showOptionDialog(
                null,
                "GESTIÓN DE ESTUDIANTES DE DISEÑO",
                "Menú Diseño",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );
            
            switch (opcion) {
                case 0: // Registrar préstamo
                    registrarPrestamoDiseño();
                    break;
                case 1: // Modificar préstamo
                    modificarPrestamoDiseño();
                    break;
                case 2: // Devolución de equipo
                    devolverEquipoDiseño();
                    break;
                case 3: // Buscar equipo
                    buscarEquipoDiseño();
                    break;
                case 4: // Volver al menú principal
                case -1: // Si se cierra la ventana
                    volver = true;
                    break;
            }
        }
    }
    
    // Métodos para gestionar préstamos de Ingeniería
    private void registrarPrestamoIngenieria() {
        // Implementación de registro de préstamo para ingeniería
        // Si hay error de validación, el método se llamará recursivamente
        try {
            String cedula = solicitarCedula();
            
            // Verificar que el estudiante no tenga ya un equipo registrado
            if (buscarEstudianteIngenieriaPorCedula(cedula) != null) {
                JOptionPane.showMessageDialog(null, "Error: El estudiante ya tiene un equipo registrado.");
                return;
            }
            
            // Recopilar datos del estudiante
            String nombre = solicitarNombre();
            String apellido = solicitarApellido();
            String telefono = solicitarTelefono();
            int semestre = solicitarSemestre();
            float promedio = solicitarPromedio();
            
            // Recopilar datos del equipo (computador portátil)
            String serial = solicitarSerial();
            
            // Verificar que el equipo no esté ya asignado
            if (buscarComputadorPorSerial(serial) != null) {
                JOptionPane.showMessageDialog(null, "Error: El computador ya está asignado a otro estudiante.");
                return;
            }
            
            // Continuar con los datos del equipo
            String marca = solicitarMarca();
            float tamano = solicitarTamano();
            float precio = solicitarPrecio();
            String sistemaOperativo = seleccionarSistemaOperativo();
            String procesador = seleccionarProcesador();
            
            // Crear objetos y agregarlos a los vectores
            ComputadorPortatil computador = new ComputadorPortatil(serial, marca, tamano, precio, sistemaOperativo, procesador);
            EstudianteIngenieria estudiante = new EstudianteIngenieria(cedula, nombre, apellido, telefono, semestre, promedio, serial);
            
            vectorPortatiles.agregar(computador);
            vectorIngenieros.agregar(estudiante);
            
            JOptionPane.showMessageDialog(null, "Préstamo registrado exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el préstamo: " + e.getMessage());
        }
    }
    
    private void modificarPrestamoIngenieria() {
        String opcionBusqueda = JOptionPane.showInputDialog(
            "Ingrese el criterio de búsqueda:\n" +
            "1. Por cédula\n" +
            "2. Por serial"
        );
        
        if (opcionBusqueda == null) return;
        
        EstudianteIngenieria estudiante = null;
        ComputadorPortatil computador = null;
        
        switch (opcionBusqueda) {
            case "1": // Por cédula
                String cedula = solicitarCedula();
                estudiante = buscarEstudianteIngenieriaPorCedula(cedula);
                if (estudiante != null) {
                    computador = buscarComputadorPorSerial(estudiante.getSerial());
                }
                break;
            case "2": // Por serial
                String serial = solicitarSerial();
                computador = buscarComputadorPorSerial(serial);
                if (computador != null) {
                    for (int i = 0; i < vectorIngenieros.getTamano(); i++) {
                        if (vectorIngenieros.getElemento(i).getSerial().equals(serial)) {
                            estudiante = vectorIngenieros.getElemento(i);
                            break;
                        }
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                return;
        }
        
        if (estudiante == null || computador == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el registro solicitado.");
            return;
        }
        
        // Mostrar menú de modificación
        boolean continuar = true;
        while (continuar) {
            String[] opciones = {
                "Modificar datos del estudiante", 
                "Modificar datos del computador", 
                "Terminar modificación"
            };
            
            int opcion = JOptionPane.showOptionDialog(
                null,
                "MODIFICACIÓN DE PRÉSTAMO\n" +
                "Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellido() + "\n" +
                "Equipo: " + computador.getMarca() + " (Serial: " + computador.getSerial() + ")",
                "Modificación",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );
            
            switch (opcion) {
                case 0: // Modificar datos del estudiante
                    modificarDatosEstudianteIngenieria(estudiante);
                    break;
                case 1: // Modificar datos del computador
                    modificarDatosComputador(computador);
                    break;
                case 2: // Terminar modificación
                case -1: // Si se cierra la ventana
                    continuar = false;
                    break;
            }
        }
    }
    
    private void modificarDatosEstudianteIngenieria(EstudianteIngenieria estudiante) {
        String[] opciones = {
            "Nombre", "Apellido", "Teléfono", "Semestre", "Promedio"
        };
        
        int opcion = JOptionPane.showOptionDialog(
            null,
            "Seleccione el dato a modificar:",
            "Modificar estudiante",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        switch (opcion) {
            case 0: // Nombre
                estudiante.setNombre(solicitarNombre());
                break;
            case 1: // Apellido
                estudiante.setApellido(solicitarApellido());
                break;
            case 2: // Teléfono
                estudiante.setTelefono(solicitarTelefono());
                break;
            case 3: // Semestre
                estudiante.setSemestre(solicitarSemestre());
                break;
            case 4: // Promedio
                estudiante.setPromedio(solicitarPromedio());
                break;
        }
        
        if (opcion != -1) {
            JOptionPane.showMessageDialog(null, "Dato modificado exitosamente.");
        }
    }
    
    private void modificarDatosComputador(ComputadorPortatil computador) {
        String[] opciones = {
            "Marca", "Tamaño", "Precio", "Sistema Operativo", "Procesador"
        };
        
        int opcion = JOptionPane.showOptionDialog(
            null,
            "Seleccione el dato a modificar:",
            "Modificar computador",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        switch (opcion) {
            case 0: // Marca
                computador.setMarca(solicitarMarca());
                break;
            case 1: // Tamaño
                computador.setTamano(solicitarTamano());
                break;
            case 2: // Precio
                computador.setPrecio(solicitarPrecio());
                break;
            case 3: // Sistema Operativo
                computador.setSistemaOperativo(seleccionarSistemaOperativo());
                break;
            case 4: // Procesador
                computador.setProcesador(seleccionarProcesador());
                break;
        }
        
        if (opcion != -1) {
            JOptionPane.showMessageDialog(null, "Dato modificado exitosamente.");
        }
    }
    
    private void devolverEquipoIngenieria() {
        String opcionBusqueda = JOptionPane.showInputDialog(
            "Ingrese el criterio de búsqueda:\n" +
            "1. Por cédula\n" +
            "2. Por serial"
        );
        
        if (opcionBusqueda == null) return;
        
        EstudianteIngenieria estudiante = null;
        ComputadorPortatil computador = null;
        int indiceEstudiante = -1;
        int indiceComputador = -1;
        
        switch (opcionBusqueda) {
            case "1": // Por cédula
                String cedula = solicitarCedula();
                
                for (int i = 0; i < vectorIngenieros.getTamano(); i++) {
                    if (vectorIngenieros.getElemento(i).getCedula().equals(cedula)) {
                        estudiante = vectorIngenieros.getElemento(i);
                        indiceEstudiante = i;
                        
                        // Buscar el computador asociado
                        String serialEquipo = estudiante.getSerial();
                        for (int j = 0; j < vectorPortatiles.getTamano(); j++) {
                            if (vectorPortatiles.getElemento(j).getSerial().equals(serialEquipo)) {
                                computador = vectorPortatiles.getElemento(j);
                                indiceComputador = j;
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
                
            case "2": // Por serial
                String serial = solicitarSerial();
                
                for (int i = 0; i < vectorPortatiles.getTamano(); i++) {
                    if (vectorPortatiles.getElemento(i).getSerial().equals(serial)) {
                        computador = vectorPortatiles.getElemento(i);
                        indiceComputador = i;
                        
                        // Buscar el estudiante asociado
                        for (int j = 0; j < vectorIngenieros.getTamano(); j++) {
                            if (vectorIngenieros.getElemento(j).getSerial().equals(serial)) {
                                estudiante = vectorIngenieros.getElemento(j);
                                indiceEstudiante = j;
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
                
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                return;
        }
        
        if (estudiante == null || computador == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el registro solicitado.");
            return;
        }
        
        // Confirmación de devolución
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "¿Está seguro de devolver el equipo?\n" +
            "Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellido() + "\n" +
            "Equipo: " + computador.getMarca() + " (Serial: " + computador.getSerial() + ")",
            "Confirmar devolución",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Eliminar registros
            vectorIngenieros.eliminar(indiceEstudiante);
            vectorPortatiles.eliminar(indiceComputador);
            
            JOptionPane.showMessageDialog(null, "Devolución registrada exitosamente.");
        }
    }
    
    private void buscarEquipoIngenieria() {
        String opcionBusqueda = JOptionPane.showInputDialog(
            "Ingrese el criterio de búsqueda:\n" +
            "1. Por cédula\n" +
            "2. Por serial"
        );
        
        if (opcionBusqueda == null) return;
        
        EstudianteIngenieria estudiante = null;
        ComputadorPortatil computador = null;
        
        switch (opcionBusqueda) {
            case "1": // Por cédula
                String cedula = solicitarCedula();
                estudiante = buscarEstudianteIngenieriaPorCedula(cedula);
                if (estudiante != null) {
                    computador = buscarComputadorPorSerial(estudiante.getSerial());
                }
                break;
            case "2": // Por serial
                String serial = solicitarSerial();
                computador = buscarComputadorPorSerial(serial);
                if (computador != null) {
                    for (int i = 0; i < vectorIngenieros.getTamano(); i++) {
                        if (vectorIngenieros.getElemento(i).getSerial().equals(serial)) {
                            estudiante = vectorIngenieros.getElemento(i);
                            break;
                        }
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                return;
        }
        
        if (estudiante == null || computador == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el registro solicitado.");
            return;
        }
        
        // Mostrar información
        String mensaje = "INFORMACIÓN DEL PRÉSTAMO\n\n" +
                       "Datos del Estudiante:\n" +
                       "- Cédula: " + estudiante.getCedula() + "\n" +
                       "- Nombre: " + estudiante.getNombre() + " " + estudiante.getApellido() + "\n" +
                       "- Teléfono: " + estudiante.getTelefono() + "\n" +
                       "- Semestre: " + estudiante.getSemestre() + "\n" +
                       "- Promedio: " + estudiante.getPromedio() + "\n\n" +
                       "Datos del Computador:\n" +
                       "- Serial: " + computador.getSerial() + "\n" +
                       "- Marca: " + computador.getMarca() + "\n" +
                       "- Tamaño: " + computador.getTamano() + " pulgadas\n" +
                       "- Precio: $" + computador.getPrecio() + "\n" +
                       "- Sistema Operativo: " + computador.getSistemaOperativo() + "\n" +
                       "- Procesador: " + computador.getProcesador();
        
        JOptionPane.showMessageDialog(null, mensaje, "Resultado de búsqueda", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Métodos para gestionar préstamos de Diseño
    private void registrarPrestamoDiseño() {
        try {
            String cedula = solicitarCedula();
            
            // Verificar que el estudiante no tenga ya un equipo registrado
            if (buscarEstudianteDiseñoPorCedula(cedula) != null) {
                JOptionPane.showMessageDialog(null, "Error: El estudiante ya tiene un equipo registrado.");
                return;
            }
            
            // Recopilar datos del estudiante
            String nombre = solicitarNombre();
            String apellido = solicitarApellido();
            String telefono = solicitarTelefono();
            String modalidad = solicitarModalidadEstudio();
            int cantidadAsignaturas = solicitarCantidadAsignaturas();
            
            // Recopilar datos del equipo (tableta gráfica)
            String serial = solicitarSerial();
            
            // Verificar que el equipo no esté ya asignado
            if (buscarTabletaPorSerial(serial) != null) {
                JOptionPane.showMessageDialog(null, "Error: La tableta ya está asignada a otro estudiante.");
                return;
            }
            
            // Continuar con los datos del equipo
            String marca = solicitarMarca();
            float tamano = solicitarTamano();
            float precio = solicitarPrecio();
            String almacenamiento = seleccionarAlmacenamiento();
            float peso = solicitarPeso();
            
            // Crear objetos y agregarlos a los vectores
            TabletaGrafica tableta = new TabletaGrafica(serial, marca, tamano, precio, almacenamiento, peso);
            EstudianteDiseño estudiante = new EstudianteDiseño(cedula, nombre, apellido, telefono, modalidad, cantidadAsignaturas, serial);
            
            vectorTabletas.agregar(tableta);
            vectorDiseñadores.agregar(estudiante);
            
            JOptionPane.showMessageDialog(null, "Préstamo registrado exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el préstamo: " + e.getMessage());
        }
    }
    
    private void modificarPrestamoDiseño() {
        String opcionBusqueda = JOptionPane.showInputDialog(
            "Ingrese el criterio de búsqueda:\n" +
            "1. Por cédula\n" +
            "2. Por serial"
        );
        
        if (opcionBusqueda == null) return;
        
        EstudianteDiseño estudiante = null;
        TabletaGrafica tableta = null;
        
        switch (opcionBusqueda) {
            case "1": // Por cédula
                String cedula = solicitarCedula();
                estudiante = buscarEstudianteDiseñoPorCedula(cedula);
                if (estudiante != null) {
                    tableta = buscarTabletaPorSerial(estudiante.getSerial());
                }
                break;
            case "2": // Por serial
                String serial = solicitarSerial();
                tableta = buscarTabletaPorSerial(serial);
                if (tableta != null) {
                    for (int i = 0; i < vectorDiseñadores.getTamano(); i++) {
                        if (vectorDiseñadores.getElemento(i).getSerial().equals(serial)) {
                            estudiante = vectorDiseñadores.getElemento(i);
                            break;
                        }
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                return;
        }
        
        if (estudiante == null || tableta == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el registro solicitado.");
            return;
        }
        
        // Mostrar menú de modificación
        boolean continuar = true;
        while (continuar) {
            String[] opciones = {
                "Modificar datos del estudiante", 
                "Modificar datos de la tableta", 
                "Terminar modificación"
            };
            
            int opcion = JOptionPane.showOptionDialog(
                null,
                "MODIFICACIÓN DE PRÉSTAMO\n" +
                "Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellido() + "\n" +
                "Equipo: " + tableta.getMarca() + " (Serial: " + tableta.getSerial() + ")",
                "Modificación",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );
            
            switch (opcion) {
                case 0: // Modificar datos del estudiante
                    modificarDatosEstudianteDiseño(estudiante);
                    break;
                case 1: // Modificar datos de la tableta
                    modificarDatosTableta(tableta);
                    break;
                case 2: // Terminar modificación
                case -1: // Si se cierra la ventana
                    continuar = false;
                    break;
            }
        }
    }
    
    private void modificarDatosEstudianteDiseño(EstudianteDiseño estudiante) {
        String[] opciones = {
            "Nombre", "Apellido", "Teléfono", "Modalidad de estudio", "Cantidad de asignaturas"
        };
        
        int opcion = JOptionPane.showOptionDialog(
            null,
            "Seleccione el dato a modificar:",
            "Modificar estudiante",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        switch (opcion) {
            case 0: // Nombre
                estudiante.setNombre(solicitarNombre());
                break;
            case 1: // Apellido
                estudiante.setApellido(solicitarApellido());
                break;
            case 2: // Teléfono
                estudiante.setTelefono(solicitarTelefono());
                break;
            case 3: // Modalidad de estudio
                estudiante.setModalidadEstudio(solicitarModalidadEstudio());
                break;
            case 4: // Cantidad de asignaturas
                estudiante.setCantidadAsignaturas(solicitarCantidadAsignaturas());
                break;
        }
        
        if (opcion != -1) {
            JOptionPane.showMessageDialog(null, "Dato modificado exitosamente.");
        }
    }
    
    private void modificarDatosTableta(TabletaGrafica tableta) {
        String[] opciones = {
            "Marca", "Tamaño", "Precio", "Almacenamiento", "Peso"
        };
        
        int opcion = JOptionPane.showOptionDialog(
            null,
            "Seleccione el dato a modificar:",
            "Modificar tableta",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        switch (opcion) {
            case 0: // Marca
                tableta.setMarca(solicitarMarca());
                break;
            case 1: // Tamaño
                tableta.setTamano(solicitarTamano());
                break;
            case 2: // Precio
                tableta.setPrecio(solicitarPrecio());
                break;
            case 3: // Almacenamiento
                tableta.setAlmacenamiento(seleccionarAlmacenamiento());
                break;
            case 4: // Peso
                tableta.setPeso(solicitarPeso());
                break;
        }
        
        if (opcion != -1) {
            JOptionPane.showMessageDialog(null, "Dato modificado exitosamente.");
        }
    }
    
    private void devolverEquipoDiseño() {
        String opcionBusqueda = JOptionPane.showInputDialog(
            "Ingrese el criterio de búsqueda:\n" +
            "1. Por cédula\n" +
            "2. Por serial"
        );
        
        if (opcionBusqueda == null) return;
        
        EstudianteDiseño estudiante = null;
        TabletaGrafica tableta = null;
        int indiceEstudiante = -1;
        int indiceTableta = -1;
        
        switch (opcionBusqueda) {
            case "1": // Por cédula
                String cedula = solicitarCedula();
                
                for (int i = 0; i < vectorDiseñadores.getTamano(); i++) {
                    if (vectorDiseñadores.getElemento(i).getCedula().equals(cedula)) {
                        estudiante = vectorDiseñadores.getElemento(i);
                        indiceEstudiante = i;
                        
                        // Buscar la tableta asociada
                        String serialEquipo = estudiante.getSerial();
                        for (int j = 0; j < vectorTabletas.getTamano(); j++) {
                            if (vectorTabletas.getElemento(j).getSerial().equals(serialEquipo)) {
                                tableta = vectorTabletas.getElemento(j);
                                indiceTableta = j;
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
                
            case "2": // Por serial
                String serial = solicitarSerial();
                
                for (int i = 0; i < vectorTabletas.getTamano(); i++) {
                    if (vectorTabletas.getElemento(i).getSerial().equals(serial)) {
                        tableta = vectorTabletas.getElemento(i);
                        indiceTableta = i;
                        
                        // Buscar el estudiante asociado
                        for (int j = 0; j < vectorDiseñadores.getTamano(); j++) {
                            if (vectorDiseñadores.getElemento(j).getSerial().equals(serial)) {
                                estudiante = vectorDiseñadores.getElemento(j);
                                indiceEstudiante = j;
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
                
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                return;
        }
        
        if (estudiante == null || tableta == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el registro solicitado.");
            return;
        }
        
        // Confirmación de devolución
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "¿Está seguro de devolver el equipo?\n" +
            "Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellido() + "\n" +
            "Equipo: " + tableta.getMarca() + " (Serial: " + tableta.getSerial() + ")",
            "Confirmar devolución",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Eliminar registros
            vectorDiseñadores.eliminar(indiceEstudiante);
            vectorTabletas.eliminar(indiceTableta);
            
            JOptionPane.showMessageDialog(null, "Devolución registrada exitosamente.");
        }
    }
    
    private void buscarEquipoDiseño() {
        String opcionBusqueda = JOptionPane.showInputDialog(
            "Ingrese el criterio de búsqueda:\n" +
            "1. Por cédula\n" +
            "2. Por serial"
        );
        
        if (opcionBusqueda == null) return;
        
        EstudianteDiseño estudiante = null;
        TabletaGrafica tableta = null;
        
        switch (opcionBusqueda) {
            case "1": // Por cédula
                String cedula = solicitarCedula();
                estudiante = buscarEstudianteDiseñoPorCedula(cedula);
                if (estudiante != null) {
                    tableta = buscarTabletaPorSerial(estudiante.getSerial());
                }
                break;
            case "2": // Por serial
                String serial = solicitarSerial();
                tableta = buscarTabletaPorSerial(serial);
                if (tableta != null) {
                    for (int i = 0; i < vectorDiseñadores.getTamano(); i++) {
                        if (vectorDiseñadores.getElemento(i).getSerial().equals(serial)) {
                            estudiante = vectorDiseñadores.getElemento(i);
                            break;
                        }
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                return;
        }
        
        if (estudiante == null || tableta == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el registro solicitado.");
            return;
        }
        
        // Mostrar información
        String mensaje = "INFORMACIÓN DEL PRÉSTAMO\n\n" +
                       "Datos del Estudiante:\n" +
                       "- Cédula: " + estudiante.getCedula() + "\n" +
                       "- Nombre: " + estudiante.getNombre() + " " + estudiante.getApellido() + "\n" +
                       "- Teléfono: " + estudiante.getTelefono() + "\n" +
                       "- Modalidad de estudio: " + estudiante.getModalidadEstudio() + "\n" +
                       "- Cantidad de asignaturas: " + estudiante.getCantidadAsignaturas() + "\n\n" +
                       "Datos de la Tableta:\n" +
                       "- Serial: " + tableta.getSerial() + "\n" +
                       "- Marca: " + tableta.getMarca() + "\n" +
                       "- Tamaño: " + tableta.getTamano() + " pulgadas\n" +
                       "- Precio: $" + tableta.getPrecio() + "\n" +
                       "- Almacenamiento: " + tableta.getAlmacenamiento() + "\n" +
                       "- Peso: " + tableta.getPeso() + " kg";
        
        JOptionPane.showMessageDialog(null, mensaje, "Resultado de búsqueda", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Método para imprimir inventario total
    private void imprimirInventarioTotal() {
        StringBuilder inventario = new StringBuilder();
        
        // Inventario de estudiantes de ingeniería y sus computadores
        inventario.append("INVENTARIO DE EQUIPOS Y ESTUDIANTES\n\n");
        inventario.append("=== ESTUDIANTES DE INGENIERÍA ===\n");
        
        if (vectorIngenieros.getTamano() == 0) {
            inventario.append("No hay registros de préstamos para estudiantes de ingeniería.\n\n");
        } else {
            for (int i = 0; i < vectorIngenieros.getTamano(); i++) {
                EstudianteIngenieria estudiante = vectorIngenieros.getElemento(i);
                ComputadorPortatil computador = buscarComputadorPorSerial(estudiante.getSerial());
                
                inventario.append("Préstamo #").append(i + 1).append(":\n");
                inventario.append("- Estudiante: ").append(estudiante.getNombre()).append(" ").append(estudiante.getApellido());
                inventario.append(" (Cédula: ").append(estudiante.getCedula()).append(")\n");
                inventario.append("- Computador: ").append(computador.getMarca());
                inventario.append(" (Serial: ").append(computador.getSerial()).append(")\n");
                inventario.append("- Sistema Operativo: ").append(computador.getSistemaOperativo()).append("\n\n");
            }
        }
        
        // Inventario de estudiantes de diseño y sus tabletas
        inventario.append("=== ESTUDIANTES DE DISEÑO ===\n");
        
        if (vectorDiseñadores.getTamano() == 0) {
            inventario.append("No hay registros de préstamos para estudiantes de diseño.\n\n");
        } else {
            for (int i = 0; i < vectorDiseñadores.getTamano(); i++) {
                EstudianteDiseño estudiante = vectorDiseñadores.getElemento(i);
                TabletaGrafica tableta = buscarTabletaPorSerial(estudiante.getSerial());
                
                inventario.append("Préstamo #").append(i + 1).append(":\n");
                inventario.append("- Estudiante: ").append(estudiante.getNombre()).append(" ").append(estudiante.getApellido());
                inventario.append(" (Cédula: ").append(estudiante.getCedula()).append(")\n");
                inventario.append("- Tableta: ").append(tableta.getMarca());
                inventario.append(" (Serial: ").append(tableta.getSerial()).append(")\n");
                inventario.append("- Almacenamiento: ").append(tableta.getAlmacenamiento()).append("\n\n");
            }
        }
        
        // Resumen
        inventario.append("=== RESUMEN DEL INVENTARIO ===\n");
        inventario.append("Total de computadores portátiles: ").append(vectorPortatiles.getTamano()).append("\n");
        inventario.append("Total de tabletas gráficas: ").append(vectorTabletas.getTamano()).append("\n");
        inventario.append("Total de equipos prestados: ").append(vectorPortatiles.getTamano() + vectorTabletas.getTamano());
        
        JOptionPane.showMessageDialog(null, inventario.toString(), "Inventario Total", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Métodos auxiliares para validación y búsqueda
    private EstudianteIngenieria buscarEstudianteIngenieriaPorCedula(String cedula) {
        for (int i = 0; i < vectorIngenieros.getTamano(); i++) {
            if (vectorIngenieros.getElemento(i).getCedula().equals(cedula)) {
                return vectorIngenieros.getElemento(i);
            }
        }
        return null;
    }
    
    private EstudianteDiseño buscarEstudianteDiseñoPorCedula(String cedula) {
        for (int i = 0; i < vectorDiseñadores.getTamano(); i++) {
            if (vectorDiseñadores.getElemento(i).getCedula().equals(cedula)) {
                return vectorDiseñadores.getElemento(i);
            }
        }
        return null;
    }
    
    private ComputadorPortatil buscarComputadorPorSerial(String serial) {
        for (int i = 0; i < vectorPortatiles.getTamano(); i++) {
            if (vectorPortatiles.getElemento(i).getSerial().equals(serial)) {
                return vectorPortatiles.getElemento(i);
            }
        }
        return null;
    }
    
    private TabletaGrafica buscarTabletaPorSerial(String serial) {
        for (int i = 0; i < vectorTabletas.getTamano(); i++) {
            if (vectorTabletas.getElemento(i).getSerial().equals(serial)) {
                return vectorTabletas.getElemento(i);
            }
        }
        return null;
    }
    
    // Métodos de validación con recursividad
    private String solicitarCedula() {
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula:");
        
        if (cedula == null) return null; // Si el usuario cancela
        
        // Verificar que no tenga caracteres especiales
        if (!cedula.matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(null, "Error: La cédula solo puede contener números.");
            return solicitarCedula(); // Llamada recursiva
        }
        
        return cedula;
    }
    
    private String solicitarNombre() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
        
        if (nombre == null) return null; // Si el usuario cancela
        
        // Verificar que no tenga números ni caracteres especiales
        if (!nombre.matches("^[a-zA-Z\\s]+$")) {
            JOptionPane.showMessageDialog(null, "Error: El nombre solo puede contener letras y espacios.");
            return solicitarNombre(); // Llamada recursiva
        }
        
        return nombre;
    }
    
    private String solicitarApellido() {
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido:");
        
        if (apellido == null) return null; // Si el usuario cancela
        
        // Verificar que no tenga números ni caracteres especiales
        if (!apellido.matches("^[a-zA-Z\\s]+$")) {
            JOptionPane.showMessageDialog(null, "Error: El apellido solo puede contener letras y espacios.");
            return solicitarApellido(); // Llamada recursiva
        }
        
        return apellido;
    }
    
    private String solicitarTelefono() {
        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono:");
        
        if (telefono == null) return null; // Si el usuario cancela
        
        // Verificar que no tenga caracteres especiales
        if (!telefono.matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(null, "Error: El teléfono solo puede contener números.");
            return solicitarTelefono(); // Llamada recursiva
        }
        
        return telefono;
    }
    
    private int solicitarSemestre() {
        String semestreStr = JOptionPane.showInputDialog("Ingrese el número de semestre:");
        
        if (semestreStr == null) return 0; // Si el usuario cancela
        
        try {
            int semestre = Integer.parseInt(semestreStr);
            
            if (semestre <= 0) {
                JOptionPane.showMessageDialog(null, "Error: El semestre debe ser un número positivo.");
                return solicitarSemestre(); // Llamada recursiva
            }
            
            return semestre;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido.");
            return solicitarSemestre(); // Llamada recursiva
        }
    }
    
    private float solicitarPromedio() {
        String promedioStr = JOptionPane.showInputDialog("Ingrese el promedio acumulado:");
        
        if (promedioStr == null) return 0; // Si el usuario cancela
        
        try {
            float promedio = Float.parseFloat(promedioStr);
            
            if (promedio < 0 || promedio > 5) {
                JOptionPane.showMessageDialog(null, "Error: El promedio debe estar entre 0 y 5.");
                return solicitarPromedio(); // Llamada recursiva
            }
            
            return promedio;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido.");
            return solicitarPromedio(); // Llamada recursiva
        }
    }
    
    private String solicitarModalidadEstudio() {
        String[] opciones = {"Virtual", "Presencial"};
        
        int seleccion = JOptionPane.showOptionDialog(
            null,
            "Seleccione la modalidad de estudio:",
            "Modalidad de Estudio",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        if (seleccion == -1) return null; // Si el usuario cancela
        
        return opciones[seleccion];
    }
    
    private int solicitarCantidadAsignaturas() {
        String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad de asignaturas:");
        
        if (cantidadStr == null) return 0; // Si el usuario cancela
        
        try {
            int cantidad = Integer.parseInt(cantidadStr);
            
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "Error: La cantidad de asignaturas debe ser un número positivo.");
                return solicitarCantidadAsignaturas(); // Llamada recursiva
            }
            
            return cantidad;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido.");
            return solicitarCantidadAsignaturas(); // Llamada recursiva
        }
    }
    
    private String solicitarSerial() {
        String serial = JOptionPane.showInputDialog("Ingrese el serial del equipo:");
        
        if (serial == null) return null; // Si el usuario cancela
        
        // Verificar que no tenga caracteres especiales
        if (!serial.matches("^[a-zA-Z0-9]+$")) {
            JOptionPane.showMessageDialog(null, "Error: El serial solo puede contener letras y números.");
            return solicitarSerial(); // Llamada recursiva
        }
        
        return serial;
    }
    
    private String solicitarMarca() {
        String marca = JOptionPane.showInputDialog("Ingrese la marca del equipo:");
        
        if (marca == null) return null; // Si el usuario cancela
        
        // Verificar que no tenga números ni caracteres especiales
        if (!marca.matches("^[a-zA-Z\\s]+$")) {
            JOptionPane.showMessageDialog(null, "Error: La marca solo puede contener letras y espacios.");
            return solicitarMarca(); // Llamada recursiva
        }
        
        return marca;
    }
    
    private float solicitarTamano() {
        String tamanoStr = JOptionPane.showInputDialog("Ingrese el tamaño en pulgadas:");
        
        if (tamanoStr == null) return 0; // Si el usuario cancela
        
        try {
            float tamano = Float.parseFloat(tamanoStr);
            
            if (tamano <= 0) {
                JOptionPane.showMessageDialog(null, "Error: El tamaño debe ser un número positivo.");
                return solicitarTamano(); // Llamada recursiva
            }
            
            return tamano;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido.");
            return solicitarTamano(); // Llamada recursiva
        }
    }
    
    private float solicitarPrecio() {
        String precioStr = JOptionPane.showInputDialog("Ingrese el precio del equipo:");
        
        if (precioStr == null) return 0; // Si el usuario cancela
        
        try {
            float precio = Float.parseFloat(precioStr);
            
            if (precio <= 0) {
                JOptionPane.showMessageDialog(null, "Error: El precio debe ser un número positivo.");
                return solicitarPrecio(); // Llamada recursiva
            }
            
            return precio;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido.");
            return solicitarPrecio(); // Llamada recursiva
        }
    }
    
    private float solicitarPeso() {
        String pesoStr = JOptionPane.showInputDialog("Ingrese el peso en kg:");
        
        if (pesoStr == null) return 0; // Si el usuario cancela
        
        try {
            float peso = Float.parseFloat(pesoStr);
            
            if (peso <= 0) {
                JOptionPane.showMessageDialog(null, "Error: El peso debe ser un número positivo.");
                return solicitarPeso(); // Llamada recursiva
            }
            
            return peso;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido.");
            return solicitarPeso(); // Llamada recursiva
        }
    }
    
    private String seleccionarSistemaOperativo() {
        String[] opciones = {"Windows 7", "Windows 10", "Windows 11"};
        
        int seleccion = JOptionPane.showOptionDialog(
            null,
            "Seleccione el sistema operativo:",
            "Sistema Operativo",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        if (seleccion == -1) return null; // Si el usuario cancela
        
        return opciones[seleccion];
    }
    
    private String seleccionarProcesador() {
        String[] opciones = {"AMD Ryzen", "Intel® Core™ i5"};
        
        int seleccion = JOptionPane.showOptionDialog(
            null,
            "Seleccione el procesador:",
            "Procesador",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        if (seleccion == -1) return null; // Si el usuario cancela
        
        return opciones[seleccion];
    }
    
    private String seleccionarAlmacenamiento() {
        String[] opciones = {"256 GB", "512 GB", "1 TB"};
        
        int seleccion = JOptionPane.showOptionDialog(
            null,
            "Seleccione el almacenamiento:",
            "Almacenamiento",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        if (seleccion == -1) return null; // Si el usuario cancela
        
        return opciones[seleccion];
    }
}
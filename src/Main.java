import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;


public class Main {

    private static final String PATH_DOCTORES = "db/database_doctores.txt";
    private static final String PATH_PACIENTES = "db/database_pacientes.txt";
    private static final String PATH_CITAS = "db/database_citas.txt";

    private static List<Doctor> doctores;
    private static List<Paciente> pacientes;
    private static List<Cita> citas;

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        // CARGAR LOS DATOS Y CREAR LAS LISTAS CON LOS OBJETOS
        doctores = leerObjetos(PATH_DOCTORES, line -> {
            String[] parts = line.split(",");
            return parts.length == 3 ? new Doctor(parts[0], parts[1], parts[2]) : null;
        });
        pacientes = leerObjetos(PATH_PACIENTES, line -> {
            String[] parts = line.split(",");
            return parts.length == 2 ? new Paciente(parts[0], parts[1]) : null;
        });
        citas = leerObjetos(PATH_CITAS, line -> {
            String[] parts = line.split(",");
            return parts.length == 5 ? new Cita(parts[0], parts[1], parts[2], parts[3], parts[4]) : null;
        });


        while (true) {
            System.out.println("\nIngresa una de las siguientes opciones o 'E' para salir:");

            System.out.println("1. Mostrar doctores");
            System.out.println("2. Mostrar pacientes");
            System.out.println("3. Mostrar citas");

            System.out.println("4. Agregar doctor");
            System.out.println("5. Eliminar doctor");

            System.out.println("6. Agregar paciente");
            System.out.println("7. Eliminar paciente");

            System.out.println("8. Agregar cita");
            System.out.println("9. Eliminar cita");
            System.out.println("10. Crear datos de prueba");
            System.out.println("11. Eliminar todos los registros");
            try {
                System.out.print("Opción: ");
                String input = sc.next();

                // Verifica si el usuario desea salir del programa
                if (input.equalsIgnoreCase("E")) {
                    break;
                }

                // Parsear el input a un número entero y procesar la opción
                int opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        // Mostrar doctores
                        imprimirLista(doctores, System.out::println);
                        break;
                    case 2:
                        // Mostrar pacientes
                        imprimirLista(pacientes, System.out::println);
                        break;
                    case 3:
                        // Mostrar citas
                        imprimirLista(citas, System.out::println);
                        break;
                    case 4:
                        // Agregar doctor
                        agregarDoctor();
                        break;
                    case 5:
                        // Eliminar doctor
                        eliminarDoctor();
                        break;
                    case 6:
                        // Agregar paciente
                        agregarPaciente();
                        break;
                    case 7:
                        // Eliminar paciente
                        eliminarPaciente();
                        break;
                    case 8:
                        // Agregar cita
                        agregarCita();
                        break;
                    case 9:
                        // Eliminar cita
                        eliminarCita();
                        break;
                    case 10:
                        // Crear datos dummy
                        crearDatosDummy();
                        break;
                    case 11:
                        // Eliminar todos los registros
                        eliminarDatos();
                        break;
                    default:
                        // Manejar el caso en el que el número no está en las opciones
                        System.out.println("Opción no reconocida. Por favor, intenta de nuevo.");
                        break;
                }
                esperarParaVolverAlMenu();
            } catch (NumberFormatException e) {
                // Manejar el caso en el que el input no es un número
                System.out.println("Entrada inválida. Por favor, introduce un número o 'E' para salir.");
            }

        }

        System.out.println("\nGuardando y saliendo...");

        // GUARDAR LOS DATOS
        guardarObjetos(PATH_DOCTORES, doctores, doctor -> {
            return doctor.getId() + "," + doctor.getNombre() + "," + doctor.getEspecialidad();
        });
        guardarObjetos(PATH_PACIENTES, pacientes, paciente -> {
            return paciente.getId() + "," + paciente.getNombre();
        });
        guardarObjetos(PATH_CITAS, citas, cita -> {
            return cita.getId() + "," + cita.getFechaHora() + "," + cita.getMotivo() + "," + cita.getDoctor() + "," + cita.getPaciente();
        });

    }


    public static void agregarPaciente() {

        System.out.print("ID: ");
        String id = leerValidarInput();

        System.out.print("Nombre: ");
        String nombre = leerValidarInput();

        Paciente paciente = new Paciente(id, nombre);
        pacientes.add(paciente);

        System.out.println("Paciente registrado: " + paciente);
    }

    public static void eliminarPaciente() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresa el ID del paciente a eliminar: ");
        String id = sc.next();

        // Encuentra el paciente con el ID dado en la lista
        Paciente pacienteAeliminar = null;
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equalsIgnoreCase(id)) {
                pacienteAeliminar = paciente;
                break;
            }
        }

        // Si se encontró el paciente, elimínalo de la lista
        if (pacienteAeliminar != null) {
            pacientes.remove(pacienteAeliminar);
            System.out.println("Paciente eliminado: " + pacienteAeliminar);
        } else {
            System.out.println("No se encontró un paciente con el ID proporcionado.");
        }
    }

    public static void agregarDoctor(){

        System.out.print("ID: ");
        String id = leerValidarInput();

        System.out.print("Nombre: ");
        String nombre = leerValidarInput();

        System.out.print("Especialidad: ");
        String especialidad = leerValidarInput();


        Doctor doctor = new Doctor(id, nombre, especialidad);
        doctores.add(doctor);

        System.out.println("Doctor registrado: " + doctor);
    }

    public static void eliminarDoctor() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresa el ID del doctor a eliminar: ");
        String id = sc.next();

        // Encuentra el doctor con el ID dado en la lista
        Doctor doctorAeliminar = null;
        for (Doctor doctor : doctores) {
            if (doctor.getId().equalsIgnoreCase(id)) {
                doctorAeliminar = doctor;
                break;
            }
        }

        // Si se encontró el doctor, elimínalo de la lista
        if (doctorAeliminar != null) {
            doctores.remove(doctorAeliminar);
            System.out.println("Doctor eliminado: " + doctorAeliminar);
        } else {
            System.out.println("No se encontró un doctor con el ID proporcionado.");
        }
    }

    public static void agregarCita() {


        Scanner sc = new Scanner(System.in);

        System.out.println("Crear cita");

        System.out.println("Ingresa el ID de la cita");
        String idCita = sc.nextLine();
        if (idCita.equals("e")) {
            return; // Regresar al menú principal
        }

        // Pedir y validar el nombre del doctor
        Doctor doctorSeleccionado = null;
        while (doctorSeleccionado == null) {
            System.out.println("Ingresa el nombre del doctor: ");
            String nombreDoctor = sc.nextLine();
            if (nombreDoctor.equals("e")) {
                return; // Regresar al menú principal
            }
            doctorSeleccionado = doctores.stream()
                    .filter(doctor -> doctor.getNombre().equalsIgnoreCase(nombreDoctor))
                    .findFirst()
                    .orElse(null);
            if (doctorSeleccionado == null) {
                System.out.println("Doctor no encontrado, intenta de nuevo o ingresa 'e' para cancelar.");
            }
        }

        // Pedir y validar el nombre del paciente
        Paciente pacienteSeleccionado = null;
        while (pacienteSeleccionado == null) {
            System.out.println("Ingresa el nombre del paciente: ");
            String nombrePaciente = sc.nextLine();
            if (nombrePaciente.equals("e")) {
                return; // Regresar al menú principal
            }
            pacienteSeleccionado = pacientes.stream()
                    .filter(paciente -> paciente.getNombre().equalsIgnoreCase(nombrePaciente))
                    .findFirst()
                    .orElse(null);
            if (pacienteSeleccionado == null) {
                System.out.println("Paciente no encontrado, intenta de nuevo o ingresa 'e' para cancelar.");
            }
        }

        // Pedir la fecha y hora de la cita
        System.out.println("Ingresa la fecha y hora de la cita: ");
        String fechaHora = sc.nextLine();
        if (fechaHora.equals("e")) {
            return; // Regresar al menú principal
        }

        // Pedir el motivo de la cita
        System.out.println("Ingresa el motivo de la cita:");
        String motivo = sc.nextLine();
        if (motivo.equals("0")) {
            return; // Regresar al menú principal
        }

        // Crear y añadir la nueva cita a la lista de citas
        Cita nuevaCita = new Cita(idCita, fechaHora, motivo, doctorSeleccionado.getNombre(), pacienteSeleccionado.getNombre());
        citas.add(nuevaCita);

        System.out.println("Cita creada: " + nuevaCita);
    }

    public static void eliminarCita() {
        System.out.println("Eliminar cita");

        Scanner sc = new Scanner(System.in);

        System.out.println("Ingresa el ID de la cita a eliminar: ");
        String idCita = sc.nextLine();

        // Encuentra la cita con el ID dado en la lista
        Cita citaAEliminar = null;
        for (Cita cita : citas) {
            if (cita.getId().equals(idCita)) {
                citaAEliminar = cita;
                break;
            }
        }

        // Si se encontró la cita, elimínala de la lista
        if (citaAEliminar != null) {
            citas.remove(citaAEliminar);
            System.out.println("Cita eliminada: " + citaAEliminar);
        } else {
            System.out.println("No se encontró una cita con el ID proporcionado.");
        }
    }

    public static <T> List<T> leerObjetos(String path, Function<String, T> mapper) {
        List<T> objetos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                T objeto = mapper.apply(line);
                if (objeto != null) {
                    objetos.add(objeto);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
        return objetos;
    }

    public static <T> void guardarObjetos(String path, List<T> objetos, Function<T, String> toStringFunction) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (T objeto : objetos) {
                bw.write(toStringFunction.apply(objeto));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    public static <T> void imprimirLista(List<T> lista, Consumer<T> impresor) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay registros para mostrar.");
            return;
        }

        int numeroDeSimbolos = 25;
        String simbolo = "-";
        String guiones = simbolo.repeat(numeroDeSimbolos);

        String nombreClase = lista.get(0).getClass().getSimpleName();
        String titulo = switch (nombreClase) {
            case "Doctor" -> "DOCTORES";
            case "Paciente" -> "PACIENTES";
            case "Cita" -> "CITAS";
            default -> "";
        };

        String encabezado = guiones + " " + titulo + " " + guiones;
        System.out.println(encabezado);

        for (T elemento : lista) {
            impresor.accept(elemento);
        }

        String separador = simbolo.repeat(encabezado.length());
        System.out.println(separador);
    }

    public static void esperarParaVolverAlMenu() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Presiona Enter para volver al menú");
        sc.nextLine(); // Espera a que el usuario presione Enter
    }

    public static String leerValidarInput(){

        Scanner sc = new Scanner(System.in);
        String textoIngresado = sc.nextLine();
        while (textoIngresado.length() < 3) {
            System.out.println("Texto demasiado corto, intenta nuevamente...");
            textoIngresado = sc.nextLine();
        }
        return textoIngresado;
    }

    public static void crearDatosDummy(){
        // Datos de prueba para doctores
        doctores.add(new Doctor("d001", "Julia Espinosa", "Cardiología"));
        doctores.add(new Doctor("d002", "Mario Benedetti", "Pediatria"));
        doctores.add(new Doctor("d003", "Laura Pausini", "Ginecología"));
        doctores.add(new Doctor("d004", "Carlos Santana", "Medicina General"));
        doctores.add(new Doctor("d005", "Anna Molina", "Neurología"));

        // Datos de prueba para pacientes
        pacientes.add(new Paciente("p001", "Juan Pérez"));
        pacientes.add(new Paciente("p002", "Maria García"));
        pacientes.add(new Paciente("p003", "José Martínez"));
        pacientes.add(new Paciente("p004", "Laura Torres"));
        pacientes.add(new Paciente("p005", "Carlos Hernández"));

        // Datos de prueba para citas
        citas.add(new Cita("c001", "2024-03-15 08:00", "Revisión anual", "Julia Espinosa", "Juan Pérez"));
        citas.add(new Cita("c002", "2024-03-15 09:00", "Consulta pediátrica", "Mario Benedetti", "Maria García"));
        citas.add(new Cita("c003", "2024-03-15 10:00", "Control prenatal", "Laura Pausini", "José Martínez"));
        citas.add(new Cita("c004", "2024-03-15 11:00", "Consulta general", "Carlos Santana", "Laura Torres"));
        citas.add(new Cita("c005", "2024-03-15 12:00", "Revisión neurológica", "Anna Molina", "Carlos Hernández"));

        System.out.println("Datos de prueba cargados correctamente.");
    }

    public static void eliminarDatos(){
        // Limpiar las listas existentes
        doctores.clear();
        pacientes.clear();
        citas.clear();

        System.out.println("Registros eliminados correctamente.");
    }
}



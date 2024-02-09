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

    public static <T> void imprimirLista( List<T> lista, Consumer<T> impresor) {

        int numeroDeSimbolos = 25;
        String simbolo = "=";
        String guiones = simbolo.repeat(numeroDeSimbolos);

        String nombreClase = lista.get(0).getClass().getSimpleName();

        String titulo = switch (nombreClase) {
            case "Doctor" -> "DOCTORES";
            case "Paciente" -> "PACIENTES";
            case "Cita" -> "CITAS";
            default -> "";
        };

        String encabezado = guiones + " " + titulo + " " + guiones;

        System.out.println();
        System.out.println(encabezado);

        for (T elemento : lista) {
            impresor.accept(elemento);
        }

        String separador = simbolo.repeat(encabezado.length());
        System.out.println(separador);

    }

    public static void agregarDoctor(){
        System.out.println("Crear doctor");
    }

    public static void agregarPaciente() {

        Scanner sc = new Scanner(System.in);


        System.out.println("Ingresa el nombre del paciente:");
        String textoIngresado = sc.nextLine();
        while (textoIngresado.length() < 3) {
            System.out.println("Texto demasiado corto, intenta nuevamente...");
            textoIngresado = sc.nextLine();
        }

        Paciente paciente = new Paciente("000", textoIngresado);
        pacientes.add(paciente);

        System.out.println("Paciente registrado: " + paciente);
    }


    public static void agregarCita(){
        System.out.println("Crear cita");
    }

    public static void esperarParaVolverAlMenu() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Presiona Enter para volver al menú");
        sc.nextLine(); // Espera a que el usuario presione Enter
    }

    // Funciones pendientes
    public static void eliminarDoctor(){
        System.out.println("Eliminando doctor");
    }

    public static void eliminarPaciente(){
        System.out.println("Eliminando paciente");
    }

    public static void eliminarCita(){
        System.out.println("Eliminando cita");
    }






// Cambiar IDs para que sean automaticos e incrementales.
}



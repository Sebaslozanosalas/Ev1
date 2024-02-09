import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String PATH_DOCTORES = "db/database_doctores.txt";
    private static final String PATH_PACIENTES = "db/database_pacientes.txt";
    private static final String PATH_CITAS = "db/database_citas.txt";


    public static void main(String[] args) {

        // crear o leer el archivo
        List<Doctor> doctores = leerDoctores();
        List<Paciente> pacientes = leerPacientes();
        List<Cita> citas = leerCitas();


        System.out.println("------- DOCTORES ---------");
        for (Doctor doctor : doctores) {
            System.out.println(doctor);
        }

        System.out.println("------- PACIENTES ---------");
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }

        System.out.println("------- CITAS ---------");
        for (Cita cita : citas) {
            System.out.println(cita);
        }

    }

    // ------------------------------------  DOCTORES -------------------

    public static List<Doctor> leerDoctores(){

        // crear lista de doctores
        List<Doctor> doctores = new ArrayList<>();

        try {
            File file = new File(PATH_DOCTORES);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) { // loop para leer linea por linea
                    String[] parts = line.split(",");
                    if (parts.length == 3) { // si hay 3 columnas
                        Doctor doctor = new Doctor(parts[0], parts[1], parts[2]); // crear doctor
                        doctores.add(doctor); // agregar doctor a la lista
                    }
                }
                br.close();
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al cargar los contactos: " + e.getMessage());
        }

        return doctores;
    }

    public static void guardarDoctores(List<Doctor> doctores){

        // guardar doctores a la base de datos
        File file = new File(PATH_DOCTORES);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Doctor doctor : doctores) {
                String linea = doctor.getId() + "," + doctor.getNombre() + "," + doctor.getEspecialidad();
                bw.write(linea);
                bw.newLine(); // Para asegurar que cada doctor esté en una línea nueva
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los doctores: " + e.getMessage());
        }
    }

    // ------------------------------------  PACIENTES -------------------

    public static List<Paciente> leerPacientes(){

        // crear lista de doctores
        List<Paciente> pacientes = new ArrayList<>();

        try {
            File file = new File(PATH_PACIENTES);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) { // loop para leer linea por linea
                    String[] parts = line.split(",");
                    if (parts.length == 2) { // si hay 3 columnas
                        Paciente paciente = new Paciente(parts[0], parts[1]); // crear doctor
                        pacientes.add(paciente); // agregar doctor a la lista
                    }
                }
                br.close();
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al cargar los contactos: " + e.getMessage());
        }

        return pacientes;
    }

    public static void guardarPacientes(List<Paciente> pacientes){

        // guardar pacientes a la base de datos
        File file = new File(PATH_PACIENTES);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Paciente paciente : pacientes) {
                String linea = paciente.getId() + "," + paciente.getNombre();
                bw.write(linea);
                bw.newLine(); // Para asegurar que cada doctor esté en una línea nueva
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los doctores: " + e.getMessage());
        }
    }


    // ------------------------------------  CITAS -------------------

    public static List<Cita> leerCitas(){

        // crear lista de citas
        List<Cita> citas = new ArrayList<>();

        try {
            File file = new File(PATH_CITAS);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) { // loop para leer linea por linea
                    String[] parts = line.split(",");
                    if (parts.length == 5) { // si hay 3 columnas
                        Cita cita = new Cita(parts[0], parts[1], parts[2], parts[3], parts[4]); // crear doctor
                        citas.add(cita); // agregar doctor a la lista
                    }
                }
                br.close();
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al cargar los contactos: " + e.getMessage());
        }

        return citas;
    }

    public static void guardarCitas(List<Cita> citas){

        // guardar pacientes a la base de datos
        File file = new File(PATH_CITAS);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Cita cita : citas) {
                String linea = cita.getId() + "," + cita.getFechaHora() + "," + cita.getMotivo() + "," + cita.getDoctor() + "," + cita.getPaciente();
                bw.write(linea);
                bw.newLine(); // Para asegurar que cada doctor esté en una línea nueva
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los doctores: " + e.getMessage());
        }
    }


}



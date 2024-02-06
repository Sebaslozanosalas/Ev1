import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Doctor doctor = new Doctor("D001", "Dr. Juan Pérez", "Cardiología");

        Paciente paciente = new Paciente("P001", "Ana Gómez");

        LocalDateTime fechaHoraCita = LocalDateTime.of(2024, 2, 10, 14, 30);
        Cita cita = new Cita("C001", fechaHoraCita, "Consulta de seguimiento", doctor, paciente);

        System.out.println("Doctor: " + doctor.getNombre() + ", Especialidad: " + doctor.getEspecialidad());
        System.out.println("Paciente: " + paciente.getNombre());
        System.out.println("Cita: " + cita.getFechaHora().toString() + ", Motivo: " + cita.getMotivo() +
                ", Doctor: " + cita.getDoctor().getNombre() +
                ", Paciente: " + cita.getPaciente().getNombre());

        System.out.println("Develop branch changes 2");
    }
}

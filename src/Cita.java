import java.time.LocalDateTime;

public class Cita {
    private String id;
    private String fechaHora;
    private String motivo;
    private String doctor;
    private String paciente;

    public Cita(String id, String fechaHora, String motivo, String doctor, String paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String getId() {
        return id;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getPaciente() {
        return paciente;
    }

    @Override
    public String toString() {
        return this.id + "\t" + this.fechaHora  + "\t" + this.motivo  + "\t" + this.doctor  + "\t" + this.paciente;
    }
}

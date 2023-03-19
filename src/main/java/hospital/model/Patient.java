package hospital.model;

import java.util.Objects;

public class Patient {
    private Long id;
    private Doctor doctor;
    private String name;
    private String diagnosis;

    public Patient() {
    }

    public Patient(Doctor doctor, String name, String diagnosis) {
        this.doctor = doctor;
        this.name = name;
        this.diagnosis = diagnosis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id)
                && Objects.equals(doctor, patient.doctor)
                && Objects.equals(name, patient.name)
                && Objects.equals(diagnosis, patient.diagnosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctor, name, diagnosis);
    }

    @Override
    public String toString() {
        return "Patient{"
                + "id=" + id
                + ", doctor=" + doctor
                + ", name='" + name + '\''
                + ", diagnosis='" + diagnosis + '\''
                + '}';
    }
}

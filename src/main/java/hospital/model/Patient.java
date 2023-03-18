package hospital.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patient {
    private Long id;
    private Doctor doctor;
    private String name;
    private String diagnosis;
    private List<Medicine> medicines;

    public Patient() {
        medicines = new ArrayList<>();
    }

    public Patient(Doctor doctor, String name, String diagnosis) {
        this.doctor = doctor;
        this.name = name;
        this.diagnosis = diagnosis;
        this.medicines = new ArrayList<>();
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

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
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
                && Objects.equals(diagnosis, patient.diagnosis)
                && Objects.equals(medicines, patient.medicines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctor, name, diagnosis, medicines);
    }
}

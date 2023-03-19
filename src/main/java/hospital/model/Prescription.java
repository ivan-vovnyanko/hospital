package hospital.model;

public class Prescription {
    private Long id;
    private Medicine medicine;
    private int count;
    private Patient patient;
    private Doctor doctorPrescribed;

    public Prescription() {
    }

    public Prescription(Medicine medicine, int count, Patient patient, Doctor doctorPrescribed) {
        this.medicine = medicine;
        this.count = count;
        this.patient = patient;
        this.doctorPrescribed = doctorPrescribed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctorPrescribed() {
        return doctorPrescribed;
    }

    public void setDoctorPrescribed(Doctor doctorPrescribed) {
        this.doctorPrescribed = doctorPrescribed;
    }

    @Override
    public String toString() {
        return "Prescription{"
                + "id=" + id
                + ", medicine=" + medicine
                + ", count=" + count
                + ", patient=" + patient
                + ", doctorPrescribed=" + doctorPrescribed
                + '}';
    }
}

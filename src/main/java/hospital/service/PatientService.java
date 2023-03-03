package hospital.service;

import hospital.model.Patient;
import java.util.List;

public interface PatientService extends GenericService<Patient> {
    List<Patient> getPatientsByDoctor(Long doctorId);

    List<Patient> getPatientsWithoutMedicine(Long medicineId);

    List<Patient> getPatientsWithMedicine(Long medicineId);

    public List<Patient> filterPatientsByDoctor(List<Patient> patients, Long doctorId);
}

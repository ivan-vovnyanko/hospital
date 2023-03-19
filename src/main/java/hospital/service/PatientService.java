package hospital.service;

import hospital.model.Patient;
import java.util.List;

public interface PatientService extends GenericService<Patient> {
    List<Patient> getPatientsByDoctor(Long doctorId);
}

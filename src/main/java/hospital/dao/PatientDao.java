package hospital.dao;

import hospital.model.Patient;
import java.util.List;

public interface PatientDao extends GenericDao<Patient> {
    List<Patient> getPatientsByDoctor(Long doctorId);
}

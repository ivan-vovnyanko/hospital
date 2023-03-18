package hospital.dao;

import hospital.model.Medicine;
import hospital.model.Patient;
import java.util.List;

public interface MedicineDao extends GenericDao<Medicine> {
    List<Patient> getPatientsByMedicine(Long medicineId);

    boolean unlinkPatient(Long medicineId, Long patientId);
}

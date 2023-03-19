package hospital.dao;

import hospital.model.Prescription;
import java.util.List;

public interface PrescriptionDao extends GenericDao<Prescription> {
    List<Prescription> getByPatient(Long patientId);

    void deleteAllByPatient(Long patientId);

    void deleteAllByDoctor(Long doctorId);

    void deleteAllByMedicine(Long medicineId);
}

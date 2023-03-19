package hospital.service;

import hospital.model.Prescription;
import java.util.List;

public interface PrescriptionService extends GenericService<Prescription> {
    List<Prescription> getByPatient(Long patientId);

    void deleteAllByPatient(Long patientId);

    void deleteAllByDoctor(Long doctorId);

    void deleteAllByMedicine(Long medicineId);
}

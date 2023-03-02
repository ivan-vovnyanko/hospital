package hospital.service;

import hospital.model.Medicine;

public interface MedicineService extends GenericService<Medicine> {
    boolean unlinkPatient(Long medicineId, Long patientId);
}

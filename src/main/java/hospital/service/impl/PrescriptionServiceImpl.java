package hospital.service.impl;

import hospital.dao.PrescriptionDao;
import hospital.lib.Inject;
import hospital.lib.Service;
import hospital.model.Prescription;
import hospital.service.PrescriptionService;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    private static final Logger logger = LogManager.getLogger(PrescriptionService.class);
    @Inject
    private PrescriptionDao prescriptionDao;

    @Override
    public Prescription create(Prescription element) {
        logger.info("Create prescription method was called " + element);
        return prescriptionDao.create(element);
    }

    @Override
    public Prescription get(Long id) {
        return prescriptionDao.get(id).orElseThrow(() -> {
            logger.error("Can't find prescription by id " + id);
            throw new NoSuchElementException("Can't find prescription by id " + id); });
    }

    @Override
    public Prescription update(Prescription element) {
        logger.info("Update prescription method was called " + element);
        return prescriptionDao.update(element);
    }

    @Override
    public List<Prescription> getAll() {
        return prescriptionDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Delete prescription method was called " + id);
        return prescriptionDao.delete(id);
    }

    @Override
    public List<Prescription> getByPatient(Long patientId) {
        return prescriptionDao.getByPatient(patientId);
    }

    @Override
    public void deleteAllByPatient(Long patientId) {
        prescriptionDao.deleteAllByPatient(patientId);
    }

    @Override
    public void deleteAllByDoctor(Long doctorId) {
        prescriptionDao.deleteAllByDoctor(doctorId);
    }

    @Override
    public void deleteAllByMedicine(Long medicineId) {
        prescriptionDao.deleteAllByMedicine(medicineId);
    }
}

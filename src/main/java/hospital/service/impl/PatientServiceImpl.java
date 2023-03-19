package hospital.service.impl;

import hospital.dao.PatientDao;
import hospital.exception.WrongInputException;
import hospital.lib.Inject;
import hospital.lib.Service;
import hospital.model.Patient;
import hospital.service.DoctorService;
import hospital.service.PatientService;
import hospital.service.PrescriptionService;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class PatientServiceImpl implements PatientService {
    private static final Logger logger = LogManager.getLogger();
    @Inject
    private PatientDao patientDao;
    @Inject
    private DoctorService doctorService;
    @Inject
    private PrescriptionService prescriptionService;

    @Override
    public Patient create(Patient element) {
        logger.info("Create method was called. Name - " + element.getName() + ", diagnosis - "
                + element.getDiagnosis());
        if (checkFields(element)) {
            return patientDao.create(element);
        }
        return element;
    }

    @Override
    public Patient get(Long id) {
        return patientDao.get(id).orElseThrow(() -> {
            logger.error("Couldn't get patient with id - " + id);
            throw new NoSuchElementException("Couldn't get patient with id - " + id); });
    }

    @Override
    public Patient update(Patient element) {
        logger.info("Update method was called. Name - " + element.getName() + ", diagnosis - "
                + element.getDiagnosis() + ", id - " + element.getId());
        if (checkFields(element)) {
            return patientDao.update(element);
        }
        return element;
    }

    @Override
    public List<Patient> getAll() {
        return patientDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Delete method was called. Id - " + id);
        prescriptionService.deleteAllByPatient(id);
        return patientDao.delete(id);
    }

    @Override
    public List<Patient> getPatientsByDoctor(Long doctorId) {
        return patientDao.getPatientsByDoctor(doctorId);
    }

    private boolean checkFields(Patient patient) {
        if (!patient.getName().matches("^[a-zA-Z ]+$")) {
            logger.error("Invalid characters in name!");
            throw new WrongInputException("Invalid characters in name!");
        }
        if (patient.getName().length() < 6 || patient.getName().length() > 40) {
            logger.error("Very few or many characters in the name!");
            throw new WrongInputException("Very few or many characters in the name!");
        }
        return true;
    }
}

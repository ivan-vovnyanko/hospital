package hospital.service.impl;

import hospital.dao.PatientDao;
import hospital.exception.WrongInputException;
import hospital.lib.Inject;
import hospital.lib.Service;
import hospital.model.Patient;
import hospital.service.DoctorService;
import hospital.service.PatientService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class PatientServiceImpl implements PatientService {
    private static final Logger logger = LogManager.getLogger();
    @Inject
    private PatientDao patientDao;
    @Inject
    private DoctorService doctorService;

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
        if (patientDao.get(id).isPresent()) {
            return patientDao.get(id).get();
        } else {
            logger.error("Couldn't get patient with id - " + id);
            throw new NoSuchElementException("Couldn't get patient with id - " + id);
        }
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
        return patientDao.delete(id);
    }

    @Override
    public List<Patient> getPatientsByDoctor(Long doctorId) {
        return patientDao.getPatientsByDoctor(doctorId);
    }

    @Override
    public List<Patient> getPatientsWithoutMedicine(Long medicineId) {
        return patientDao.getPatientsWithoutMedicine(medicineId);
    }

    @Override
    public List<Patient> getPatientsWithMedicine(Long medicineId) {
        return patientDao.getPatientsWithMedicine(medicineId);
    }

    @Override
    public List<Patient> filterPatientsByDoctor(List<Patient> patients, Long doctorId) {
        return patients.stream()
                .filter(patient -> {
                    if (patient.getDoctor() != null) {
                        return patient.getDoctor().getId().equals(doctorId);
                    }
                    return false;
                })
                .collect(Collectors.toList());
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

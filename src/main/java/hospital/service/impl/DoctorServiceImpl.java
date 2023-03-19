package hospital.service.impl;

import hospital.dao.DoctorDao;
import hospital.exception.WrongInputException;
import hospital.lib.Inject;
import hospital.lib.Service;
import hospital.model.Doctor;
import hospital.service.DoctorService;
import hospital.service.DoctorValidator;
import hospital.service.PrescriptionService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class DoctorServiceImpl implements DoctorService {
    private static final Logger logger = LogManager.getLogger();
    @Inject
    private DoctorDao doctorDao;
    @Inject
    private PrescriptionService prescriptionService;
    @Inject
    private DoctorValidator doctorValidator;

    @Override
    public Doctor create(Doctor element) {
        logger.info("Create method was called. Name - " + element.getName() + ", birthday - "
                + element.getBirthDate() + ", login - " + element.getLogin());
        if (doctorDao.isPresentByLogin(element.getLogin())) {
            logger.error("Attempt to register with an existing login. Login - "
                    + element.getLogin());
            throw new WrongInputException("This login already used!");
        }
        if (doctorValidator.isValid(element)) {
            return doctorDao.create(element);
        }
        return element;
    }

    @Override
    public Doctor get(Long id) {
        return doctorDao.get(id).orElseThrow(() -> {
            logger.error("Can't get Doctor with id - " + id);
            throw new NoSuchElementException("Can't get Doctor with id - " + id); });
    }

    @Override
    public Doctor update(Doctor element) {
        logger.info("Update method was called. Name - " + element.getName() + ", birthday - "
                + element.getBirthDate() + ", login - " + element.getLogin() + ", id - "
                + element.getId());
        if (doctorValidator.isValid(element)) {
            return doctorDao.update(element);
        }
        return element;
    }

    @Override
    public List<Doctor> getAll() {
        return doctorDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Delete method was called. Id - " + id);
        prescriptionService.deleteAllByDoctor(id);
        return doctorDao.delete(id);
    }

    @Override
    public Optional<Doctor> findDoctorByLogin(String login) {
        List<Doctor> doctors = doctorDao.getAll();
        return doctors.stream().filter(i -> i.getLogin().equals(login)).findFirst();
    }
}

package hospital.service.impl;

import hospital.dao.DoctorDao;
import hospital.exception.WrongInputException;
import hospital.lib.Inject;
import hospital.lib.Service;
import hospital.model.Doctor;
import hospital.service.DoctorService;
import java.time.LocalDate;
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

    @Override
    public Doctor create(Doctor element) {
        logger.info("Create method was called. Name - " + element.getName() + ", birthday - "
                + element.getBirthDate() + ", login - " + element.getLogin());
        if (findDoctorByLogin(element.getLogin()).isPresent()) {
            logger.error("Attempt to register with an existing login. Login - "
                    + element.getLogin());
            throw new WrongInputException("This login already used!");
        }
        if (checkFields(element)) {
            return doctorDao.create(element);
        }
        return element;
    }

    @Override
    public Doctor get(Long id) {
        if (doctorDao.get(id).isPresent()) {
            return doctorDao.get(id).get();
        } else {
            logger.error("Can't get Doctor with id - " + id);
            throw new NoSuchElementException("Can't get Doctor with id - " + id);
        }
    }

    @Override
    public Doctor update(Doctor element) {
        logger.info("Update method was called. Name - " + element.getName() + ", birthday - "
                + element.getBirthDate() + ", login - " + element.getLogin() + ", id - "
                + element.getId());
        if (checkFields(element)) {
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
        return doctorDao.delete(id);
    }

    @Override
    public Optional<Doctor> findDoctorByLogin(String login) {
        List<Doctor> doctors = doctorDao.getAll();
        return doctors.stream().filter(i -> i.getLogin().equals(login)).findFirst();
    }

    private boolean checkFields(Doctor doctor) {
        if (!doctor.getName().matches("^[a-zA-Z ]+$")) {
            logger.error("Attempt to register with incorrect name. Name - " + doctor.getName());
            throw new WrongInputException("Invalid characters in name!");
        }
        if (doctor.getName().length() < 6 || doctor.getName().length() > 40) {
            logger.error("Attempt to register with incorrect name. Name - " + doctor.getName());
            throw new WrongInputException("Very few or many characters in the name!");
        }
        if (LocalDate.now().minusYears(18).isBefore(doctor.getBirthDate())) {
            logger.error("Attempt to register with incorrect birthdate. Birthday - "
                    + doctor.getBirthDate());
            throw new WrongInputException("You are very young, or from the future!");
        }
        if (!doctor.getLogin().matches("^[a-zA-Z0-9]+$")) {
            logger.error("Attempt to register with incorrect login. Login - " + doctor.getLogin());
            throw new WrongInputException("Invalid characters in login!");
        }
        if (doctor.getLogin().length() < 6 || doctor.getLogin().length() > 20) {
            logger.error("Attempt to register with incorrect login. Login - " + doctor.getLogin());
            throw new WrongInputException("Very few or many characters in the login!");
        }
        if (!doctor.getPassword().matches("^[a-zA-Z0-9]+$")) {
            logger.error("Attempt to register with incorrect password");
            throw new WrongInputException("Invalid characters in password!");
        }
        if (doctor.getPassword().length() < 6 || doctor.getPassword().length() > 20) {
            logger.error("Attempt to register with incorrect password");
            throw new WrongInputException("Very few or many characters in the password!");
        }
        return true;
    }
}

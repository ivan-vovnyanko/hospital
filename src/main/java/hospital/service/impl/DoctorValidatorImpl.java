package hospital.service.impl;

import hospital.exception.WrongInputException;
import hospital.lib.Service;
import hospital.model.Doctor;
import hospital.service.DoctorValidator;
import java.time.LocalDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class DoctorValidatorImpl implements DoctorValidator {
    private static final Logger logger = LogManager.getLogger(DoctorValidator.class);

    @Override
    public boolean isValid(Doctor doctor) {
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

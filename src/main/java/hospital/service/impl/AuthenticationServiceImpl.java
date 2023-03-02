package hospital.service.impl;

import hospital.exception.AuthenticationException;
import hospital.lib.Inject;
import hospital.lib.Service;
import hospital.model.Doctor;
import hospital.service.AuthenticationService;
import hospital.service.DoctorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = LogManager.getLogger();
    @Inject
    private DoctorService doctorService;

    @Override
    public Doctor login(String login, String password) {
        logger.info("Login method was called. Login - " + login);
        if (doctorService.findDoctorByLogin(login).isEmpty()) {
            logger.error("An unsuccessful authorization attempt has occurred. Login - " + login);
            throw new AuthenticationException("Invalid login or password");
        }
        if (doctorService.findDoctorByLogin(login).get().getPassword().equals(password)) {
            return doctorService.findDoctorByLogin(login).get();
        } else {
            logger.error("An unsuccessful authorization attempt has occurred. Login - " + login);
            throw new AuthenticationException("Invalid login or password");
        }
    }
}

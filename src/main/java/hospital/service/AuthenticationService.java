package hospital.service;

import hospital.model.Doctor;

public interface AuthenticationService {
    Doctor login(String login, String password);
}

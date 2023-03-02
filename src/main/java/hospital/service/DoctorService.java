package hospital.service;

import hospital.model.Doctor;
import java.util.Optional;

public interface DoctorService extends GenericService<Doctor> {
    Optional<Doctor> findDoctorByLogin(String login);
}

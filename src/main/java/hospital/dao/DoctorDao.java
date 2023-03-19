package hospital.dao;

import hospital.model.Doctor;

public interface DoctorDao extends GenericDao<Doctor> {
    boolean isPresentByLogin(String login);
}

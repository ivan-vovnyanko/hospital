package hospital.dao.impl;

import hospital.dao.DoctorDao;
import hospital.exception.DataProcessingException;
import hospital.lib.Dao;
import hospital.model.Doctor;
import hospital.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Dao
public class DoctorDaoImpl implements DoctorDao {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Doctor create(Doctor doctor) {
        String query = "INSERT INTO doctors (name, birth_date, login, password) "
                + "VALUES (?, ?, ?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, doctor.getName());
            statement.setDate(2, Date.valueOf(doctor.getBirthDate()));
            statement.setString(3, doctor.getLogin());
            statement.setString(4, doctor.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                doctor.setId(resultSet.getLong(1));
            }
            return doctor;
        } catch (SQLException e) {
            logger.error("Couldn't create new doctor. Name: " + doctor.getName()
                    + ", login: " + doctor.getLogin()
                    + ", birthdate: " + doctor.getBirthDate());
            throw new DataProcessingException("Couldn't create new doctor with name "
                    + doctor.getName() + e);
        }
    }

    @Override
    public Doctor update(Doctor doctor) {
        String query = "UPDATE doctors "
                + "SET name = ?, birth_date = ?, login = ?, password = ? "
                + "WHERE doctor_id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                    PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctor.getName());
            statement.setDate(2, Date.valueOf(doctor.getBirthDate()));
            statement.setString(3, doctor.getLogin());
            statement.setString(4, doctor.getPassword());
            statement.setLong(5, doctor.getId());
            statement.executeUpdate();
            return doctor;
        } catch (SQLException e) {
            logger.error("Couldn't update doctor with id - " + doctor.getId()
                    + ". Name - " + doctor.getName() + ", login - " + doctor.getLogin()
                    + ", birthdate - " + doctor.getBirthDate());
            throw new DataProcessingException("Couldn't update doctor by id = "
                    + doctor.getId() + e);
        }
    }

    @Override
    public boolean delete(Long doctorId) {
        String query = "UPDATE doctors "
                + "SET is_deleted = true "
                + "WHERE doctor_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, doctorId);
            deleteDoctorFromPatient(doctorId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Couldn't delete doctor by id = " + doctorId);
            throw new DataProcessingException("Couldn't delete doctor by id = " + doctorId + e);
        }
    }

    @Override
    public Optional<Doctor> get(Long doctorId) {
        String query = "SELECT doctor_id, name, birth_date, login, password "
                + "FROM doctors WHERE is_deleted = false AND doctor_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, doctorId);
            ResultSet resultSet = statement.executeQuery();
            Doctor doctor = null;
            if (resultSet.next()) {
                doctor = getDoctorFromResultSet(resultSet);
            }
            return Optional.ofNullable(doctor);
        } catch (SQLException e) {
            logger.error("Couldn't get doctor with id = " + doctorId);
            throw new DataProcessingException("Couldn't get doctor with id = " + doctorId + e);
        }

    }

    @Override
    public List<Doctor> getAll() {
        String query = "SELECT doctor_id, name, birth_date, login, password "
                + "FROM doctors WHERE is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Doctor> doctors = new ArrayList<>();
            while (resultSet.next()) {
                doctors.add(getDoctorFromResultSet(resultSet));
            }
            return doctors;
        } catch (SQLException e) {
            logger.error("Couldn't get all doctors");
            throw new DataProcessingException("Couldn't get all doctors." + e);
        }
    }

    private Doctor getDoctorFromResultSet(ResultSet resultSet) {
        try {
            Doctor doctor = new Doctor();
            doctor.setId(resultSet.getLong("doctor_id"));
            doctor.setName(resultSet.getString("name"));
            doctor.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
            doctor.setLogin(resultSet.getString("login"));
            doctor.setPassword(resultSet.getString("password"));
            return doctor;
        } catch (SQLException e) {
            logger.error("Couldn't get doctor from result set.");
            throw new DataProcessingException("Couldn't get doctor from result set." + e);
        }
    }

    private void deleteDoctorFromPatient(Long doctorId) {
        String query = "UPDATE patients "
                + "SET doctor_id = null "
                + "WHERE doctor_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, doctorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Couldn't delete doctor from patient. Doctor id: " + doctorId);
            throw new DataProcessingException("Couldn't delete doctor from patient. Doctor id: "
                    + doctorId + e);
        }
    }
}

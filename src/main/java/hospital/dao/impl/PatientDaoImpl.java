package hospital.dao.impl;

import hospital.dao.DoctorDao;
import hospital.dao.PatientDao;
import hospital.exception.DataProcessingException;
import hospital.lib.Dao;
import hospital.lib.Inject;
import hospital.model.Patient;
import hospital.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Dao
public class PatientDaoImpl implements PatientDao {
    private static final Logger logger = LogManager.getLogger();
    @Inject
    private DoctorDao doctorDao;

    @Override
    public Patient create(Patient patient) {
        String query = "INSERT INTO patients (name, diagnosis, doctor_id) "
                + "VALUES (?, ?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getDiagnosis());
            statement.setLong(3, patient.getDoctor().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                patient.setId(resultSet.getLong(1));
            }
            return patient;
        } catch (SQLException e) {
            logger.error("Couldn't create new patient with name " + patient.getName()
                    + ", diagnosis - " + patient.getDiagnosis()
                    + ", doctor id - " + patient.getDoctor().getId());
            throw new DataProcessingException("Couldn't create new patient with name "
                    + patient.getName() + e);
        }
    }

    @Override
    public boolean delete(Long patientId) {
        String query = "UPDATE patients "
                + "SET is_deleted = true "
                + "WHERE patient_id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, patientId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Couldn't delete patient by id" + patientId);
            throw new DataProcessingException("Couldn't delete patient by id" + patientId + e);
        }
    }

    @Override
    public Patient update(Patient patient) {
        String query = "UPDATE patients "
                + "SET doctor_id = ?, name = ?, diagnosis = ? "
                + "WHERE patient_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, patient.getDoctor().getId());
            statement.setString(2, patient.getName());
            statement.setString(3, patient.getDiagnosis());
            statement.setLong(4, patient.getId());
            statement.executeUpdate();
            return patient;
        } catch (SQLException e) {
            logger.error("Couldn't update new patient with id - " + patient.getId()
                    + ", name " + patient.getName() + ", diagnosis - " + patient.getDiagnosis()
                    + ", doctor id - " + patient.getDoctor().getId());
            throw new DataProcessingException("Couldn't update patient with id "
                    + patient.getId() + e);
        }
    }

    @Override
    public List<Patient> getAll() {
        String query = "SELECT * "
                + "FROM patients "
                + "WHERE is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Patient> patients = new ArrayList<>();
            while (resultSet.next()) {
                Patient patient = getPatientByResultSet(resultSet);
                patients.add(patient);
            }
            return patients;
        } catch (SQLException e) {
            logger.error("Couldn't get all patients");
            throw new DataProcessingException("Couldn't get all patients" + e);
        }
    }

    @Override
    public Optional<Patient> get(Long patientId) {
        String query = "SELECT * "
                + "FROM patients "
                + "WHERE patient_id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Patient patient = null;
            if (resultSet.next()) {
                patient = getPatientByResultSet(resultSet);
            }
            return Optional.ofNullable(patient);
        } catch (SQLException e) {
            logger.error("Couldn't get patient by id = " + patientId);
            throw new DataProcessingException("Couldn't get patient by id = " + patientId + e);
        }
    }

    @Override
    public List<Patient> getPatientsByDoctor(Long doctorId) {
        String query = "SELECT * "
                + "FROM patients "
                + "WHERE is_deleted = false and doctor_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Patient> patients = new ArrayList<>();
            return patients;
        } catch (SQLException e) {
            logger.error("Couldn't get all patients by doctor");
            throw new DataProcessingException("Couldn't get all patients by doctor" + e);
        }
    }

    private Patient getPatientByResultSet(ResultSet resultSet) {
        try {
            Patient patient = new Patient();
            patient.setId(resultSet.getLong("patient_id"));
            patient.setName(resultSet.getString("name"));
            patient.setDiagnosis(resultSet.getString("diagnosis"));
            if (resultSet.getLong("doctor_id") != 0) {
                patient.setDoctor(doctorDao.get(resultSet.getLong("doctor_id"))
                        .orElse(null));
            }
            return patient;
        } catch (SQLException e) {
            logger.error("Couldn't get patient by result set");
            throw new DataProcessingException("Couldn't get patient by result set" + e);
        }
    }
}

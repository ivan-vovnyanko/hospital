package hospital.dao.impl;

import hospital.dao.DoctorDao;
import hospital.dao.MedicineDao;
import hospital.dao.PatientDao;
import hospital.dao.PrescriptionDao;
import hospital.exception.DataProcessingException;
import hospital.lib.Dao;
import hospital.lib.Inject;
import hospital.model.Prescription;
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
public class PrescriptionDaoImpl implements PrescriptionDao {
    private static final Logger logger = LogManager.getLogger(PrescriptionDao.class);
    @Inject
    private DoctorDao doctorDao;
    @Inject
    private MedicineDao medicineDao;
    @Inject
    private PatientDao patientDao;

    @Override
    public Prescription create(Prescription prescription) {
        String query = "INSERT INTO prescriptions "
                + "(patient_id, doctor_prescribed_id, medicine_id, count) "
                + "values (?, ?, ?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, prescription.getPatient().getId());
            statement.setLong(2, prescription.getDoctorPrescribed().getId());
            statement.setLong(3, prescription.getMedicine().getId());
            statement.setInt(4, prescription.getCount());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                prescription.setId(resultSet.getLong(1));
            }
            return prescription;
        } catch (SQLException e) {
            logger.error("Can't insert new prescription " + prescription);
            throw new DataProcessingException("Can't insert new prescription " + prescription + e);
        }
    }

    @Override
    public Optional<Prescription> get(Long id) {
        String query = "SELECT * FROM prescriptions WHERE id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Prescription prescription = null;
            if (resultSet.next()) {
                prescription = getPrescriptionByResultSet(resultSet);
            }
            return Optional.ofNullable(prescription);
        } catch (SQLException e) {
            logger.error("Can't get prescription by id " + id);
            throw new DataProcessingException("Can't get prescription by id " + id + e);
        }
    }

    @Override
    public List<Prescription> getAll() {
        String query = "SELECT * FROM prescriptions WHERE is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Prescription> prescriptions = new ArrayList<>();
            while (resultSet.next()) {
                Prescription prescription = getPrescriptionByResultSet(resultSet);
                prescriptions.add(prescription);
            }
            return prescriptions;
        } catch (SQLException e) {
            logger.error("Can't get all prescriptions from db");
            throw new RuntimeException("Can't get all prescriptions from db" + e);
        }
    }

    @Override
    public Prescription update(Prescription prescription) {
        String query = "UPDATE prescriptions "
                + "SET patient_id = ?, doctor_prescribed_id = ?, medicine_id = ?, count = ? "
                + "WHERE id = ? AND is_deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, prescription.getPatient().getId());
            statement.setLong(2, prescription.getDoctorPrescribed().getId());
            statement.setLong(3, prescription.getMedicine().getId());
            statement.setInt(4, prescription.getCount());
            statement.setLong(5, prescription.getId());
            statement.executeUpdate();
            return prescription;
        } catch (SQLException e) {
            logger.error("Can't update prescription " + prescription);
            throw new DataProcessingException("Can't update prescription " + prescription + e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE prescriptions SET is_deleted = true "
                + "WHERE id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Can't delete prescription by id " + id);
            throw new DataProcessingException("Can't delete prescription by id " + id + e);
        }
    }

    private Prescription getPrescriptionByResultSet(ResultSet resultSet) throws SQLException {
        Prescription prescription = new Prescription();
        Long medicineId = resultSet.getLong("medicine_id");
        if (medicineDao.get(medicineId).isPresent()) {
            prescription.setMedicine(medicineDao.get(medicineId).get());
        }
        Long doctorId = resultSet.getLong("doctor_prescribed_id");
        if (doctorDao.get(doctorId).isPresent()) {
            prescription.setDoctorPrescribed(doctorDao.get(doctorId).get());
        }
        Long patientId = resultSet.getLong("patient_id");
        if (patientDao.get(patientId).isPresent()) {
            prescription.setPatient(patientDao.get(patientId).get());
        }
        prescription.setCount(resultSet.getInt("count"));
        prescription.setId(resultSet.getLong("id"));
        return prescription;
    }

    @Override
    public List<Prescription> getByPatient(Long patientId) {
        String query = "SELECT * FROM prescriptions WHERE is_deleted = false AND patient_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            List<Prescription> prescriptions = new ArrayList<>();
            while (resultSet.next()) {
                Prescription prescription = getPrescriptionByResultSet(resultSet);
                prescriptions.add(prescription);
            }
            return prescriptions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAllByPatient(Long patientId) {
        String query = "UPDATE prescriptions SET is_deleted = true "
                + "WHERE patient_id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, patientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete all prescriptions by patient with id "
                    + patientId + e);
        }
    }

    @Override
    public void deleteAllByDoctor(Long doctorId) {
        String query = "UPDATE prescriptions SET is_deleted = true "
                + "WHERE doctor_prescribed_id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, doctorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete all prescriptions by doctor with id "
                    + doctorId + e);
        }
    }

    @Override
    public void deleteAllByMedicine(Long medicineId) {
        String query = "UPDATE prescriptions SET is_deleted = true "
                + "WHERE medicine_id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, medicineId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete all prescriptions by medicine with id "
                    + medicineId + e);
        }
    }
}

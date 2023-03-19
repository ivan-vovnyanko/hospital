package hospital.dao.impl;

import hospital.dao.MedicineDao;
import hospital.dao.PatientDao;
import hospital.exception.DataProcessingException;
import hospital.lib.Dao;
import hospital.lib.Inject;
import hospital.model.Medicine;
import hospital.util.ConnectionUtil;
import java.sql.Connection;
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
public class MedicineDaoImpl implements MedicineDao {
    private static final Logger logger = LogManager.getLogger();
    @Inject
    private PatientDao patientDao;

    @Override
    public Medicine create(Medicine medicine) {
        String query = "INSERT INTO medicines (name) "
                + "VALUES (?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, medicine.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                medicine.setId(resultSet.getLong(1));
            }
            return medicine;
        } catch (SQLException e) {
            logger.error("Couldn't create medicine with name - " + medicine.getName());
            throw new DataProcessingException("Couldn't create medicine with name "
                    + medicine.getName() + e);
        }
    }

    @Override
    public boolean delete(Long medicineId) {
        String query = "UPDATE medicines "
                + "SET is_deleted = true "
                + "WHERE medicine_id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, medicineId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Couldn't delete medicine with id " + medicineId);
            throw new DataProcessingException("Couldn't delete medicine with id " + medicineId + e);
        }
    }

    @Override
    public Medicine update(Medicine medicine) {
        String query = "UPDATE medicines "
                + "SET name = ? "
                + "WHERE medicine_id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(2, medicine.getId());
            statement.setString(1, medicine.getName());
            statement.executeUpdate();
            return medicine;
        } catch (SQLException e) {
            logger.error("Couldn't update medicine with id " + medicine.getId() + ", name - "
                    + medicine.getName());
            throw new DataProcessingException("Couldn't update medicine with id "
                    + medicine.getId() + e);
        }
    }

    @Override
    public Optional<Medicine> get(Long medicineId) {
        String query = "SELECT * "
                + "FROM medicines "
                + "WHERE medicine_id = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, medicineId);
            ResultSet resultSet = statement.executeQuery();
            Medicine medicine = null;
            if (resultSet.next()) {
                medicine = getMedicineByResultSet(resultSet);
            }
            return Optional.ofNullable(medicine);
        } catch (SQLException e) {
            logger.error("Couldn't get medicine with id " + medicineId);
            throw new DataProcessingException("Couldn't get medicine with id " + medicineId + e);
        }
    }

    @Override
    public List<Medicine> getAll() {
        String query = "SELECT * "
                + "FROM medicines "
                + "WHERE is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Medicine> medicines = new ArrayList<>();
            while (resultSet.next()) {
                Medicine medicine = getMedicineByResultSet(resultSet);
                medicines.add(medicine);
            }
            return medicines;
        } catch (SQLException e) {
            logger.error("Couldn't get all medicines");
            throw new DataProcessingException("Couldn't get all medicines" + e);
        }
    }

    private Medicine getMedicineByResultSet(ResultSet resultSet) {
        try {
            Medicine medicine = new Medicine();
            medicine.setId(resultSet.getLong("medicine_id"));
            medicine.setName(resultSet.getString("name"));
            return medicine;
        } catch (SQLException e) {
            logger.error("Couldn't get medicine by result set");
            throw new DataProcessingException("Couldn't get medicine by result set." + e);
        }
    }
}

package hospital.service.impl;

import hospital.dao.MedicineDao;
import hospital.lib.Inject;
import hospital.lib.Service;
import hospital.model.Medicine;
import hospital.service.MedicineService;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class MedicineServiceImpl implements MedicineService {
    private static final Logger logger = LogManager.getLogger();
    @Inject
    private MedicineDao medicineDao;

    @Override
    public Medicine create(Medicine element) {
        logger.info("Create method was called. Name - " + element.getName());
        return medicineDao.create(element);
    }

    @Override
    public Medicine get(Long id) {
        if (medicineDao.get(id).isPresent()) {
            return medicineDao.get(id).get();
        } else {
            logger.error("Can't get Medicine with id - " + id);
            throw new NoSuchElementException("Can't get Medicine with id - " + id);
        }
    }

    @Override
    public Medicine update(Medicine element) {
        logger.info("Update method was called. Name - " + element.getName()
                + ", id - " + element.getId());
        return medicineDao.update(element);
    }

    @Override
    public List<Medicine> getAll() {
        return medicineDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Delete method was called. Id - " + id);
        return medicineDao.delete(id);
    }

    @Override
    public boolean unlinkPatient(Long medicineId, Long patientId) {
        logger.info("Unlink patient method was called. medicineId - " + medicineId
                + ", patient id - " + patientId);
        return medicineDao.unlinkPatient(medicineId, patientId);
    }
}

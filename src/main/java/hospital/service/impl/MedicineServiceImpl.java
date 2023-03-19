package hospital.service.impl;

import hospital.dao.MedicineDao;
import hospital.lib.Inject;
import hospital.lib.Service;
import hospital.model.Medicine;
import hospital.service.MedicineService;
import hospital.service.PrescriptionService;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class MedicineServiceImpl implements MedicineService {
    private static final Logger logger = LogManager.getLogger();
    @Inject
    private MedicineDao medicineDao;
    @Inject
    private PrescriptionService prescriptionService;

    @Override
    public Medicine create(Medicine element) {
        logger.info("Create method was called. Name - " + element.getName());
        return medicineDao.create(element);
    }

    @Override
    public Medicine get(Long id) {
        return medicineDao.get(id).orElseThrow(() -> {
            logger.error("Can't get Medicine with id - " + id);
            throw new NoSuchElementException("Can't get Medicine with id - " + id); });
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
        prescriptionService.deleteAllByMedicine(id);
        return medicineDao.delete(id);
    }

}

package hospital.service;

import hospital.dao.DoctorDao;
import hospital.lib.Injector;
import hospital.model.Doctor;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoctorServiceImplTest {
    private static final Injector injector = Injector.getInstance("hospital");
    private DoctorService doctorService =
            (DoctorService) injector.getInstance(DoctorService.class);
    private DoctorDao doctorDao =
            (DoctorDao) injector.getInstance(DoctorDao.class);

    @Test
    void findDoctorByLogin_Ok() {
        Doctor doctorFromDb;
        if (doctorDao.getAll().isEmpty()) {
            Doctor doctor = new Doctor();
            doctor.setName("DemoName");
            doctor.setLogin("demoLogin123");
            doctor.setPassword("Qwerty123");
            doctor.setBirthDate(LocalDate.of(1990, 1, 1));
            doctorFromDb = doctorDao.create(doctor);
        } else {
            doctorFromDb = doctorDao.getAll().stream().findFirst().get();
        }
        Assertions.assertEquals(doctorFromDb,
                doctorService.findDoctorByLogin(doctorFromDb.getLogin()).get());
    }
}
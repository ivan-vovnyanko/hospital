package hospital.service;

import hospital.dao.DoctorDao;
import hospital.dao.PatientDao;
import hospital.lib.Injector;
import hospital.model.Doctor;
import hospital.model.Patient;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PatientServiceImplTest {
    private static final Injector injector = Injector.getInstance("hospital");
    private PatientService patientService =
            (PatientService) injector.getInstance(PatientService.class);
    private DoctorDao doctorDao =
            (DoctorDao) injector.getInstance(DoctorDao.class);
    private PatientDao patientDao =
            (PatientDao) injector.getInstance(PatientDao.class);

    @Test
    void getPatientByDoctor_Ok() {
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
        Patient patientFromDb;
        if (patientDao.getAll().isEmpty()) {
            Patient patient = new Patient();
            patient.setName("DemoName");
            patient.setDoctor(doctorFromDb);
            patientFromDb = patientDao.create(patient);
        } else {
            patientFromDb = patientDao.getAll().stream().findFirst().get();
        }
        Assertions.assertTrue(
                patientService.getPatientsByDoctor(doctorFromDb.getId()).contains(patientFromDb));
    }
}
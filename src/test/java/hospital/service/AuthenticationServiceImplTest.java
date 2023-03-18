package hospital.service;

import hospital.exception.AuthenticationException;
import hospital.lib.Injector;
import hospital.model.Doctor;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthenticationServiceImplTest {
    private static final Injector injector = Injector.getInstance("hospital");
    private final AuthenticationService authenticationService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private final DoctorService doctorService =
            (DoctorService) injector.getInstance(DoctorService.class);
    private static final String LOGIN = "demoLogin123";
    private static final String PASSWORD = "Qwerty123";
    private static final String SALT = "SALT";
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        if (doctorService.findDoctorByLogin(LOGIN).isEmpty()) {
            Doctor doctor = new Doctor();
            doctor.setName("DemoName");
            doctor.setBirthDate(LocalDate.of(1990, 1, 1));
            doctor.setLogin(LOGIN);
            doctor.setPassword(PASSWORD);
            doctorService.create(doctor);
        }
        doctor = doctorService.findDoctorByLogin(LOGIN).get();
    }

    @Test
    void loginDoctor_Ok() {
        Assertions.assertEquals(doctor, authenticationService.login(LOGIN, PASSWORD));
    }

    @Test
    void loginDoctor_invalidPassword_NotOk() {
        Assertions.assertThrows(AuthenticationException.class, () ->
                authenticationService.login(LOGIN, PASSWORD + SALT));
    }

    @Test
    void loginDoctor_invalidLogin_NotOk() {
        Assertions.assertThrows(AuthenticationException.class, () ->
                authenticationService.login(LOGIN + SALT, PASSWORD));
    }
}
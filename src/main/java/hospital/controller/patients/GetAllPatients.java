package hospital.controller.patients;

import hospital.lib.Injector;
import hospital.model.Patient;
import hospital.service.PatientService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/patients/all")
public class GetAllPatients extends HttpServlet {
    private static final Injector injector = Injector.getInstance("hospital");
    private final PatientService patientService =
            (PatientService) injector.getInstance(PatientService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Patient> patients = patientService.getAll();
        req.setAttribute("patients", patients);
        req.getRequestDispatcher("/WEB-INF/views/patients/all.jsp").forward(req, resp);
    }
}

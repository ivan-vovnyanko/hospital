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
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/patients")
public class GetMyPatientsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("hospital");
    private final PatientService patientService =
            (PatientService) injector.getInstance(PatientService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long doctorId = (Long) session.getAttribute("doctor_id");
        List<Patient> patients = patientService.getPatientsByDoctor(doctorId);
        req.setAttribute("patients", patients);
        req.getRequestDispatcher("WEB-INF/views/patients/my-patients.jsp").forward(req, resp);
    }
}

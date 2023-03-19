package hospital.controller.patients;

import hospital.lib.Injector;
import hospital.model.Patient;
import hospital.service.PatientService;
import hospital.service.PrescriptionService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/patients/details")
public class GetPatientDetailsController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("hospital");
    private final PatientService patientService =
            (PatientService) injector.getInstance(PatientService.class);
    private final PrescriptionService prescriptionService =
            (PrescriptionService) injector.getInstance(PrescriptionService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Patient patient = patientService.get(Long.parseLong(req.getParameter("id")));
        req.setAttribute("prescriptions", prescriptionService.getByPatient(patient.getId()));
        req.setAttribute("patient", patient);
        req.getRequestDispatcher("/WEB-INF/views/patients/details.jsp").forward(req, resp);
    }
}

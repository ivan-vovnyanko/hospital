package hospital.controller.patients;

import hospital.lib.Injector;
import hospital.model.Patient;
import hospital.service.PatientService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = "/patients/update/diagnosis")
public class UpdateDiagnosisController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final Injector injector = Injector.getInstance("hospital");
    private final PatientService patientService =
            (PatientService) injector.getInstance(PatientService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("patient_id", req.getParameter("id"));
        req.getRequestDispatcher("/WEB-INF/views/patients/update/diagnosis.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Patient patient = patientService.get(Long.valueOf(req.getParameter("id")));
        patient.setDiagnosis(req.getParameter("diagnosis"));
        patientService.update(patient);
        logger.info("The diagnosis was changed for a patient with ID - " + patient.getId());
        resp.sendRedirect("/patients/details?id=" + patient.getId());
    }
}

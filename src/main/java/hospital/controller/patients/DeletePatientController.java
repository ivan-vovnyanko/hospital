package hospital.controller.patients;

import hospital.lib.Injector;
import hospital.service.PatientService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = "/patients/delete")
public class DeletePatientController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final Injector injector = Injector.getInstance("hospital");
    private final PatientService patientService =
            (PatientService) injector.getInstance(PatientService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        patientService.delete(Long.valueOf(req.getParameter("id")));
        logger.info("A patient with ID - " + req.getParameter("id") + " was deleted");
        resp.sendRedirect("/patients");
    }
}

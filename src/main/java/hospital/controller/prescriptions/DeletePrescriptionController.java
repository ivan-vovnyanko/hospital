package hospital.controller.prescriptions;

import hospital.lib.Injector;
import hospital.service.PrescriptionService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = "/prescriptions/delete")
public class DeletePrescriptionController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final Injector injector = Injector.getInstance("hospital");
    private final PrescriptionService prescriptionService =
            (PrescriptionService) injector.getInstance(PrescriptionService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        prescriptionService.delete(Long.valueOf(req.getParameter("id")));
        logger.info("A prescription with ID - " + req.getParameter("id") + " was deleted");
        resp.sendRedirect("/patients/details?id=" + req.getParameter("patientId"));
    }
}

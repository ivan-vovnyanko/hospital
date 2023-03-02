package hospital.controller.medicines;

import hospital.lib.Injector;
import hospital.service.MedicineService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = "/medicines/patients/delete")
public class DeletePatientController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final Injector injector = Injector.getInstance("hospital");
    private final MedicineService medicineService =
            (MedicineService) injector.getInstance(MedicineService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        medicineService.unlinkPatient(Long.parseLong(req.getParameter("medicine")),
                Long.parseLong(req.getParameter("patient")));
        logger.info("A patient with ID " + req.getParameter("patient")
                + " had the medicine removed from ID "
                + req.getParameter("medicine") + ".");
        resp.sendRedirect("/medicines/details?id="
                + Long.parseLong(req.getParameter("medicine")));
    }
}

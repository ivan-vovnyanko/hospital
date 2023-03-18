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

@WebServlet(urlPatterns = "/medicines/delete")
public class DeleteMedicineController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final Injector injector = Injector.getInstance("hospital");
    private final MedicineService medicineService =
            (MedicineService) injector.getInstance(MedicineService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        medicineService.delete(Long.valueOf(req.getParameter("id")));
        logger.info("The medicine has been removed from the ID - "
                + req.getParameter("id") + ".");
        resp.sendRedirect("/medicines");
    }
}

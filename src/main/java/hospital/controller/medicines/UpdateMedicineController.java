package hospital.controller.medicines;

import hospital.lib.Injector;
import hospital.model.Medicine;
import hospital.service.MedicineService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = "/medicines/update")
public class UpdateMedicineController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final Injector injector = Injector.getInstance("hospital");
    private final MedicineService medicineService =
            (MedicineService) injector.getInstance(MedicineService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("medicine_id", req.getParameter("id"));
        req.getRequestDispatcher("/WEB-INF/views/medicines/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Medicine medicine = medicineService.get(Long.valueOf(req.getParameter("id")));
        medicine.setName(req.getParameter("name"));
        medicineService.update(medicine);
        logger.info("The information about the medicine with ID - " + medicine.getId()
                + " has been updated");
        resp.sendRedirect("/medicines/details?id=" + medicine.getId());
    }
}

package hospital.controller.prescriptions;

import hospital.lib.Injector;
import hospital.service.PrescriptionService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/prescriptions")
public class GetAllPrescriptionsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("hospital");
    private final PrescriptionService prescriptionService =
            (PrescriptionService) injector.getInstance(PrescriptionService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("prescriptions", prescriptionService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/prescriptions/all.jsp").forward(req, resp);;
    }
}

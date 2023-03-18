package hospital.controller.doctors;

import hospital.lib.Injector;
import hospital.service.DoctorService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = "/doctors/delete")
public class DeleteDoctorController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final Injector injector = Injector.getInstance("hospital");
    private final DoctorService doctorService =
            (DoctorService) injector.getInstance(DoctorService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doctorService.delete(Long.valueOf(req.getParameter("id")));
        logger.info("The doctor with id - " + req.getParameter("id") + " was deleted");
        resp.sendRedirect("/login");
    }
}

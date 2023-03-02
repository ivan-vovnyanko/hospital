package hospital.controller.doctors;

import hospital.lib.Injector;
import hospital.service.DoctorService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/doctors")
public class GetDoctorsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("hospital");
    private final DoctorService doctorService =
            (DoctorService) injector.getInstance(DoctorService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("user", doctorService.get((Long) session.getAttribute("doctor_id")));
        req.setAttribute("doctors", doctorService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/doctors/all.jsp").forward(req, resp);
    }
}

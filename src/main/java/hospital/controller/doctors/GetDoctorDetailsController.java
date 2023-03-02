package hospital.controller.doctors;

import hospital.lib.Injector;
import hospital.model.Doctor;
import hospital.service.DoctorService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/doctors/details")
public class GetDoctorDetailsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("hospital");
    private final DoctorService doctorService =
            (DoctorService) injector.getInstance(DoctorService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Doctor doctor = doctorService.get(Long.valueOf(req.getParameter("id")));
        req.setAttribute("doctor", doctor);
        req.getRequestDispatcher("/WEB-INF/views/doctors/details.jsp").forward(req, resp);
    }
}

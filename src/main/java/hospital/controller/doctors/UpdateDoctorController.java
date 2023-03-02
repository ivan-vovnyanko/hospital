package hospital.controller.doctors;

import hospital.lib.Injector;
import hospital.model.Doctor;
import hospital.service.DoctorService;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/doctors/update")
public class UpdateDoctorController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final Injector injector = Injector.getInstance("hospital");
    private final DoctorService doctorService =
            (DoctorService) injector.getInstance(DoctorService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Doctor doctor = doctorService.get(Long.valueOf(req.getParameter("id")));
        if (req.getParameter("name").length() != 0) {
            doctor.setName(req.getParameter("name"));
        }
        if (req.getParameter("birthdate").length() != 0) {
            doctor.setBirthDate(LocalDate.parse(req.getParameter("birthdate")));
        }
        if (req.getParameter("login").length() != 0) {
            doctor.setLogin(req.getParameter("login"));
        }
        if (req.getParameter("password").length() != 0) {
            doctor.setPassword(req.getParameter("password"));
        }
        doctorService.update(doctor);
        logger.info("The information about the doctor with ID " + doctor.getId()
                + " has been updated");
        resp.sendRedirect("/doctors");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        req.setAttribute("doctor_id", id);
        req.getRequestDispatcher("/WEB-INF/views/doctors/update.jsp").forward(req, resp);
    }
}

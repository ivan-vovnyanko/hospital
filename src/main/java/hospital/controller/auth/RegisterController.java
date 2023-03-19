package hospital.controller.auth;

import hospital.exception.WrongInputException;
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
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final Injector injector = Injector.getInstance("hospital");
    private final DoctorService doctorService =
            (DoctorService) injector.getInstance(DoctorService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Doctor doctor = new Doctor();
        doctor.setName(req.getParameter("doctor_name"));
        doctor.setBirthDate(LocalDate.parse(req.getParameter("birth_date")));
        doctor.setLogin(req.getParameter("username"));
        doctor.setPassword(req.getParameter("password"));
        try {
            doctor = doctorService.create(doctor);
            logger.info("A new doctor was registered. Name - " + doctor.getName());
            req.getSession().setAttribute("doctor_id", doctor.getId());
            resp.sendRedirect("/");
        } catch (WrongInputException e) {
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("doctor_id", null);
        req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
    }
}

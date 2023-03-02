package hospital.controller.auth;

import hospital.lib.Injector;
import hospital.model.Doctor;
import hospital.service.AuthenticationService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final Injector injector = Injector.getInstance("hospital");
    private final AuthenticationService authenticationService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("doctor_id", null);
        req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Doctor doctor = authenticationService.login(req.getParameter("username"),
                req.getParameter("password"));
        HttpSession session = req.getSession();
        session.setAttribute("doctor_id", doctor.getId());
        logger.info("The doctor was authorized with login - " + doctor.getLogin());
        resp.sendRedirect(req.getContextPath() + "/");
    }
}

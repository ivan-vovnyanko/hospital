package hospital.filter;

import hospital.lib.Injector;
import hospital.model.Doctor;
import hospital.service.PatientService;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/medicines/patients/add")
public class PrescribeMedicineFilter implements Filter {
    private static final Injector injector = Injector.getInstance("hospital");
    private final PatientService patientService =
            (PatientService) injector.getInstance(PatientService.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        Doctor doctor =
                patientService.get(Long.valueOf(req.getParameter("patient"))).getDoctor();
        if (!doctor.getId().equals(session.getAttribute("doctor_id"))) {
            throw new AccessDeniedException("You cannot prescribe this medicine to this patient. "
                    + "Your id - " + doctor.getId() + ", but required id - "
                    + session.getAttribute("doctor_id"));
        }
        filterChain.doFilter(req, resp);
    }
}

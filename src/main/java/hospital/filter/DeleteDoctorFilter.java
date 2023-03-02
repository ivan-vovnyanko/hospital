package hospital.filter;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(urlPatterns = "/doctors/delete")
public class DeleteDoctorFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if (!session.getAttribute("doctor_id").equals(Long.valueOf(req.getParameter("id")))) {
            logger.error("Attempting to remove a doctor "
                    + "without the necessary permission. User id - " + req.getParameter("id")
                    + ", required id - " + session.getAttribute("doctor_id"));
            throw new AccessDeniedException("You can't delete this doctor");
        }
        filterChain.doFilter(req, resp);
    }
}

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

@WebFilter(urlPatterns = "/doctors/update")
public class UpdateDoctorFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (!session.getAttribute("doctor_id").equals(Long.valueOf(req.getParameter("id")))) {
            logger.error("You cannot change the details of other doctors."
                    + "Your id - " + req.getParameter("id") + ", but needed - "
                    + session.getAttribute("doctor_id"));
            throw new AccessDeniedException("An attempt was made to update information about "
                    + "another user. Your id - " + req.getParameter("id") + ", but needed - "
                    + session.getAttribute("doctor_id"));
        }
        chain.doFilter(req, resp);
    }
}

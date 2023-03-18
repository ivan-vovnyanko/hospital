package hospital.controller.medicines;

import hospital.lib.Injector;
import hospital.model.Patient;
import hospital.service.MedicineService;
import hospital.service.PatientService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = "/medicines/patients/add")
public class PrescribeMedicineController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final Injector injector = Injector.getInstance("hospital");
    private final MedicineService medicineService =
            (MedicineService) injector.getInstance(MedicineService.class);
    private final PatientService patientService =
            (PatientService) injector.getInstance(PatientService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Patient patient = patientService.get(Long.valueOf(req.getParameter("patient")));
        patient.getMedicines()
                .add(medicineService.get(Long.valueOf(req.getParameter("medicine"))));
        patientService.update(patient);
        logger.info("A medicine with ID - " + req.getParameter("medicine")
                + " was assigned to a patient with ID - " + patient.getId());
        resp.sendRedirect("/medicines/patients?id=" + req.getParameter("medicine"));
    }
}

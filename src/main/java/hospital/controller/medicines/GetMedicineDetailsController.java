package hospital.controller.medicines;

import hospital.lib.Injector;
import hospital.model.Patient;
import hospital.service.MedicineService;
import hospital.service.PatientService;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/medicines/details")
public class GetMedicineDetailsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("hospital");
    private final MedicineService medicineService =
            (MedicineService) injector.getInstance(MedicineService.class);
    private final PatientService patientService =
            (PatientService) injector.getInstance(PatientService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long doctorId = (Long) session.getAttribute("doctor_id");
        req.setAttribute("medicine",
                medicineService.get(Long.valueOf(req.getParameter("id"))));
        List<Patient> patients = patientService
                .getPatientsWithMedicine(Long.valueOf(req.getParameter("id")))
                .stream()
                .filter(patient -> {
                    if (patient.getDoctor() != null) {
                        return patient.getDoctor().getId().equals(doctorId);
                    }
                    return false;
                })
                .collect(Collectors.toList());
        req.setAttribute("patients", patients);
        req.getRequestDispatcher("/WEB-INF/views/medicines/details.jsp").forward(req, resp);
    }
}

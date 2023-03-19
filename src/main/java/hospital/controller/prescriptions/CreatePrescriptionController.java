package hospital.controller.prescriptions;

import hospital.lib.Injector;
import hospital.model.Prescription;
import hospital.service.DoctorService;
import hospital.service.MedicineService;
import hospital.service.PatientService;
import hospital.service.PrescriptionService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/patients/prescriptions/add")
public class CreatePrescriptionController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("hospital");
    private final MedicineService medicineService =
            (MedicineService) injector.getInstance(MedicineService.class);
    private final PrescriptionService prescriptionService =
            (PrescriptionService) injector.getInstance(PrescriptionService.class);
    private final DoctorService doctorService =
            (DoctorService) injector.getInstance(DoctorService.class);
    private final PatientService patientService =
            (PatientService) injector.getInstance(PatientService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Prescription prescription = new Prescription();
        HttpSession session = req.getSession();
        if (!req.getParameter("medicine_id").matches("[0-9]+")
                || !req.getParameter("count").matches("[0-9]+")) {
            req.setAttribute("errorMsg", "Only numbers!!");
            req.getRequestDispatcher("/WEB-INF/views/patients/prescriptions/add.jsp")
                    .forward(req, resp);
        }
        try {
            prescription.setDoctorPrescribed(doctorService
                    .get((Long) session.getAttribute("doctor_id")));
            prescription.setMedicine(medicineService
                    .get(Long.valueOf(req.getParameter("medicine_id"))));
            prescription.setPatient(patientService
                    .get(Long.valueOf(req.getParameter("patientId"))));
            prescription.setCount(Integer.parseInt(req.getParameter("count")));
            prescriptionService.create(prescription);
        } catch (Exception e) {
            req.setAttribute("medicines", medicineService.getAll());
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/patients/prescriptions/add.jsp")
                    .forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/patients/details?id="
                + req.getParameter("patientId"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("patientId", req.getParameter("patientId"));
        req.setAttribute("medicines", medicineService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/patients/prescriptions/add.jsp")
                .forward(req, resp);;
    }
}

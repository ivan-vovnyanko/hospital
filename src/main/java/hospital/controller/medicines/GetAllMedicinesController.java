package hospital.controller.medicines;

import hospital.lib.Injector;
import hospital.service.MedicineService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/medicines")
public class GetAllMedicinesController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("hospital");
    private final MedicineService medicineService =
            (MedicineService) injector.getInstance(MedicineService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("medicines", medicineService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/medicines/all.jsp").forward(req, resp);
    }
}

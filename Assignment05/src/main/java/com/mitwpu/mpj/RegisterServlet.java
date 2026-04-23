package com.mitwpu.mpj;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final EmployeeDAO dao = new EmployeeDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String department = req.getParameter("department");
        String salaryRaw = req.getParameter("salary");

        if (name == null || name.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || department == null || department.trim().isEmpty()
                || salaryRaw == null || salaryRaw.trim().isEmpty()) {
            req.setAttribute("error", "All fields are required.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        double salary;
        try {
            salary = Double.parseDouble(salaryRaw.trim());
            if (salary < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Salary must be a non-negative number.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        if (!email.trim().toLowerCase().endsWith("@mitwpu.edu.in")) {
            req.setAttribute("error", "Email must end with @mitwpu.edu.in");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        Employee e = new Employee(name.trim(), email.trim().toLowerCase(), department.trim(), salary);
        try {
            dao.insert(e);
        } catch (SQLException ex) {
            throw new ServletException("Database insert failed", ex);
        }

        resp.sendRedirect("list");
    }
}

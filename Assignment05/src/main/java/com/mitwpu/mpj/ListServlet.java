package com.mitwpu.mpj;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/list")
public class ListServlet extends HttpServlet {

    private final EmployeeDAO dao = new EmployeeDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Employee> employees = dao.findAll();
            req.setAttribute("employees", employees);
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Database read failed", e);
        }
    }
}

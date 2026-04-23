<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.mitwpu.mpj.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registered Employees</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="card wide">
        <h1>Registered Employees</h1>
        <p class="sub">MIT-WPU Payroll Portal</p>

        <% List<Employee> employees = (List<Employee>) request.getAttribute("employees"); %>
        <% if (employees == null || employees.isEmpty()) { %>
            <p>No employees registered yet.</p>
        <% } else { %>
            <table>
                <thead>
                    <tr><th>ID</th><th>Name</th><th>Email</th><th>Department</th><th>Salary</th></tr>
                </thead>
                <tbody>
                <% for (Employee e : employees) { %>
                    <tr>
                        <td><%= e.getId() %></td>
                        <td><%= e.getName() %></td>
                        <td><%= e.getEmail() %></td>
                        <td><%= e.getDepartment() %></td>
                        <td>Rs. <%= String.format("%.2f", e.getSalary()) %></td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        <% } %>

        <p><a href="register.jsp">Register another employee</a></p>
    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Registration</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="card">
        <h1>Employee Registration</h1>
        <p class="sub">MIT-WPU Payroll Portal</p>

        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) { %>
            <div class="error"><%= error %></div>
        <% } %>

        <form action="register" method="post">
            <label>Name
                <input type="text" name="name" required>
            </label>

            <label>Email
                <input type="email" name="email" placeholder="yourname@mitwpu.edu.in" required>
            </label>

            <label>Department
                <select name="department" required>
                    <option value="">-- Select --</option>
                    <option>Computer Science</option>
                    <option>Electronics</option>
                    <option>Mechanical</option>
                    <option>Civil</option>
                    <option>Satellite Systems</option>
                </select>
            </label>

            <label>Salary (Rs.)
                <input type="number" name="salary" min="0" step="0.01" required>
            </label>

            <button type="submit">Register</button>
        </form>

        <p><a href="list">View all registered employees</a></p>
    </div>
</body>
</html>

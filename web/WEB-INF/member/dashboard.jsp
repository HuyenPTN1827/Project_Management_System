<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Member Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 800px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
        }
        .profile-section {
            margin-top: 20px;
        }
        .btn-profile {
            padding: 10px 15px;
            color: white;
            background-color: #007bff;
            text-decoration: none;
            border-radius: 5px;
        }
        .btn-profile:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Welcome to the Member Dashboard</h1>
    <p>Hello, <strong><%= ((User) session.getAttribute("user")).getFull_name() %></strong>!</p>
    
    <div class="profile-section">
        <a href="<%= request.getContextPath() %>/member-profilecontroller">View Profile</a>
         <a href="<%= request.getContextPath() %>/changepasswordcontroller">Change Password</a>

      
    </div>
</div>

</body>
</html>

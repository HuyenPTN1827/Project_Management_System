<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
</head>
<body>

<h1>User Profile</h1>

<div>
    <strong>Full Name:</strong> <%= ((User) request.getAttribute("memberProfile")).getFull_name() %><br>
    <strong>Email:</strong> <%= ((User) request.getAttribute("memberProfile")).getEmail() %><br>
    <strong>Mobile:</strong> <%= ((User) request.getAttribute("memberProfile")).getMobile() %><br>
</div>

<div>
    <a href="<%= request.getContextPath() %>/member-dashboard">Back to Dashboard</a>
</div>

<!-- Form chỉnh sửa thông tin trực tiếp -->
<h2>Edit Profile</h2>
<form action="<%= request.getContextPath() %>/member-profilecontroller" method="post">
    <label for="fullName">Full Name:</label>
    <input type="text" id="fullname" name="fullname" value="<%= ((User) request.getAttribute("memberProfile")).getFull_name() %>"><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="<%= ((User) request.getAttribute("memberProfile")).getEmail() %>"><br>

    <label for="mobile">Mobile:</label>
    <input type="text" id="mobile" name="mobile" value="<%= ((User) request.getAttribute("memberProfile")).getMobile() %>"><br>

    <button type="submit">Save Changes</button>
   
</form>
     ${message}

</body>
</html>

<%-- 
    Document   : unauthorized.jsp
    Created on : Current Date
    Author     : Your Name
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Unauthorized Access</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                text-align: center;
                padding: 50px;
            }
            h1 {
                color: #d9534f; /* Màu đỏ */
            }
            p {
                font-size: 1.2em;
            }
            a {
                text-decoration: none;
                color: #5bc0de; /* Màu xanh */
            }
        </style>
    </head>
    <body>
        <form action="login" method="" >
        <h1>Unauthorized Access</h1>
        <p>You do not have permission to access this page.</p>
        <a href="<%=request.getContextPath()%>/logout">Back to Login</a>
        </form>
    </body>
</html>
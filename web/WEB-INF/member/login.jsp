<%-- 
    Document   : login
    Created on : Sep 12, 2024, 2:36:08 AM
    Author     : kelma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            body {
                overflow-x: hidden;
                padding-bottom: 40px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../component/header-guest.jsp"></jsp:include>

            <div class="container col-md-8 col-md-offset-3" style="overflow: auto; padding: 10px 0px 10px 0px">
                <h1>Login Form</h1>

                <div class="alert alert-success center" role="alert">
                    <p>${NOTIFICATION}</p>
            </div>

            <form action="<%=request.getContextPath()%>/login" method="post">
                <div class="form-group">
                    <label for="uname">Email:</label>
                    <input type="text" class="form-control" id="email" value="${sessionScope.user}"
                           placeholder="Enter the Email" name="email" required/>
                </div>

                <div class="form-group">
                    <label for="uname">Password:</label>
                    <input type="password" class="form-control" id="password"
                           placeholder="Enter the Password" name="password" required/>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
<!--                5/10
                BachHD-->
                <button type="button" class="btn btn-link" onclick="location.href='<%=request.getContextPath()%>/forgotpassword'">Quên mật khẩu?</button>
            </form>
        </div>

        <jsp:include page="../component/footer.jsp"></jsp:include>
    </body>
</html>
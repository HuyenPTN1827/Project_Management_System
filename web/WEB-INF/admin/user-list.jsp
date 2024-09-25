<%-- 
    Document   : user-list
    Created on : Sep 15, 2024, 6:19:09 PM
    Author     : kelma
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users List</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            body {
                overflow-x: hidden;
                padding-bottom: 40px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../component/header-admin.jsp"></jsp:include>

            <div class="row" style="padding: 10px 0px 10px 0px">
                <div class="container">
                    <h2 class="text-center">List of Users</h2>
                    <hr>

                    <div class="container text-left">
                        <a href="<%=request.getContextPath()%>/add-user" class="btn btn-success">Add User</a>
                </div>
                <br>

                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Full Name</th>
                            <th>Email</th>
                            <th>Mobile</th>
                            <th>Department</th>
                            <th>Role</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.listUser}" var="user">
                            <tr>
                                <td>${user.full_name}</td>
                                <td>${user.email}</td>
                                <td>${user.mobile}</td>
                                <c:forEach items="${user.depts}" var="d">
                                    <td>${d.code}</td>
                                </c:forEach>
                                <c:forEach items="${user.roles}" var="r">
                                    <td>${r.role_name}</td>
                                </c:forEach>
                                <td>
                                    <c:if test="${user.status eq '0'}">
                                        Deactivate
                                    </c:if>
                                    <c:if test="${user.status eq '1'}">
                                        Activate
                                    </c:if>
                                </td>
                                <td>
                                    <a href="<%=request.getContextPath()%>/update-user?id=<c:out value='${user.id}'/>">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="<%=request.getContextPath()%>/delete-user?id=<c:out value='${user.id}'/>">Delete</a>

                                    <!--<button (click)="updateUser(user.id)" class="btn btn-success">Update</button>
                                    <button (click)="deleteUser(user.id)" class="btn btn-warning">Delete</button>-->
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div> 

        <jsp:include page="../component/footer.jsp"></jsp:include>
    </body>
</html>
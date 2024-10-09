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

                    <div class="container">
                        <form action="user-management" method="post">
                            <div class="form-row align-items-center">
                                <div class="col-5">
                                    <input type="text" name="keyword" class="form-control" 
                                           placeholder="Enter Full Name or Email or Mobile"
                                           id="keyword" value="${keyword}">
                            </div>

                            <div class="col">
                                <select class="form-control" name="deptId">
                                    <option value="">All Departments</option>
                                    <c:forEach items="${dept}" var="d">
                                        <option 
                                            <c:if test="${deptId eq d.id}">
                                                selected="selected"
                                            </c:if>
                                            value="${d.id}">${d.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col">
                                <select class="form-control" name="roleId">
                                    <option value="">All Roles</option>
                                    <c:forEach items="${role}" var="r">
                                        <option 
                                            <c:if test="${roleId eq r.id}">
                                                selected="selected"
                                            </c:if>
                                            value="${r.id}">${r.value}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col">
                                <select class="form-control" name="status">
                                    <option value="">All Statuses</option>
                                    <option 
                                        <c:if test="${status eq 1}">
                                            selected="selected"
                                        </c:if>
                                        value="1">Active
                                    </option>
                                    <option 
                                        <c:if test="${status eq 0}">
                                            selected="selected"
                                        </c:if>
                                        value="0">Inactive
                                    </option>
                                    <option 
                                        <c:if test="${status eq 3}">
                                            selected="selected"
                                        </c:if>
                                        value="3">Unverified
                                    </option>
                                </select>
                            </div>

                            <div class="col-auto">
                                <button type="submit" class="btn btn-primary">Search</button>
                            </div>
                        </div>
                    </form>
                </div>

                </br>

                <div class="container text-left">
                    <a href="<%=request.getContextPath()%>/add-user" class="btn btn-success">Add User</a>
                </div>
                <br>

                <c:if test="${not empty message}">
                    <div class="alert alert-warning text-center">
                        <h4>${message}</h4>
                    </div>
                </c:if>

                <c:if test="${not empty listUser}">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
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
                                    <td>${user.id}</td>
                                    <td>${user.full_name}</td>
                                    <td>${user.email}</td>
                                    <td>${user.mobile}</td>
                                    <c:forEach items="${user.depts}" var="d">
                                        <td>${d.code}</td>
                                    </c:forEach>
                                    <c:forEach items="${user.settings}" var="r">
                                        <td>${r.value}</td>
                                    </c:forEach>
                                    <td>
                                        <c:if test="${user.status eq '0'}">
                                            Inactive
                                        </c:if>
                                        <c:if test="${user.status eq '1'}">
                                            Active
                                        </c:if>
                                        <c:if test="${user.status eq '3'}">
                                            Unverified
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${user.status eq '0'}">
                                            <a href="<%=request.getContextPath()%>/edit-user?id=${user.id}">Edit</a>
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <a href="<%=request.getContextPath()%>/change-status-user?id=${user.id}&status=${user.status}"
                                               onclick="return confirm('Are you sure you want to activate this user?');">Activate</a>
                                        </c:if>
                                        <c:if test="${user.status eq '1'}">
                                            <a href="<%=request.getContextPath()%>/edit-user?id=${user.id}">Edit</a>
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <a href="<%=request.getContextPath()%>/change-status-user?id=${user.id}&status=${user.status}"
                                               onclick="return confirm('Are you sure you want to deactivate this user?');">Deactivate</a>
                                        </c:if>
                                        <c:if test="${user.status eq '3'}">
                                            <a href="<%=request.getContextPath()%>/edit-user?id=<c:out value='${user.id}'/>">Edit</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div> 

        <jsp:include page="../component/footer.jsp"></jsp:include>
    </body>
</html>
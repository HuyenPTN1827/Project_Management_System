<%-- 
    Document   : todo-list
    Created on : Sep 12, 2024, 2:36:48 AM
    Author     : kelma
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todos List</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            body {
                overflow-x: hidden;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../component/header-admin.jsp"></jsp:include>

        <div class="row">
            <div class="container">
                <h2 class="text-center">List of Todos</h2>
                <hr>

                <div class="container text-left">
                    <a href="<%=request.getContextPath()%>/add-todo" class="btn btn-success">Add Todo</a>
                </div>
                <br>

                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Target Date</th>
                            <th>Todo Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="todo" items="${listTodo}">
                            <tr>
                                <td><c:out value="${todo.title}"/></td>
                                <td><c:out value="${todo.targetDate}"/></td>
                                <td>
                                    <c:if test="${todo.status eq 'false'}">
                                        In Progress
                                    </c:if>
                                    <c:if test="${todo.status eq 'true'}">
                                        Completed
                                    </c:if>
                                </td>
                                <td>
                                    <a href="update-todo?id=<c:out value='${todo.id}'/>">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="delete-todo?id=<c:out value='${todo.id}'/>">Delete</a>

                                    <!--<button (click)="updateTodo(todo.id)" class="btn btn-success">Update</button>
                                    <button (click)="deleteTodo(todo.id)" class="btn btn-warning">Delete</button>-->
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

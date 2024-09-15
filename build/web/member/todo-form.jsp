<%-- 
    Document   : todo-form
    Created on : Sep 14, 2024, 1:11:24 AM
    Author     : kelma
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todo Form</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="../component/header-admin.jsp"></jsp:include>

        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${todo != null}">
                        <form action="update-todo" method="post">
                        </c:if>
                        <c:if test="${todo == null}">
                            <form action="add-todo" method="post">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${todo != null}">Edit Todo</c:if>
                                    <c:if test="${todo == null}">Add New Todo</c:if>
                                    </h2>
                                </caption>

                            <c:if test="${todo != null}">
                                <input type="hidden" name="id" value="<c:out value="${todo.id}"/>"/>
                            </c:if>

                            <fieldset class="form-group">
                                <label>Todo Title</label>
                                <input type="text" name="title" value="<c:out value="${todo.title}"/>"
                                       class="form-control" minlength="5" required/>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Todo Description</label>
                                <input type="text" name="description" value="<c:out value="${todo.description}"/>"
                                       class="form-control" minlength="5"/>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Todo Target Date</label>
                                <input type="date" name="targetDate" value="<c:out value="${todo.targetDate}"/>"
                                       class="form-control" required/>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Todo Status</label>
                                <select name="status" class="form-control">
                                    <option 
                                        <c:if test="${todo.status eq 'false'}">
                                            selected="selected"
                                        </c:if>
                                        value="false">In Progress
                                    </option>
                                    <option 
                                        <c:if test="${todo.status eq 'true'}">
                                            selected="selected"
                                        </c:if>
                                        value="true">Completed
                                    </option>
                                </select>
                            </fieldset>

                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                </div>
            </div>
        </div>

        <jsp:include page="../component/footer.jsp"></jsp:include>
    </body>
</html>

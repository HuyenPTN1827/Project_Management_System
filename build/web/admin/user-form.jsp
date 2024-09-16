<%-- 
    Document   : user-form
    Created on : Sep 15, 2024, 7:45:37 PM
    Author     : kelma
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Form</title>
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

            <div class="container col-md-5" style="padding: 10px 0px 10px 0px">
                <div class="card">
                    <div class="card-body">
                    <c:if test="${user != null}">
                        <form action="update-user" method="post">
                        </c:if>
                        <c:if test="${user == null}">
                            <form action="add-user" method="post">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${user != null}">Edit User</c:if>
                                    <c:if test="${user == null}">Add New User</c:if>
                                    </h2>
                                </caption>

                            <c:if test="${user != null}">
                                <input type="hidden" name="id" value="<c:out value="${user.userId}"/>"/>
                            </c:if>

                            <fieldset class="form-group">
                                <label>Username:</label>
                                <input type="text" name="username" value="<c:out value="${user.username}"/>"
                                       class="form-control" required/>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Password:</label>
                                <input type="password" name="password" value="<c:out value="${user.password}"/>"
                                       class="form-control" required/>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Full Name:</label>
                                <input type="text" name="fullname" value="<c:out value="${user.fullname}"/>"
                                       class="form-control" required/>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Email:</label>
                                <input type="text" name="email" value="<c:out value="${user.email}"/>"
                                       class="form-control" required/>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Mobile:</label>
                                <input type="text" name="mobile" value="<c:out value="${user.mobile}"/>"
                                       class="form-control"/>
                            </fieldset>

                            <!--                            <fieldset class="form-group">
                                                            <label>Notes:</label>
                                                            <input type="text" name="notes" value="<c:out value="${user.notes}"/>"
                                                                   class="form-control"/>
                                                        </fieldset>-->

                            <fieldset class="form-group">
                                <label>User Role</label>
                                <select name="role" class="form-control" required>
                                    <option value="" disabled selected hidden>Choose Role</option>

                                    <option 
                                        <c:if test="${user.role eq 'Dept Manager'}">
                                            selected="selected"
                                        </c:if>
                                        value="Dept Manager">Dept Manager
                                    </option>

                                    <option 
                                        <c:if test="${user.role eq 'PMO Manager'}">
                                            selected="selected"
                                        </c:if>
                                        value="PMO Manager">PMO Manager
                                    </option>

                                    <option 
                                        <c:if test="${user.role eq 'Project QA'}">
                                            selected="selected"
                                        </c:if>
                                        value="Project QA">Project QA
                                    </option>

                                    <option 
                                        <c:if test="${user.role eq 'Project Manager'}">
                                            selected="selected"
                                        </c:if>
                                        value="Project Manager">Project Manager
                                    </option>

                                    <option 
                                        <c:if test="${user.role eq 'Team Leader'}">
                                            selected="selected"
                                        </c:if>
                                        value="Team Leader">Team Leader
                                    </option>

                                    <option 
                                        <c:if test="${user.role eq 'Member'}">
                                            selected="selected"
                                        </c:if>
                                        value="Member">Member
                                    </option>
                                </select>
                            </fieldset>

                            <c:if test="${user != null}">
                                <fieldset class="form-group">
                                    <label>User Status</label>
                                    <select name="status" class="form-control">
                                        <option 
                                            <c:if test="${user.status eq 'false'}">
                                                selected="selected"
                                            </c:if>
                                            value="false">Deactivate
                                        </option>
                                        <option 
                                            <c:if test="${user.status eq 'true'}">
                                                selected="selected"
                                            </c:if>
                                            value="true">Activate
                                        </option>
                                    </select>
                                </fieldset>
                            </c:if>

                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                </div>
            </div>
        </div>

        <jsp:include page="../component/footer.jsp"></jsp:include>
    </body>
</html>

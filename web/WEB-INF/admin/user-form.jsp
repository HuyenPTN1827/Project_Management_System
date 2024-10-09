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

                    <c:if test="${user == null}">
                        <form action="insert-user" method="post">
                            <caption>
                                <h2>Add New User</h2>
                            </caption>

                            <c:if test="${not empty errorMessages}">
                                <div class="alert alert-danger">
                                    <ul>
                                        <c:forEach items="${errorMessages}" var="error" >
                                            <li>${error}</li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </c:if>

                            <fieldset class="form-group">
                                <label>Full Name*:</label>
                                <input type="text" name="fullname" class="form-control" 
                                       value="${fullname}" required/>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Email*:</label>
                                <input type="text" name="email" class="form-control" 
                                       value="${email}" required/>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Mobile:</label>
                                <input type="text" name="mobile" class="form-control"
                                       value="${mobile}"/>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Password*:</label>
                                <input type="password" name="password" class="form-control" required/>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Department:</label>
                                <select name="dept" class="form-control">
                                    <option value="">Choose Department</option>
                                    <c:forEach items="${dept}" var="d">
                                        <option 
                                            <c:if test="${deptId eq d.id}">
                                                selected="selected"
                                            </c:if>
                                            value=${d.id}>${d.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Role:</label>
                                <select name="role" class="form-control">
                                    <option value="">Choose Role</option>
                                    <c:forEach items="${role}" var="r">
                                        <option 
                                            <c:if test="${roleId eq r.id}">
                                                selected="selected"
                                            </c:if>
                                            value=${r.id}>${r.value}
                                        </option>
                                    </c:forEach>
                                </select>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Notes:</label>
                                <input type="text" name="notes" class="form-control"/>
                            </fieldset>

                            <!--<input class="btn btn-secondary" type="button" value="Cancel" onclick="history.back()">-->
                            <a href="<%=request.getContextPath()%>/user-management" class="btn btn-secondary">Cancel</a>
                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </c:if>

                    <c:if test="${user != null}">
                        <form action="update-user" method="post">
                            <caption>
                                <h2>Edit User</h2>
                            </caption>

                            <c:if test="${not empty errorMessages}">
                                <div class="alert alert-danger">
                                    <ul>
                                        <c:forEach items="${errorMessages}" var="error" >
                                            <li>${error}</li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </c:if>

                            <input type="hidden" name="id" value="${user.id}"/>

                            <fieldset class="form-group">
                                <label>Full Name*:</label>
                                <input type="text" name="fullname" value="${user.full_name}"
                                       class="form-control" required/>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Email*:</label>
                                <input type="text" name="email" value="${user.email}"
                                       class="form-control" required/>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Mobile:</label>
                                <input type="text" name="mobile" value="${user.mobile}"
                                       class="form-control"/>
                            </fieldset>


                            <input type="hidden" name="password" value="${user.password}"/>

                            <fieldset class="form-group">
                                <label>Department:</label>
                                <select name="dept" class="form-control">
                                    <option value="" disabled hidden selected>Choose Department</option>
                                    <c:forEach items="${dept}" var="d">
                                        <option 
                                            <c:if test="${user.dept.id eq d.id}">
                                                selected="selected"
                                            </c:if>
                                            value=${d.id}>${d.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Role:</label>
                                <select name="role" class="form-control">
                                    <option value="" disabled hidden selected>Choose Role</option>
                                    <c:forEach items="${role}" var="r">
                                        <option 
                                            <c:if test="${user.setting.id eq r.id}">
                                                selected="selected"
                                            </c:if>
                                            value=${r.id}>${r.value}
                                        </option>
                                    </c:forEach>
                                </select>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Notes:</label>
                                <input type="text" name="notes" value="${user.notes}"
                                       class="form-control"/>
                            </fieldset>

                            <c:if test="${user.status eq '3'}">
                                <fieldset class="form-group">
                                    <label>Status*:</label>
                                    <input type="text" value="Unverified"
                                           class="form-control" readonly/>
                                    <input type="text" name="status" value="3"
                                           class="form-control" hidden/>
                                </fieldset>
                            </c:if>

                            <c:if test="${user.status ne '3'}">
                                <fieldset class="form-group">
                                    <label>Status*:</label>
                                    <select name="status" class="form-control">
                                        <option 
                                            <c:if test="${user.status eq '1'}">
                                                selected="selected"
                                            </c:if>
                                            value="1">Active
                                        </option>
                                        <option 
                                            <c:if test="${user.status eq '0'}">
                                                selected="selected"
                                            </c:if>
                                            value="0">Inactive
                                        </option>
                                    </select>
                                </fieldset>
                            </c:if>  
                            <!--                                <input class="btn btn-secondary" type="button" value="Cancel" onclick="history.back()">-->
                            <a href="<%=request.getContextPath()%>/user-management" class="btn btn-secondary">Cancel</a>
                            <button type="reset" class="btn btn-primary">Reset</button>
                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </c:if>

                </div>
            </div>
        </div>

        <jsp:include page="../component/footer.jsp"></jsp:include>
    </body>
</html>
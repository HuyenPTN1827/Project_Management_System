<%-- 
    Document   : project-detail
    Created on : Oct 18, 2024, 10:18:04 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Responsive Bootstrap 5 Admin & Dashboard Template">
        <meta name="author" content="AdminKit">
        <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icons/icon-48x48.png" />
        <title>Project Type Details | PMS</title>

        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">
        <link class="js-stylesheet" href="${pageContext.request.contextPath}/css/light.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/settings.js"></script>
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <jsp:include page="../component/sidebar.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>

                    <main class="content">
                        <div class="container-fluid p-0">
                        <c:if test="${projectType == null}">
                            <h1 class="h1 mb-3"> Create New Project Type</h1>
                            <div class="row">
                                <div class="col-md-12 col-xl-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <form action="insert-project" method="post" class="row">

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Biz Term<span style="color: red;">*</span></label>
                                                    <select class="form-control" name="bizterm" required>
                                                        <option value="">Select Biz Term</option>
                                                        <option value="1" <c:if test="${bizterm == 1}">selected</c:if>>Admin</option>
                                                        <option value="2" <c:if test="${bizterm == 2}">selected</c:if>>PMO Manager</option>
                                                        <option value="3" <c:if test="${bizterm == 3}">selected</c:if>>Dept Manager</option>
                                                        <option value="4" <c:if test="${bizterm == 4}">selected</c:if>>Project Manager</option>
                                                        <option value="5" <c:if test="${bizterm == 5}">selected</c:if>>Project QA</option>
                                                        <option value="6" <c:if test="${bizterm == 6}">selected</c:if>>Team Leader</option>
                                                        <option value="7" <c:if test="${bizterm == 7}">selected</c:if>>Member</option>
                                                        <option value="8" <c:if test="${bizterm == 8}">selected</c:if>>Critical</option>
                                                        <option value="9" <c:if test="${bizterm == 9}">selected</c:if>>High</option>
                                                        <option value="10" <c:if test="${bizterm == 10}">selected</c:if>>Medium</option>
                                                        <option value="11" <c:if test="${bizterm == 11}">selected</c:if>>Low</option>
                                                        </select>
                                                    </div>



                                                    <!-- Project Name Field -->
                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label">Project Name<span style="color: red;">*</span></label>
                                                        <input type="text" class="form-control" name="name" placeholder="Enter project Name" 
                                                               value="${name}" required>
                                                </div>

                                                <!-- Project Code Field -->
                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Project Code<span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="code" placeholder="Enter project Code" 
                                                           value="${code}" required>
                                                </div>

                                                <!-- Start Date Field -->
                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Start Date<span style="color: red;">*</span></label>
                                                    <input type="date" class="form-control" name="startDate" 
                                                           value="${startDate}" required>
                                                </div>
                                                <!-- Department Field -->
                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Department<span style="color: red;">*</span></label>
                                                    <select class="form-control" name="department" required>
                                                        <option value="">Select Department</option>
                                                        <option value="1">EM</option>
                                                        <option value="2">HR</option>
                                                        <option value="3">F&A</option>
                                                        <option value="4">S&M</option>
                                                        <option value="5">IT</option>
                                                        <option value="6">R&D</option>
                                                        <option value="7">OP</option>
                                                        <option value="8">Legal</option>
                                                        <option value="9">Admin</option>
                                                        <option value="10">CS</option>
                                                    </select>
                                                </div>

                                                <!-- Status Field -->
                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label">Status<span style="color: red;">*</span></label><br/>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="status" value="active" 
                                                               <c:if test="${status == 'active'}">checked</c:if>>
                                                               <label class="form-check-label text-success">Active</label>
                                                        </div>
                                                        <div class="form-check form-check-inline">
                                                            <input class="form-check-input" type="radio" name="status" value="inactive" 
                                                            <c:if test="${status == 'inactive'}">checked</c:if>>
                                                            <label class="form-check-label text-danger">Inactive</label>
                                                        </div>
                                                    </div>

                                                    <!-- Type Field -->
                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label">Type<span style="color: red;">*</span></label>
                                                        <select class="form-control" name="type" required>
                                                            <option value="">Select Type</option>
                                                            <option value="1" <c:if test="${type == 1}">selected</c:if>>WF</option>
                                                        <option value="2" <c:if test="${type == 2}">selected</c:if>>AG</option>
                                                        <option value="3" <c:if test="${type == 3}">selected</c:if>>SC</option>
                                                        <option value="4" <c:if test="${type == 4}">selected</c:if>>VM</option>
                                                        <option value="5" <c:if test="${type == 5}">selected</c:if>>SM</option>
                                                        <option value="6" <c:if test="${type == 6}">selected</c:if>>IM</option>
                                                        <option value="7" <c:if test="${type == 7}">selected</c:if>>DO</option>
                                                        </select>
                                                    </div>





                                                    <!-- Details Field -->
                                                    <div class="mb-3 col-md-12">
                                                        <label class="form-label">Details</label>
                                                        <textarea class="form-control" name="details" 
                                                                  placeholder="Enter project type details" rows="3">${details}</textarea>
                                                </div>
                                                ${errorMessage}
                                                <!-- Save and Cancel Buttons -->
                                                <div>
                                                    <button type="submit" class="btn btn-lg btn-success">Save</button>
                                                    <a href="<%=request.getContextPath()%>/project-type-management" class="btn btn-lg btn-light">Cancel</a>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>



                    </div>
                </main>

                <footer class="footer">
                    <div class="container-fluid">
                        <div class="row text-muted">
                            <div class="col-6 text-start">
                                <p class="mb-0">
                                    <a href="https://adminkit.io/" target="_blank" class="text-muted">AdminKit</a> - 
                                    <a href="#" class="text-muted">Documentation</a>
                                </p>
                            </div>
                            <div class="col-6 text-end">
                                <ul class="list-inline">
                                    <li class="list-inline-item">
                                        <a class="text-muted" href="#">Support</a>
                                    </li>
                                    <li class="list-inline-item">
                                        <a class="text-muted" href="#">Terms</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/app.js"></script>
    </body>
</html>

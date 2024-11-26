<%-- 
    Document   : projectlist
    Created on : Oct 17, 2024, 10:29:52 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
        <meta name="author" content="Admin">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icons/icon-48x48.png" />
        <title>Project List | PMS</title>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">
        <link class="js-stylesheet" href="${pageContext.request.contextPath}/css/light.css" rel="stylesheet">
        <!-- Thêm Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet">

        <script src="${pageContext.request.contextPath}/js/settings.js"></script>
        <style>
            body {
                opacity: 0;
            }
        </style>
    </head>
    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <jsp:include page="../component/sidebar-manager.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>
                    <main class="content">
                        <div class="container-fluid p-0">
                            <div class="mb-3">
                                <h1 class="h1 d-inline align-middle">Project List</h1>
                            </div>
                            <div class="row">
                                <div class="col-md-12 col-xl-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <div class="d-flex justify-content-between align-items-center" style="margin: 10px;">
                                                <form action="projectlist" method="GET" class="d-flex align-items-center" style="gap: 15px;">

                                                    <select name="status" class="form-select" style="width: 130px;" onchange="this.form.submit();">
                                                        <option value="">All Status</option>
                                                        <option value="0" <c:if test="${status == 0}">selected="selected"</c:if>>Pending</option>
                                                    <option value="1" <c:if test="${status == 1}">selected="selected"</c:if>>To Do</option>
                                                    <option value="2" <c:if test="${status == 2}">selected="selected"</c:if>>Doing</option>
                                                    <option value="3" <c:if test="${status == 3}">selected="selected"</c:if>>Done</option>
                                                    <option value="4" <c:if test="${status == 4}">selected="selected"</c:if>>Closed</option>
                                                    <option value="5" <c:if test="${status == 5}">selected="selected"</c:if>>Cancelled</option>
                                                    </select>

                                                    <input type="search" name="keyword" class="form-control" style="width: 270px;" placeholder="Enter Project Name or Code" id="keyword" value="${keyword}">
                                                <button type="submit" class="btn btn-primary">Search</button>
                                            </form>

                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewModal">
                                                Add New
                                            </button>

                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <table id="datatables-multi" class="table table-striped" style="width:100%; table-layout: auto;">
                                            <thead>
                                                <tr style="text-align: center">
                                                    <th>ID</th>
                                                    <th>Name</th>
                                                    <th>Code</th>
                                                    <th>Type Name</th>
                                                    <th>Department Name</th>
                                                    <th>Status</th>
                                                    <th>Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.listProjects}" var="project">
                                                    <tr style="text-align: center">
                                                        <td>${project.id}</td>
                                                        <td>${project.name}</td>
                                                        <td>${project.code}</td>
                                                        <td>${project.typeName}</td>
                                                        <td>${project.departmentName}</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${project.status == 0}">
                                                                    <span class="badge bg-secondary">Pending</span>
                                                                </c:when>
                                                                <c:when test="${project.status == 1}">
                                                                    <span class="badge bg-info">To Do</span>
                                                                </c:when>
                                                                <c:when test="${project.status == 2}">
                                                                    <span class="badge bg-primary">Doing</span>
                                                                </c:when>
                                                                <c:when test="${project.status == 3}">
                                                                    <span class="badge bg-success">Done</span>
                                                                </c:when>
                                                                <c:when test="${project.status == 4}">
                                                                    <span class="badge bg-warning">Closed</span>
                                                                </c:when>
                                                                <c:when test="${project.status == 5}">
                                                                    <span class="badge bg-danger">Cancelled</span>
                                                                </c:when>
                                                            </c:choose>
                                                        </td>

                                                        <td>
                                                            <a href="<%=request.getContextPath()%>/edit-project?id=${project.id}" class="btn btn-link text-primary">Edit</a>
                                                            <a href="<%=request.getContextPath()%>/projectconfig?id=${project.id}" class="btn btn-link text-secondary">Config</a>

                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="footer">
                    <div class="container-fluid">
                        <div class="row text-muted">
                            <div class="col-6 text-start">
                                <p class="mb-0">
                                    <a href="https://adminkit.io/" target="_blank" class="text-muted"><strong>AdminKit</strong></a> &copy;
                                </p>
                            </div>
                            <div class="col-6 text-end">
                                <ul class="list-inline">
                                    <li class="list-inline-item"><a class="text-muted" href="#">Support</a></li>
                                    <li class="list-inline-item"><a class="text-muted" href="#">Help Center</a></li>
                                    <li class="list-inline-item"><a class="text-muted" href="#">Privacy</a></li>
                                    <li class="list-inline-item"><a class="text-muted" href="#">Terms</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
                                                <style>
  
    /* Modal căn giữa theo chiều ngang và dọc */
#addNewModal .modal-dialog {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%); /* Căn giữa cả chiều ngang và chiều dọc */
    margin: 0;
    max-width: 800px; /* Có thể thay đổi theo ý muốn */
}

#addNewModal .modal-content {
    border-radius: 8px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3); /* Bóng đổ nhẹ cho modal */
}

</style>

        <!-- Add New Modal -->
        <div class="modal fade" id="addNewModal" tabindex="-1" aria-labelledby="addNewModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addNewModalLabel">New Project</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="insert-project" method="post" class="row">

                            <!-- Project Name Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label">Project Name<span style="color: red;">*</span></label>
                                <input type="text" class="form-control" name="name" placeholder="Enter project Name" value="${name}" required>
                            </div>

                            <!-- Project Code Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label">Project Code<span style="color: red;">*</span></label>
                                <input type="text" class="form-control" name="code" placeholder="Enter project Code" value="${code}" required>
                            </div>

                            <!--Type Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label">Project Type<span style="color: red;">*</span></label>
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

                                <!-- Estimated Effort Field -->
                                <div class="mb-3 col-md-6">
                                    <label class="form-label">Estimated Effort(man-days)<span style="color: red;">*</span></label>
                                    <input type="number" class="form-control" name="estimatedEffort" placeholder="Enter estimated effort" value="${estimatedEffort}" required>
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

                            <!-- Project Manager Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label">Project Manager<span style="color: red;">*</span></label>
                                <select class="form-control" name="projectManagerId" required>
                                    <option value="">Select Project Manager</option>
                                    <!-- Populate options dynamically -->
                                    <c:forEach var="manager" items="${listManagers}">
                                        <option value="${manager.id}" <c:if test="${manager.id == projectManagerId}">selected</c:if>>
                                            ${manager.full_name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- Start Date Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label">Start Date</label>
                                <input type="date" class="form-control" name="startDate" value="${startDate}" required>
                            </div>

                            <!-- Last Update Field -->
                            <!--                            <div class="mb-3 col-md-6">
                                                            <label class="form-label">Last Update</label>
                                                            <input type="date" class="form-control" name="lastUpdate" value="${lastUpdate}" required>
                                                        </div>-->


                            <!-- End Date Field -->
                            <div class="mb-3 col-md-6">
                                <label class="form-label">End Date</label>
                                <input type="date" class="form-control" name="endDate" value="${endDate}" required>
                            </div>






                            <!-- Descriptions Field -->
                            <div class="mb-3 col-md-12">
                                <label class="form-label">Description</label>
                                <textarea class="form-control" name="details" placeholder="Enter project type details" rows="3">${details}</textarea>
                            </div>

                            ${errorMessage}

                            <!-- Save and Cancel Buttons -->
                            <div>
                                <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                <a href="<%=request.getContextPath()%>/project-type-management" class="btn btn-lg btn-light">Cancel</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

       

        <script>
                                                        // Initialize DataTables with sorting functionality
                                                        $(document).ready(function () {
                                                            $('#datatables-multi').DataTable({
                                                                "order": [], // Removes default ordering if required
                                                                "paging": true,
                                                                "searching": false,
                                                                "info": true,
                                                                "autoWidth": false,
                                                                "columnDefs": [
                                                                    {"orderable": false, "targets": [8]} // Disable sorting for the Actions column
                                                                ]
                                                            });
                                                        });
        </script>
        
        <script src="${pageContext.request.contextPath}/js/app.js"></script>
        <script src="${pageContext.request.contextPath}/js/datatables.js"></script>
    </body>
</html>

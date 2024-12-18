<%-- 
    Document   : project-list
    Created on : Dec 2, 2024, 4:47:46 PM
    Author     : BachDH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
        <meta name="author" content="PMS">
        <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icons/icon-48x48.png" />

        <!--<link rel="canonical" href="tables-datatables-multi.html" />-->

        <title>Project Management | PMS</title>

        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">

        <!-- Choose your prefered color scheme -->
        <!-- <link href="css/light.css" rel="stylesheet"> -->
        <!-- <link href="css/dark.css" rel="stylesheet"> -->

        <!-- BEGIN SETTINGS -->
        <!-- Remove this after purchasing -->
        <link class="js-stylesheet" href="${pageContext.request.contextPath}/css/light.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/settings.js"></script>
        <style>
            body {
                opacity: 0;
            }
        </style>
        <!-- END SETTINGS -->
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-120946860-10"></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());

            gtag('config', 'UA-120946860-10', {'anonymize_ip': true});

            function openProjectModal(id = null) {
                let url = '<%=request.getContextPath()%>/add-project'; // Default for Create New
                if (id) {
                    url = '<%=request.getContextPath()%>/edit-project?id=' + id; // For Edit
                }

                fetch(url)
                        .then(response => response.text())
                        .then(data => {
                            document.querySelector('#projectModal .modal-body').innerHTML = data;
                            document.getElementById('projectModal').style.display = 'block';
                        })
                        .catch(error => console.log('Error loading the form:', error));
            }

            function closeModal() {
                document.getElementById('projectModal').style.display = 'none';
            }
        </script>
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <% request.setAttribute("currentPage", "project"); %>
            <jsp:include page="../component/sidebar.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>

                    <main class="content">
                        <div class="container-fluid p-0">

                            <div class="mb-3">
                                <h1 class="h1 d-inline align-middle">Project Management</h1>
                            </div>

                            <div class="row">
                                <div class="col-md-12 col-xl-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <div class="d-flex justify-content-between align-items-center" style="margin: 10px;">
                                                <form action="projectlist" method="post" class="d-flex align-items-center" style="gap: 10px;">
                                                    <div class="col-md-2">
                                                        <select name="typeId" class="form-select">
                                                            <option value="">All Project Types</option>
                                                        <c:forEach items="${listProjectTypes}" var="type">
                                                            <option 
                                                                <c:if test="${projectType eq type.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="${type.id}">${type.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>


                                                <div class="col-md-2">
                                                    <select name="deptId" class="form-select">
                                                        <option value="">All Departments</option>
                                                        <c:forEach items="${listDepartments}" var="dept">
                                                            <option 
                                                                <c:if test="${department eq dept.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="${dept.id}">${dept.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>


                                                <div class="col-md-2">
                                                    <select name="status" class="form-select">
                                                        <option value="">All Status</option>
                                                        <option value="0" <c:if test="${status == 0}">selected="selected"</c:if>>Pending</option>
                                                        <option value="1" <c:if test="${status == 1}">selected="selected"</c:if>>Doing</option>
                                                        <option value="2" <c:if test="${status == 2}">selected="selected"</c:if>>Closed</option>
                                                        <option value="3" <c:if test="${status == 3}">selected="selected"</c:if>>Cancelled</option>
                                                        </select>
                                                    </div>

                                                    <div class="col-md-4">
                                                        <input type="search" name="keyword" class="form-control" 
                                                               placeholder="Enter Project Name or Code" id="keyword" value="${keyword}">
                                                </div>

                                                <div class="col-md-2">
                                                    <button type="submit" class="btn btn-primary">Search</button>
                                                </div>
                                            </form>

                                            <div class="col-md-2 d-flex justify-content-end align-items-end">

                                                <c:if test="${user.role_id == 2 || user.role_id == 3}">
                                                    <a class="btn btn-primary" href="javascript:void(0);" onclick="openProjectModal();">Create new</a>
                                                </c:if>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-body">
                                        <table id="datatables-multi" class="table table-striped" style="width:100%">
                                            <thead>
                                                <tr style="text-align: center">
                                                    <th>ID</th>
                                                    <th>Name</th>
                                                    <th>Code</th>
                                                    <th>Project Type</th>
                                                    <th>Department</th>
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
                                                                    <span class="badge bg-primary">Doing</span>
                                                                </c:when>
                                                                <c:when test="${project.status == 2}">
                                                                    <span class="badge bg-success">Closed</span>
                                                                </c:when>
                                                                <c:when test="${project.status == 3}">
                                                                    <span class="badge bg-danger">Cancelled</span>
                                                                </c:when>
                                                            </c:choose>
                                                        </td>

                                                        <td>
                                                            <a href="<%=request.getContextPath()%>/projectconfig?id=${project.id}" class="btn btn-secondary">
                                                                <i class="align-middle" data-feather="settings"></i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                                <!-- Add New Modal -->
                                <div id="projectModal" class="modal" tabindex="-1" role="dialog">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title">Add New Project</h1>
                                                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close" onclick="closeModal();"></button>
                                            </div>
                                            <div class="modal-body">
                                                <!-- This is where the project-add.jsp will be loaded via AJAX -->
                                                <c:if test="${not empty errorMessage}">
                                                    <div class="alert alert-danger" id role="alert">
                                                        ${errorMessage}
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
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
                                    <a href="https://adminkit.io/" target="_blank" class="text-muted"><strong>PMS</strong></a> &copy;
                                </p>
                            </div>
                            <div class="col-6 text-end">
                                <ul class="list-inline">
                                    <li class="list-inline-item">
                                        <a class="text-muted" href="#">Support</a>
                                    </li>
                                    <li class="list-inline-item">
                                        <a class="text-muted" href="#">Help Center</a>
                                    </li>
                                    <li class="list-inline-item">
                                        <a class="text-muted" href="#">Privacy</a>
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

        <script src="${pageContext.request.contextPath}/js/datatables.js"></script>

        <script>
                                                    document.addEventListener("DOMContentLoaded", function () {
                                                        var datatablesMulti = $("#datatables-multi").DataTable({
                                                            responsive: true,
                                                            paging: true,
                                                            searching: false,
                                                            info: true,
                                                            order: [[0, 'desc']], // Default sort by ID column in descending order
                                                            columnDefs: [
                                                                {orderable: false, targets: 6} // Disable sorting on the 'Action' column
                                                            ],
                                                            language: {
                                                                paginate: {
                                                                    previous: '<i class="align-middle" data-feather="chevron-left"></i>',
                                                                    next: '<i class="align-middle" data-feather="chevron-right"></i>'
                                                                },
                                                                info: "_TOTAL_ project(s) found",
                                                                infoEmpty: "No project found"
                                                            },
                                                            dom: '<"row"<"col-sm-6"i><"col-sm-6 d-flex justify-content-end"l>>t<"row"<"col-sm-12"p>>', // Updated layout for page-length to be at the end
                                                            initComplete: function () {
                                                                // Add necessary classes for alignment
                                                                $('.dataTables_info').addClass('text-left fw-bolder');
                                                                $('.dataTables_length').addClass('mt-2'); // Add necessary margin classes

                                                                // Replace Feather icons after DataTable initializes
                                                                feather.replace();
                                                            }
                                                        });

                                                        // Replace Feather icons in case of dynamic changes
                                                        datatablesMulti.on('draw', function () {
                                                            feather.replace();
                                                        });
                                                    });

        </script>

        <script>
            function validateForm(event) {
                event.preventDefault(); // Prevent the form from submitting immediately
                const errorContainer = document.getElementById("errorContainer");
                const errorList = document.getElementById("errorList");
                errorList.innerHTML = ""; // Clear previous error messages
                errorContainer.classList.add("d-none");

                const code = document.getElementById("code").value;
                const estimatedEffort = document.getElementById("estimatedEffort").value;
                const startDate = document.getElementById("startDate").value;
                const endDate = document.getElementById("endDate").value;
                const today = new Date().toISOString().split("T")[0];
                let hasError = false;

                // Perform AJAX check for existing project code
                fetch('<%=request.getContextPath()%>/check-project-code?code=' + encodeURIComponent(code))
                        .then(response => response.json())
                        .then(data => {
                            if (data.exists) {
                                hasError = true;
                                errorList.innerHTML += "<li>Project Code already exists. Please use a different code.</li>";
                            }

                            //Kiểm tra estimatedEffort
                            if (estimatedEffort <= 0) {
                                hasError = true;
                                errorList.innerHTML += "<li>Estimated Effort must be a positive number greater than 0.</li>";
                            }

                            // Kiểm tra startDate
                            if (!startDate) {
                                hasError = true;
                                errorList.innerHTML += "<li>Start Date is required.</li>";
                            } else if (startDate < today) {
                                hasError = true;
                                errorList.innerHTML += "<li>Start Date cannot be earlier than today.</li>";
                            }

                            // Kiểm tra toDate
                            if (endDate && endDate < startDate) {
                                hasError = true;
                                errorList.innerHTML += "<li>End Date cannot be earlier than Start Date.</li>";
                            }

                            // Show error messages if there are any
                            if (hasError) {
                                errorContainer.classList.remove("d-none");
                            } else {
                                // If no errors, submit the form
                                document.getElementById("projectForm").submit();
                            }
                        })
                        .catch(error => {
                            console.log('Error checking project code:', error);
                            // Optionally display an error message for AJAX failure
                            errorList.innerHTML += "<li>Error checking project code. Please try again.</li>";
                            errorContainer.classList.remove("d-none");
                        });
            }

        </script>

        <script>
            document.addEventListener("DOMContentLoaded", function (event) {
                // Check URL for 'success' parameter
                const urlParams = new URLSearchParams(window.location.search);
                if (urlParams.get('create-project') === 'success') {
                    // Show success notification
                    window.notyf.open({
                        type: "success",
                        message: "New Project created successfully. Parent milestone has been automatically created, please check!",
                        duration: 5000, // Adjust duration as needed
                        ripple: true,
                        dismissible: true,
                        position: {
                            x: "right",
                            y: "top"
                        }
                    });
                }
            });
        </script>

    </body>

</html>

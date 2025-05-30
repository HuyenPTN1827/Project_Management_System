<%-- 
    Document   : issue-list
    Created on : Nov 23, 2024, 5:31:33 AM
    Author     : HuyenPTNHE160769
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

        <title>Issue Management | PMS</title>

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

            function redirectToConfigPage(userId) {
                const selectedId = document.getElementById("projectDropdown").value;
                if (selectedId) {
                    // Redirect to the project type config page with the selected ID
                    window.location.href = 'issue-management?projectId=' + selectedId;
                }
            }
        </script>
    </head>

    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <% request.setAttribute("currentPage", "issue-management"); %>
            <jsp:include page="../component/sidebar.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>

                    <main class="content">
                        <div class="container-fluid p-0">

                            <div class="mb-3">
                                <h1 class="h1 d-inline align-middle">Issue Management</h1>
                            </div>

                            <div class="row">
                                <div class="col-md-12 col-xl-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <div class="d-flex justify-content-between align-items-center" style="margin: 10px;">
                                                <form action="issue-management" method="post" class="d-flex align-items-center flex-wrap" style="gap: 10px; width: 97%;">
                                                    <div class="form-group d-flex align-items-center" style="gap: 10px; width: 97%;">
                                                        <!--<input type="hidden" name="userId" value="${user.id}">-->

                                                    <select name="projectId" id="projectDropdown" class="form-select" onchange="redirectToConfigPage(${user.id})">
                                                        <option value="">All Projects</option>
                                                        <c:forEach items="${listPj}" var="p">
                                                            <option 
                                                                <c:if test="${projectId eq p.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="${p.id}">${p.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>

                                                    <select name="milestone" class="form-select">
                                                        <option value="">All Milestones</option>
                                                        <c:forEach items="${listMilestone}" var="m">
                                                            <option 
                                                                <c:if test="${milestone eq m.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="${m.id}">${m.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>

                                                    <!--                                                    <select name="scope" class="form-select">
                                                                                                            <option value="">All Work Packages</option>
                                                    <%--<c:forEach items="${listScope}" var="wp">--%>
                                                        <option 
                                                    <%--<c:if test="${scope eq wp.id}">--%>
                                                        selected="selected"
                                                    <%--</c:if>--%>
                                                    value="${wp.id}">${wp.title}
                                                </option>
                                                    <%--</c:forEach>--%>
                                                </select>-->

                                                    <select name="assigner" class="form-select">
                                                        <option value="">All Assigners</option>
                                                        <c:forEach items="${listAssigner}" var="u">
                                                            <option 
                                                                <c:if test="${assigner eq u.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="${u.id}">${u.full_name} (${(u.username)})
                                                            </option>
                                                        </c:forEach>
                                                    </select>

                                                    <select name="assignee" class="form-select">
                                                        <option value="">All Assignees</option>
                                                        <c:forEach items="${listAssignee}" var="u">
                                                            <option 
                                                                <c:if test="${assignee eq u.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="${u.id}">${u.full_name} (${(u.username)})
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="d-flex align-items-center" style="gap: 10px; width: 97%;">
                                                    <select name="type" class="form-select">
                                                        <option value="">All Types</option>
                                                        <c:forEach items="${listType}" var="t">
                                                            <option 
                                                                <c:if test="${type eq t.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="${t.id}">${t.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>


                                                    <select name="status" class="form-select">
                                                        <option value="">All Status</option>
                                                        <option 
                                                            <c:if test="${status eq '0'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="0">Pending
                                                        </option>
                                                        <option 
                                                            <c:if test="${status eq '1'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="1">In Progress
                                                        </option>
                                                        <option 
                                                            <c:if test="${status eq '4'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="2">Closed
                                                        </option>
                                                        <option 
                                                            <c:if test="${status eq '5'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="3">Cancelled
                                                        </option>
                                                    </select>

                                                    <div class="form-group" style="width: 210%;">
                                                        <input type="search" name="keywordIssue" class="form-control" placeholder="Enter the Project Issue" id="keywordIssue" value="${keywordIssue}">
                                                    </div>
                                                    <button type="submit" class="btn btn-primary">Search</button>
                                                </div>
                                            </form>

                                            <a class="btn btn-primary" href="<%=request.getContextPath()%>/add-issue?userId=${user.id}">Create new</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-body">
                                        <table id="datatables-multi" class="table table-striped" style="width:100%">
                                            <thead>
                                                <tr style="text-align: center">
                                                    <th>ID</th>
                                                    <th>Issue</th>
                                                    <th>Type</th>
                                                    <!--<th>Project</th>-->
                                                    <th>Milestone</th>
                                                    <!--<th>Work Package</th>-->
                                                    <th>Assigner</th>
                                                    <th>Assignee</th>
                                                    <th>Deadline</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.listIssue}" var="issue">
                                                    <tr style="text-align: center">
                                                        <td>${issue.id}</td>
                                                        <td>${issue.name}</td>
                                                        <td>${issue.type.name}</td>
                                                        <!--<td>${issue.project.code}</td>-->
                                                        <td>${issue.milestone.name}</td>
                                                        <!--<td>${issue.scope.title}</td>-->
                                                        <td>${issue.created_by.username}</td>
                                                        <td>${issue.assignee.username}</td>
                                                        <td>${issue.deadline}</td>
                                                        <td>
                                                            <c:if test="${issue.status eq '0'}">
                                                                <span class="badge bg-secondary">Pending</span>
                                                            </c:if>
                                                            <c:if test="${issue.status eq '1'}">
                                                                <span class="badge bg-primary">In progress</span>
                                                            </c:if>
                                                            <c:if test="${issue.status eq '2'}">
                                                                <span class="badge bg-success">Closed</span>
                                                            </c:if>
                                                            <c:if test="${issue.status eq '3'}">
                                                                <span class="badge bg-danger">Cancelled</span>
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <c:if test="${user.role_id eq 5}">
                                                                <c:if test="${user.id eq issue.created_by.id || user.id eq issue.assignee.id}">
                                                                    <a href="<%=request.getContextPath()%>/edit-issue?action=edit&id=${issue.id}&userId=${user.id}&projectId=${issue.project.id}" 
                                                                       class="btn btn-info"><i class="align-middle" data-feather="edit"></i></a>
                                                                    </c:if>
                                                                    <c:if test="${user.id ne issue.created_by.id && user.id ne issue.assignee.id}">
                                                                    <a href="<%=request.getContextPath()%>/edit-issue?action=view&id=${issue.id}&userId=${user.id}&projectId=${issue.project.id}" 
                                                                       class="btn btn-info"><i class="align-middle" data-feather="eye"></i></a>
                                                                    </c:if>
                                                                </c:if>

                                                            <c:if test="${user.role_id eq 2}">
                                                                <a href="<%=request.getContextPath()%>/edit-issue?action=edit&id=${issue.id}&userId=${user.id}&projectId=${issue.project.id}" 
                                                                   class="btn btn-info"><i class="align-middle" data-feather="edit"></i></a>
                                                                </c:if>

                                                            <c:if test="${user.role_id eq 3}">
                                                                <c:if test="${issue.deptManager eq user.id}">
                                                                    <a href="<%=request.getContextPath()%>/edit-issue?action=edit&id=${issue.id}&userId=${user.id}&projectId=${issue.project.id}" 
                                                                       class="btn btn-info"><i class="align-middle" data-feather="edit"></i></a>
                                                                    </c:if>
                                                                    <c:if test="${issue.deptManager ne user.id}">
                                                                    <a href="<%=request.getContextPath()%>/edit-issue?action=view&id=${issue.id}&userId=${user.id}&projectId=${issue.project.id}" 
                                                                       class="btn btn-info"><i class="align-middle" data-feather="eye"></i></a>
                                                                    </c:if>
                                                                </c:if>
                                                            
                                                            <c:if test="${user.role_id eq 4}">
                                                                <c:if test="${issue.project.userId eq user.id}">
                                                                    <a href="<%=request.getContextPath()%>/edit-issue?action=edit&id=${issue.id}&userId=${user.id}&projectId=${issue.project.id}" 
                                                                       class="btn btn-info"><i class="align-middle" data-feather="edit"></i></a>
                                                                    </c:if>
                                                                    <c:if test="${issue.project.userId ne user.id}">
                                                                    <a href="<%=request.getContextPath()%>/edit-issue?action=view&id=${issue.id}&userId=${user.id}&projectId=${issue.project.id}" 
                                                                       class="btn btn-info"><i class="align-middle" data-feather="eye"></i></a>
                                                                    </c:if>
                                                                </c:if>

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
                                                                    {orderable: false, targets: 8} // Disable sorting on the 'Action' column
                                                                ],
                                                                language: {
                                                                    paginate: {
                                                                        previous: '<i class="align-middle" data-feather="chevron-left"></i>',
                                                                        next: '<i class="align-middle" data-feather="chevron-right"></i>'
                                                                    },
                                                                    info: "_TOTAL_ issue(s) found",
                                                                    infoEmpty: "No issue found"
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
            document.addEventListener("DOMContentLoaded", function (event) {
                // Check URL for 'success' parameter
                const urlParams = new URLSearchParams(window.location.search);
                if (urlParams.get('update-issue') === 'success') {
                    // Show success notification
                    window.notyf.open({
                        type: "success",
                        message: "Issue details updated successfully.",
                        duration: 5000, // Adjust duration as needed
                        ripple: true,
                        dismissible: true,
                        position: {
                            x: "right",
                            y: "top"
                        }
                    });
                }
                if (urlParams.get('create-issue') === 'success') {
                    // Show success notification
                    window.notyf.open({
                        type: "success",
                        message: "New Issue created successfully.",
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

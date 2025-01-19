<%-- 
    Document   : issue-detail
    Created on : Nov 24, 2024, 2:48:08 AM
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

        <link rel="canonical" href="pages-profile.html" />

        <title>Issue Details | PMS</title>

        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">

        <!-- Choose your prefered color scheme -->
        <!-- <link href="css/light.css" rel="stylesheet"> -->
        <!-- <link href="css/dark.css" rel="stylesheet"> -->

        <!-- BEGIN SETTINGS -->
        <!-- Remove this after purchasing -->
        <link class="js-stylesheet" href="${pageContext.request.contextPath}/css/light.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/settings.js"></script>
        <style>body {
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
                const projectId = document.getElementById("projectDropdown").value;
                const type = document.getElementById("type").value;
                const name = document.getElementById("name").value;
                const milestone = document.getElementById("milestone").value;
                const assignee = document.getElementById("assignee").value;
                const deadline = document.getElementById("deadline").value;
                const description = document.getElementById("description").value;

                let url = 'add-issue?userId=' + userId;
                if (projectId) {
                    url += '&projectId=' + projectId;
                }
                if (type) {
                    url += '&type=' + type;
                }
                if (name) {
                    url += '&name=' + name;
                }
                if (milestone) {
                    url += '&milestone=' + milestone;
                }
                if (deadline) {
                    url += '&assignee=' + assignee;
                }
                if (deadline) {
                    url += '&deadline=' + deadline;
                }
                if (description) {
                    url += '&description=' + description;
                }
                window.location.href = url;
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
                        <c:if test="${projectId == null}">
                            <a href="fallback-url.html" onclick="history.back(); return false;">Issue Management ></a>
                        </c:if>
                        <c:if test="${projectId != null}">
                            <a href="<%=request.getContextPath()%>/issue-management?projectId=${projectId}">Issue Management ></a>
                        </c:if>

                        <h1 class="h1 mt-2 mb-3"> Create New Issue</h1>
                        <div class="row">

                            <div class="col-md-12 col-xl-12">
                                <div class="card">
                                    <div class="card-body">

                                        <c:if test="${not empty errorMessages}">
                                            <div class="alert alert-danger pt-3 pe-3 ps-3">
                                                <ul>
                                                    <c:forEach items="${errorMessages}" var="error" >
                                                        <li>${error}</li>
                                                        </c:forEach>
                                                </ul>
                                            </div>
                                        </c:if>

                                        <c:if test="${issue == null}">
                                            <form action="insert-issue" method="post" class="row">
                                                <input type="hidden" name="userId" value="${user.id}">

                                                <div class="mb-3 col-md-12">
                                                    <label class="form-label"><strong>Issue</strong> <span style="color: red;">*</span></label>
                                                    <input type="text" class="form-control" name="name" placeholder="Enter the Issue name"
                                                           value="${name}" id="name" required>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label"><strong>Issue Type</strong> <span style="color: red;">*</span></label>
                                                    <select name="type" id="type" class="form-select" required>
                                                        <option value="" hidden disable>Choose Issue Type</option>
                                                        <c:forEach items="${listType}" var="t">
                                                            <option 
                                                                <c:if test="${type eq t.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="${t.id}">${t.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label"><strong>Project</strong> <span style="color: red;">*</span></label>
                                                    <select name="projectId" id="projectDropdown" class="form-select" 
                                                            onchange="redirectToConfigPage(${userId})" required>
                                                        <option value="" hidden disable>Choose Project</option>
                                                        <c:if test="${user.role_id == '2'}">
                                                            <c:forEach items="${listAllPj}" var="p">
                                                                <option 
                                                                    <c:if test="${projectId eq p.id}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="${p.id}">${p.name} (${p.code})
                                                                </option>
                                                            </c:forEach>
                                                        </c:if>
                                                        <c:if test="${user.role_id != '2'}">
                                                            <c:forEach items="${listPj}" var="p">
                                                                <option 
                                                                    <c:if test="${projectId eq p.id}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="${p.id}">${p.name} (${p.code})
                                                                </option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label"><strong>Milestone</strong> <span style="color: red;">*</span></label>
                                                    <select name="milestone" id="milestone" class="form-select" required>
                                                        <option value="" hidden disable>Choose Milestone</option>
                                                        <c:forEach items="${listMilestone}" var="m">
                                                            <option 
                                                                <c:if test="${milestone eq m.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value=${m.id}>${m.name}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label"><strong>Assignee</strong> <span style="color: red;">*</span></label>
                                                    <select name="assignee" id="assignee" class="form-select" required>
                                                        <option value="" hidden disable>Choose Assignee</option>
                                                        <c:forEach items="${listAssignee}" var="u">
                                                            <option 
                                                                <c:if test="${assignee eq u.id}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value=${u.id}>${u.full_name} (${u.username})
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label"><strong>Deadline</strong> <span style="color: red;">*</span></label>
                                                    <input type="date" class="form-control" name="deadline" placeholder="dd/MM/yyyy" 
                                                           value="${deadline}" id="deadline" required>
                                                </div>


                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label"><strong>Status</strong></label>
                                                    <select name="status" class="form-select">
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
                                                            value="1">In progress
                                                        </option>
                                                        <option 
                                                            <c:if test="${status eq '2'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="2">Closed
                                                        </option>
                                                        <option 
                                                            <c:if test="${status eq '3'}">
                                                                selected="selected"
                                                            </c:if>
                                                            value="3">Cancelled
                                                        </option>
                                                    </select>
                                                </div>

                                                <div class="mb-3 col-md-12">
                                                    <label class="form-label"><strong>Description</strong></label>
                                                    <textarea class="form-control" name="description" id="description" 
                                                              placeholder="Enter the Description" rows="3">${description}</textarea>
                                                </div>

                                                <div>
                                                    <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                                </div>
                                            </form>
                                        </c:if> 

                                        <c:if test="${issue != null}">
                                            <form action="update-issue" method="post" class="row">
                                                <c:if test="${action == 'edit' && user.role_id ne 5 || user.role_id eq 5 && user.id eq issue.created_by.id}">
                                                    <input type="hidden" name="id" value="${issue.id}"/>
                                                    <input type="hidden" name="userId" value="${user.id}"/>
                                                    <input type="hidden" name="action" value="edit"/>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Created By</strong></label>
                                                        <input type="text" class="form-control" placeholder="Full Name (Username)" 
                                                               value="${issue.created_by.full_name} (${issue.created_by.username})" readonly>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Last Updated</strong></label>
                                                        <input type="datetime-local" class="form-control" name="last_updated" placeholder="dd/MM/yyyy hh:mm:ss" 
                                                               value="${issue.last_updated}" readonly>
                                                    </div>

                                                    <div class="mb-3 col-md-12">
                                                        <label class="form-label"><strong>Issue</strong> <span style="color: red;">*</span></label>
                                                        <input type="text" class="form-control" name="name" placeholder="Enter the Issue name"
                                                               value="${issue.name}" required>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Issue Type</strong></label>
                                                        <select name="type" class="form-select" required>
                                                            <c:forEach items="${listType}" var="t">
                                                                <option 
                                                                    <c:if test="${issue.type.id eq t.id}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value="${t.id}">${t.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Project</strong></label>
                                                        <input type="text" class="form-control" placeholder="Project Name (Project Code)"
                                                               value="${issue.project.name} (${issue.project.code})" readonly>
                                                        <input type="hidden" class="form-control" name="projectId" value="${issue.project.id}">
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Milestone</strong></label>
                                                        <select name="milestone" class="form-select" required>
                                                            <c:forEach items="${listMilestone}" var="m">
                                                                <option 
                                                                    <c:if test="${issue.milestone.id eq m.id}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value=${m.id}>${m.name}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Assignee</strong></label>
                                                        <select name="assignee" class="form-select" required>
                                                            <c:forEach items="${listAssignee}" var="u">
                                                                <option 
                                                                    <c:if test="${issue.assignee.id eq u.id}">
                                                                        selected="selected"
                                                                    </c:if>
                                                                    value=${u.id}>${u.full_name} (${u.username})
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Deadline</strong> <span style="color: red;">*</span></label>
                                                        <input type="date" class="form-control" name="deadline" placeholder="dd/MM/yyyy" 
                                                               value="${issue.deadline}">
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Status</strong></label>
                                                        <select name="status" class="form-select">
                                                            <option 
                                                                <c:if test="${issue.status eq '0'}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="0">Pending
                                                            </option>
                                                            <option 
                                                                <c:if test="${issue.status eq '1'}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="1">In progress
                                                            </option>
                                                            <option 
                                                                <c:if test="${issue.status eq '2'}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="2">Closed
                                                            </option>
                                                            <option 
                                                                <c:if test="${issue.status eq '3'}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="3">Cancelled
                                                            </option>
                                                        </select>
                                                    </div>

                                                    <div class="mb-3 col-md-12">
                                                        <label class="form-label"><strong>Description</strong></label>
                                                        <textarea class="form-control" name="description" 
                                                                  placeholder="Enter the Description" rows="3">${issue.details}</textarea>
                                                    </div>

                                                    <div>
                                                        <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                                    </div>
                                                </c:if>

                                                <c:if test="${action == 'edit' && user.role_id eq 5 && user.id eq issue.assignee.id}">
                                                    <input type="hidden" name="id" value="${issue.id}"/>
                                                    <input type="hidden" name="userId" value="${user.id}"/>
                                                    <input type="hidden" name="action" value="edit"/>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Created By</strong></label>
                                                        <input type="text" class="form-control" placeholder="Full Name (Username)" 
                                                               value="${issue.created_by.full_name} (${issue.created_by.username})" readonly>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Last Updated</strong></label>
                                                        <input type="datetime-local" class="form-control" name="last_updated" placeholder="dd/MM/yyyy hh:mm:ss" 
                                                               value="${issue.last_updated}" readonly>
                                                    </div>

                                                    <div class="mb-3 col-md-12">
                                                        <label class="form-label"><strong>Issue</strong> <span style="color: red;">*</span></label>
                                                        <input type="text" class="form-control" name="name" placeholder="Enter the Issue name"
                                                               value="${issue.name}" readonly>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Issue Type</strong></label>
                                                        <input type="text" class="form-control" placeholder="Issue type"
                                                               value="${issue.type.name}" readonly>
                                                        <input type="hidden" class="form-control" name="type" value="${issue.type.id}">
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Project</strong></label>
                                                        <input type="text" class="form-control" placeholder="Project Name (Project Code)"
                                                               value="${issue.project.name} (${issue.project.code})" readonly>
                                                        <input type="hidden" class="form-control" name="projectId" value="${issue.project.id}">
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Milestone</strong></label>
                                                        <input type="text" class="form-control" placeholder="Milestone Name" 
                                                               value="${issue.milestone.name}" readonly>
                                                        <input type="hidden" class="form-control" name="milestone" value="${issue.milestone.id}">
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Assignee</strong></label>
                                                        <input type="text" class="form-control" placeholder="Full Name (Username)"
                                                               value="${issue.assignee.full_name} (${issue.assignee.username})" readonly>
                                                        <input type="hidden" class="form-control" name="assignee" value="${issue.assignee.id}">
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Deadline</strong> <span style="color: red;">*</span></label>
                                                        <input type="date" class="form-control" name="deadline" placeholder="dd/MM/yyyy" 
                                                               value="${issue.deadline}" readonly>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Status</strong></label>
                                                        <select name="status" class="form-select">
                                                            <option 
                                                                <c:if test="${issue.status eq '0'}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="0">Pending
                                                            </option>
                                                            <option 
                                                                <c:if test="${issue.status eq '1'}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="1">In progress
                                                            </option>
                                                            <option 
                                                                <c:if test="${issue.status eq '2'}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="2">Closed
                                                            </option>
                                                            <option 
                                                                <c:if test="${issue.status eq '3'}">
                                                                    selected="selected"
                                                                </c:if>
                                                                value="3">Cancelled
                                                            </option>
                                                        </select>
                                                    </div>

                                                    <div class="mb-3 col-md-12">
                                                        <label class="form-label"><strong>Description</strong></label>
                                                        <textarea class="form-control" name="description" readonly
                                                                  placeholder="Enter the Description" rows="3" >${issue.details}</textarea>
                                                    </div>

                                                    <div>
                                                        <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                                    </div>
                                                </c:if>

                                                <c:if test="${action == 'view'}">
                                                    <input type="hidden" name="id" value="${issue.id}"/>
                                                    <input type="hidden" name="userId" value="${user.id}"/>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Created By</strong></label>
                                                        <input type="text" class="form-control" placeholder="Full Name (Username)" 
                                                               value="${issue.created_by.full_name} (${issue.created_by.username})" readonly>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Last Updated</strong></label>
                                                        <input type="datetime-local" class="form-control" name="last_updated" placeholder="dd/MM/yyyy hh:mm:ss" 
                                                               value="${issue.last_updated}" readonly>
                                                    </div>

                                                    <div class="mb-3 col-md-12">
                                                        <label class="form-label"><strong>Issue</strong> <span style="color: red;">*</span></label>
                                                        <input type="text" class="form-control" name="name" placeholder="Issue Name"
                                                               value="${issue.name}" readonly>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Issue Type</strong></label>
                                                        <input type="text" class="form-control" name="type" placeholder="Issue type"
                                                               value="${issue.type.name}" readonly>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Project</strong></label>
                                                        <input type="text" class="form-control" placeholder="Project Name (Project Code)"
                                                               value="${issue.project.name} (${issue.project.code})" readonly>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Milestone</strong></label>
                                                        <input type="text" class="form-control" placeholder="Milestone Name"
                                                               value="${issue.milestone.name}" readonly>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Assignee</strong></label>
                                                        <input type="text" class="form-control" placeholder="Full Name (Username)"
                                                               value="${issue.assignee.full_name} (${issue.assignee.username})" readonly>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Deadline</strong> <span style="color: red;">*</span></label>
                                                        <input type="date" class="form-control" name="deadline" placeholder="dd/MM/yyyy" 
                                                               value="${issue.deadline}" readonly>
                                                    </div>

                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label"><strong>Status</strong></label>
                                                        <c:if test="${issue.status eq '0'}">
                                                            <input type="text" class="form-control" name="status" placeholder="Status" 
                                                                   value="Pending" readonly>
                                                        </c:if>
                                                        <c:if test="${issue.status eq '1'}">
                                                            <input type="text" class="form-control" name="status" placeholder="Status" 
                                                                   value="In progress" readonly>
                                                        </c:if>
                                                        <c:if test="${issue.status eq '2'}">
                                                            <input type="text" class="form-control" name="status" placeholder="Status" 
                                                                   value="Closed" readonly>
                                                        </c:if>
                                                        <c:if test="${issue.status eq '3'}">
                                                            <input type="text" class="form-control" name="status" placeholder="Status" 
                                                                   value="Cancelled" readonly>
                                                        </c:if>
                                                    </div>

                                                    <div class="mb-3 col-md-12">
                                                        <label class="form-label"><strong>Description</strong></label>
                                                        <textarea class="form-control" name="description" readonly
                                                                  placeholder="Enter the Description" rows="3">${issue.details}</textarea>
                                                    </div>
                                                </c:if>
                                            </form>
                                        </c:if> 
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

    </body>

</html>

<%-- 
    Document   : milestone-detail
    Created on : Dec 3, 2024, 2:58:05 AM
    Author     : BachHD
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

        <title>Milestone Details | PMS</title>

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
        </script>
    </head>
    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="row">

            <div class="col-md-12 col-xl-12">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${milestone == null}">
                            <form action="insert-milestone" method="post" class="row"
                                  id="milestoneForm" onsubmit="return validateMilestoneForm(event)">

                                <!-- Created By -->
                                <div class="col-md-6 mb-3" hidden>
                                    <label for="createdBy" class="form-label">Created By</label>
                                    <input type="text" class="form-control"  name="createdBy" placeholder="Full Name (UserName)" value="${sessionScope.username}" readonly>
                                </div>

                                <!-- Last Updated -->
                                <div class="col-md-6 mb-3" hidden>
                                    <label for="lastUpdated" class="form-label">Last Updated</label>
                                    <input type="text" class="form-control"  name="lastUpdated" placeholder="dd/MM/yyyy hh:mm:ss" readonly>
                                </div>

                                <!-- Milestone Name -->
                                <div class="col-md-6 mb-3">
                                    <label for="milestoneName" class="form-label"><strong>Milestone</strong> <span style="color: red;">*</span></label>
                                    <input type="text" class="form-control" placeholder="Enter Milestone Name" name="milestoneName" required>
                                </div>

                                <!-- Parent Milestone -->
                                <div class="col-md-6 mb-3">
                                    <label for="parentMilestone" class="form-label"><strong>Parent Milestone</strong></label>
                                    <select class="form-select"  name="parentMilestone" onchange="updateProjectId()" id="parentMilestone">
                                        <option value="">Select Parent Milestone</option>
                                        <c:forEach var="milestone" items="${parentMilestones}">
                                            <!-- Thêm projectId vào attribute data-project-id -->
                                            <option value="${milestone.id}" data-project-id="${milestone.projectId}">
                                                ${milestone.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- Trường ẩn để gửi projectId -->
                                <input type="hidden" id="projectId" name="projectId" value="${projectId}">


                                <!-- Priority -->
                                <div class="col-md-6 mb-3">
                                    <label for="priority" class="form-label"><strong>Priority</strong> <span style="color: red;">*</span></label>
                                    <input type="number" class="form-control" placeholder="Enter Milestone priority" name="priority" required>
                                </div>

                                <!-- Target Date -->
                                <div class="col-md-6 mb-3">
                                    <label for="targetDate" class="form-label"><strong>Target Date</strong> <span style="color: red;">*</span></label>
                                    <input type="date" class="form-control" id="targetDate" name="targetDate" placeholder="dd/MM/yyyy" required>
                                </div>

                                <!-- Description -->
                                <div class="col-md-12 mb-3">
                                    <label for="description" class="form-label"><strong>Description</strong></label>
                                    <textarea class="form-control" placeholder="Enter Milestone description" name="description" rows="3"></textarea>
                                </div>

                                <div id="errorContainer" class="alert alert-danger pt-3 pe-3 ps-3 d-none">
                                    <ul id="errorList"></ul>
                                </div>
                                
                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                </div>
                            </form>
                        </c:if> 

                        <c:if test="${milestone != null}">
                            <c:if test="${action == 'edit'}">
                                <form id="editMilestoneForm" action="/PMS/updatemilestone" method="POST" class="row">
                                    <!-- Trường ẩn để gửi projectId -->
                                    <input type="hidden" id="project_Id" name="project_Id" value="${projectId}">
                                    <input type="hidden" id="id" name="id" value="${milestone.id}"/>

                                    <!-- Created By -->
                                    <div class="col-md-6 mb-3">
                                        <label for="createdBy" class="form-label"><strong>Created By</strong></label>
                                        <input type="text" class="form-control" id="createdBy" name="createdBy" value="${milestone.createdbyuserName}" readonly>
                                        <input type="hidden" id="createdById" name="createdById" value="${milestone.createdBy}">
                                    </div>

                                    <!-- Last Updated -->
                                    <div class="col-md-6 mb-3">
                                        <label for="lastUpdated" class="form-label"><strong>Last Updated</strong></label>
                                        <input type="text" class="form-control" id="lastupdated" name="lastupdated" value="${milestone.lastUpdated}" readonly>
                                    </div>

                                    <!-- Milestone Name -->
                                    <div class="col-md-6 mb-3">
                                        <label for="milestoneName" class="form-label"><strong>Milestone</strong> <span style="color: red;">*</span></label>
                                        <input type="text" class="form-control" id="milestoneName" name="milestonename" 
                                               placeholder="Enter Milestone Name" value="${milestone.name}" required>
                                    </div>

                                    <!-- Parent Milestone -->
                                    <div class="col-md-6 mb-3">
                                        <label for="parentMilestone" class="form-label"><strong>Parent Milestone</strong></label>
                                        <select class="form-select" id="parentMilestoneId" name="parentMilestoneId">
                                            <option value="">Select Parent Milestone</option>
                                            <c:forEach var="parent" items="${parentMilestones}">
                                                <option value="${parent.id}" <c:if test="${parent.id == milestone.parentMilestone}">selected</c:if>>
                                                    ${parent.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <!-- Priority -->
                                    <div class="col-md-6 mb-3">
                                        <label for="priority" class="form-label"><strong>Priority</strong> <span style="color: red;">*</span></label>
                                        <input type="number" class="form-control" id="priority" name="priority" 
                                               placeholder="Enter Milestone priority" value="${milestone.priority}" required>
                                    </div>

                                    <!-- Target Date -->
                                    <div class="col-md-6 mb-3">
                                        <label for="targetDate" class="form-label"><strong>Target Date</strong> <span style="color: red;">*</span></label>
                                        <input type="date" class="form-control" id="targetdate" name="targetdate" 
                                               placeholder="dd/MM/yyyy" value="${milestone.targetDate}" required>
                                    </div>

                                    <!-- Status -->
                                    <div class="col-md-6 mb-3">
                                        <label for="status" class="form-label"><strong>Status</strong></label>
                                        <select class="form-select" id="status" name="status">
                                            <option value="0" <c:if test="${milestone.status == 0}">selected</c:if>>Pending</option>
                                            <option value="1" <c:if test="${milestone.status == 1}">selected</c:if>>Doing</option>
                                            <option value="2" <c:if test="${milestone.status == 2}">selected</c:if>>Closed</option>
                                            <option value="3" <c:if test="${milestone.status == 3}">selected</c:if>>Cancelled</option>
                                            </select>
                                        </div>

                                        <!-- Actual Date -->
                                        <div class="col-md-6 mb-3">
                                            <label for="actualDate" class="form-label"><strong>Actual Date</strong></label>
                                            <input type="date" class="form-control" id="actualDate" name="actualDate" 
                                                   placeholder="dd/MM/yyyy" value="${milestone.actualDate}">
                                    </div>

                                    <!-- Description -->
                                    <div class="col-md-12 mb-3">
                                        <label for="detail" class="form-label"><strong>Description</strong></label>
                                        <textarea class="form-control" id="detail" placeholder="Enter Milestone description" name="description" rows="3">${milestone.details}</textarea>
                                    </div>

                                    <!-- Submit Button -->
                                    <div class="d-flex justify-content-end">
                                        <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                    </div>
                                </form>
                            </c:if>

                            <c:if test="${action == 'view'}">
                                <form id="editMilestoneForm" action="/PMS/updatemilestone" method="POST" class="row">
                                    <!-- Trường ẩn để gửi projectId -->
                                    <input type="hidden" id="project_Id" name="project_Id" value="${projectId}">
                                    <input type="hidden" id="id" name="id" value="${milestone.id}"/>

                                    <!-- Created By -->
                                    <div class="col-md-6 mb-3">
                                        <label for="createdBy" class="form-label"><strong>Created By</strong></label>
                                        <input type="text" class="form-control" id="createdBy" name="createdBy" value="${milestone.createdbyuserName}" readonly>
                                        <input type="hidden" id="createdById" name="createdById" value="${milestone.createdBy}">
                                    </div>

                                    <!-- Last Updated -->
                                    <div class="col-md-6 mb-3">
                                        <label for="lastUpdated" class="form-label"><strong>Last Updated</strong></label>
                                        <input type="text" class="form-control" id="lastupdated" name="lastupdated" value="${milestone.lastUpdated}" readonly>
                                    </div>

                                    <!-- Milestone Name -->
                                    <div class="col-md-6 mb-3">
                                        <label for="milestoneName" class="form-label"><strong>Milestone</strong> <span style="color: red;">*</span></label>
                                        <input type="text" class="form-control" id="milestoneName" name="milestonename" value="${milestone.name}" readonly>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label for="parentMilestoneId" class="form-label"><strong>Parent Milestone</strong></label>
                                        <input type="text" class="form-control" id="parentMilestoneId" name="parentMilestoneId" value="${milestone.parentMilestoneName}" readonly>
                                    </div>

                                    <!-- Priority -->
                                    <div class="col-md-6 mb-3">
                                        <label for="priority" class="form-label"><strong>Priority</strong> <span style="color: red;">*</span></label>
                                        <input type="number" class="form-control" id="priority" name="priority" value="${milestone.priority}" readonly>
                                    </div>

                                    <!-- Target Date -->
                                    <div class="col-md-6 mb-3">
                                        <label for="targetDate" class="form-label"><strong>Target Date</strong> <span style="color: red;">*</span></label>
                                        <input type="date" class="form-control" id="targetdate" name="targetdate" value="${milestone.targetDate}" readonly>
                                    </div>

                                    <!-- Status -->
                                    <div class="col-md-6 mb-3">
                                        <label for="status" class="form-label"><strong>Status</strong></label>
                                        <c:if test="${milestone.status eq '0'}">
                                            <input type="text" class="form-control" placeholder="Milestone Status" 
                                                   value="Pending" readonly>
                                        </c:if>
                                        <c:if test="${milestone.status eq '1'}">
                                            <input type="text" class="form-control" placeholder="Milestone Status" 
                                                   value="Doing" readonly>
                                        </c:if>
                                        <c:if test="${milestone.status eq '2'}">
                                            <input type="text" class="form-control" placeholder="Milestone Status" 
                                                   value="Closed" readonly>
                                        </c:if>
                                        <c:if test="${milestone.status eq '3'}">
                                            <input type="text" class="form-control" placeholder="Milestone Status" 
                                                   value="Cancelled" readonly>
                                        </c:if>
                                    </div>

                                    <!-- Actual Date -->
                                    <div class="col-md-6 mb-3">
                                        <label for="actualDate" class="form-label"><strong>Actual Date</strong></label>
                                        <input type="date" class="form-control" id="actualDate" name="actualDate" value="${milestone.actualDate}" readonly>
                                    </div>

                                    <!-- Description -->
                                    <div class="col-md-12 mb-3">
                                        <label for="detail" class="form-label"><strong>Description</strong></label>
                                        <textarea class="form-control" id="detail" name="description" rows="3" readonly>${milestone.details}</textarea>
                                    </div>

                                </form>
                            </c:if>
                        </c:if>

                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/app.js"></script>

    </body>
</html>
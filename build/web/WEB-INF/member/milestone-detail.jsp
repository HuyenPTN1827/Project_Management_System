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
        <meta name="author" content="AdminKit">
        <meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icons/icon-48x48.png" />

        <link rel="canonical" href="pages-profile.html" />

        <title>Project Details | PMS</title>

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
                            <form action="insert-milestone" method="post" class="row">
                                <!-- Created By -->
                                <div class="col-md-6 mb-3" hidden>
                                    <label for="createdBy" class="form-label">Created By</label>
                                    <input type="text" class="form-control"  name="createdBy" value="${sessionScope.username}" readonly>
                                </div>

                                <!-- Last Updated -->
                                <div class="col-md-6 mb-3" hidden>
                                    <label for="lastUpdated" class="form-label">Last Updated</label>
                                    <input type="text" class="form-control"  name="lastUpdated" placeholder="dd/MM/yyyy hh:mm:ss" readonly>
                                </div>

                                <!-- Milestone Name -->
                                <div class="col-md-6 mb-3">
                                    <label for="milestoneName" class="form-label"><strong>Milestone/Deliverable</strong> <span style="color: red;">*</span></label>
                                    <input type="text" class="form-control"  name="milestoneName" required>
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
                                    <input type="number" class="form-control"  name="priority" required>
                                </div>



                                <!-- Target Date -->
                                <div class="col-md-6 mb-3">
                                    <label for="targetDate" class="form-label"><strong>Target Date</strong> <span style="color: red;">*</span></label>
                                    <input type="date" class="form-control"  name="targetDate" required>
                                </div>

                                <!-- Description -->
                                <div class="col-md-12 mb-3">
                                    <label for="description" class="form-label"><strong>Description</strong></label>
                                    <textarea class="form-control"  name="description" rows="3"></textarea>
                                </div>

                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                </div>
                            </form>
                        </c:if> 

                        <c:if test="${milestone != null}">
                            <form id="editMilestoneForm" action="/PMS/updatemilestone" method="POST" class="row">
                                <input type="hidden" id="id" name="id" value="${milestone.id}"/>

                                <!-- Created By -->
                                <div class="col-md-6 mb-3">
                                    <label for="createdBy" class="form-label"><strong>Created By</strong></label>
                                    <input type="hidden" id="createdById" name="createdById" />
                                    <input type="text" class="form-control" id="createdBy" name="createdBy"  readonly>
                                </div>

                                <!-- Last Updated -->
                                <div class="col-md-6 mb-3">
                                    <label for="lastupdated" class="form-label"><strong>Last Updated</strong></label>
                                    <input type="text" class="form-control" id="lastupdated" name="lastupdated" readonly>
                                </div>

                                <!-- Milestone Name -->
                                <div class="col-md-6 mb-3">
                                    <label for="milestoneName" class="form-label"><strong>Milestone/Deliverable</strong> <span style="color: red;">*</span></label>
                                    <input type="text" class="form-control"  name="milestoneName" required>
                                </div>

                                <!-- Parent Milestone -->
                                <div class="col-md-6 mb-3">
                                    <label for="parentmilestone" class="form-label"><strong>Parent Milestone</strong></label>
                                    <input type="hidden" id="parentMilestoneId" name="parentMilestoneId" />
                                    <input type="text" class="form-control" id="parentmilestone" name="parentMilestone">
                                </div>

                                <!-- Trường ẩn để gửi projectId -->
                                <input type="hidden" id="projectId" name="projectId" value="${projectId}">

                                <!-- Priority -->
                                <div class="col-md-6 mb-3">
                                    <label for="priority" class="form-label"><strong>Priority</strong> <span style="color: red;">*</span></label>
                                    <input type="number" class="form-control" id="priority" name="priority" required>
                                </div>

                                <!-- Target Date -->
                                <div class="col-md-6 mb-3">
                                    <label for="targetDate" class="form-label"><strong>Target Date</strong> <span style="color: red;">*</span></label>
                                    <input type="date" class="form-control" id="targetDate" name="targetdate" required>
                                </div>

                                <!-- Status -->
                                <div class="col-md-6 mb-3">
                                    <label for="status" class="form-label"><strong>Status</strong></label>
                                    <select class="form-control" id="status" name="status">
                                        <option value="0">Pending</option>
                                        <option value="1">To Do</option>
                                        <option value="2">Doing</option>
                                        <option value="3">Done</option>
                                        <option value="4">Closed</option>
                                        <option value="5">Cancelled</option>
                                    </select>
                                </div>

                                <!-- Target Date -->
                                <div class="col-md-6 mb-3">
                                    <label for="targetDate" class="form-label"><strong>Actual Date</strong></label>
                                    <input type="date" class="form-control" id="actualDate" name="actualDate">
                                </div>

                                <!-- Description -->
                                <div class="col-md-12 mb-3">
                                    <label for="detail" class="form-label"><strong>Description</strong></label>
                                    <textarea class="form-control" id="detail" name="description" rows="3"></textarea>
                                </div>

                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                </div>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/app.js"></script>

        <script>
                                        document.addEventListener("DOMContentLoaded", function (event) {
                                            setTimeout(function () {
                                                if (localStorage.getItem('popState') !== 'shown') {
                                                    window.notyf.open({
                                                        type: "success",
                                                        message: "Get access to all 500+ components and 45+ pages with AdminKit PRO. <u><a class=\"text-white\" href=\"https://adminkit.io/pricing\" target=\"_blank\">More info</a></u> 🚀",
                                                        duration: 10000,
                                                        ripple: true,
                                                        dismissible: false,
                                                        position: {
                                                            x: "left",
                                                            y: "bottom"
                                                        }
                                                    });

                                                    localStorage.setItem('popState', 'shown');
                                                }
                                            }, 15000);
                                        });
        </script>
    </body>
</html>
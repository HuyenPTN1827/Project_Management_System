<%-- 
    Document   : allocation-detail
    Created on : Nov 28, 2024, 2:45:42 AM
    Author     : kelma
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

        <title>Allocation Details | PMS</title>

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

                        <c:if test="${al == null}">
                            <form action="insert-allocation" id="allocationForm" method="post" 
                                  class="row" onsubmit="return validateForm(event)">
                                <input type="hidden" name="projectId" value="${projectId}">
                                <input type="hidden" name="userId" value="${user.id}">

                                <div class="mb-3 col-md-6">
                                    <label class="form-label"><strong>Department</strong> <span style="color: red;">*</span></label>
                                    <select name="deptId" id="deptDropdown" class="form-select" 
                                            onchange="redirectToDetailPage(${projectId})" required>
                                        <option value="" hidden disable>Choose Department</option>
                                        <c:forEach items="${listDept}" var="d">
                                            <option 
                                                <c:if test="${deptId eq d.id}">
                                                    selected="selected"
                                                </c:if>
                                                value="${d.id}">${d.name} (${d.code})
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label"><strong>Member</strong> <span style="color: red;">*</span></label>
                                    <select name="memId" id="memId" class="form-select" required>
                                        <option value="" hidden disable>Choose Member</option>
                                        <c:forEach items="${listMem}" var="m">
                                            <option 
                                                <c:if test="${memId eq m.id}">
                                                    selected="selected"
                                                </c:if>
                                                value="${m.id}">${m.full_name} (${m.username})
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label"><strong>Project</strong></label>
                                    <input type="text" class="form-control" placeholder="Project Name (Project Code)" 
                                           value="${project.name}" readonly>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label"><strong>Project Role</strong> <span style="color: red;">*</span></label>
                                    <select name="roleId" id="roleId" class="form-select" required>
                                        <option value="" hidden disable>Choose Project Role</option>
                                        <!--<option value="1">Project Manager</option>-->
                                        <c:forEach items="${listRole}" var="r">
                                            <option 
                                                <c:if test="${roleId eq r.id}">
                                                    selected="selected"
                                                </c:if>
                                                value="${r.id}">${r.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!--                                <div class="mb-3 col-md-6">
                                                                    <label class="form-label">Form Date <span style="color: red;">*</span></label>
                                                                    <input type="date" class="form-control" name="fromDate" placeholder="dd/MM/yyyy" 
                                                                           value="${fromDate}" id="fromDate" required>
                                                                </div>
                                
                                                                <div class="mb-3 col-md-6">
                                                                    <label class="form-label">To Date</label>
                                                                    <input type="date" class="form-control" name="toDate" placeholder="dd/MM/yyyy" 
                                                                           value="${toDate}" id="toDate">
                                                                </div>-->

                                <div class="mb-3 col-md-6">
                                    <label class="form-label"><strong>From Date</strong> <span style="color: red;">*</span> <strong>- To Date</strong> </label>
                                    <div class="input-group">
                                        <input type="date" class="form-control" name="fromDate" placeholder="dd/MM/yyyy" 
                                               value="${fromDate}" id="fromDate" required>
                                        <span class="input-group-text">to</span>
                                        <input type="date" class="form-control" name="toDate" placeholder="dd/MM/yyyy" 
                                               value="${toDate}" id="toDate">
                                    </div>
                                </div>

                                <div class="mb-3 col-md-6">
                                    <label class="form-label"><strong>Effort Rate (%)</strong> <span style="color: red;">*</span></label>
                                    <input type="number" class="form-control" name="effort" placeholder="Enter the Effort Rate"
                                           value="${effort}" id="effort" required>
                                </div>

                                <div class="mb-3 col-md-12">
                                    <label class="form-label"><strong>Description</strong></label>
                                    <textarea class="form-control" name="descriptionAllocation" id="descriptionAllocation"
                                              placeholder="Enter the Description" rows="3">${descriptionAllocation}</textarea>
                                </div>

                                <div id="errorContainer" class="alert alert-danger pt-3 pe-3 ps-3 d-none">
                                    <ul id="errorList"></ul>
                                </div>

                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                </div>
                            </form>
                        </c:if>

                        <c:if test="${al != null}">
                            <c:if test="${action == 'edit'}">
                                <form action="update-allocation" id="allocationForm" method="post" 
                                      class="row" onsubmit="return validateFormEdit(event)">
                                    <input type="hidden" name="projectId" value="${projectId}">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <input type="hidden" name="id" value="${al.id}">

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Created By</strong></label>
                                        <input type="text" class="form-control" placeholder="Full Name (Username)" 
                                               value="${al.created_by.full_name} (${al.created_by.username})" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Created At</strong></label>
                                        <input type="datetime-local" class="form-control" placeholder="dd/MM/yyyy hh:mm:ss" 
                                               value="${al.createdAt}" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Updated By</strong></label>
                                        <input type="text" class="form-control" placeholder="Full Name (Username)" 
                                               readonly
                                               <c:choose>
                                                   <c:when test="${al.updated_by != null}">
                                                       value="${al.updated_by.full_name} (${al.updated_by.username})"
                                                   </c:when>
                                                   <c:otherwise>
                                                       value=""
                                                   </c:otherwise>
                                               </c:choose>>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Last Updated</strong></label>
                                        <input type="datetime-local" class="form-control" placeholder="dd/MM/yyyy hh:mm:ss" 
                                               value="${al.lastUpdated}" readonly>
                                    </div>

                                    <div class="d-flex align-items-center my-4">
                                        <div class="flex-grow-1 border-top"></div>
                                        <!--<span class="mx-3">Department Manager</span>-->
                                        <div class="flex-grow-1 border-top"></div>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Department</strong></label>
                                        <input type="hidden" class="form-control" name="deptId"
                                               value="${al.dept.id}">
                                        <input type="text" class="form-control" placeholder="Department (Department Code)" 
                                               value="${al.dept.name} (${al.dept.code})" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Member</strong></label>
                                        <input type="hidden" class="form-control" name="memId"
                                               value="${al.user.id}">
                                        <input type="text" class="form-control" placeholder="Full Name (Username)" 
                                               value="${al.user.full_name} (${al.user.username})" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Project</strong></label>
                                        <input type="text" class="form-control" placeholder="Project Name (Project Code)" 
                                               value="${project.name}" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Project Role</strong> <span style="color: red;">*</span></label>
                                        <select name="roleId" id="roleId" class="form-select" required>
                                            <option value="" hidden disable>Choose Project Role</option>
                                            <!--<option value="1">Project Manager</option>-->
                                            <c:forEach items="${listRole}" var="r">
                                                <option 
                                                    <c:if test="${al.role.id eq r.id}">
                                                        selected="selected"
                                                    </c:if>
                                                    value="${r.id}">${r.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Form Date</strong> <span style="color: red;">*</span></label>
                                        <input type="date" class="form-control" name="fromDate" placeholder="dd/MM/yyyy" 
                                               value="${al.startDate}" id="fromDate" required>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>To Date</strong></label>
                                        <input type="date" class="form-control" name="toDate" placeholder="dd/MM/yyyy" 
                                               value="${al.endDate}" id="toDate">
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Effort Rate (%)</strong> <span style="color: red;">*</span></label>
                                        <input type="number" class="form-control" name="effort" placeholder="Enter the Effort Rate"
                                               value="${al.effortRate}" id="effort" required>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Status</strong></label>
                                        <div class="check  mt-1">
                                            <input class="form-check-input" type="radio" name="statusAllocation"
                                                   <c:if test="${al.status eq 'true'}">
                                                       checked
                                                   </c:if>
                                                   value="true"> Active
                                            <input class="form-check-input ms-3" type="radio" name="statusAllocation"
                                                   <c:if test="${al.status eq 'false'}">
                                                       checked
                                                   </c:if>
                                                   value="false"> Inactive
                                        </div>
                                    </div>

                                    <div class="mb-3 col-md-12">
                                        <label class="form-label"><strong>Description</strong></label>
                                        <textarea class="form-control" name="descriptionAllocation" id="descriptionAllocation"
                                                  placeholder="Enter the Description" rows="3">${al.description}</textarea>
                                    </div>

                                    <div id="errorContainer" class="alert alert-danger pt-3 pe-3 ps-3 d-none">
                                        <ul id="errorList"></ul>
                                    </div>

                                    <div class="d-flex justify-content-end">
                                        <button type="submit" class="btn btn-lg btn-success">Submit</button>
                                    </div>
                                </form>
                            </c:if>

                            <c:if test="${action == 'view'}">
                                <form action="update-allocation" id="allocationForm" method="post" 
                                      class="row" onsubmit="return validateFormEdit(event)">
                                    <input type="hidden" name="projectId" value="${projectId}">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <input type="hidden" name="id" value="${al.id}">

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Created By</strong></label>
                                        <input type="text" class="form-control" placeholder="Full Name (Username)" 
                                               value="${al.created_by.full_name} (${al.created_by.username})" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Created At</strong></label>
                                        <input type="datetime-local" class="form-control" placeholder="dd/MM/yyyy hh:mm:ss" 
                                               value="${al.createdAt}" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Updated By</strong></label>
                                        <input type="text" class="form-control" placeholder="Full Name (Username)" 
                                               readonly
                                               <c:choose>
                                                   <c:when test="${al.updated_by != null}">
                                                       value="${al.updated_by.full_name} (${al.updated_by.username})"
                                                   </c:when>
                                                   <c:otherwise>
                                                       value=""
                                                   </c:otherwise>
                                               </c:choose>>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Last Updated</strong></label>
                                        <input type="datetime-local" class="form-control" placeholder="dd/MM/yyyy hh:mm:ss" 
                                               value="${al.lastUpdated}" readonly>
                                    </div>

                                    <div class="d-flex align-items-center my-4">
                                        <div class="flex-grow-1 border-top"></div>
                                        <!--<span class="mx-3">Department Manager</span>-->
                                        <div class="flex-grow-1 border-top"></div>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Department</strong></label>
                                        <input type="hidden" class="form-control" name="deptId"
                                               value="${al.dept.id}">
                                        <input type="text" class="form-control" placeholder="Department (Department Code)" 
                                               value="${al.dept.name} (${al.dept.code})" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Member</strong></label>
                                        <input type="hidden" class="form-control" name="memId"
                                               value="${al.user.id}">
                                        <input type="text" class="form-control" placeholder="Full Name (Username)" 
                                               value="${al.user.full_name} (${al.user.username})" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Project</strong></label>
                                        <input type="text" class="form-control" placeholder="Project Name (Project Code)" 
                                               value="${project.name}" readonly>
                                    </div>
                                    
                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Project Role</strong> <span style="color: red;">*</span></label>
                                        <input type="text" class="form-control" placeholder="Choose Project Role" 
                                               value="${al.role.name}" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Form Date</strong> <span style="color: red;">*</span></label>
                                        <input type="date" class="form-control" name="fromDate" placeholder="dd/MM/yyyy" 
                                               value="${al.startDate}" id="fromDate" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>To Date</strong></label>
                                        <input type="date" class="form-control" name="toDate" placeholder="dd/MM/yyyy" 
                                               value="${al.endDate}" id="toDate" readonly>
                                    </div>

                                    <div class="mb-3 col-md-6">
                                        <label class="form-label"><strong>Effort Rate (%)</strong> <span style="color: red;">*</span></label>
                                        <input type="number" class="form-control" name="effort" placeholder="Enter the Effort Rate"
                                               value="${al.effortRate}" id="effort" readonly>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label"><strong>Status</strong></label>
                                        <c:if test="${al.status eq 'true'}">
                                            <input type="text" class="form-control" placeholder="Allocation Status" 
                                                   value="Active" readonly>
                                        </c:if>
                                        <c:if test="${al.status eq 'false'}">
                                            <input type="text" class="form-control" placeholder="Allocation Status" 
                                                   value="Inactive" readonly>
                                        </c:if>
                                    </div>

                                    <div class="mb-3 col-md-12">
                                        <label class="form-label"><strong>Description</strong></label>
                                        <textarea class="form-control" name="descriptionAllocation" id="descriptionAllocation"
                                                  placeholder="Enter the Description" rows="3" readonly>${al.description}</textarea>
                                    </div>

                                    <div id="errorContainer" class="alert alert-danger pt-3 pe-3 ps-3 d-none">
                                        <ul id="errorList"></ul>
                                    </div>

                                </form>
                            </c:if>
                        </c:if>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/app.js"></script>

</body>

</html>

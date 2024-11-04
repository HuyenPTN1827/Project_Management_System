<%-- 
    Document   : project-config
    Created on : Oct 27, 2024, 11:13:02 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Project Configs | PMS</title>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/light.css" rel="stylesheet">
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="../component/sidebar-manager.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>
                    <main class="content">
                        <div class="container-fluid p-0">
                            <!-- Search Section -->
                            <div class="mb-3">
                                <h1 class="h1 d-inline align-middle">Project Configs</h1>
                                <div class="float-end">
                                    <form class="d-flex" action="searchProject" method="get">
                                        <input type="text" name="projectName" placeholder="Type project name to search" class="form-control" style="width: 300px;">
                                        <button class="btn btn-primary ms-2" type="submit">Search</button>
                                    </form>
                                </div>
                            </div>

                            <!-- Project Info Section -->
                            <div class="card mb-3">
                                <div class="card-body">
                                    <form class="row g-3" action="viewProjectDetails" method="get">
                                        <div class="col-md-6">
                                            <label for="projectName" class="form-label">Project</label>
                                            <input type="text" name="projectName" class="form-control" id="projectName" placeholder="Enter project name" required>
                                        </div>
                                        <div class="col-md-4">
                                            <label for="projectCode" class="form-label">Code</label>
                                            <input type="text" name="projectCode" class="form-control" id="projectCode" placeholder="Enter code" required>
                                        </div>
                                        <div class="col-md-2 d-flex align-items-end">
                                            <button type="submit" class="btn btn-primary w-100">View Details</button>
                                        </div>
                                    </form>
                                </div>
                            </div>

                            <!-- Tabs Section -->
                            <div class="card">
                                <div class="card-header pb-0">
                                    <ul class="nav nav-tabs card-header-tabs" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link active" data-bs-toggle="tab" href="#milestones" role="tab">Milestones</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" data-bs-toggle="tab" href="#evalCriteria" role="tab">Eval Criteria</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" data-bs-toggle="tab" href="#members" role="tab">Members</a>
                                        </li>

                                        <li class="nav-item">
                                            <a class="nav-link" data-bs-toggle="tab" href="#team" role="tab" >Teams</a>
                                        </li>


                                    </ul>
                                </div>
                                <div class="card-body">
                                    <div class="tab-content">
                                        <!-- Milestones Section -->
                                        <div class="tab-pane fade show active" id="milestones" role="tabpanel">
                                            <div class="d-flex justify-content-between mb-3">
                                                <form class="d-flex align-items-center" action="filterandsearchmilestone" method="get" id="filterForm">
                                                    <select class="form-select me-2" name="statusFilter" id="statusFilter" style="width: 150px;">
                                                        <option value="">All Status</option>
                                                        <option value="0">Closed</option>
                                                        <option value="1">In Progress</option>
                                                        <option value="2">Pending</option>
                                                    </select>
                                                    <input type="text" class="form-control" name="searchKeyword" placeholder="Search" style="width: 250px;">
                                                    <button class="btn btn-primary ms-2" type="submit">Search</button>
                                                </form>
                                                <a href="addMilestone" class="btn btn-primary">Add New</a>
                                            </div>

                                            <!-- JavaScript for automatic form submission -->
                                            <script>
                                                document.getElementById('statusFilter').addEventListener('change', function () {
                                                    document.getElementById('filterForm').submit();
                                                });

                                                window.onload = function () {
                                                    const urlParams = new URLSearchParams(window.location.search);
                                                    const statusFilter = urlParams.get('statusFilter');
                                                    if (statusFilter) {
                                                        document.getElementById('statusFilter').value = statusFilter;
                                                    }
                                                };
                                            </script>

                                            <!-- Milestones Table -->
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Milestone Name</th>
                                                        <th>Code</th>
                                                        <th>Details</th>
                                                        <th>Priority</th>
                                                        <th>End Date</th>
                                                        <th>Status</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="milestone" items="${milestoneList}">
                                                    <tr>
                                                        <td>${milestone.id}</td>
                                                        <td>${milestone.name}</td>
                                                        <td>${milestone.code}</td>
                                                        <td>${milestone.details}</td>
                                                        <td>${milestone.priority}</td>
                                                        <td>${milestone.deadline}</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${milestone.status == 0}">Closed</c:when>
                                                                <c:when test="${milestone.status == 1}">In Progress</c:when>
                                                                <c:when test="${milestone.status == 2}">Pending</c:when>
                                                                <c:otherwise>Unknown</c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>
                                                            <a href="edit?id=${milestone.id}" class="btn btn-link">Edit</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>

                                    <!-- Eval Criteria Section -->
                                    <div class="tab-pane fade" id="evalCriteria" role="tabpanel">
                                        <p>Eval Criteria Content Here</p>
                                    </div>




                                    <!-- Teams Section -->
                                    <div class="tab-pane fade" id="team" role="tabpanel">
                                        <div class="d-flex justify-content-between mb-3">
                                            <form class="d-flex align-items-center" action="searchteam" method="get" id="teamFilterForm">
                                                <input type="hidden" name="projectId" value="${project.id}"> <!-- Thêm trường ẩn cho projectId -->
                                                <input type="text" class="form-control me-2" name="searchTeam" placeholder="Search Teams" style="width: 300px;" id="searchTeam">
                                                <button class="btn btn-primary" type="submit">Search</button>
                                            </form>
                                            <a href="<%=request.getContextPath()%>/addteam" class="btn btn-primary">Add New Team</a>
                                        </div>
                                        ${errorMessage}
                                        <!-- Teams Table -->
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Team Name</th>
                                                    <th>Topic</th>
                                                    <th>Details</th>
                                                    <th>Project</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="team" items="${teamList}">
                                                    <tr>
                                                        <td>${team.id}</td>
                                                        <td>${team.name}</td>
                                                        <td>${team.topic}</td>
                                                        <td>${team.details}</td>
                                                        <td>${team.projectId}</td>
                                                        <td>${team.status}</td>
                                                        <td>
                                                            <a href="editteam?id=${team.id}" class="btn btn-link">Edit</a>
                                                            <a href="deleteteam?id=${team.id}" class="btn btn-link text-danger" onclick="return confirm('Are you sure you want to delete this team?');">Delete</a>
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
            </div>
        </div>
    </div>
</main>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/light.js"></script>
<script src="https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace();
</script>

</body>
</html>

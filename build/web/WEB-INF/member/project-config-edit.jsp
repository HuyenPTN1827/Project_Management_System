<%-- 
    Document   : project-config-edit
    Created on : Oct 31, 2024, 1:08:13 AM
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
    <title>Milestone Detail | PMS</title>
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
                    <h1 class="h1 d-inline align-middle">Milestone Detail</h1>
                    

                    <div class="card mb-3">
                        <div class="card-body">
                            <form action="update" method="post">
                                <input type="hidden" name="id" value="${milestone.id}"/>
                                <input type="hidden" name="projectId" value="${milestone.projectId}"/> <!-- Thêm trường ẩn cho projectId -->
                                <div class="row g-3">
                                    <div class="col-md-6">
                                        <label for="milestoneName" class="form-label">Milestone Name</label>
                                        <input type="text" class="form-control" id="milestoneName" name="name" value="${milestone.name}" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="milestoneCode" class="form-label">Code</label>
                                        <input type="text" class="form-control" id="milestoneCode" name="code" value="${milestone.code}" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="milestoneDetails" class="form-label">Details</label>
                                        <textarea class="form-control" id="milestoneDetails" name="details" rows="3">${milestone.details}</textarea>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="milestonePriority" class="form-label">Priority</label>
                                        <select class="form-select" id="milestonePriority" name="priority">
                                            <option value="1" <c:if test="${milestone.priority == '1'}">selected</c:if>>1</option>
                                            <option value="2" <c:if test="${milestone.priority == '2'}">selected</c:if>>2</option>
                                            <option value="3" <c:if test="${milestone.priority == '3'}">selected</c:if>>3</option>
                                        </select>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="milestoneDeadline" class="form-label">Deadline</label>
                                        <input type="date" class="form-control" id="milestoneDeadline" name="deadline" value="${milestone.deadline}" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="milestoneStatus" class="form-label">Status</label>
                                        <select class="form-select" id="milestoneStatus" name="status">
                                            <option value="0" <c:if test="${milestone.status == 0}">selected</c:if>>Closed</option>
                                            <option value="1" <c:if test="${milestone.status == 1}">selected</c:if>>In Progress</option>
                                            <option value="2" <c:if test="${milestone.status == 2}">selected</c:if>>Pending</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="mt-3">
                                    ${successMessage}
                                    <button type="submit" class="btn btn-primary">Save Changes</button>
                                    <a href="project-config" class="btn btn-secondary ms-2">Cancel</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace();
</script>
</body>
</html>


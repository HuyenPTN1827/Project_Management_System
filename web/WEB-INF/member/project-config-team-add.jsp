<%-- 
    Document   : project-config-team-add
    Created on : Nov 4, 2024, 4:54:53 PM
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
        <title>Add Team | PMS</title>
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
                        <h1 class="h1 d-inline align-middle">Add Team</h1>

                        <div class="card mb-3">
                            <div class="card-body">
                                <form action="addteam" method="post">
                                    <div class="row g-3">
                                        <div class="col-md-6">
                                            <label for="projectId" class="form-label">Project ID</label>
                                            <input type="number" class="form-control" id="projectId" name="projectId" required>
                                        </div>

                                        <div class="col-md-6">
                                            <label for="teamName" class="form-label">Team Name</label>
                                            <input type="text" class="form-control" id="teamName" name="name" required>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="teamTopic" class="form-label">Topic</label>
                                            <input type="text" class="form-control" id="teamTopic" name="topic" required>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="teamDetails" class="form-label">Details</label>
                                            <textarea class="form-control" id="teamDetails" name="details" rows="3"></textarea>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="teamStatus" class="form-label">Status</label>
                                            <select class="form-select" id="teamStatus" name="status">
                                                <option value="1">Active</option>
                                                <option value="0">Inactive</option>
                                            </select>
                                        </div>
                                    </div>
                                    ${errorMessage}
                                    <div class="mt-3">
                                        <button type="submit" class="btn btn-primary">Add Team</button>
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

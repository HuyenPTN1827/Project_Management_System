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
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/light.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">



    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="../component/sidebar-manager.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="../component/header.jsp"></jsp:include>
                    <main class="content">
                        <div class="container-fluid p-0">
                            <!-- Page Title and Project Dropdown -->
                            <div class="mb-3 d-flex justify-content-between align-items-center">
                                <h1 class="h1 d-inline align-middle">Project Details</h1>
                                <div>
                                    <form action="${pageContext.request.contextPath}/projectconfig" method="get">
                                    <select name="id" class="form-select" style="width: 300px;" onchange="this.form.submit()">
                                        <option value="">Select Project</option>
                                        <c:forEach var="project" items="${projectList}">
                                            <option value="${project.id}" ${project.id == param.id ? 'selected' : ''}>
                                                ${project.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </form>

                            </div>
                        </div>

                        <!-- Tabs Section -->
                        <div class="card">
                            <div class="card-header pb-0">
                                <ul class="nav nav-tabs card-header-tabs" role="tablist">

                                    <li class="nav-item">
                                        <a class="nav-link active" data-bs-toggle="tab" href="#project" role="tab">Project</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" data-bs-toggle="tab" href="#milestones" role="tab">Milestones</a>
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

                                    <div class="tab-pane fade show active" id="project" role="tabpanel">
                                        <h2>General Information</h2>

                                        <c:if test="${not empty message}">
                                            <div class="alert alert-success" role="alert">
                                                ${message}
                                            </div>
                                        </c:if>

                                        <!-- Thông báo lỗi -->
                                        <c:if test="${not empty error}">
                                            <div class="alert alert-danger" role="alert">
                                                ${error}
                                            </div>
                                        </c:if>
${errors}
                                        <style>
                                            .tab-pane {
                                                padding: 15px;
                                            }

                                            form .row {
                                                margin-bottom: 10px;
                                            }

                                            form .form-label {
                                                font-weight: bold;
                                            }

                                            .form-control, .form-select {
                                                border: 1px solid #ccc;
                                                border-radius: 4px;
                                                padding: 8px;
                                            }

                                            .btn-primary {
                                                background-color: #007bff;
                                                border-color: #007bff;
                                                color: white;
                                                padding: 10px 20px;
                                                border-radius: 4px;
                                                cursor: pointer;
                                            }

                                            .btn-primary:hover {
                                                background-color: #0056b3;
                                            }

                                            /* Màu sắc chữ cho thông báo thành công và lỗi */
                                            .alert-success {
                                                color: #155724;  /* Màu xanh đậm cho thông báo thành công */
                                            }

                                            .alert-danger {
                                                color: #721c24;  /* Màu đỏ đậm cho thông báo lỗi */
                                            }
                                        </style>
                                        <form action="updateproject" method="post">
                                            <!-- Hidden input to include project ID -->
                                            <input type="hidden" name="projectId" value="${project.id}" />

                                            <div class="row">
                                                <div class="col-md-6 mb-3">
                                                    <label for="projectName" class="form-label">Name</label>
                                                    <input type="text" class="form-control" id="projectName" name="projectName" placeholder="Project Name" value="${project.name}">
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label for="projectCode" class="form-label">Code</label>
                                                    <input type="text" class="form-control" id="projectCode" name="projectCode" placeholder="PrjCode" value="${project.code}">
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6 mb-3">
                                                    <label for="projectType" class="form-label">Project Type</label>
                                                    <input type="text" class="form-control" id="projectType" name="projectType" placeholder="Project Type" value="${project.typeName}" readonly>
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label for="department" class="form-label">Department</label>
                                                    <select class="form-select" id="department" name="department">
                                                        <option value="${project.departmentId}">Dept Name (DeptCode)</option>
                                                        <c:forEach var="projectname" items="${projectListName}">
                                                            <option value="${projectname.departmentId}" 
                                                                    ${projectname.departmentId == project.departmentId ? 'selected' : ''}>
                                                                ${projectname.departmentName}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6 mb-3">
                                                    <label for="estimatedEffort" class="form-label">Estimated Effort (man-days)</label>
                                                    <input type="number" class="form-control" id="estimatedEffort" name="estimatedEffort" value="${project.estimatedEffort}">
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label for="projectManager" class="form-label">Project Manager</label>
                                                    <select class="form-select" id="projectManager" name="projectManager">
                                                        <option value="${project.userId}">Full Name (UserName)</option>
                                                        <!-- Duyệt qua danh sách userList -->
                                                        <c:forEach var="user" items="${listManagers}">
                                                            <option value="${user.id}" ${user.id == project.userId ? 'selected' : ''}>
                                                                ${user.full_name} 
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6 mb-3">
                                                    <label for="startDate" class="form-label">Start Date</label>
                                                    <input type="date" class="form-control" id="startDate" name="startDate" value="${project.startDate}">
                                                </div>
                                                <div class="col-md-6 mb-3">
                                                    <label for="endDate" class="form-label">End Date</label>
                                                    <input type="date" class="form-control" id="endDate" name="endDate" value="${project.endDate}">
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6 mb-3">
                                                    <label for="status" class="form-label">Status</label>
                                                    <select class="form-select" id="status" name="status">
                                                        <option value="">Status Name</option>
                                                        <option value="0" ${project.status == 0 ? 'selected' : ''}>Pending</option>
                                                        <option value="1" ${project.status == 1 ? 'selected' : ''}>To Do</option>
                                                        <option value="2" ${project.status == 2 ? 'selected' : ''}>Doing</option>
                                                        <option value="3" ${project.status == 3 ? 'selected' : ''}>Done</option>
                                                        <option value="4" ${project.status == 4 ? 'selected' : ''}>Closed</option>
                                                        <option value="5" ${project.status == 5 ? 'selected' : ''}>Cancelled</option>
                                                    </select>
                                                </div>

                                                <div class="col-md-6 mb-3">
                                                    <label for="lastUpdated" class="form-label">Last Updated At</label>
                                                    <input type="date" class="form-control" id="lastUpdated" value="${project.lastUpdated}" readonly>
                                                </div>
                                            </div>

                                            <div class="mb-3">
                                                <label for="description" class="form-label">Description</label>
                                                <textarea class="form-control" id="description" name="description" rows="3">${project.details}</textarea>
                                            </div>

                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </form>
                                    </div>




                                    <!-- Milestones Section -->
                                    <div class="tab-pane fade" id="milestones" role="tabpanel">
                                        <div class="d-flex justify-content-between mb-3">
                                            <!-- Form lọc và tìm kiếm Milestone -->
                                            <form class="d-flex align-items-center" action="filterandsearchmilestone" method="get" id="filterForm">
                                                <!-- Input ẩn để gửi projectId -->
                                                <input type="hidden" name="projectId" value="${param.id}" />

                                                <!-- Dropdown lọc trạng thái -->
                                                <select class="form-select me-2" name="statusFilter" id="statusFilter" style="width: 150px; "onchange="this.form.submit()">
                                                    <option value="">All Status</option>
                                                    <option value="0" ${param.statusFilter == '0' ? 'selected' : ''}>Pending</option>
                                                    <option value="1" ${param.statusFilter == '1' ? 'selected' : ''}>To Do</option>
                                                    <option value="2" ${param.statusFilter == '2' ? 'selected' : ''}>Doing</option>
                                                    <option value="3" ${param.statusFilter == '3' ? 'selected' : ''}>Done</option>
                                                    <option value="4" ${param.statusFilter == '4' ? 'selected' : ''}>Closed</option>
                                                    <option value="5" ${param.statusFilter == '5' ? 'selected' : ''}>Cancelled</option>
                                                </select>

                                                <!-- Input tìm kiếm -->
                                                <input type="text" class="form-control" name="searchKeyword" placeholder="Search" style="width: 250px;" value="${param.searchKeyword}">

                                                <!-- Nút tìm kiếm -->
                                                <button class="btn btn-primary ms-2" type="submit">Search</button>
                                            </form>


                                            <!-- Nút thêm mới Milestone -->
                                            <a href="#" class="btn btn-primary" id="addNewMilestoneButton">Add New</a>

                                        </div>
                                                <style>
  
    /* Modal căn giữa theo chiều ngang và dọc */
#addMilestoneModal .modal-dialog {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%); /* Căn giữa cả chiều ngang và chiều dọc */
    margin: 0;
    max-width: 800px; /* Có thể thay đổi theo ý muốn */
}

#addMilestoneModal .modal-content {
    border-radius: 8px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3); /* Bóng đổ nhẹ cho modal */
}

</style>
                                        <!-- Modal Thêm Milestone -->
                                        <div class="modal fade" id="addMilestoneModal" tabindex="-1" aria-labelledby="addMilestoneModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="addMilestoneModalLabel">Add New Milestone</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="addmilestone" method="post">
                                                            <div class="row">
                                                                <!-- Created By -->
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="createdBy" class="form-label">Created By</label>
                                                                    <input type="text" class="form-control"  name="createdBy" value="${sessionScope.username}" readonly>
                                                                </div>

                                                                <!-- Last Updated -->
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="lastUpdated" class="form-label">Last Updated</label>
                                                                    <input type="text" class="form-control"  name="lastUpdated" placeholder="dd/MM/yyyy hh:mm:ss" readonly>
                                                                </div>

                                                                <!-- Milestone Name -->
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="milestoneName" class="form-label">Milestone/Deliverable*</label>
                                                                    <input type="text" class="form-control"  name="milestoneName" required>
                                                                </div>

                                                                <!-- Parent Milestone -->
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="parentMilestone" class="form-label">Parent Milestone</label>
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
                                                                <input type="hidden" id="projectId" name="projectId">


                                                                <!-- Priority -->
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="priority" class="form-label">Priority</label>
                                                                    <input type="text" class="form-control"  name="priority">
                                                                </div>



                                                                <!-- Target Date -->
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="targetDate" class="form-label">Target Date*</label>
                                                                    <input type="date" class="form-control"  name="targetDate" required>
                                                                </div>

                                                                <!-- Description -->
                                                                <div class="col-md-12 mb-3">
                                                                    <label for="description" class="form-label">Description</label>
                                                                    <textarea class="form-control"  name="description" rows="3"></textarea>
                                                                </div>


                                                            </div>

                                                            <!-- Submit Button -->
                                                            <button type="submit" class="btn btn-primary">Submit</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Bao gồm thư viện Bootstrap CSS và JavaScript --> <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/css/bootstrap.min.css" rel="stylesheet"> <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/js/bootstrap.bundle.min.js"></script>

                                                <style>
  
    /* Modal căn giữa theo chiều ngang và dọc */
#editMilestoneModal .modal-dialog {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%); /* Căn giữa cả chiều ngang và chiều dọc */
    margin: 0;
    max-width: 800px; /* Có thể thay đổi theo ý muốn */
}

#editMilestoneModal .modal-content {
    border-radius: 8px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3); /* Bóng đổ nhẹ cho modal */
}

</style>
                                        <!-- Modal Edit Milestone -->
                                        <div class="modal fade" id="editMilestoneModal" tabindex="-1" aria-labelledby="editMilestoneModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="editMilestoneModalLabel">Edit Milestone</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form id="editMilestoneForm" action="/PMS/updatemilestone" method="POST">
                                                            <div class="row">
                                                                <input type="hidden" id="id" name="id" />
                                                                <!-- Milestone Name -->
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="milestoneName" class="form-label">Milestone Name</label>
                                                                    <input type="text" class="form-control" id="milestoneName" name="milestonename">
                                                                </div>

                                                                <!-- Created By -->
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="createdBy" class="form-label">Created By</label>
                                                                    <input type="hidden" id="createdById" name="createdById" />
                                                                    <input type="text" class="form-control" id="createdBy" name="createdBy"  readonly>
                                                                </div>

                                                                <!-- Project Name (new field) -->
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="projectname" class="form-label">Project Name</label>
                                                                    <input type="hidden" id="project_Id" name="project_Id" />
                                                                    <input type="text" class="form-control" id="projectname" name="projectName"  readonly>
                                                                </div>
                                                                <!-- Parent Milestone -->
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="parentmilestone" class="form-label">Parent Milestone</label>
                                                                    <input type="hidden" id="parentMilestoneId" name="parentMilestoneId" />
                                                                    <input type="text" class="form-control" id="parentmilestone" name="parentMilestone"  readonly>
                                                                </div>
                                                                <!-- Priority -->
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="priority" class="form-label">Priority</label>
                                                                    <input type="text" class="form-control" id="priority" name="priority">
                                                                </div>

                                                                <!-- Target Date -->
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="targetDate" class="form-label">Target Date</label>
                                                                    <input type="text" class="form-control" id="targetDate" name="targetdate" readonly>
                                                                </div>
                                                                <!-- Last Updated -->
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="lastupdated" class="form-label">Last Updated</label>
                                                                    <input type="text" class="form-control" id="lastupdated" name="lastupdated" readonly>
                                                                </div>


                                                                <!-- Status -->
                                                                <div class="col-md-6 mb-3">
                                                                    <label for="status" class="form-label">Status</label>
                                                                    <select class="form-control" id="status" name="status">
                                                                        <option value="0">Pending</option>
                                                                        <option value="1">To Do</option>
                                                                        <option value="2">Doing</option>
                                                                        <option value="3">Done</option>
                                                                        <option value="4">Closed</option>
                                                                        <option value="5">Cancelled</option>
                                                                    </select>
                                                                </div>
                                                                <!-- Description -->
                                                                <div class="col-md-12 mb-3">
                                                                    <label for="detail" class="form-label">Description</label>
                                                                    <textarea class="form-control" id="detail" name="description"></textarea>
                                                                </div>

                                                                <div class="col-md-12 mb-3">
                                                                    <button type="submit" class="btn btn-primary">Save Changes</button>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <script>
                                                                        document.getElementById("addNewMilestoneButton").addEventListener("click", function (event) {
                                                                            event.preventDefault(); // Ngăn chặn hành động mặc định của liên kết

                                                                            // Mở modal
                                                                            const modal = new bootstrap.Modal(document.getElementById("addMilestoneModal"));
                                                                            modal.show(); // Hiển thị modal
                                                                        });

                                                                        // Hàm để lấy thời gian hiện tại và định dạng nó
                                                                        function getCurrentDateTime() {
                                                                            const now = new Date();
                                                                            const options = {
                                                                                year: 'numeric',
                                                                                month: '2-digit',
                                                                                day: '2-digit',
                                                                                hour: '2-digit',
                                                                                minute: '2-digit',
                                                                                second: '2-digit',
                                                                                hour12: false
                                                                            };
                                                                            // Định dạng ngày tháng theo "dd/MM/yyyy hh:mm:ss"
                                                                            const formattedDate = now.toLocaleDateString('en-GB', options).replace(',', '');
                                                                            return formattedDate.replace(/\/|,|\./g, '/'); // Định dạng dd/MM/yyyy hh:mm:ss
                                                                        }

                                                                        // Gán giá trị vào trường lastUpdated
                                                                        document.addEventListener("DOMContentLoaded", function () {
                                                                            const lastUpdatedField = document.getElementById("lastUpdated");
                                                                            if (lastUpdatedField) {
                                                                                lastUpdatedField.value = getCurrentDateTime();
                                                                            }
                                                                        });
                                                                        function updateProjectId() {
                                                                            // Lấy phần tử select của parent milestone
                                                                            var parentMilestoneSelect = document.getElementById("parentMilestone");
                                                                            // Lấy option đang được chọn
                                                                            var selectedOption = parentMilestoneSelect.options[parentMilestoneSelect.selectedIndex];
                                                                            // Lấy projectId từ thuộc tính data-project-id
                                                                            var projectId = selectedOption.getAttribute("data-project-id");
                                                                            // Gán giá trị projectId vào trường ẩn
                                                                            document.getElementById("projectId").value = projectId || "";
                                                                        }



                                        </script>



                                        <script>
                                            function loadMilestone(id) {
                                                // Kiểm tra giá trị id trước khi sử dụng
                                                console.log('Milestone ID:', id);

                                                // Chuyển id thành chuỗi và kiểm tra xem id có hợp lệ không
                                                const stringId = String(id).trim();
                                                if (!stringId || stringId === '') {
                                                    alert('ID không hợp lệ');
                                                    return;  // Nếu id không hợp lệ, dừng việc gửi yêu cầu
                                                }

                                                // Mã hóa id để đảm bảo không có ký tự không hợp lệ
                                                const encodedId = encodeURIComponent(stringId);
                                                console.log('Encoded Milestone ID:', encodedId);

                                                // Gửi yêu cầu Ajax đến URL với id milestone sử dụng jQuery
                                                const url = '<%= request.getContextPath() %>/getmilestone?id=' + encodedId;
                                                console.log('URL gửi yêu cầu:', url);  // Kiểm tra URL trước khi gửi

                                                $.ajax({
                                                    url: url,
                                                    method: 'GET',
                                                    dataType: 'json',
                                                    success: function (data) {
                                                        console.log(data);  // Kiểm tra dữ liệu nhận được từ API

                                                        // Kiểm tra xem dữ liệu có hợp lệ không
                                                        if (!data || Object.keys(data).length === 0) {
                                                            alert("Dữ liệu không có");
                                                            return;
                                                        }

                                                        // Cập nhật modal với dữ liệu milestone
                                                        $('#id').val(data.id || '');
                                                        $('#milestoneName').val(data.name || '');
                                                        $('#parentmilestone').val(data.parentMilestoneName || '');
                                                        $('#parentMilestoneId').val(data.parentMilestone || '');
                                                        $('#priority').val(data.priority || '');
                                                        $('#targetDate').val(data.targetDate || '');
                                                        $('#detail').val(data.details || '');
                                                        $('#createdBy').val(data.createdbyuserName || '');
                                                        $('#createdById').val(data.createdBy || '');
                                                        $('#status').val(data.status || '');
                                                        $('#projectname').val(data.projectName || '');
                                                        $('#project_Id').val(data.projectId || '');
                                                        $('#lastupdated').val(data.lastUpdated || '');
                                                        console.log('Created By:', data.createdBy);  // Ghi nhật ký để kiểm tra

                                                        // Mở modal sau khi dữ liệu đã được load vào
                                                        $('#editMilestoneModal').modal('show');
                                                    },
                                                    error: function (xhr, status, error) {
                                                        console.error('Lỗi khi lấy dữ liệu milestone:', error);
                                                        alert('Đã xảy ra lỗi khi lấy dữ liệu milestone');
                                                    }
                                                });




                                            }



                                        </script>



                                        <!-- Bảng Milestones -->
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Project Name</th>
                                                    <th>Milestone Name</th>
                                                    <th>Parent Milestone</th>
                                                    <th>Priority</th>
                                                    <th>Target Date</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <style>
                                                .bold-milestone {
                                                    font-weight: bold;
                                                }
                                            </style>
                                            <tbody>
                                                <!-- Hiển thị các milestone có parentMilestone = 0 trước -->
                                                <c:forEach var="milestone" items="${milestoneList}">
                                                    <c:if test="${milestone.parentMilestone == 0}">
                                                        <tr class="bold-milestone">
                                                            <td>${milestone.id}</td>
                                                            <td>${milestone.projectName}</td>
                                                            <td>${milestone.name}</td>
                                                            <td>${milestone.parentMilestoneName}</td>
                                                            <td>${milestone.priority}</td>
                                                            <td>${milestone.targetDate}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${milestone.status == 0}">Pending</c:when>
                                                                    <c:when test="${milestone.status == 1}">To Do</c:when>
                                                                    <c:when test="${milestone.status == 2}">Doing</c:when>
                                                                    <c:when test="${milestone.status == 3}">Done</c:when>
                                                                    <c:when test="${milestone.status == 4}">Closed</c:when>
                                                                    <c:when test="${milestone.status == 5}">Cancelled</c:when>
                                                                    <c:otherwise>Unknown</c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editMilestoneModal" 
                                                                        onclick="loadMilestone(${milestone.id})">Edit</button>

                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>

                                                <!-- Hiển thị các milestone còn lại -->
                                                <c:forEach var="milestone" items="${milestoneList}">
                                                    <c:if test="${milestone.parentMilestone != 0}">
                                                        <tr>
                                                            <td>${milestone.id}</td>
                                                            <td>${milestone.projectName}</td>
                                                            <td>${milestone.name}</td>
                                                            <td>${milestone.parentMilestoneName}</td>
                                                            <td>${milestone.priority}</td>
                                                            <td>${milestone.targetDate}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${milestone.status == 0}">Pending</c:when>
                                                                    <c:when test="${milestone.status == 1}">To Do</c:when>
                                                                    <c:when test="${milestone.status == 2}">Doing</c:when>
                                                                    <c:when test="${milestone.status == 3}">Done</c:when>
                                                                    <c:when test="${milestone.status == 4}">Closed</c:when>
                                                                    <c:when test="${milestone.status == 5}">Cancelled</c:when>
                                                                    <c:otherwise>Unknown</c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <!-- Nút Edit -->
                                                                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editMilestoneModal" 
                                                                        onclick="loadMilestone(${milestone.id})">Edit</button>

                                                            </td>
                                                        </tr>
                                                    </c:if>
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



<!-- Bao gồm thư viện jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/light.js"></script>
<script src="https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.min.js"></script>
<script>
                                                                            feather.replace();
</script>

</body>
</html>

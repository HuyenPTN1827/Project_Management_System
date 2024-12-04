<%-- 
    Document   : dept-user-add
    Created on : Nov 1, 2024, 6:43:42 PM
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

        <title>Department Details | PMS</title>

        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">

        <!-- Choose your prefered color scheme -->
        <!-- <link href="css/light.css" rel="stylesheet"> -->
        <!-- <link href="css/dark.css" rel="stylesheet"> -->

        <!-- BEGIN SETTINGS -->
        <!-- Remove this after purchasing -->
        <link class="js-stylesheet" href="${pageContext.request.contextPath}/css/light.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/settings.js"></script>

        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <style>
            body {
                opacity: 0;
            }

            /* Dropdown container */
            .dropbtn {
                background-color: #04AA6D;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropbtn:hover, .dropbtn:focus {
                background-color: #3e8e41;
            }

            #myInput {
                box-sizing: border-box;
                font-size: 16px;
                padding: 14px 20px 12px 45px;
                border: none;
                border-bottom: 1px solid #ddd;
            }

            #myInput:focus {
                outline: 3px solid #ddd;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f6f6f6;
                min-width: 230px;
                border: 1px solid #ddd;
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown-content a:hover {
                background-color: #f1f1f1
            }

            .show {
                display: block;
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

        <!--        <script>
                    $(document).ready(function () {
                        // Khởi tạo Select2 cho dropdown
                        $('#keywordDropdown').select2({
                            width: 'resolve',
                            allowClear: true
                        });
        
                        // Xử lý giữ modal sau khi submit
                        $('#searchForm').on('submit', function (e) {
                            e.preventDefault();
                            $.ajax({
                                url: $(this).attr('action'),
                                type: 'POST',
                                data: $(this).serialize(),
                                success: function (response) {
                                    // Cập nhật modal-body với nội dung mới
                                    $('.modal-body').html(response);
                                    $('#departmentModal').modal('show'); // Giữ modal
                                },
                                error: function () {
                                    alert('An error occurred while processing the request.');
                                }
                            });
                        });
                    });
                </script>-->

        <script>
            function updateFormFields(selectElement) {
                // Get selected option values
                var selectedOption = selectElement.options[selectElement.selectedIndex];
                // Extract data attributes
                var fullname = selectedOption.getAttribute('data-fullname');
                var phone = selectedOption.getAttribute('data-phone');
                var role = selectedOption.getAttribute('data-role');
                // Update form fields
                document.getElementById('fullname').value = fullname;
                document.getElementById('mobile').value = phone;
                document.getElementById('email').value = selectedOption.value; // Email is the value of the option
                document.getElementById('roleId').value = role;
            }
        </script>

    </head>
    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <!--<div class="wrapper">-->
        <% request.setAttribute("currentPage", "department-management"); %>
        <%--<jsp:include page="../component/sidebar-admin.jsp"></jsp:include>--%>
        <!--<div class="main">-->
        <%--<jsp:include page="../component/header.jsp"></jsp:include>--%>

        <!--<main class="content">-->
        <!--<div class="container-fluid p-0">-->
<!--                        <a href="<%=request.getContextPath()%>/department-management">Departments Management > </a>
            <a href="<%=request.getContextPath()%>/department-config?id=${deptId}">Department Details > </a>-->

        <!--                        <h1 class="h1 mt-2 mb-3"> Add User</h1>-->
        <div class="row">

            <div class="col-md-12 col-xl-12">
                <div class="card">
                    <div class="card-header">
                        <div class="mt-2 mb-2">
                            <select name="keyword" id="keywordDropdown" class="form-control select2" style="width: 100%;" 
                                    data-placeholder="Enter Full Name or Email" onchange="updateFormFields(this)">
                                <option></option>
                                <c:forEach var="user" items="${deptUserList}">
                                    <option value="${user.email}" 
                                            data-fullname="${user.full_name}" 
                                            data-phone="${user.mobile}" 
                                            data-role="${user.setting.name}">
                                        ${user.full_name} - ${user.email}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>       

                <div class="card">
                    <div class="card-body">
                        <c:if test="${not empty errorMessages}">
                            <div class="alert alert-danger pt-3 pe-3 ps-3">
                                <ul>
                                    <c:forEach items="${errorMessages}" var="error">
                                        <li>${error}</li>
                                        </c:forEach>
                                </ul>
                            </div>
                        </c:if>

                        <form action="insert-department-user" method="post" class="row" id="insertForm">
                            <input type="hidden" name="deptId" value="${deptId}">
                            <input type="text" name="id" value="${deptUser.id}">

                            <div class="mb-3 col-md-6">
                                <label class="form-label">Manager</label>
                                <input type="text" class="form-control" name="fullname" id="fullname" placeholder="Full name (Username)" 
                                       value="${deptUser.full_name}" readonly>
                            </div>

                            <div class="mb-3 col-md-6">
                                <label class="form-label">Phone</label>
                                <input type="text" class="form-control" name="mobile" id="mobile" placeholder="Phone number"
                                       value="${deptUser.mobile}" readonly>
                            </div>

                            <div class="mb-3 col-md-6">
                                <label class="form-label">Email</label>
                                <input type="text" class="form-control" name="email" id="email" placeholder="Email address" 
                                       value="${deptUser.email}" readonly>
                            </div>

                            <div class="mb-3 col-md-6">
                                <label class="form-label">Role</label>
                                <input type="text" class="form-control" name="roleId" id="roleId" placeholder="Role" 
                                       value="${deptUser.setting.name}" readonly>
                            </div>

                            <div>
                                <button type="submit" class="btn btn-lg btn-success">Submit</button>
                            </div>
                        </form>                                    
                    </div>
                </div>
            </div>
        </div>
        <!--</div>-->
        <!--</main>-->

        <!--                <footer class="footer">
                            <div class="container-fluid">
                                <div class="row text-muted">
                                    <div class="col-6 text-start">
                                        <p class="mb-0">
                                            <a href="https://adminkit.io/" target="_blank" class="text-muted"><strong>AdminKit</strong></a> &copy;
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
                        </footer>-->
        <!--</div>-->
        <!--</div>-->

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

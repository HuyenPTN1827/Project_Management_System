

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
        <link rel="shortcut icon" href="img/icons/icon-48x48.png" />

        <link rel="canonical" href="tables-datatables-buttons.html" />

        <title>Table with Buttons | AdminKit Demo</title>

        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">

        <!-- Choose your prefered color scheme -->
        <!-- <link href="css/light.css" rel="stylesheet"> -->
        <!-- <link href="css/dark.css" rel="stylesheet"> -->

        <!-- BEGIN SETTINGS -->
        <!-- Remove this after purchasing -->
        <link class="js-stylesheet" href="css/light.css" rel="stylesheet">
        <script src="js/settings.js"></script>
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
        </script></head>


    <body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
        <div class="wrapper">
            <jsp:include page="./layout/sidebar.jsp"></jsp:include>
                <div class="main">
                <jsp:include page="./layout/header.jsp"></jsp:include>

                <main class="content">
                    <div class="container-fluid p-0">

                        <div class="mb-3">
                            <h1 class="h3 d-inline align-middle">Group List/Department</h1>
                        </div>

                        <div class="row">
                            <div class="col-12">

                                <div class="card">
                                    <div class="card-header">
                                        <a class="btn btn-primary" href="">Create new</a>
                                    </div>
                                    <form action="" style="display: flex; justify-content: start; padding-left: 15px">
                                        <select name="type" class="form-select" style="display: block; width: 150px;margin-right: 15px; position: relative; top: 25px">
                                            <option value="">All Roles</option>
                                            <option value="1">Role</option>
                                            <option value="2">Category</option>
                                        </select>
                                        <select name="status" class="form-select "  style="display: block; width: 150px;margin-right: 15px; position: relative; top: 25px">
                                            <option value="">All Status</option>
                                            <option value="1">Active</option>
                                            <option value="0">Inactive</option>
                                        </select>
                                    </form>
                                    <div class="card-body">
                                        <table id="datatables-buttons" class="table table-striped" style="width:100%">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Code</th>
                                                    <th>Name</th>
                                                    <th>Detail</th>
                                                    <th>Parent</th>
                                                    <th>Status</th>
                                                    <th> Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td>John Chris</td>
                                                    <td>John@gmail.com</td>
                                                    <td>IT</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">Active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>Michle Act</td>
                                                    <td>Michle@gmail.com</td>
                                                    <td>Marketing</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">De-active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>John Chris</td>
                                                    <td>John@gmail.com</td>
                                                    <td>IT</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">Active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>Michle Act</td>
                                                    <td>Michle@gmail.com</td>
                                                    <td>Marketing</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">De-active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>John Chris</td>
                                                    <td>John@gmail.com</td>
                                                    <td>IT</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">Active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>Michle Act</td>
                                                    <td>Michle@gmail.com</td>
                                                    <td>Marketing</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">De-active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>John Chris</td>
                                                    <td>John@gmail.com</td>
                                                    <td>IT</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">Active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>Michle Act</td>
                                                    <td>Michle@gmail.com</td>
                                                    <td>Marketing</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">De-active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>John Chris</td>
                                                    <td>John@gmail.com</td>
                                                    <td>IT</td>
                                                    <td>HR</td>
                                                    <td><p style="color: red">Inactive</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">Active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>Michle Act</td>
                                                    <td>Michle@gmail.com</td>
                                                    <td>Marketing</td>
                                                    <td>HR</td>
                                                    <td><p style="color: red">Inactive</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">De-active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>John Chris</td>
                                                    <td>John@gmail.com</td>
                                                    <td>IT</td>
                                                    <td>HR</td>
                                                    <td><p style="color: red">Inactive</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">Active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>Michle Act</td>
                                                    <td>Michle@gmail.com</td>
                                                    <td>Marketing</td>
                                                    <td>HR</td>
                                                    <td><p style="color: red">Inactive</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">De-active</a>
                                                    </td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="container-fluid p-0">

                        <div class="mb-3">
                            <h1 class="h3 d-inline align-middle">Group List/Domain</h1>
                        </div>

                        <div class="row">
                            <div class="col-12">

                                <div class="card">
                                    <div class="card-header">
                                        <a class="btn btn-primary" href="">Create new</a>
                                    </div>
                                    <form action="" style="display: flex; justify-content: start; padding-left: 15px">
                                        <select name="type" class="form-select" style="display: block; width: 150px;margin-right: 15px; position: relative; top: 25px">
                                            <option value="">All Roles</option>
                                            <option value="1">Role</option>
                                            <option value="2">Category</option>
                                        </select>
                                        <select name="status" class="form-select "  style="display: block; width: 150px;margin-right: 15px; position: relative; top: 25px">
                                            <option value="">All Status</option>
                                            <option value="1">Active</option>
                                            <option value="0">Inactive</option>
                                        </select>
                                    </form>
                                    <div class="card-body">
                                        <table id="datatables-buttons1" class="table table-striped" style="width:100%">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Code</th>
                                                    <th>Name</th>
                                                    <th>Detail</th>
                                                    <th>Parent</th>
                                                    <th>Status</th>
                                                    <th> Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td>John Chris</td>
                                                    <td>John@gmail.com</td>
                                                    <td>IT</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">Active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>Michle Act</td>
                                                    <td>Michle@gmail.com</td>
                                                    <td>Marketing</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">De-active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>John Chris</td>
                                                    <td>John@gmail.com</td>
                                                    <td>IT</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">Active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>Michle Act</td>
                                                    <td>Michle@gmail.com</td>
                                                    <td>Marketing</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">De-active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>John Chris</td>
                                                    <td>John@gmail.com</td>
                                                    <td>IT</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">Active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>Michle Act</td>
                                                    <td>Michle@gmail.com</td>
                                                    <td>Marketing</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">De-active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>John Chris</td>
                                                    <td>John@gmail.com</td>
                                                    <td>IT</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">Active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>Michle Act</td>
                                                    <td>Michle@gmail.com</td>
                                                    <td>Marketing</td>
                                                    <td>HR</td>
                                                    <td><p style="color: green">Active</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">De-active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>John Chris</td>
                                                    <td>John@gmail.com</td>
                                                    <td>IT</td>
                                                    <td>HR</td>
                                                    <td><p style="color: red">Inactive</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">Active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>Michle Act</td>
                                                    <td>Michle@gmail.com</td>
                                                    <td>Marketing</td>
                                                    <td>HR</td>
                                                    <td><p style="color: red">Inactive</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">De-active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>1</td>
                                                    <td>John Chris</td>
                                                    <td>John@gmail.com</td>
                                                    <td>IT</td>
                                                    <td>HR</td>
                                                    <td><p style="color: red">Inactive</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">Active</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td>Michle Act</td>
                                                    <td>Michle@gmail.com</td>
                                                    <td>Marketing</td>
                                                    <td>HR</td>
                                                    <td><p style="color: red">Inactive</p></td>
                                                    <td>
                                                        <a href="" style="margin-right: 10px">Edit</a>
                                                        <a href="" style="margin-right: 10px">De-active</a>
                                                    </td>
                                                </tr>

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
                </footer>
            </div>
        </div>

        <script src="js/app.js"></script>

        <script src="js/datatables.js"></script>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Datatables with Buttons
                var datatablesButtons = $("#datatables-buttons").DataTable({
                    responsive: true,
                    lengthChange: !1
                });
                var datatablesButtons1 = $("#datatables-buttons1").DataTable({
                    responsive: true,
                    lengthChange: !1
                });
            });
        </script>
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
        </script></body>

</html>
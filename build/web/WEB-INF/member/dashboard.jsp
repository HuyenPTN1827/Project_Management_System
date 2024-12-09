<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--BachHD-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>


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

        <link rel="canonical" href="index.html" />

        <title>AdminKit Demo - Bootstrap 5 Admin Template</title>

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
            <% request.setAttribute("currentPage", "dashboard"); %>
            <jsp:include page="../component/sidebar.jsp"></jsp:include>
                <div class="main"> 
                <jsp:include page="../component/header.jsp"></jsp:include>

                    <main class="content">
                        <div class="container-fluid p-0">

                            <div class="row mb-2 mb-xl-3">
                                <div class="col-auto d-none d-sm-block">
                                    <h1>Dashboard</h1>
                                </div>

                                <div class="col-auto ms-auto text-end mt-n1">
                                    <form action="member-dashboard" method="get" class="d-flex">
                                        <select name="deptId" class="form-select me-2" onchange="this.form.submit()">
                                            <option value="">All Departments</option>
                                        <c:forEach items="${dept}" var="d">
                                            <option 
                                                <c:if test="${deptId eq d.id}">
                                                    selected="selected"
                                                </c:if>
                                                value="${d.id}">${d.name} (${d.code})
                                            </option>
                                        </c:forEach>
                                    </select>

                                    <select name="bizTerm" class="form-select" onchange="this.form.submit()">
                                        <option value="">All Business Terms</option>
                                        <c:forEach items="${setting}" var="s">
                                            <option 
                                                <c:if test="${bizTerm eq s.id}">
                                                    selected="selected"
                                                </c:if>
                                                value="${s.id}">${s.name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </form>
                            </div>
                        </div>
                            
                        <div class="row">
                            
                            <div class="col-12 col-md-3 col-xxl-3 d-flex order-1 order-xxl-3">
                                <div class="card flex-fill w-100">
                                    <div class="card-header">
                                        <div class="card-actions float-end">
                                            <div class="dropdown position-relative">
                                                <a href="#" data-bs-toggle="dropdown" data-bs-display="static">
                                                    <i class="align-middle" data-feather="more-horizontal"></i>
                                                </a>

                                                <div class="dropdown-menu dropdown-menu-end">
                                                    <a class="dropdown-item" href="#">Action</a>
                                                    <a class="dropdown-item" href="#">Another action</a>
                                                    <a class="dropdown-item" href="#">Something else here</a>
                                                </div>
                                            </div>
                                        </div>
                                        <h5 class="card-title mb-0">Projects</h5>
                                    </div>
                                    <div class="card-body d-flex">
                                        <div class="align-self-center w-100">
                                            <div class="py-3">
                                                <div class="chart chart-xs">
                                                    <canvas id="chartjs-dashboard-pie"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
<!--
                            <div class="col-12 col-md-4 col-xxl-3 d-flex order-1 order-xxl-3">
                                <div class="card flex-fill w-100">
                                    <div class="card-header">
                                        <div class="card-actions float-end">
                                            <div class="dropdown position-relative">
                                                <a href="#" data-bs-toggle="dropdown" data-bs-display="static">
                                                    <i class="align-middle" data-feather="more-horizontal"></i>
                                                </a>

                                                <div class="dropdown-menu dropdown-menu-end">
                                                    <a class="dropdown-item" href="#">Action</a>
                                                    <a class="dropdown-item" href="#">Another action</a>
                                                    <a class="dropdown-item" href="#">Something else here</a>
                                                </div>
                                            </div>
                                        </div>
                                        <h5 class="card-title mb-0">Issues</h5>
                                    </div>
                                    <div class="card-body d-flex">
                                        <div class="align-self-top w-100">
                                            <div class="py-3">
                                                <div class="chart chart-xs">
                                                    <canvas id="chartjs-dashboard-pie1"></canvas>
                                                </div>
                                            </div>

                                            <table class="table mb-0">
                                                <tbody>
                                                    <%--<c:forEach items="${requestScope.issues}" var="issue">--%>
                                                        <tr>
                                                            <td>
                                                                <%--<c:if test="${issue.status eq 0}">--%>
                                                                    <i class="fas fa-circle text-secondary fa-fw"></i> Pending
                                                                <%--</c:if>--%>
                                                                <%--<c:if test="${issue.status eq 1}">--%>
                                                                    <i class="fas fa-circle text-primary fa-fw"></i> In Progress
                                                                <%--</c:if>--%>
                                                                <%--<c:if test="${issue.status eq 2}">--%>
                                                                    <i class="fas fa-circle text-success fa-fw"></i> Closed
                                                                <%--</c:if>--%>
                                                                <%--<c:if test="${issue.status eq 3}">--%>
                                                                    <i class="fas fa-circle text-danger fa-fw"></i> Cancelled
                                                                <%--</c:if>--%>
                                                            </td>
                                                            <td class="text-end">${issue.count}</td>
                                                        </tr>
                                                    <%--</c:forEach>--%>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>-->

                            <div class="col-xl-5" hidden>
                                <div class="card flex-fill w-100">
                                    <div class="card-header">
                                        <div class="float-end">
                                            <form class="row g-2">
                                                <div class="col-auto">
                                                    <select class="form-select form-select-sm bg-light border-0">
                                                        <option>Jan</option>
                                                        <option value="1">Feb</option>
                                                        <option value="2">Mar</option>
                                                        <option value="3">Apr</option>
                                                    </select>
                                                </div>
                                                <div class="col-auto">
                                                    <input type="text" class="form-control form-control-sm bg-light rounded-2 border-0" style="width: 100px;"
                                                           placeholder="Search..">
                                                </div>
                                            </form>
                                        </div>
                                        <h5 class="card-title mb-0">Recent Movement</h5>
                                    </div>
                                    <div class="card-body pt-2 pb-3">
                                        <div class="chart chart-sm">
                                            <canvas id="chartjs-dashboard-line"></canvas>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12 col-lg-12 col-xxl-9 d-flex">
                                <div class="card flex-fill">
                                    <div class="card-header">
                                        <div class="card-actions float-end">
                                            <div class="dropdown position-relative">
                                                <a href="#" data-bs-toggle="dropdown" data-bs-display="static">
                                                    <i class="align-middle" data-feather="more-horizontal"></i>
                                                </a>

                                                <div class="dropdown-menu dropdown-menu-end">
                                                    <a class="dropdown-item" href="<%=request.getContextPath()%>/issue-management?projectId=1">View all Issues</a>
                                                    <a class="dropdown-item" href="<%=request.getContextPath()%>/add-issue?userId=${user.id}">Add new Issue</a>
                                                </div>
                                            </div>
                                        </div>
                                        <h5 class="card-title mb-0">Latest Project Issues</h5>
                                    </div>
                                    <table class="table table-borderless my-0">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Project</th>
                                                <th>Issue</th>
                                                <th>Assigner</th>
                                                <th>Assignee</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.listIssue}" var="issue">
                                                <tr>
                                                    <td>${issue.id}</td>
                                                    <td>${issue.project.name}</td>
                                                    <td>${issue.name}</td>
                                                    <td>${issue.created_by.full_name}</td>
                                                    <td>${issue.assignee.full_name}</td>
                                                    <td>
                                                        <c:if test="${user.id eq issue.created_by.id || user.id eq issue.assignee.id}">
                                                            <a href="<%=request.getContextPath()%>/edit-issue?action=edit&id=${issue.id}&userId=${user.id}&projectId=${issue.project.id}" 
                                                               class="btn btn-info"><i class="align-middle" data-feather="eye"></i></a>
                                                            </c:if>
                                                            <c:if test="${user.id ne issue.created_by.id && user.id ne issue.assignee.id}">
                                                            <a href="<%=request.getContextPath()%>/edit-issue?action=view&id=${issue.id}&userId=${user.id}&projectId=${issue.project.id}" 
                                                               class="btn btn-info"><i class="align-middle" data-feather="eye"></i></a>
                                                            </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <!--                            <div class="col-12 col-lg-4 col-xxl-3 d-flex">
                                                            <div class="card flex-fill w-100">
                                                                <div class="card-header">
                                                                    <div class="card-actions float-end">
                                                                        <div class="dropdown position-relative">
                                                                            <a href="#" data-bs-toggle="dropdown" data-bs-display="static">
                                                                                <i class="align-middle" data-feather="more-horizontal"></i>
                                                                            </a>
                            
                                                                            <div class="dropdown-menu dropdown-menu-end">
                                                                                <a class="dropdown-item" href="#">Action</a>
                                                                                <a class="dropdown-item" href="#">Another action</a>
                                                                                <a class="dropdown-item" href="#">Something else here</a>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <h5 class="card-title mb-0">Monthly Sales</h5>
                                                                </div>
                                                                <div class="card-body d-flex w-100">
                                                                    <div class="align-self-center chart chart-lg">
                                                                        <canvas id="chartjs-dashboard-bar"></canvas>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>-->

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

        <script>
                                        document.addEventListener("DOMContentLoaded", function () {
                                            var ctx = document.getElementById("chartjs-dashboard-line").getContext("2d");
                                            var gradientLight = ctx.createLinearGradient(0, 0, 0, 225);
                                            gradientLight.addColorStop(0, "rgba(215, 227, 244, 1)");
                                            gradientLight.addColorStop(1, "rgba(215, 227, 244, 0)");
                                            var gradientDark = ctx.createLinearGradient(0, 0, 0, 225);
                                            gradientDark.addColorStop(0, "rgba(51, 66, 84, 1)");
                                            gradientDark.addColorStop(1, "rgba(51, 66, 84, 0)");
                                            // Line chart
                                            new Chart(document.getElementById("chartjs-dashboard-line"), {
                                                type: "line",
                                                data: {
                                                    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                                                    datasets: [{
                                                            label: "Sales ($)",
                                                            fill: true,
                                                            backgroundColor: window.theme.id === "light" ? gradientLight : gradientDark,
                                                            borderColor: window.theme.primary,
                                                            data: [
                                                                2115,
                                                                1562,
                                                                1584,
                                                                1892,
                                                                1587,
                                                                1923,
                                                                2566,
                                                                2448,
                                                                2805,
                                                                3438,
                                                                2917,
                                                                3327
                                                            ]
                                                        }]
                                                },
                                                options: {
                                                    maintainAspectRatio: false,
                                                    legend: {
                                                        display: false
                                                    },
                                                    tooltips: {
                                                        intersect: false
                                                    },
                                                    hover: {
                                                        intersect: true
                                                    },
                                                    plugins: {
                                                        filler: {
                                                            propagate: false
                                                        }
                                                    },
                                                    scales: {
                                                        xAxes: [{
                                                                reverse: true,
                                                                gridLines: {
                                                                    color: "rgba(0,0,0,0.0)"
                                                                }
                                                            }],
                                                        yAxes: [{
                                                                ticks: {
                                                                    stepSize: 1000
                                                                },
                                                                display: true,
                                                                borderDash: [3, 3],
                                                                gridLines: {
                                                                    color: "rgba(0,0,0,0.0)",
                                                                    fontColor: "#fff"
                                                                }
                                                            }]
                                                    }
                                                }
                                            });
                                        });
        </script>

        <script>
            var labels = ${labels};
            var data = ${data};
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Pie chart
                new Chart(document.getElementById("chartjs-dashboard-pie"), {
                    type: "pie",
                    data: {
                        labels: ["Chrome", "Firefox", "IE", "Other"],
                        datasets: [{
                                data: [4306, 3801, 1689, 3251],
                                backgroundColor: [
                                    window.theme.primary,
                                    window.theme.warning,
                                    window.theme.danger,
                                    "#E8EAED"
                                ],
                                borderWidth: 5,
                                borderColor: window.theme.white
                            }]
                    },
                    options: {
                        responsive: !window.MSInputMethodContext,
                        maintainAspectRatio: false,
                        legend: {
                            display: false
                        },
                        cutoutPercentage: 70
                    }
                });
            });

            document.addEventListener("DOMContentLoaded", function () {
                var ctx = document.getElementById("chartjs-dashboard-pie1").getContext("2d");

                // Kiểm tra nếu data trống
                var dataValues = ${data}; // Chuyển đổi từ JSP
                if (dataValues.length === 0) {
                    // Nếu data trống, hiển thị màu #E8EAED
                    new Chart(ctx, {
                        type: "pie",
                        data: {
                            labels: ["No Data"], // Có thể thay đổi nếu cần
                            datasets: [{
                                    data: [1], // Hiển thị một giá trị mặc định
                                    backgroundColor: ["#E8EAED"], // Màu khi không có dữ liệu
                                    borderWidth: 5,
                                    borderColor: window.theme.white
                                }]
                        },
                        options: {
                            responsive: !window.MSInputMethodContext,
                            maintainAspectRatio: false,
                            legend: {
                                display: false
                            },
                            cutoutPercentage: 50
                        }
                    });
                } else {
                    // Nếu có dữ liệu, hiển thị biểu đồ bình thường
                    new Chart(ctx, {
                        type: "pie",
                        data: {
                            labels: ["Pending", "In Progress", "Closed", "Cancelled"],
                            datasets: [{
                                    data: dataValues, // Dữ liệu thực tế
                                    backgroundColor: [
                                        window.theme.secondary,
                                        window.theme.primary,
                                        window.theme.success,
                                        window.theme.danger
                                    ],
                                    borderWidth: 5,
                                    borderColor: window.theme.white
                                }]
                        },
                        options: {
                            responsive: !window.MSInputMethodContext,
                            maintainAspectRatio: false,
                            legend: {
                                display: false
                            },
                            cutoutPercentage: 50
                        }
                    });
                }
            });


            document.addEventListener("DOMContentLoaded", function () {
                // Pie chart
                new Chart(document.getElementById("chartjs-dashboard-pie2"), {
                    type: "pie",
                    data: {
                        labels: ["Chrome", "Firefox", "IE", "Other"],
                        datasets: [{
                                data: [4306, 3801, 1689, 3251],
                                backgroundColor: [
                                    window.theme.primary,
                                    window.theme.warning,
                                    window.theme.danger,
                                    "#E8EAED"
                                ],
                                borderWidth: 5,
                                borderColor: window.theme.white
                            }]
                    },
                    options: {
                        responsive: !window.MSInputMethodContext,
                        maintainAspectRatio: false,
                        legend: {
                            display: false
                        },
                        cutoutPercentage: 70
                    }
                });
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Bar chart
                new Chart(document.getElementById("chartjs-dashboard-bar"), {
                    type: "bar",
                    data: {
                        labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                        datasets: [{
                                label: "This year",
                                backgroundColor: window.theme.primary,
                                borderColor: window.theme.primary,
                                hoverBackgroundColor: window.theme.primary,
                                hoverBorderColor: window.theme.primary,
                                data: [54, 67, 41, 55, 62, 45, 55, 73, 60, 76, 48, 79],
                                barPercentage: .75,
                                categoryPercentage: .5
                            }]
                    },
                    options: {
                        maintainAspectRatio: false,
                        legend: {
                            display: false
                        },
                        scales: {
                            yAxes: [{
                                    gridLines: {
                                        display: false
                                    },
                                    stacked: false,
                                    ticks: {
                                        stepSize: 20
                                    }
                                }],
                            xAxes: [{
                                    stacked: false,
                                    gridLines: {
                                        color: "transparent"
                                    }
                                }]
                        }
                    }
                });
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var markers = [{
                        coords: [31.230391, 121.473701],
                        name: "Shanghai"
                    },
                    {
                        coords: [28.704060, 77.102493],
                        name: "Delhi"
                    },
                    {
                        coords: [6.524379, 3.379206],
                        name: "Lagos"
                    },
                    {
                        coords: [35.689487, 139.691711],
                        name: "Tokyo"
                    },
                    {
                        coords: [23.129110, 113.264381],
                        name: "Guangzhou"
                    },
                    {
                        coords: [40.7127837, -74.0059413],
                        name: "New York"
                    },
                    {
                        coords: [34.052235, -118.243683],
                        name: "Los Angeles"
                    },
                    {
                        coords: [41.878113, -87.629799],
                        name: "Chicago"
                    },
                    {
                        coords: [51.507351, -0.127758],
                        name: "London"
                    },
                    {
                        coords: [40.416775, -3.703790],
                        name: "Madrid "
                    }
                ];
                var map = new jsVectorMap({
                    map: "world",
                    selector: "#world_map",
                    zoomButtons: true,
                    markers: markers,
                    markerStyle: {
                        initial: {
                            r: 9,
                            stroke: window.theme.white,
                            strokeWidth: 7,
                            stokeOpacity: .4,
                            fill: window.theme.primary
                        },
                        hover: {
                            fill: window.theme.primary,
                            stroke: window.theme.primary
                        }
                    },
                    regionStyle: {
                        initial: {
                            fill: window.theme["gray-200"]
                        }
                    },
                    zoomOnScroll: false
                });
                window.addEventListener("resize", () => {
                    map.updateSize();
                });
                setTimeout(function () {
                    map.updateSize();
                }, 250);
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var date = new Date(Date.now() - 5 * 24 * 60 * 60 * 1000);
                var defaultDate = date.getUTCFullYear() + "-" + (date.getUTCMonth() + 1) + "-" + date.getUTCDate();
                document.getElementById("datetimepicker-dashboard").flatpickr({
                    inline: true,
                    prevArrow: "<span class=\"fas fa-chevron-left\" title=\"Previous month\"></span>",
                    nextArrow: "<span class=\"fas fa-chevron-right\" title=\"Next month\"></span>",
                    defaultDate: defaultDate
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
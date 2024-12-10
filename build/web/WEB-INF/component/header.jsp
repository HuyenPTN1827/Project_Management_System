<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand navbar-light navbar-bg">
    <a class="sidebar-toggle js-sidebar-toggle">
        <i class="hamburger align-self-center"></i>
    </a>

    <form class="d-none d-sm-inline-block col-5" action="issue-management" method="get">
        <div class="input-group input-group-navbar">
                <!--<input type="hidden" name="userId" value="${user.id}"/>-->
            <input type="text" class="form-control" name="keywordIssue" value="${param.keywordIssue}" id="keyword-input" 
                   placeholder="Type keywords to search issues" aria-label="Search">
            <button class="btn" id="search-button" type="sumbit">
                <i class="align-middle" data-feather="search"></i>
            </button>
        </div>
    </form>

    <div class="d-flex justify-content-between align-items-center">
        <!-- Menu dropdown mini -->
        <div class="nav-item dropdown d-lg-none" hidden>
            <a class="nav-link dropdown-toggle text-dark" href="#" role="button" id="menuDropdown" data-bs-toggle="dropdown" 
               aria-haspopup="true" aria-expanded="false">
                Menu
            </a>
            <ul class="dropdown-menu" aria-labelledby="menuDropdown">
                <a class="dropdown-item <c:if test="${currentPage == 'requirement-management'}">active</c:if>"
                   href="WorkPackageController" hidden>Requirements</a>

                    <a class="dropdown-item <c:if test="${currentPage == 'issue-management'}">active</c:if>" 
                   href="<%=request.getContextPath()%>/issue-management?projectId=1">Issues</a>

                <a class="dropdown-item <c:if test="${currentPage == 'defect-management'}">active</c:if>" 
                   href="#" hidden>Defects</a>

                    <a class="dropdown-item <c:if test="${currentPage == 'timesheet-management'}">active</c:if>"
                       href="#" hidden>Timesheets</a>

                </ul>
            </div>

            <!-- Large menu -->
            <!--<ul class="navbar-nav d-none d-lg-flex">-->
            <ul class="navbar-nav">
                <li class="nav-item ps-4">
                    <a class="nav-link <c:if test="${currentPage == 'dashboard'}">active</c:if>"
                       href="<%=request.getContextPath()%>/member-dashboard">Dashboard</a>
                </li>  
<!--                
                <li class="nav-item px-1">
                    <a class="nav-link <c:if test="${currentPage == 'requirement-management'}">active</c:if>"
                       href="WorkPackageController" hidden>Requirements</a>
                </li>  -->

                <li class="nav-item ps-3">
                <c:if test="${user.id == null}">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout">Issues</a>
                </c:if>
                <c:if test="${user.id != null}">
                    <a class="nav-link <c:if test="${currentPage == 'issue-management'}">active</c:if>" 
                       href="<%=request.getContextPath()%>/issue-management?projectId=1">Issues</a>
                </c:if>

            </li> 
            <li class="nav-item px-1">
                <a class="nav-link <c:if test="${currentPage == 'defect-management'}">active</c:if>" 
                   href="#" hidden>Defects</a>
                </li>   

                <li class="nav-item px-1">
                    <a class="nav-link <c:if test="${currentPage == 'timesheet-management'}">active</c:if>" 
                       href="#" hidden>Timesheets</a>
                </li>
            </ul>
        </div>


        <div class="navbar-collapse collapse">
            <ul class="navbar-nav navbar-align">
<!--                <li class="nav-item dropdown">
                    <a class="nav-icon dropdown-toggle" href="#" id="alertsDropdown" data-bs-toggle="dropdown">
                        <div class="position-relative">
                            <i class="align-middle" data-feather="bell"></i>
                            <span class="indicator">4</span>
                        </div>
                    </a>
                    <div class="dropdown-menu dropdown-menu-lg dropdown-menu-end py-0" aria-labelledby="alertsDropdown">
                        <div class="dropdown-menu-header">
                            4 New Notifications
                        </div>
                        <div class="list-group">
                            <a href="#" class="list-group-item">
                                <div class="row g-0 align-items-center">
                                    <div class="col-2">
                                        <i class="text-danger" data-feather="alert-circle"></i>
                                    </div>
                                    <div class="col-10">
                                        <div class="text-dark">Update completed</div>
                                        <div class="text-muted small mt-1">Restart server 12 to complete the update.</div>
                                        <div class="text-muted small mt-1">30m ago</div>
                                    </div>
                                </div>
                            </a>
                            <a href="#" class="list-group-item">
                                <div class="row g-0 align-items-center">
                                    <div class="col-2">
                                        <i class="text-warning" data-feather="bell"></i>
                                    </div>
                                    <div class="col-10">
                                        <div class="text-dark">Lorem ipsum</div>
                                        <div class="text-muted small mt-1">Aliquam ex eros, imperdiet vulputate hendrerit et.</div>
                                        <div class="text-muted small mt-1">2h ago</div>
                                    </div>
                                </div>
                            </a>
                            <a href="#" class="list-group-item">
                                <div class="row g-0 align-items-center">
                                    <div class="col-2">
                                        <i class="text-primary" data-feather="home"></i>
                                    </div>
                                    <div class="col-10">
                                        <div class="text-dark">Login from 192.186.1.8</div>
                                        <div class="text-muted small mt-1">5h ago</div>
                                    </div>
                                </div>
                            </a>
                            <a href="#" class="list-group-item">
                                <div class="row g-0 align-items-center">
                                    <div class="col-2">
                                        <i class="text-success" data-feather="user-plus"></i>
                                    </div>
                                    <div class="col-10">
                                        <div class="text-dark">New connection</div>
                                        <div class="text-muted small mt-1">Christina accepted your request.</div>
                                        <div class="text-muted small mt-1">14h ago</div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="dropdown-menu-footer">
                            <a href="#" class="text-muted">Show all notifications</a>
                        </div>
                    </div>
                </li>-->


<!--                <li class="nav-item">
                    <a class="nav-icon js-fullscreen d-none d-lg-block" href="#">
                        <div class="position-relative">
                            <i class="align-middle" data-feather="maximize"></i>
                        </div>
                    </a>
                </li>-->
                
                <li class="nav-item dropdown">
                    <a class="nav-icon pe-md-0 dropdown-toggle" href="#" data-bs-toggle="dropdown">
                    <c:if test="${user.avatar == null}">
                        <img src="img/avatars/avatar-default.png" alt="${user.full_name}" class="avatar img-fluid rounded"/>
                    </c:if>

                    <c:if test="${user.avatar != null}">
                        <img src="img/avatars/${user.avatar}" alt="${user.full_name}" class="avatar img-fluid rounded"/>
                    </c:if>
                </a>
                <div class="dropdown-menu dropdown-menu-end">
                    <a class="dropdown-item" href="member-profile"><i class="align-middle me-1" data-feather="user"></i> Settings & Profile</a>
                    <a class="dropdown-item" href="changepassword"><i class="align-middle me-1" data-feather="pie-chart"></i> Change password</a>

                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="<%=request.getContextPath()%>/logout">Log out</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
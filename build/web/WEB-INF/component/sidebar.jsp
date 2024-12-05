<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav id="sidebar" class="sidebar js-sidebar">
    <div class="sidebar-content js-simplebar">
        <a class="sidebar-brand" href="<%=request.getContextPath()%>/member-dashboard">
            <span class="sidebar-brand-text align-middle" style="font-size: 2.5rem; letter-spacing: 0.1rem;">
                <strong>PMS</strong>
            </span>
            <svg class="sidebar-brand-icon align-middle" width="32px" height="32px" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="1.5"
                 stroke-linecap="square" stroke-linejoin="miter" color="#FFFFFF" style="margin-left: -3px">
            <path d="M12 4L20 8.00004L12 12L4 8.00004L12 4Z"></path>
            <path d="M20 12L12 16L4 12"></path>
            <path d="M20 16L12 20L4 16"></path>
            </svg>
        </a>

        <div class="sidebar-user">
            <div class="d-flex justify-content-center">
                <div class="flex-shrink-0">
                    <img src="img/avatars/avatar.jpg" class="avatar img-fluid rounded me-1" alt="${user.username}" />
                </div>
                <div class="flex-grow-1 ps-2">
                    <a class="sidebar-user-title dropdown-toggle" href="#" data-bs-toggle="dropdown">
                        <strong>${user.full_name}</strong>
                    </a>
                    <div class="dropdown-menu dropdown-menu-start">
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/member-profilecontroller">
                            <i class="align-middle me-1" data-feather="user"></i> Setting & Profile
                        </a>
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/changepasswordcontroller">
                            <i class="align-middle me-1" data-feather="lock"></i> Change password
                        </a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/logout">
                            <i class="align-middle me-1" data-feather="log-out"></i> Log out
                        </a>
                    </div>

                    <div class="sidebar-user-subtitle">${user.role_name}</div>
                </div>
            </div>
        </div>

        <ul class="sidebar-nav">
            <li class="sidebar-item <c:if test="${currentPage == 'dashboard'}">active</c:if>">
                <a class="sidebar-link" href="<%=request.getContextPath()%>/member-dashboard">
                    <i class="align-middle" data-feather="sliders"></i> <span class="align-middle">Dashboards</span>
                </a>
            </li>

            <li class="sidebar-item <c:if test="${currentPage == 'project' || activeTab == 'detail'}">active</c:if>">
                <a class="sidebar-link" href="<%= request.getContextPath() %>/projectlist">
                    <i class="align-middle" data-feather="briefcase"></i> <span class="align-middle">Project Admin</span>
                </a>
            </li>

            <li class="sidebar-item <c:if test="${activeTab == 'milestone'}">active</c:if>">
                <a class="sidebar-link" href="<%= request.getContextPath() %>/projectconfig?activeTab=milestone">
                    <i class="align-middle" data-feather="server"></i> <span class="align-middle">Milestones</span>
                </a>
            </li>

            <li class="sidebar-item <c:if test="${activeTab == 'allocation'}">active</c:if>">
                <a class="sidebar-link" href="<%= request.getContextPath() %>/projectconfig?activeTab=allocation">
                    <i class="align-middle" data-feather="users"></i> <span class="align-middle">HR Allocations</span>
                </a>
            </li>

            <!--            <li class="sidebar-item">
                            <a data-bs-target="#project" data-bs-toggle="collapse" class="sidebar-link collapsed">
                                <i class="align-middle" data-feather="briefcase"></i> <span class="align-middle">Project Admin</span>
                            </a>
                            <ul id="project" class="sidebar-dropdown list-unstyled collapse " data-bs-parent="#sidebar">
                                <li class="sidebar-item">
                                    <a class="sidebar-link" href="<%= request.getContextPath() %>/projectlist">Projects</a>
                                </li>
                                <li class="sidebar-item">
                                    <a class="sidebar-link" href="<%= request.getContextPath() %>/projectconfig?activeTab=milestone">Milestones</a>
                                </li>
                                <li class="sidebar-item">
                                    <a class="sidebar-link" href="<%= request.getContextPath() %>/projectconfig?activeTab=allocation">HR Allocations</a>
                                </li>
                            </ul>
                        </li>-->

            <li class="sidebar-item" hidden>
                <a data-bs-target="#evaluation" data-bs-toggle="collapse" class="sidebar-link collapsed">
                    <i class="align-middle" data-feather="check-circle"></i> <span class="align-middle">Evaluation</span>
                </a>
                <ul id="evaluation" class="sidebar-dropdown list-unstyled collapse " data-bs-parent="#sidebar">
                    <li class="sidebar-item"><a class="sidebar-link" href="#">Teams</a></li>
                    <li class="sidebar-item"><a class="sidebar-link" href="#">Members</a></li>
                </ul>
            </li>

            <%--<c:if test="${user.role_id == 1}">--%>
            <!--                <li class="sidebar-header">
                                System Management
                            </li>-->

            <li class="sidebar-item <c:if test="${currentPage == 'department-management' || currentPage == 'project-type-management'
                                                  || currentPage == 'user-management' || currentPage == 'setting-management'}">active</c:if>">
                      <a data-bs-target="#system" data-bs-toggle="collapse" class="sidebar-link collapsed
                      <c:if test="${currentPage == 'department-management' || currentPage == 'project-type-management'
                                    || currentPage == 'user-management' || currentPage == 'setting-management'}">active</c:if>">
                          <i class="align-middle" data-feather="settings"></i> <span class="align-middle">System Admin</span>
                      </a>

                      <ul id="system" class="sidebar-dropdown list-unstyled collapse
                      <c:if test="${currentPage == 'department-management' || currentPage == 'project-type-management'
                                    || currentPage == 'user-management' || currentPage == 'setting-management'}">show</c:if>" data-bs-parent="#sidebar">

                          <li class="sidebar-item <c:if test="${currentPage == 'department-management'}">active</c:if>">
                          <a class="sidebar-link" href="<%=request.getContextPath()%>/department-management">Departments</a>
                      </li>
                      <li class="sidebar-item <c:if test="${currentPage == 'project-type-management'}">active</c:if>">
                          <a class="sidebar-link" href="<%=request.getContextPath()%>/project-type-management">Project Types</a>
                      </li>
                      <c:if test="${user.role_id == 1}">
                          <li class="sidebar-item <c:if test="${currentPage == 'user-management'}">active</c:if>">
                              <a class="sidebar-link" href="<%=request.getContextPath()%>/user-management">Users</a>
                          </li>
                          <li class="sidebar-item <c:if test="${currentPage == 'setting-management'}">active</c:if>">
                              <a class="sidebar-link" href="<%=request.getContextPath()%>/setting-management">Settings</a>
                          </li>
                      </c:if>
                  </ul>
            </li>
            <%-- </c:if>--%>

            <!--            <li class="sidebar-item">
                            <a data-bs-target="#pages" data-bs-toggle="collapse" class="sidebar-link collapsed">
                                <i class="align-middle" data-feather="layout"></i> <span class="align-middle">Pages</span>
                            </a>
                            <ul id="pages" class="sidebar-dropdown list-unstyled collapse " data-bs-parent="#sidebar">
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-settings.html">Settings</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-projects.html">Projects <span
                                            class="sidebar-badge badge bg-primary">Pro</span></a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-clients.html">Clients <span
                                            class="sidebar-badge badge bg-primary">Pro</span></a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-pricing.html">Pricing <span
                                            class="sidebar-badge badge bg-primary">Pro</span></a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-chat.html">Chat <span
                                            class="sidebar-badge badge bg-primary">Pro</span></a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-blank.html">Blank Page</a></li>
                            </ul>
                        </li>
            
                        <li class="sidebar-item active">
                            <a class="sidebar-link" href="pages-profile.html">
                                <i class="align-middle" data-feather="user"></i> <span class="align-middle">Profile</span>
                            </a>
                        </li>
            
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="pages-invoice.html">
                                <i class="align-middle" data-feather="credit-card"></i> <span class="align-middle">Invoice</span>
                            </a>
                        </li>
            
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="pages-tasks.html">
                                <i class="align-middle" data-feather="list"></i> <span class="align-middle">Tasks</span>
                                <span class="sidebar-badge badge bg-primary">Pro</span>
                            </a>
                        </li>
            
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="calendar.html">
                                <i class="align-middle" data-feather="calendar"></i> <span class="align-middle">Calendar</span>
                                <span class="sidebar-badge badge bg-primary">Pro</span>
                            </a>
                        </li>
            
                        <li class="sidebar-item">
                            <a href="#auth" data-bs-toggle="collapse" class="sidebar-link collapsed">
                                <i class="align-middle" data-feather="users"></i> <span class="align-middle">Auth</span>
                            </a>
                            <ul id="auth" class="sidebar-dropdown list-unstyled collapse " data-bs-parent="#sidebar">
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-sign-in.html">Sign In</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-sign-up.html">Sign Up</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-reset-password.html">Reset Password <span
                                            class="sidebar-badge badge bg-primary">Pro</span></a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-404.html">404 Page <span
                                            class="sidebar-badge badge bg-primary">Pro</span></a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="pages-500.html">500 Page <span
                                            class="sidebar-badge badge bg-primary">Pro</span></a></li>
                            </ul>
                        </li>
            
                        <li class="sidebar-header">
                            Components
                        </li>
                        <li class="sidebar-item">
                            <a data-bs-target="#ui" data-bs-toggle="collapse" class="sidebar-link collapsed">
                                <i class="align-middle" data-feather="briefcase"></i> <span class="align-middle">UI Elements</span>
                            </a>
                            <ul id="ui" class="sidebar-dropdown list-unstyled collapse " data-bs-parent="#sidebar">
                                <li class="sidebar-item"><a class="sidebar-link" href="ui-alerts.html">Alerts</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="ui-buttons.html">Buttons</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="ui-cards.html">Cards</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="ui-general.html">General</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="ui-grid.html">Grid</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="ui-modals.html">Modals</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="ui-offcanvas.html">Offcanvas <span
                                            class="sidebar-badge badge bg-primary">Pro</span></a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="ui-placeholders.html">Placeholders <span
                                            class="sidebar-badge badge bg-primary">Pro</span></a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="ui-tabs.html">Tabs <span
                                            class="sidebar-badge badge bg-primary">Pro</span></a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="ui-typography.html">Typography</a></li>
                            </ul>
                        </li>
                        <li class="sidebar-item">
                            <a data-bs-target="#icons" data-bs-toggle="collapse" class="sidebar-link collapsed">
                                <i class="align-middle" data-feather="coffee"></i> <span class="align-middle">Icons</span>
                                <span class="sidebar-badge badge bg-light">1.500+</span>
                            </a>
                            <ul id="icons" class="sidebar-dropdown list-unstyled collapse " data-bs-parent="#sidebar">
                                <li class="sidebar-item"><a class="sidebar-link" href="icons-feather.html">Feather</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="icons-font-awesome.html">Font Awesome <span
                                            class="sidebar-badge badge bg-primary">Pro</span></a></li>
                            </ul>
                        </li>
                        <li class="sidebar-item">
                            <a data-bs-target="#forms" data-bs-toggle="collapse" class="sidebar-link collapsed">
                                <i class="align-middle" data-feather="check-circle"></i> <span class="align-middle">Forms</span>
                            </a>
                            <ul id="forms" class="sidebar-dropdown list-unstyled collapse " data-bs-parent="#sidebar">
                                <li class="sidebar-item"><a class="sidebar-link" href="forms-basic-inputs.html">Basic Inputs</a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="forms-layouts.html">Form Layouts <span
                                            class="sidebar-badge badge bg-primary">Pro</span></a></li>
                                <li class="sidebar-item"><a class="sidebar-link" href="forms-input-groups.html">Input Groups <span
                                            class="sidebar-badge badge bg-primary">Pro</span></a></li>
                            </ul>
                        </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link" href="tables-bootstrap.html">
                                <i class="align-middle" data-feather="list"></i> <span class="align-middle">Tables</span>
                            </a>
                        </li>-->

        </ul>

    </div>
</nav>
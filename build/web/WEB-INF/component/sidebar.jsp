<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav id="sidebar" class="sidebar js-sidebar">
    <div class="sidebar-content js-simplebar">
        <a class="sidebar-brand" href="<%=request.getContextPath()%>/member-dashboard">
            <div style="display: flex; align-items: center;">
                <img src="${pageContext.request.contextPath}/img/logo/PMSLogo-big.jpg" width="50px" style="border-radius: 50%;" alt="logo" />
                <span class="sidebar-brand-text ms-3" style="font-size: 2.5rem; letter-spacing: 0.1rem;">
                    <strong>PMS</strong>
                </span>
            </div>
        </a>

        <div class="sidebar-user">
            <div class="d-flex justify-content-center">
                <div class="flex-shrink-0">
                    <c:if test="${user.avatar == null}">
                        <img src="img/avatars/avatar-default.png" alt="${user.full_name}" class="avatar img-fluid rounded me-1"/>
                    </c:if>

                    <c:if test="${user.avatar != null}">
                        <img src="img/avatars/${user.avatar}" alt="${user.full_name}" class="avatar img-fluid rounded me-1"/>
                    </c:if>
                </div>
                <div class="flex-grow-1 ps-2">
                    <a class="sidebar-user-title dropdown-toggle" href="#" data-bs-toggle="dropdown">
                        <strong>${user.full_name}</strong>
                    </a>
                    <div class="dropdown-menu dropdown-menu-start">
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/member-profile">
                            <i class="align-middle me-1" data-feather="user"></i> Setting & Profile
                        </a>
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/changepassword">
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

        <ul class="sidebar-nav mt-4">
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
                <c:if test="${user.role_id == 5}">
                    <a class="sidebar-link" href="<%= request.getContextPath() %>/projectconfig?activeTab=milestone&action=view">
                        <i class="align-middle" data-feather="server"></i> <span class="align-middle">Milestones</span>
                    </a>
                </c:if>
                <c:if test="${user.role_id != 5}">
                    <a class="sidebar-link" href="<%= request.getContextPath() %>/projectconfig?activeTab=milestone&action=edit">
                        <i class="align-middle" data-feather="server"></i> <span class="align-middle">Milestones</span>
                    </a>
                </c:if>
            </li>

            <li class="sidebar-item <c:if test="${activeTab == 'allocation'}">active</c:if>">
                <c:if test="${user.role_id == 5}">
                    <a class="sidebar-link" href="<%= request.getContextPath() %>/projectconfig?activeTab=allocation&action=view">
                        <i class="align-middle" data-feather="users"></i> <span class="align-middle">HR Allocations</span>
                    </a>
                </c:if>
                <c:if test="${user.role_id != 5}">
                    <a class="sidebar-link" href="<%= request.getContextPath() %>/projectconfig?activeTab=allocation&action=edit">
                        <i class="align-middle" data-feather="users"></i> <span class="align-middle">HR Allocations</span>
                    </a>
                </c:if>
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
                      <c:if test="${user.role_id == 2}">
                          <li class="sidebar-item <c:if test="${currentPage == 'user-management'}">active</c:if>">
                              <a class="sidebar-link" href="<%=request.getContextPath()%>/user-management">Users</a>
                          </li>
                          <li class="sidebar-item <c:if test="${currentPage == 'setting-management'}">active</c:if>">
                              <a class="sidebar-link" href="<%=request.getContextPath()%>/setting-management">Settings</a>
                          </li>
                      </c:if>
                  </ul>
            </li>
        </ul>

    </div>
</nav>
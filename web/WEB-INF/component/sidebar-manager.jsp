<%-- 
    Document   : sidebar-member
    Created on : Nov 3, 2024, 12:22:01 AM
    Author     : Admin
--%>
<%@ page import="model.User" %>
<nav id="sidebar" class="sidebar js-sidebar">
    <div class="sidebar-content js-simplebar">
        <a class="sidebar-brand" href="index.html">
            <span class="sidebar-brand-text align-middle">
                AdminKit
                <sup><small class="badge bg-primary text-uppercase">Pro</small></sup>
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
                    <img src="img/avatars/avatar.jpg" class="avatar img-fluid rounded me-1" alt="Charles Hall" />
                </div>
                <div class="flex-grow-1 ps-2">
                    <a class="sidebar-user-title dropdown-toggle" href="#" data-bs-toggle="dropdown">
                        Hello, <strong><%= ((User) session.getAttribute("user")).getFull_name() %></strong>!
                    </a>
                    <div class="dropdown-menu dropdown-menu-start">
                        <a class="dropdown-item" href="pages-profile.html"><i class="align-middle me-1" data-feather="user"></i> Profile</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Log out</a>
                    </div>
                    
                </div>
            </div>
        </div>

        <ul class="sidebar-nav">
            <li class="sidebar-header">
                Project Management
            </li>
            <li class="sidebar-item">
                <a class="sidebar-link" href="<%= request.getContextPath() %>/projectlist">
                    <i class="align-middle" data-feather="list"></i> <span class="align-middle">Project List</span>
                </a>
            </li>
            <li class="sidebar-item">
                <a class="sidebar-link" href="allocation-list.html">
                    <i class="align-middle" data-feather="list"></i> <span class="align-middle">Allocation List</span>
                </a>
            </li>
            <li class="sidebar-item">
                <a class="sidebar-link" href="new-allocation.html">
                    <i class="align-middle" data-feather="plus-circle"></i> <span class="align-middle">New Allocation</span>
                </a>
            </li>
            <li class="sidebar-item">
                <a class="sidebar-link" href="allocation-details.html">
                    <i class="align-middle" data-feather="info"></i> <span class="align-middle">Allocation Details</span>
                </a>
            </li>
            <li class="sidebar-item">
                <a class="sidebar-link" href="scope-list.html">
                    <i class="align-middle" data-feather="file-text"></i> <span class="align-middle">Scope List</span>
                </a>
            </li>
            <li class="sidebar-item">
                <a class="sidebar-link" href="scope-details.html">
                    <i class="align-middle" data-feather="file"></i> <span class="align-middle">Scope Details</span>
                </a>
            </li>
            <li class="sidebar-header">
                Evaluations
            </li>
            <li class="sidebar-item">
                <a class="sidebar-link" href="team-evaluations.html">
                    <i class="align-middle" data-feather="users"></i> <span class="align-middle">Team Evaluations</span>
                </a>
            </li>
            <li class="sidebar-item">
                <a class="sidebar-link" href="member-evaluations.html">
                    <i class="align-middle" data-feather="user-check"></i> <span class="align-middle">Member Evaluations</span>
                </a>
            </li>
        </ul>
    </div>
</nav>

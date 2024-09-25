<%-- 
    Document   : header-guest
    Created on : Sep 12, 2024, 12:29:26 AM
    Author     : kelma
--%>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="#" class="navbar-brand">Todo App</a>
        </div>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li><a href="<%=request.getContextPath()%>" class="nav-link">Login</a></li>
            <li><a href="<%=request.getContextPath()%>/register-form" class="nav-link">Sign up</a></li>
        </ul>
    </nav>
</header>

<%-- 
    Document   : header-admin
    Created on : Sep 15, 2024, 6:42:55 PM
    Author     : kelma
--%>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="#" class="navbar-brand">Todo App</a>
        </div>
        
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/todo-list"
                   class="nav-link">Todos</a></li>
        </ul>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/user-management"
                   class="nav-link">Users</a></li>
        </ul>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li><a href="<%=request.getContextPath()%>/logout"
                   class="nav-link">Logout</a></li>
        </ul>
    </nav>
</header>

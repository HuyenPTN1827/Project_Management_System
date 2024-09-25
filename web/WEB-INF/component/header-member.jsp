<%-- 
    Document   : header-member
    Created on : Sep 16, 2024, 10:54:14 PM
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

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li><a href="<%=request.getContextPath()%>/logout"
                   class="nav-link">Logout</a></li>
        </ul>
    </nav>
</header>

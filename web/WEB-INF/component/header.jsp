<nav class="navbar navbar-expand navbar-light navbar-bg">
    <a class="sidebar-toggle js-sidebar-toggle">
        <i class="hamburger align-self-center"></i>
    </a>

    <form class="d-none d-sm-inline-block">
        <div class="input-group input-group-navbar">
            <input type="text" class="form-control" placeholder="Search?" aria-label="Search">
            <button class="btn" type="button">
                <i class="align-middle" data-feather="search"></i>
            </button>
        </div>
    </form>

    <ul class="navbar-nav d-none d-lg-flex">
        <li class="nav-item px-2 dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="megaDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true"
               aria-expanded="false">
                Mega Menu
            </a>
            <div class="dropdown-menu dropdown-menu-start dropdown-mega" aria-labelledby="megaDropdown">
                <div class="d-md-flex align-items-start justify-content-start">
                    <div class="dropdown-mega-list">
                        <div class="dropdown-header">UI Elements</div>
                        <a class="dropdown-item" href="#">Alerts</a>
                        <a class="dropdown-item" href="#">Buttons</a>
                        <a class="dropdown-item" href="#">Cards</a>
                        <a class="dropdown-item" href="#">Carousel</a>
                        <a class="dropdown-item" href="#">General</a>
                        <a class="dropdown-item" href="#">Grid</a>
                        <a class="dropdown-item" href="#">Modals</a>
                        <a class="dropdown-item" href="#">Tabs</a>
                        <a class="dropdown-item" href="#">Typography</a>
                    </div>
                    <div class="dropdown-mega-list">
                        <div class="dropdown-header">Forms</div>
                        <a class="dropdown-item" href="#">Layouts</a>
                        <a class="dropdown-item" href="#">Basic Inputs</a>
                        <a class="dropdown-item" href="#">Input Groups</a>
                        <a class="dropdown-item" href="#">Advanced Inputs</a>
                        <a class="dropdown-item" href="#">Editors</a>
                        <a class="dropdown-item" href="#">Validation</a>
                        <a class="dropdown-item" href="#">Wizard</a>
                    </div>
                    <div class="dropdown-mega-list">
                        <div class="dropdown-header">Tables</div>
                        <a class="dropdown-item" href="#">Basic Tables</a>
                        <a class="dropdown-item" href="#">Responsive Table</a>
                        <a class="dropdown-item" href="#">Table with Buttons</a>
                        <a class="dropdown-item" href="#">Column Search</a>
                        <a class="dropdown-item" href="#">Muulti Selection</a>
                        <a class="dropdown-item" href="#">Ajax Sourced Data</a>
                    </div>
                </div>
            </div>
        </li>

        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="resourcesDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true"
               aria-expanded="false">
                Resources
            </a>
            <div class="dropdown-menu" aria-labelledby="resourcesDropdown">
                <a class="dropdown-item" href="https://adminkit.io/" target="_blank"><i class="align-middle me-1" data-feather="home"></i>
                    Homepage</a>
                <a class="dropdown-item" href="https://adminkit.io/docs/" target="_blank"><i class="align-middle me-1" data-feather="book-open"></i>
                    Documentation</a>
                <a class="dropdown-item" href="https://adminkit.io/docs/getting-started/changelog/" target="_blank"><i class="align-middle me-1"
                                                                                                                       data-feather="edit"></i> Changelog</a>
            </div>
        </li>
    </ul>

    <div class="navbar-collapse collapse">
        <ul class="navbar-nav navbar-align">
            <li class="nav-item dropdown">
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
            </li>
            
            
            <li class="nav-item">
                <a class="nav-icon js-fullscreen d-none d-lg-block" href="#">
                    <div class="position-relative">
                        <i class="align-middle" data-feather="maximize"></i>
                    </div>
                </a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-icon pe-md-0 dropdown-toggle" href="#" data-bs-toggle="dropdown">
                    <img src="img/avatars/avatar.jpg" class="avatar img-fluid rounded" alt="Charles Hall" />
                </a>
                <div class="dropdown-menu dropdown-menu-end">
                    <a class="dropdown-item" href="member-profilecontroller"><i class="align-middle me-1" data-feather="user"></i>Settings & Profile</a>
                    <a class="dropdown-item" href="changepasswordcontroller"><i class="align-middle me-1" data-feather="pie-chart"></i> Change password</a>
                    
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Log out</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
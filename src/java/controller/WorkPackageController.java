package controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import model.WorkPackage;
import service.ProjectService;
import service.ProjectTypeService;
import service.UserService;
import service.WorkPackageService;

public class WorkPackageController extends HttpServlet {

    private final WorkPackageService workPackageService = new WorkPackageService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");

        try {
            switch (action) {
                case "list":
                    listWorkPackages(request, response);
                    break;
                case "detail":
                    getWorkPackageDetail(request, response);
                    break;
                case "add":
                    showAddForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteWorkPackage(request, response);
                    break;
                case "changeStatus":
                    changeStatus(request, response);
                    break;
                default:
                    listWorkPackages(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listWorkPackages(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String title = request.getParameter("title");
        String status = request.getParameter("status");

        List<WorkPackage> list = workPackageService.getList(title, status);
        request.setAttribute("workPackages", list);
        request.getRequestDispatcher("/WEB-INF/admin/work-package-list.jsp").forward(request, response);
    }

    private void getWorkPackageDetail(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        WorkPackage workPackage = workPackageService.getOne(id);
        request.setAttribute("workPackage", workPackage);
        request.getRequestDispatcher("/WEB-INF/admin/work-package-detail.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProjectService pSer = new ProjectService();
        UserService userService = new UserService();
        ProjectTypeService pts = new ProjectTypeService();
        try {
            request.setAttribute("sl", pts.getAllProjectTypeSettings("", true, "Scope Status", 2));
            request.setAttribute("cl", pts.getAllProjectTypeSettings("", true, "Scope Complexity", 2));
            request.setAttribute("pl", pSer.getProjectsDropDown());
            request.setAttribute("ul", userService.getAllUsers(null, null, null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/admin/work-package-add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        WorkPackage workPackage = workPackageService.getOne(id);
        ProjectService pSer = new ProjectService();
        UserService userService = new UserService();
        ProjectTypeService pts = new ProjectTypeService();

        try {
            
            request.setAttribute("sl", pts.getAllProjectTypeSettings("", true, "Scope Status ", 2));
            request.setAttribute("cl", pts.getAllProjectTypeSettings("", true, "Scope Complexity ", 2));
            request.setAttribute("pl", pSer.getProjectsDropDown());
            request.setAttribute("ul", userService.getAllUsers(null, null, null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("workPackage", workPackage);
        request.getRequestDispatcher("/WEB-INF/admin/work-package-edit.jsp").forward(request, response);
    }

    private void changeStatus(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        int newStatus = Integer.parseInt(request.getParameter("status"));
        workPackageService.changeStatus(id, newStatus);
        response.sendRedirect("WorkPackageController?action=list");
    }

    private void deleteWorkPackage(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        workPackageService.changeStatus(id, 0); // Set status to inactive as "soft delete"
        response.sendRedirect("WorkPackageController?action=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                WorkPackage workPackage = new WorkPackage();
                workPackage.setCreatedBy(Integer.parseInt(request.getParameter("createdBy")));
                workPackage.setTitle(request.getParameter("title"));
                workPackage.setComplexity(request.getParameter("complexity"));
                workPackage.setPlannedEffort(Integer.parseInt(request.getParameter("plannedEffort")));
                workPackage.setStatus(1); // Default status: active
                workPackage.setActualEffort(request.getParameter("actualEffort") != null
                        ? Integer.parseInt(request.getParameter("actualEffort"))
                        : null);
                workPackage.setDetails(request.getParameter("details"));
                workPackage.setProjectId(request.getParameter("projectId") != null
                        ? Integer.parseInt(request.getParameter("projectId"))
                        : null);
                workPackage.setUserId(request.getParameter("userId") != null
                        ? Integer.parseInt(request.getParameter("userId"))
                        : null);
                workPackage.setStatus(Integer.parseInt(request.getParameter("status")));

                workPackageService.createWorkPackage(workPackage);
                response.sendRedirect("WorkPackageController?action=list");

            } else if ("edit".equals(action)) {
                // Handle updating an existing WorkPackage
                int id = Integer.parseInt(request.getParameter("id"));
                WorkPackage workPackage = workPackageService.getOne(id);
                if (workPackage != null) {
                    workPackage.setTitle(request.getParameter("title"));
                    workPackage.setComplexity(request.getParameter("complexity"));
                    workPackage.setPlannedEffort(Integer.parseInt(request.getParameter("plannedEffort")));
                    workPackage.setStatus(Integer.parseInt(request.getParameter("status")));
                    workPackage.setActualEffort(request.getParameter("actualEffort") != null
                            ? Integer.parseInt(request.getParameter("actualEffort"))
                            : null);
                    workPackage.setDetails(request.getParameter("details"));
                    workPackage.setProjectId(request.getParameter("projectId") != null
                            ? Integer.parseInt(request.getParameter("projectId"))
                            : null);
                    workPackage.setUserId(request.getParameter("userId") != null
                            ? Integer.parseInt(request.getParameter("userId"))
                            : null);

                    workPackageService.updateWorkPackage(workPackage);
                }
                response.sendRedirect("WorkPackageController?action=list");

            } else if ("changeStatus".equals(action)) {
                // Handle changing the status of a WorkPackage
                int id = Integer.parseInt(request.getParameter("id"));
                int newStatus = Integer.parseInt(request.getParameter("status"));
                workPackageService.changeStatus(id, newStatus);
                response.sendRedirect("WorkPackageController?action=list");

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error handling POST request", e);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Person;
import entity.Role;
import facade.PersonFacade;
import facade.RoleFacade;
import facade.RolePersonFacade;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author angel
 */
@WebServlet(name = "AdminServlet", urlPatterns = {
    "/adminPanel",
    "/changeRole"
})
public class AdminServlet extends HttpServlet {
    @EJB private RolePersonFacade rolePersonFacade;
    @EJB private RoleFacade roleFacade;
    @EJB private PersonFacade personFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "Авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        Person authPerson = (Person) session.getAttribute("authPerson");
        if(authPerson == null){
            request.setAttribute("info", "Авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        if(!rolePersonFacade.isRole("ADMINISTRATOR",authPerson)){
            request.setAttribute("info", "У вас нет прав!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        
        String path = request.getServletPath();
        switch (path) {
            case "/adminPanel":
                request.setAttribute("activeAdminPanel", true);
                Map<Person,String> mapPersons = new HashMap<>();
                List<Person> persons = personFacade.findAll();
                for(Person p : persons){
                    String topRole = rolePersonFacade.getTopRole(p);
                    mapPersons.put(p, topRole);
                }
                request.setAttribute("mapPersons", mapPersons);
                List<Role> roles = roleFacade.findAll();
                request.setAttribute("roles", roles);
                request.getRequestDispatcher("/WEB-INF/adminPanel.jsp").forward(request, response);
                break;
            case "/changeRole":
                String personId = request.getParameter("personId");
                String roleId = request.getParameter("roleId");
                Person p = personFacade.find(Long.parseLong(personId));
                Role r = roleFacade.find(Long.parseLong(roleId));
                rolePersonFacade.setRoleToUser(r,p);
                request.setAttribute("info", "Роль изменена");
                request.getRequestDispatcher("/adminPanel").forward(request, response);
                break;
        }
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

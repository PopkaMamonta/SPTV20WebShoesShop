/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Person;
import entity.Role;
import entity.RolePerson;
import entity.User;
import facade.HistoryFacade;
import facade.ModelFacade;
import facade.PersonFacade;
import facade.RoleFacade;
import facade.RolePersonFacade;
import facade.UserFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@WebServlet(name = "LoginServlet",loadOnStartup=1, urlPatterns = {
    "/showLogin",
    "/login",
    "/logout",
    
    
})
public class LoginServlet extends HttpServlet {
    @EJB private ModelFacade modelFacade;
    @EJB private PersonFacade personFacade;
    @EJB private RoleFacade roleFacade;
    @EJB private RolePersonFacade rolePersonFacade;
    @EJB private UserFacade userFacade;
    
    @Override
    public void init() throws ServletException{
        super.init();
        if(personFacade.count()!=0)return;
        User user=new User();
        user.setName("Повелитель");
        user.setSurname("Величайший");
        user.setTel("+3256843420");
        userFacade.create(user);
        Person person=new Person();
        person.setLogin("admin");
        person.setPassword("12345");
        person.setUser(user);
        personFacade.create(person);
        Role role =new Role();
        role.setRoleName("READER");
        roleFacade.create(role);
        RolePerson rolePerson=new RolePerson();
        rolePerson.setRole(role);
        rolePerson.setUser(user);
        rolePersonFacade.create(rolePerson);
        role= new Role();
        role.setRoleName("MANAGER");
        roleFacade.create(role);
        rolePerson=new RolePerson();
        rolePerson.setRole(role);
        rolePerson.setUser(user);
        rolePersonFacade.create(rolePerson);
        role= new Role();
        role.setRoleName("ADMINISTRATOR");
        roleFacade.create(role);
        rolePerson=new RolePerson();
        rolePerson.setRole(role);
        rolePerson.setUser(user);
        rolePersonFacade.create(rolePerson);
    }
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
        HttpSession session=request.getSession(false);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Model;
import entity.Person;
import entity.Role;
import entity.RolePerson;
import entity.User;
import facade.ModelFacade;
import facade.PersonFacade;
import facade.RoleFacade;
import facade.RolePersonFacade;
import facade.UserFacade;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tools.PasswordProtected;

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
        user.setAmountMoney(9999999);
        userFacade.create(user);
        Person person=new Person();
        person.setLogin("admin");
        PasswordProtected pp=new PasswordProtected();
        String salt=pp.getSalt();
        person.setSalt(salt);
        String password=pp.passwordEncript("12345", salt);
        person.setPassword(password);
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
        request.setCharacterEncoding("UTF-8");
        String path=request.getServletPath();
        switch(path){
            case "/showLogin":
                request.setAttribute("activeShowLogin", true);
                request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
            case "/login":
                String login=request.getParameter("login");
                String password=request.getParameter("password");
                Person authUser=personFacade.findByLogin(login);
                if(authUser==null){
                request.setAttribute("info", "Неверный логин или пароль");
                request.getRequestDispatcher("/showLogin").forward(request, response);
                }
                PasswordProtected pp=new PasswordProtected();
                String salt=authUser.getSalt();
                String sequrePassword=pp.passwordEncript(password, salt);
                if(!sequrePassword.equals(authUser.getPassword())){
                request.setAttribute("info", "Неверный логин или пароль");
                request.getRequestDispatcher("/showLogin").forward(request, response);
                }
                HttpSession session=request.getSession(true);
                session.setAttribute("authUser",authUser);
                String topRoleAuthUser=rolePersonFacade.getTopRole(authUser);
                session.setAttribute("topRole", topRoleAuthUser);
                request.setAttribute("info","Здраствуйте"+ authUser.getUser().getName());
                request.getRequestDispatcher("/listShoes").forward(request, response);
                break;
            case "/logout":
                session=request.getSession(false);
                if(session!=null){
                    session.invalidate();
                    session.setAttribute("info", "Вы вышли!");
                }
                request.setAttribute("activeLogout", true);
                request.getRequestDispatcher("/listShoes").forward(request, response);
                break;
            case "/listShoes":
                request.setAttribute("activeListShoes", true);
                request.getRequestDispatcher("/listShoes.jsp").forward(request, response);
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

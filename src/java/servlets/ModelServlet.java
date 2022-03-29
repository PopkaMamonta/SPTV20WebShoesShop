/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Model;
import entity.Person;
import facade.ModelFacade;
import facade.RolePersonFacade;
import java.io.IOException;
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
@WebServlet(name = "ModelServlet", urlPatterns = {
    "/addModel",
    "/createModel"
})
public class ModelServlet extends HttpServlet {
    @EJB private ModelFacade modelFacade;
    @EJB private RolePersonFacade rolePersonFacade;

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
        if(session==null){
            request.setAttribute("info", "Авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request,response);
        }
        Person authUser = (Person) session.getAttribute("authUser");
        if(authUser==null){
            request.setAttribute("info", "Авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request,response);
        }
        if(!rolePersonFacade.isRole("MANAGER",authUser)){
            request.setAttribute("info", "Авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request,response);
        }
        String path=request.getServletPath();
        switch(path){
            case "/addModel":
                request.setAttribute("activeAddModel", true);
                request.getRequestDispatcher("/WEB-INF/addModel.jsp").forward(request,response);
            case "/createModel":
                String modelName = request.getParameter("model");
                String brandModel = request.getParameter("brand");
                String modelPrice = request.getParameter("price");
                String modelQuantity = request.getParameter("quantity");
                String modelSize = request.getParameter("size");
                if("".equals(modelName) || "".equals(brandModel) || "".equals(modelPrice) || "".equals(modelQuantity) || "".equals(modelSize)){
                    request.setAttribute("model", modelName);
                    request.setAttribute("brand", brandModel);
                    request.setAttribute("price", modelPrice);
                    request.setAttribute("quantity", modelQuantity);
                    request.setAttribute("size", modelSize);
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/addModel").forward(request, response);
                    break;
                }
                Model model=new Model();
                model.setName(modelName);
                model.setBrand(brandModel);
                try {
                    model.setPrice(Integer.parseInt(modelPrice));
                    model.setQuantity(Integer.parseInt(modelQuantity));
                    model.setSize(Integer.parseInt(modelSize));
                } catch (Exception e) {
                    request.setAttribute("price", modelPrice);
                    request.setAttribute("quantity", modelQuantity);
                    request.setAttribute("size", modelSize);
                    request.setAttribute("info", "Год публикации и количество введите цифры");
                    request.getRequestDispatcher("/addBook").forward(request, response);
                    break;
                }
                modelFacade.create(model);
                request.setAttribute("info", "Обувь добавлена");
                request.getRequestDispatcher("/listShoes").forward(request, response);
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

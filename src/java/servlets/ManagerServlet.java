/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Cover;
import entity.Model;
import entity.Person;
import facade.CoverFacade;
import facade.ModelFacade;
import facade.RolePersonFacade;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import static jdk.nashorn.internal.objects.NativeError.getFileName;

/**
 *
 * @author user
 */
@WebServlet(name = "ManagerServlet", urlPatterns = {
    "/addModel",
    "/createModel",
    "/uploadCover"
})
public class ManagerServlet extends HttpServlet {
    @EJB private ModelFacade modelFacade;
    @EJB private RolePersonFacade rolePersonFacade;
    @EJB private CoverFacade coverFacade;

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
        Person authPerson = (Person) session.getAttribute("authPerson");
        if(authPerson==null){
            request.setAttribute("info", "Авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request,response);
        }
        if(!rolePersonFacade.isRole("MANAGER",authPerson)){
            request.setAttribute("info", "Авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request,response);
        }
        request.setAttribute("topRole", session.getAttribute("topRole"));
        String path=request.getServletPath();
        String uploadFolder = "F:\\UploadDir\\SPTV20WebShoesShop";
        switch(path){
            case "/addModel":
                request.setAttribute("activeAddModel", true);
                List<Cover> covers = coverFacade.findAll();
                request.setAttribute("covers", covers);
                request.getRequestDispatcher("/WEB-INF/addModel.jsp").forward(request,response);
                break;
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
                    request.setAttribute("model",modelName);
                    request.setAttribute("price", modelPrice);
                    request.setAttribute("quantity", modelQuantity);
                    request.setAttribute("size", modelSize);
                    request.setAttribute("info", "Цена, размер и количество пишите в цифрах!");
                    request.getRequestDispatcher("/addModel").forward(request, response);
                    break;
                }
                List<Part> fileParts = request.getParts().stream().filter(
                        part -> "file".equals(part.getName()))
                    .collect(Collectors.toList());
                StringBuilder sb = new StringBuilder();
                for(Part filePart : fileParts){
                   sb.append(uploadFolder+File.separator + getFileName(filePart));
                   File file = new File(sb.toString());
                   file.mkdirs();
                   try(InputStream fileContent = filePart.getInputStream()){
                       Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                   }
                }
                String description = request.getParameter("description");
                Cover cover = new Cover();
                cover.setDescription(description);
                cover.setFileName(sb.toString());
                coverFacade.create(cover);
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
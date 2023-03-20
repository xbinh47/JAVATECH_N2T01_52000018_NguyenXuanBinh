package servlet;

import com.google.gson.Gson;
import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("logged") == null){
            if(req.getSession().getAttribute("error") != null){
                req.setAttribute("error",req.getSession().getAttribute("error"));
                req.getSession().removeAttribute("error");
            }
            req.getRequestDispatcher("jsp/login.jsp").forward(req,resp);
        }else{
            resp.sendRedirect("product");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("email") == "" || req.getParameter("password") == "") {
            req.getSession().setAttribute("error","Email/Password can not be null");
            resp.sendRedirect("/login");
        }else{
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            if(UserDAO.getInstance().login(email,password)){
                req.getSession().setAttribute("logged",true);
                req.getSession().setAttribute("username",UserDAO.getInstance().getUsername(email));
                resp.sendRedirect("/product");
            }else{
                req.getSession().setAttribute("error","Invalid Email/Password");
                resp.sendRedirect("/login");
            }
        }
    }
}

package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("")
public class HomeServlet extends HttpServlet {
    private List<String> urlList;

    @Override
    public void init() throws ServletException {
        urlList = new ArrayList<>();
        urlList.add("about");
        urlList.add("help");
        urlList.add("contact");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("page") == null){
            req.getRequestDispatcher("index.jsp").forward(req,resp);
            return;
        }

        if(!urlList.contains(req.getParameter("page"))){
            req.getRequestDispatcher("index.jsp").forward(req,resp);
            return;
        }

        req.getRequestDispatcher(req.getParameter("page")+".jsp").forward(req,resp);
    }
}

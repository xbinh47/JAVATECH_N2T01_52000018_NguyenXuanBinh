package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private HashMap<String,String> users;
    @Override
    public void init() throws ServletException {
        users = new HashMap<String, String>();
        users.put("binh","123456");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello Readers</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        if(users.containsKey(req.getParameter("username"))){
            if(users.get(req.getParameter("username")).equals(req.getParameter("password"))){
                out.println("<html><body>");
                out.println("<h1>Login successfully</h1>");
                out.println("</body></html>");
                return;
            }
            out.println("<html><body>");
            out.println("<h1>Wrong password</h1>");
            out.println("</body></html>");
            return;
        }
        out.println("<html><body>");
        out.println("<h1>Wrong username</h1>");
        out.println("</body></html>");
    }
}

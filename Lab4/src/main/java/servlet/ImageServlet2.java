package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/image2")
public class ImageServlet2 extends HttpServlet {
    private final int ARBITARY_SIZE = 1048;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("img/jpg");
        resp.setHeader("Content-disposition", "attachment; filename=Bao-Gia-29-01-08-23.jpg");

        try(InputStream in = getServletContext().getResourceAsStream("/img/Bao-Gia-29-01-08-23.jpg");
            OutputStream out = resp.getOutputStream()) {

            byte[] buffer = new byte[ARBITARY_SIZE];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }
}

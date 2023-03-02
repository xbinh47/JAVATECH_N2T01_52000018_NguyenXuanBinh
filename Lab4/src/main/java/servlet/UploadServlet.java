package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        allowFiles.add("txt");
        allowFiles.add("doc");
        allowFiles.add("docx");
        allowFiles.add("img");
        allowFiles.add("pdf");
        allowFiles.add("rar");
        allowFiles.add("zip");
    }

    private List<String> allowFiles = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("upload.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean override = false;
        resp.setContentType("text/html");
        // gets absolute path of the web application
        String appPath = getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + "uploads";

        // creates the save directory if it does not exist
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        // parses the request using Apache Commons FileUpload
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            for(FileItem item : items){
                if (item.isFormField()){
                    String nameField = item.getFieldName();
                    String valueField = item.getString();
                    if("override".equals(nameField)){
                        override = Boolean.parseBoolean(valueField);
                    }
                }
            }

            for (FileItem item : items) {
                if (!item.isFormField()) {

                    if(!allowFiles.contains(getFileFormat(new File(item.getName())))){
                        resp.getWriter().print("Unsupported file extension");
                        return;
                    }
                    String fileName = new File(item.getName()).getName();
                    String filePath = savePath + File.separator + fileName;
                    File storeFile = new File(filePath);
                    if(storeFile.exists() && override  == false){
                        resp.getWriter().println("File already exists");
                        return;
                    }
                    if(storeFile.exists() && override  == true){
                        item.write(storeFile);
                        resp.getWriter().println("File has been overriden.");
                        return;
                    }

                    // saves the file to the server
                    item.write(storeFile);
                    PrintWriter out = resp.getWriter();
                    out.println(
                            "<h1>File uploaded. Click <a href=\"/uploads/"+fileName+"\">here</a> to visit site</h1>"
                    );
                }
            }
        } catch (FileUploadException e) {
            resp.getWriter().println("File upload failed: " + e.getMessage());
        } catch (Exception e) {
            resp.getWriter().println("An error occurred: " + e.getMessage());
        }
    }
    private String getFileFormat(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0) {
            return fileName.substring(dotIndex + 1);
        } else {
            return "";
        }
    }
}

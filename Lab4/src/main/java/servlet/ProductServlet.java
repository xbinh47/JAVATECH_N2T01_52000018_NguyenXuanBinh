package servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private List<Product> productList;
    private int count = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") != null){
            Product product =productList.stream().filter(i -> i.getId() == Integer.parseInt(req.getParameter("id"))).findFirst().orElse(null);
            if(product != null){
                Map<String,Object> data = new HashMap<>();
                Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues()
                        .setLenient()
                        .create();
                data.put("code",0);
                data.put("message","Sản phẩm");
                data.put("data",product);
                PrintWriter out = resp.getWriter();
                resp.setContentType("application/json");
                out.println(gson.toJson(data));
                return;
            }else{
                Map<String,Object> data = new HashMap<>();
                Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues()
                        .setLenient()
                        .create();
                data.put("code",1);
                data.put("message","ID không tồn tại");
                PrintWriter out = resp.getWriter();
                resp.setContentType("application/json");
                out.println(gson.toJson(data));
                return;
            }
        }

        Map<String,Object> data = new HashMap<>();
        Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues()
                .setLenient()
                .create();
        data.put("code",0);
        data.put("message","Danh sách sản phẩm");
        data.put("data",productList);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        out.println(gson.toJson(data));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") != null || req.getParameter("id") != null || req.getParameter("price") != null){
            if(productList.stream().filter(i->i.getId() == Integer.parseInt(req.getParameter("id"))).findFirst().orElse(null) != null){
                Map<String,Object> data = new HashMap<>();
                Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues()
                        .setLenient()
                        .create();
                data.put("code",1);
                data.put("message","ID đã tồn tại");
                PrintWriter out = resp.getWriter();
                resp.setContentType("application/json");
                out.println(gson.toJson(data));
                return;
            }
            Product product = new Product(Integer.parseInt(req.getParameter("id")),req.getParameter("name"),Double.parseDouble(req.getParameter("id")));
            productList.add(product);
            Map<String,Object> data = new HashMap<>();
            Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues()
                    .setLenient()
                    .create();
            data.put("code",0);
            data.put("message","Thêm sản phẩm thành công");
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            out.println(gson.toJson(data));
        }else{
            Map<String,Object> data = new HashMap<>();
            Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues()
                    .setLenient()
                    .create();
            data.put("code",1);
            data.put("message","Thiếu tham số");
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            out.println(gson.toJson(data));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") != null || req.getParameter("id") != null || req.getParameter("price") != null){
            if(productList.stream().filter(i->i.getId() == Integer.parseInt(req.getParameter("id"))).findFirst().orElse(null) == null){
                Map<String,Object> data = new HashMap<>();
                Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues()
                        .setLenient()
                        .create();
                data.put("code",1);
                data.put("message","ID không tồn tại");
                PrintWriter out = resp.getWriter();
                resp.setContentType("application/json");
                out.println(gson.toJson(data));
                return;
            }
            productList.stream().filter(i->i.getId() == Integer.parseInt(req.getParameter("id"))).forEach(e->{
                e.setName(req.getParameter("name"));
                e.setPrice(Double.parseDouble(req.getParameter("price")));
            });
            Map<String,Object> data = new HashMap<>();
            Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues()
                    .setLenient()
                    .create();
            data.put("code",0);
            data.put("message","Cập nhật sản phẩn thành công");
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            out.println(gson.toJson(data));
        }else{
            Map<String,Object> data = new HashMap<>();
            Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues()
                    .setLenient()
                    .create();
            data.put("code",1);
            data.put("message","Thiếu tham số");
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            out.println(gson.toJson(data));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") != null){
            if(productList.stream().filter(i->i.getId() == Integer.parseInt(req.getParameter("id"))).findFirst().orElse(null) == null){
                Map<String,Object> data = new HashMap<>();
                Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues()
                        .setLenient()
                        .create();
                data.put("code",1);
                data.put("message","ID không tồn tại");
                PrintWriter out = resp.getWriter();
                resp.setContentType("application/json");
                out.println(gson.toJson(data));
                return;
            }
            Predicate<Product> predicate = product -> product.getId() == Integer.parseInt(req.getParameter("id"));
            productList.removeIf(predicate);
            Map<String,Object> data = new HashMap<>();
            Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues()
                    .setLenient()
                    .create();
            data.put("code",0);
            data.put("message","Xoá sản phẩn thành công");
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            out.println(gson.toJson(data));
        }else{
            Map<String,Object> data = new HashMap<>();
            Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues()
                    .setLenient()
                    .create();
            data.put("code",1);
            data.put("message","Thiếu tham số");
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            out.println(gson.toJson(data));
        }
    }

    @Override
    public void init() throws ServletException {
        productList = new ArrayList<>();
        Product product1 = new Product(1, "Product 1", 10.0);
        Product product2 = new Product(2, "Product 2", 20.0);
        Product product3 = new Product(3, "Product 3", 30.0);

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        count = 3;
    }
}

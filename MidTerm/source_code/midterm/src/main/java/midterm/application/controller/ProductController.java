package midterm.application.controller;

import jakarta.servlet.http.HttpServletRequest;
import midterm.application.entity.OrderHistory;
import midterm.application.entity.Product;
import midterm.application.service.OrderService;
import midterm.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @GetMapping(value = "/",produces = "text/html;charset=UTF-8")
    public String renderMain(Model model){
        Long account_id = null;
        if(request.getSession().getAttribute("account_id") != null){
            account_id = Long.parseLong(request.getSession().getAttribute("account_id").toString());
            model.addAttribute("account_id",account_id);
            List<OrderHistory> orderHistories = orderService.getOrderHistory(account_id,0);
            if(orderHistories != null && orderHistories.size() > 0){
                model.addAttribute("order_id",orderHistories.get(0));
            }
        }
        model.addAttribute("products",productService.getAllProducts());
        model.addAttribute("categories",productService.getAllCategories());
        model.addAttribute("count",orderService.getNumberOfOrderDetail(account_id));
        return "index";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id, Model model){
        try{
            Long account_id = null;
            if(request.getSession().getAttribute("account_id") != null){
                account_id = Long.parseLong(request.getSession().getAttribute("account_id").toString());
                model.addAttribute("account_id",account_id);
                List<OrderHistory> orderHistories = orderService.getOrderHistory(account_id,0);
                if(orderHistories != null && orderHistories.size() > 0){
                    model.addAttribute("order_id",orderHistories.get(0));
                }
            }
            Product product = productService.getProduct(id);
            model.addAttribute("product",product);
            model.addAttribute("count",orderService.getNumberOfOrderDetail(account_id));
            return "productDetail";
        }catch (Exception e){
            return "redirect:/error";
        }

    }

    @GetMapping("/api/getProductsByName")
    @ResponseBody
    public HashMap<String, Object> getProductsByName(@RequestParam HashMap<String,String> params) throws IOException {
        String name = params.get("name");
        HashMap<String,Object> result = new HashMap<>();
        if(name == null || name.equals("")){
            result.put("status",400);
            result.put("data",null);
            return result;
        }
        List<Product> products = productService.getProductsByName(name);
        result.put("status",200);
        result.put("data",products);
        return result;
    }

    @GetMapping("/api/getProducts")
    @ResponseBody
    public HashMap<String,Object> getProducts(@RequestParam HashMap<String,String> params) throws IOException {
        HashMap<String,Object> result = new HashMap<>();
        result.put("status",200);
        result.put("data",productService.filterProducts(params));
        return result;
    }
}

package midterm.application.controller;

import jakarta.servlet.http.HttpServletRequest;
import midterm.application.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest request;
    @GetMapping("cart")
    public String cart(Model model){
        Long account_id = null;
        if(request.getSession().getAttribute("account_id") != null){
            account_id = Long.parseLong(request.getSession().getAttribute("account_id").toString());
            model.addAttribute("account_id",account_id);
            if(orderService.getOrderHistory(account_id,0) != null){
                model.addAttribute("orderHistory",orderService.getOrderHistory(account_id,0).get(0));
            }
        }
        return "cart";
    }
    @GetMapping("api/order/getOrder")
    @ResponseBody
    public HashMap<String,Object> getOrderHistory(@RequestParam HashMap<String,String> body){
        Long account_id = null;
        if(request.getSession().getAttribute("account_id") != null){
            account_id = Long.parseLong(request.getSession().getAttribute("account_id").toString());
            body.put("account_id",account_id.toString());
        }
        return orderService.getAllOrder(body);
    }
    @PostMapping("api/order/addOrder")
    @ResponseBody
    public HashMap<String,Object> addOrder(@RequestBody HashMap<String,String> body){
        Long account_id = null;
        if(request.getSession().getAttribute("account_id") != null){
            account_id = Long.parseLong(request.getSession().getAttribute("account_id").toString());
            body.put("account_id",account_id.toString());
        }
        return orderService.addOrder(body);
    }
    @PostMapping("api/order/confirmOrder")
    @ResponseBody
    public HashMap<String,Object> confirmOrder(@RequestBody HashMap<String,String> body){
        Long account_id = null;
        if(request.getSession().getAttribute("account_id") != null){
            account_id = Long.parseLong(request.getSession().getAttribute("account_id").toString());
            body.put("account_id",account_id.toString());
        }
        return orderService.confirmOrder(body);
    }

    @PostMapping("api/order/deleteOrderDetail")
    @ResponseBody
    public HashMap<String,Object> deleteOrderDetail(@RequestBody HashMap<String,String> body){
        Long account_id = null;
        if(request.getSession().getAttribute("account_id") != null){
            account_id = Long.parseLong(request.getSession().getAttribute("account_id").toString());
            body.put("account_id",account_id.toString());
        }
        return orderService.deleteOrderDetail(body);
    }

    @GetMapping("/history")
    public String getOrderHistories(Model model){
        Long account_id = null;
        if(request.getSession().getAttribute("account_id") != null){
            account_id = Long.parseLong(request.getSession().getAttribute("account_id").toString());
            model.addAttribute("account_id",account_id);
        }
        model.addAttribute("orderHistories",orderService.getOrderHistory(account_id,1));
        return "history";
    }

    @GetMapping("/api/order/getOrderDetails")
    @ResponseBody
    public HashMap<String,Object> getOrderHistoryDetail(@RequestParam HashMap<String,String> body){
        Long account_id = null;
        if(request.getSession().getAttribute("account_id") != null){
            account_id = Long.parseLong(request.getSession().getAttribute("account_id").toString());
            body.put("account_id",account_id.toString());
        }
        return orderService.getOrderDetails(body);
    }

}

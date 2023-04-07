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
        model.addAttribute("account_id",1L);
        if(orderService.getOrderHistory(1L,0) != null){
            model.addAttribute("orderHistory",orderService.getOrderHistory(1L,0).get(0));
        }
        return "cart";
    }
    @GetMapping("api/order/getOrder")
    @ResponseBody
    public HashMap<String,Object> getOrderHistory(@RequestParam HashMap<String,String> body){
        return orderService.getAllOrder(body);
    }
    @PostMapping("api/order/addOrder")
    @ResponseBody
    public HashMap<String,Object> addOrder(@RequestBody HashMap<String,String> body){
        return orderService.addOrder(body);
    }
    @PostMapping("api/order/confirmOrder")
    @ResponseBody
    public HashMap<String,Object> confirmOrder(@RequestBody HashMap<String,String> body){
        return orderService.confirmOrder(body);
    }

    @PostMapping("api/order/deleteOrderDetail")
    @ResponseBody
    public HashMap<String,Object> deleteOrderDetail(@RequestBody HashMap<String,String> body){
        return orderService.deleteOrderDetail(body);
    }

    @GetMapping("/history")
    public String getOrderHistories(Model model){
        model.addAttribute("account_id",1L);
        model.addAttribute("orderHistories",orderService.getOrderHistory(1L,1));
        return "history";
    }

    @GetMapping("/api/order/getOrderDetails")
    @ResponseBody
    public HashMap<String,Object> getOrderHistoryDetail(@RequestParam HashMap<String,String> body){
        return orderService.getOrderDetails(body);
    }

}

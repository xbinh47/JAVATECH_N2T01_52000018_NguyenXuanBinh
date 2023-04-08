package midterm.application.serviceimpl;

import midterm.application.entity.OrderDetail;
import midterm.application.entity.OrderHistory;
import midterm.application.entity.Account;
import midterm.application.repository.OrderDetailRepository;
import midterm.application.repository.OrderHistoryRepository;
import midterm.application.repository.ProductRepository;
import midterm.application.repository.AccountRepository;
import midterm.application.service.AccountService;
import midterm.application.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<OrderHistory> getOrderHistory(Long account_id, Integer status) {
        if(orderHistoryRepository.findAllByAccountAndStatusOrderByCreatedAtDesc(accountRepository.findById(account_id).get(),status).isEmpty())
            return null;
        else
            return orderHistoryRepository.findAllByAccountAndStatusOrderByCreatedAtDesc(accountRepository.findById(account_id).get(),status);
    }

    @Override
    public HashMap<String, Object> getAllOrder(HashMap<String, String> body) {
        HashMap<String,Object> result = new HashMap<>();
        if(body.get("account_id") == null){
            result.put("status",400);
            result.put("message","account_id is required");
            return result;
        }
        if(body.get("status") == null){
            result.put("status",400);
            result.put("message","status is required");
            return result;
        }
        Long account_id = Long.valueOf(body.get("account_id"));
        Integer status = Integer.valueOf(body.get("status"));
        Account account = accountRepository.findById(account_id).get();
        if(status == 0){
            List<OrderHistory> orderHistories = orderHistoryRepository.findAllByAccountAndStatusOrderByCreatedAtDesc(account,0);
            result.put("status",200);
            result.put("data",orderHistories);
            return result;
        }else{
            List<OrderHistory> orderHistories = orderHistoryRepository.findAllByAccountAndStatusOrderByCreatedAtDesc(account,1);
            result.put("status",200);
            result.put("data",orderHistories);
            return result;
        }
    }

    @Override
    public HashMap<String, Object> addOrder(HashMap<String, String> body) {
        Integer quantity = 1;
        if(body.get("account_id") == null){
            HashMap<String,Object> result = new HashMap<>();
            result.put("status",400);
            result.put("message","account_id is required");
            return result;
        }
        Long account_id = Long.valueOf(body.get("account_id"));
        List<OrderHistory> orderHistories = getOrderHistory(account_id,0);
        if(orderHistories == null){
            return addNewOrder(body);
        }else{
            body.put("order_id",orderHistories.get(0).getId().toString());
            return addOldOrder(body);
        }
    }


    @Override
    public HashMap<String,Object> addNewOrder(HashMap<String, String> body) {
        HashMap<String,Object> result = new HashMap<>();
        if(body.get("account_id") == null){
            result.put("status",400);
            result.put("message","account_id is required");
            return result;
        }
        if(body.get("product_id") == null){
            result.put("status",400);
            result.put("message","product_id is required");
            return result;
        }
        if(body.get("quantity") == null){
            result.put("status",400);
            result.put("message","quantity is required");
            return result;
        }
        Long account_id = Long.valueOf(body.get("account_id"));
        Long product_id = Long.valueOf(body.get("product_id"));
        Integer quantity = Integer.valueOf(body.get("quantity"));
        Account account = accountRepository.findAccountById(account_id);
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setAccount(account);
        orderHistory.setTotalPrice(0);
        orderHistory.setStatus(0);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderHistory(orderHistory);
        orderDetail.setProduct(productRepository.findProductById(product_id));
        orderDetail.setQuantity(quantity);

        orderHistory.setTotalPrice(orderHistory.getTotalPrice() + productRepository.findProductById(product_id) .getPrice() * quantity);
        orderHistoryRepository.save(orderHistory);
        orderDetailRepository.save(orderDetail);
        result.put("data","Order created successfully");
        result.put("status",200);
        return result;
    }

    @Override
    public HashMap<String, Object> updateOldOrder(HashMap<String, String> body) {
        HashMap<String, Object> result = new HashMap<>();
        if(body.get("product_id") == null){
            result.put("status",400);
            result.put("message","product_id is required");
            return result;
        }
        if(body.get("quantity") == null){
            result.put("status",400);
            result.put("message","quantity is required");
            return result;
        }

        Long product_id = Long.valueOf(body.get("product_id"));
        Integer quantity = Integer.valueOf(body.get("quantity"));
        OrderHistory orderHistory = orderHistoryRepository.findAllByAccountAndStatusOrderByCreatedAtDesc(accountRepository.findAccountById(Long.valueOf(body.get("account_id"))),0).get(0);
        OrderDetail orderDetail = orderDetailRepository.findOrderDetailByOrderHistoryAndProduct(orderHistory,productRepository.findProductById(product_id));
        orderDetail.setQuantity(quantity+orderDetail.getQuantity());
        orderDetailRepository.save(orderDetail);
        result.put("data","Order updated successfully");
        result.put("status",200);
        return result;
    }

    @Override
    public HashMap<String, Object> addOldOrder(HashMap<String, String> body){
        HashMap<String, Object> result = new HashMap<>();
        if(body.get("order_id") == null){
            result.put("status",400);
            result.put("message","order_id is required");
            return result;
        }
        if(body.get("product_id") == null){
            result.put("status",400);
            result.put("message","product_id is required");
            return result;
        }
        if(body.get("quantity") == null){
            result.put("status",400);
            result.put("message","quantity is required");
            return result;
        }
        Long order_id = Long.valueOf(body.get("order_id"));
        Long product_id = Long.valueOf(body.get("product_id"));
        Integer quantity = Integer.valueOf(body.get("quantity"));
        List<OrderHistory> orderHistories = orderHistoryRepository.findAllByAccountAndStatusOrderByCreatedAtDesc(accountRepository.findAccountById(Long.valueOf(body.get("account_id"))),0);
        OrderHistory orderHistory = orderHistories.get(0);
        if(orderHistory.getOrderDetails().contains(orderDetailRepository.findOrderDetailByOrderHistoryAndProduct(orderHistory,productRepository.findProductById(product_id)))){
            return updateOldOrder(body);
        }

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderHistory(orderHistory);
        orderDetail.setProduct(productRepository.findProductById(product_id));
        orderDetail.setQuantity(quantity);
        orderHistory.setTotalPrice(orderHistory.getTotalPrice() + productRepository.findProductById(product_id) .getPrice() * quantity);
        orderHistoryRepository.save(orderHistory);
        orderDetailRepository.save(orderDetail);

        result.put("message","Order updated successfully");
        result.put("status",200);
        return result;
    }

    @Override
    public HashMap<String, Object> confirmOrder(HashMap<String, String> body) {
        HashMap<String, Object> result = new HashMap<>();
        if(body.get("order_id") == null){
            result.put("status",400);
            result.put("message","order_id is required");
            return result;
        }
        List<OrderHistory> orderHistories = orderHistoryRepository.findAllByAccountAndStatusOrderByCreatedAtDesc(accountRepository.findAccountById(Long.valueOf(body.get("account_id"))),0);
        if(orderHistories.size() == 0){
            result.put("status",400);
            result.put("message","No order to confirm");
            return result;
        }
        OrderHistory orderHistory = orderHistories.get(0);
        orderHistory.setStatus(1);
        orderHistoryRepository.save(orderHistory);
        result.put("message","Order confirmed successfully");
        result.put("status",200);
        return result;
    }

    @Override
    public HashMap<String, Object> deleteOrder(HashMap<String, String> body) {
        return null;
    }

    @Override
    public HashMap<String, Object> deleteOrderDetail(HashMap<String, String> body) {
        HashMap<String, Object> result = new HashMap<>();
        if(body.get("product_id") == null){
            result.put("status",400);
            result.put("message","product_id is required");
            return result;
        }
        if(body.get("account_id") == null){
            result.put("status",400);
            result.put("message","order_id is required");
            return result;
        }
        Long product_id = Long.valueOf(body.get("product_id"));
        Long account_id = Long.valueOf(body.get("account_id"));
        List<OrderHistory> orderHistories = orderHistoryRepository.findAllByAccountAndStatusOrderByCreatedAtDesc(accountRepository.findAccountById(account_id),0);
        OrderHistory orderHistory = orderHistories.get(0);
        OrderDetail orderDetail = orderDetailRepository.findOrderDetailByOrderHistoryAndProduct(orderHistory,productRepository.findProductById(product_id));
        orderHistory.setTotalPrice(orderHistory.getTotalPrice() - orderDetail.getProduct().getPrice() * orderDetail.getQuantity());
        orderHistoryRepository.save(orderHistory);
        orderDetailRepository.delete(orderDetail);
        if(orderHistory.getOrderDetails().size() == 0){
            orderHistoryRepository.deleteById(orderHistory.getId());
            result.put("message","Order deleted successfully");
            result.put("status",200);
            return result;
        }
        result.put("message","Order detail deleted successfully");
        result.put("status",200);
        return result;
    }

    private Integer getTotalPrice(List<OrderDetail> orderDetails){
        Integer totalPrice = 0;
        for(OrderDetail orderDetail : orderDetails){
            totalPrice += orderDetail.getProduct().getPrice() * orderDetail.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public Integer getNumberOfOrderDetail(Long account_id) {
        Account account = accountRepository.findAccountById(account_id);
        List<OrderHistory> orderHistoryList = orderHistoryRepository.findAllByAccountAndStatusOrderByCreatedAtDesc(account,0);
        if(orderHistoryList.isEmpty()){
            return 0;
        }
        List<OrderDetail> orderDetails = orderHistoryList.get(0).getOrderDetails();
        if(orderDetails.isEmpty()){
            return 0;
        }else{
            return orderDetails.size();
        }
    }

    @Override
    public HashMap<String, Object> getOrderDetails(HashMap<String, String> body) {
        HashMap<String, Object> result = new HashMap<>();
        if(body.get("order_id") == null){
            result.put("status",400);
            result.put("message","order_id is required");
            return result;
        }
        Long order_id = Long.valueOf(body.get("order_id"));
        OrderHistory orderHistory = orderHistoryRepository.findOrderHistoryById(order_id);
        if(orderHistory == null){
            result.put("status",400);
            result.put("message","Empty order");
            return result;
        }
        List<OrderDetail> orderDetails = orderHistory.getOrderDetails();
        if(orderDetails.isEmpty()){
            result.put("status",400);
            result.put("message","Empty order");
            return result;
        }
        result.put("data",orderDetails);
        result.put("status",200);
        return result;
    }
}

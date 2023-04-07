package midterm.application.service;

import midterm.application.entity.OrderHistory;

import java.util.HashMap;
import java.util.List;

public interface OrderService {
    List<OrderHistory> getOrderHistory(Long user_id, Integer status);
    HashMap<String, Object> addOrder(HashMap<String, String> body);
    HashMap<String, Object> getAllOrder(HashMap<String, String> body);
    HashMap<String, Object> addNewOrder(HashMap<String, String> body);
    HashMap<String, Object> updateOldOrder(HashMap<String, String> body);
    HashMap<String, Object> addOldOrder(HashMap<String, String> body);
    HashMap<String, Object> confirmOrder(HashMap<String, String> body);
    HashMap<String, Object> deleteOrder(HashMap<String, String> body);
    HashMap<String, Object> deleteOrderDetail(HashMap<String, String> body);
    Integer getNumberOfOrderDetail(Long order_id);
    HashMap<String, Object> getOrderDetails(HashMap<String, String> body);
}

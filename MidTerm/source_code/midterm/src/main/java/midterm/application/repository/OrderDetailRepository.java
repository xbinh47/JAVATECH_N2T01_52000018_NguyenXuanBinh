package midterm.application.repository;

import midterm.application.entity.OrderDetail;
import midterm.application.entity.OrderHistory;
import midterm.application.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail,Long>{
    OrderDetail findOrderDetailById(Long id);
    List<OrderDetail> findAllByOrderHistory(OrderHistory orderHistory);
    OrderDetail findOrderDetailByOrderHistoryAndProduct(OrderHistory orderHistory, Product product);
    @Transactional
    OrderDetail save(OrderDetail orderDetail);
    @Transactional
    void deleteById(Long id);
}

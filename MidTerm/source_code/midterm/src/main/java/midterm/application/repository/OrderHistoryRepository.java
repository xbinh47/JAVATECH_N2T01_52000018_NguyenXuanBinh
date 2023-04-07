package midterm.application.repository;

import midterm.application.entity.OrderHistory;
import midterm.application.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends CrudRepository<OrderHistory,Long>{
    List<OrderHistory> findAllByAccountAndStatusOrderByCreatedAtDesc(Account account, Integer status);
    OrderHistory findOrderHistoryById(Long id);
    @Transactional
    OrderHistory save(OrderHistory orderHistory);

}

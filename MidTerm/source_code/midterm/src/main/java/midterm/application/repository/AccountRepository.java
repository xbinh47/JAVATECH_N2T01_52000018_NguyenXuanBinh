package midterm.application.repository;

import midterm.application.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findAccountById(Long id);
    Account findAccountByUsername(String username);
}

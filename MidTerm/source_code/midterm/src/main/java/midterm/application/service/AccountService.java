package midterm.application.service;

import midterm.application.entity.Account;

public interface AccountService {
    Account findAccountById(Long id);
    Account findAccountByUsername(String username);
}

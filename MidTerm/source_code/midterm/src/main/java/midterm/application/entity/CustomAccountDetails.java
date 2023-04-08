package midterm.application.entity;


import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collections;


public class CustomAccountDetails extends User implements Serializable {
    private final Account account;

    public CustomAccountDetails(Account account) {
        super(account.getUsername(), account.getPassword(), Collections.emptyList());
        this.account = account;
    }

    public Long getId(){
        return account.getId();
    }

    public Account getAccount(){
        return account;
    }
}

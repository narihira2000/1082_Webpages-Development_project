package project.model;

import java.util.Optional;

public interface AccountDAO {
    void createAccount(Account acct);
    Optional<Account> accountBy(String name);
    Optional<Account> accountByEmail(String email);
    void activateAccount(Account acct);
	void updatePasswordSalt(String email, String password, String salt);
}

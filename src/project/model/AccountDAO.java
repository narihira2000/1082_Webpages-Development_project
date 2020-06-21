package project.model;

import java.util.Optional;

public interface AccountDAO {
    void createAccount(Account acct);
    void setAccountAvatar(String username, byte[] image);
    Optional<Account> accountBy(String name);
    Optional<Account> accountByEmail(String email);
    void activateAccount(Account acct);
	void updatePasswordSalt(String email, String password, String salt);
	void resetLoginFailTime(String username);
	void setLoginFailed(String username, int failTime);
	String getUserRole(String username);
}

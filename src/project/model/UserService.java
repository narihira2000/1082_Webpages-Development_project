package project.model;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final AccountDAO acctDAO;
    private final MessageDAO messageDAO;
    private final ReplyDAO replyDAO;

    public UserService(AccountDAO acctDAO, MessageDAO messageDAO, ReplyDAO replyDAO) {
        this.acctDAO = acctDAO;
        this.messageDAO = messageDAO;
        this.replyDAO = replyDAO;
    }

    public Optional<Account> tryCreateUser(String email, String username, String password)  {
        if(isUserExist(username) || isEmailExist(email)) {
            return Optional.empty();
        }
        return Optional.of(createUser(username, email, password));
    }
    
    public boolean isUserExist(String username) {
    	return acctDAO.accountBy(username).isPresent();
    }
    
    public boolean isEmailExist(String email) {
    	return acctDAO.accountByEmail(email).isPresent();
    }
    
    public Optional<Account> accountByEmail(String email){
    	Optional<Account> optionalAcct= acctDAO.accountByEmail(email);
    	if(optionalAcct.isPresent()) {
    		return optionalAcct;
    	}
    	return Optional.empty();
    }
    
    public void resetPassword(String email, String password) {
    	int salt = (int) (Math.random() * 100);
        int encrypt = salt + password.hashCode();
        acctDAO.updatePasswordSalt(email, String.valueOf(encrypt), String.valueOf(salt));
    }

    private Account createUser(String username, String email, String password) {
        int salt = (int) (Math.random() * 100);
        int encrypt = salt + password.hashCode();
        Account acct = new Account(username, email,String.valueOf(encrypt), String.valueOf(salt));
        acctDAO.createAccount(acct);
        return acct;
    }
    
    public Optional<Account> verify(String email, String token) {
        Optional<Account> optionalAcct= acctDAO.accountByEmail(email);
        if(optionalAcct.isPresent()) {
            Account acct = optionalAcct.get();
            if(acct.getPassword().equals(token)) {
                acctDAO.activateAccount(acct);
                return Optional.of(acct);
            }
        }
        return Optional.empty();
    }

    public boolean login(String username, String password) {
        if(username == null || username.trim().length() == 0) {
            return false;
        }
        
        Optional<Account> optionalAcct = acctDAO.accountBy(username);
        if(optionalAcct.isPresent()) {
            Account acct = optionalAcct.get();
            int encrypt = Integer.parseInt(acct.getPassword());
            int salt = Integer.parseInt(acct.getSalt());
            return password.hashCode() + salt == encrypt;
        }
        return false;
    }
    
    public List<Message> messages(String username) {
        List<Message> messages = messageDAO.messagesBy(username);
        messages.sort(Comparator.comparing(Message::getMillis).reversed());
        return messages;
    }
    
    public Message messageByID(int ID) {
        Message message = messageDAO.getMessageByID(ID);
        return message;
    }
    
    public void addMessage(String username, String title, String content) {
        messageDAO.createMessage(
                new Message(
                        username, Instant.now().toEpochMilli(), title, content, 0));
    }    
    
    public void deleteMessage(String username, String millis, int ID) {
        messageDAO.deleteMessageBy(username, millis, ID);
    }
    
    public List<Reply> reply(int id) {
        List<Reply> replies = replyDAO.ReplyUnderID(id);
        replies.sort(Comparator.comparing(Reply::getMillis));
        return replies;
    }
    
    public void addReply(String username, String content, int id) {
        replyDAO.createReply(
                new Reply(
                        username, Instant.now().toEpochMilli(), content, id));
    }
    
    public void deleteReply(String username, String millis, int id) {
        replyDAO.deleteReplyBy(username, millis, id);
    }
    
    public boolean exist(String username) {
        return acctDAO.accountBy(username).isPresent();
    }
    
    public List<Message> newestMessages(int n){
    	return messageDAO.newestMessages(n);
    }

	public Optional<String> encryptedPassword(String username, String password) {
		Optional<Account> optionalAcct = acctDAO.accountBy(username);
        if(optionalAcct.isPresent()) {
            Account acct = optionalAcct.get();
            int salt = Integer.parseInt(acct.getSalt());
            return Optional.of(String.valueOf(password.hashCode() + salt));
        }
        return Optional.empty();
	}
}


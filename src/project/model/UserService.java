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

    public void tryCreateUser(String email, String username, String password)  {
        if(!acctDAO.accountBy(username).isPresent()) {
            createUser(username, email, password);
        }
    }

    private void createUser(String username, String email, String password) {
        int salt = (int) (Math.random() * 100);
        int encrypt = salt + password.hashCode();
        acctDAO.createAccount(
                new Account(username, email, 
                        String.valueOf(encrypt), String.valueOf(salt)));
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
    
    public void deleteMessage(String username, String millis) {
        messageDAO.deleteMessageBy(username, millis);
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


package project.model;

import java.time.Instant;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final AccountDAO acctDAO;
    private final MessageDAO messageDAO;
    private final ReplyDAO replyDAO;
    private final String avatar = "ffd8ffe000104a46494600010100000100010000fffe001f436f6d70726573736564206279206a7065672d7265636f6d7072657373ffdb0084000404040404040404040406060506060807070707080c09090909090c130c0e0c0c0e0c131114100f1014111e171515171e221d1b1d222a25252a34323444445c010404040404040404040406060506060807070707080c09090909090c130c0e0c0c0e0c131114100f1014111e171515171e221d1b1d222a25252a34323444445cffc20011080040004003012200021101031101ffc4001c0001000300020300000000000000000000000708090106020405ffda0008010100000000bfcab799bf4754674185fd4535ec98c458ed6035f8e2bad02f7f4426978e424156cb8a993e6bbc398ca0da0e8193c0d679cf1d230128ec67ffc40014010100000000000000000000000000000000ffda00080102100000000000ffc40014010100000000000000000000000000000000ffda00080103100000000000ffc40037100002020102030308080700000000000002030104060507000811202131101222324161717213152442435152a216233462828492ffda0008010100013f00f27313cc28ed70062d8b8a6c6576530d3367a6aa0a3f54cc7ef30bd83c6479b65f975b6ddc9b24d475271cccfda2c1900f5f6007aa11ee8888e312dcbcef06b8abb8be517e948144ca61d275d9ee624fa81c71b05be947783477a2ea574b27d38066f5509f41a1e11613fd93d9cfb22b79666d9564775926dbfa9d9777cf5f3572730011ee018818f2f2ef915bc6f793057d664c05ed4034b787b0d57bf93d27e13313d9dd8c42e60db89966397124108bee65699fc4aae29624e3e213e5e59310b9966f0630c4a8a6a68cefadadb7d8b1addebffa6748ec39c9aea37586829411e719994088c47b66678ddcdb9db5dfc44d3d232ed1e32fd2d53f41629da4d960ae7f0acad73332ae323e5af79b1bb4dae7865ad45513301674b98b6b647e7101e9c7f947189f2bfbc193db52ece367a2d3928865bd54a1101f05f7b0b8db7c4b6c36234b4e27fc51a5235bbd02fb8fbf6d15eddc38fc80ca26163f70785354f50390d0628e2080c0a084a27db131e46b548531ee60ad4b1933329e822231d666667c2238df6df3d7775720bd569de7d6c46b37cca3404a401d01e0f7c7de33e286a17b4abb5b51d32e3ea5dae70c4d843096d59c781090f4989e313e73772744a8aa59069da6ebe21f8ee89ad667e24af438cb79ccdcad76a369681434ed000fc5e889b16a23dc6de2f5ebba9dcb1a86a56dd6ae5839639ef39635873e24445d66678d86df5d736b720a146fdf7d8c42cb6177689949022193fd42227d520e16c0680354706063042433d62627be26278e653206639b2f9ada41c8bedd65e9c1fee32127fb267b7cbae46cc9365f07bf60e49f5e995064cf7cfd899281fda11c738c974ecd325013201ad51977c9e9c76f93f87ab65e913866019ab5f24fc90511c6e0611a66e161bae61baa11057be880168faca70141ad91f21c44f19decf6e16ddea4fa19063b6a521d6577aaac9f51a1fa8583d9c0b66f70b71752451d071eb2082e92dbf69648a890fd44c28eff0080f59e304c3b4bdbfc3f42c3f4a922a9a6d7fa3f3cfd66b08a58c64fce7325c7ffc40014110100000000000000000000000000000040ffda0008010201013f0007ffc40014110100000000000000000000000000000040ffda0008010301013f0007ffd9";
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
        Account acct = new Account(username, email,String.valueOf(encrypt), String.valueOf(salt), avatar.getBytes());
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

    public boolean setUserAvatar(String username, byte[] image) {
    	if(username == null || username.trim().length() == 0) {
            return false;
        }
    	
    	Optional<Account> optionalAcct = acctDAO.accountBy(username);
        if(optionalAcct.isPresent()) {
        	acctDAO.setAccountAvatar(username, image);
        	return true;
        }
    	
        return false;
    }
    
    
    public String getUserAvatar(String username) {
    	if(username == null || username.trim().length() == 0) {
            return null;
        }
    	Optional<Account> optionalAcct = acctDAO.accountBy(username);
        if(optionalAcct.isPresent()) {
        	Account acct = optionalAcct.get();
        	//byte[] image = Base64.getEncoder().encode(acct.getAvatar());
        	String image = new String(Base64.getEncoder().encode(acct.getAvatar()));
        	return image;
        }
        
        return null;
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


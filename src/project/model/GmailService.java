package project.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.*; 

public class GmailService implements EmailService {
	private final Properties props = new Properties();
    private final String mailUser;
    private final String mailPassword;
    
    public GmailService(String mailUser, String mailPassword) {
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", 587);
        this.mailUser = mailUser;
        this.mailPassword = mailPassword;
    }

	@Override
	public void validationLink(Account acct) {
		try {
            String link = String.format(
                "http://localhost:8023/project/verify?email=%s&token=%s", 
                acct.getEmail(), acct.getPassword()
            );
            
            String anchor = String.format("<a href='%s'>here</a>", link);
            
            String html = String.format("Click %s to verify your account, or copy this link:<br><br> %s", anchor, link);

            javax.mail.Message message = createMessage(
                    mailUser, acct.getEmail(), "Validate your account", html);
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
		
	}

	private Message createMessage(String from, String to, String subject, String context) throws MessagingException, AddressException, IOException {
		Session session = Session.getDefaultInstance(props, new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailUser, mailPassword);
			}
		});
		
		Multipart multiPart = multiPart(context);
		javax.mail.Message message = new MimeMessage(session);  
        message.setFrom(new InternetAddress(from));
        message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setContent(multiPart);
        
        return message;
	}

	private Multipart multiPart(String context) throws MessagingException, UnsupportedEncodingException, IOException {
		MimeBodyPart htmlPart = new MimeBodyPart(); 
        htmlPart.setContent(context, "text/html;charset=UTF-8");
        
        Multipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(htmlPart);

        return multiPart;
	}

	@Override
	public void failedRegistration(String username, String email) {
		try {
            javax.mail.Message message = createMessage(mailUser, email, "Register Failed!", 
                    String.format("Username %s or %s has existed!", 
                            username, email));
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void passwordResetLink(Account acct) {
		try {
            String link = String.format(
                "http://localhost:8023/project/reset_password?email=%s&token=%s", 
                acct.getEmail(), acct.getPassword()
            );
            
            String anchor = String.format("<a href='%s'>here</a>", link);
            
            String html = String.format("Click %s to reset your password, or copy this link:<br><br> %s", anchor, link);

            javax.mail.Message message = createMessage(
                    mailUser, acct.getEmail(), "Reset your password", html);
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
		
	}

}

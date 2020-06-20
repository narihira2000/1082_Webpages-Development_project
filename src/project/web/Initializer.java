package project.web;

import project.model.*;
import javax.naming.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


@WebListener
public class Initializer implements ServletContextListener {
	
	private DataSource dataSource() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			return (DataSource) envContext.lookup("jdbc/data");
		} catch(NamingException e) {
			throw new RuntimeException(e);
		}
	}
	
    public void contextInitialized(ServletContextEvent sce) {
    	DataSource dataSource = dataSource();
        ServletContext context = sce.getServletContext();
        AccountDAO acctDAO = new AccountDAOJdbcImpl(dataSource);
        MessageDAO messageDAO = new MessageDAOJdbcImpl(dataSource);
        ReplyDAO replyDAO = new ReplyDAOJdbcImpl(dataSource);
        context.setAttribute("userService", new UserService(acctDAO, messageDAO, replyDAO));
        context.setAttribute("emailService", new GmailService(context.getInitParameter("MAIL_USER"),context.getInitParameter("MAIL_PASSWORD")));
    }
}

package project.web;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineUser implements HttpSessionListener {
	public static int counter;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		OnlineUser.counter++;
		System.out.println("created, "+counter);
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		OnlineUser.counter--;
		System.out.println("destroyed, "+counter);
	}
	
	public static int GetOnlineUser() {
	    return counter;
	}
}

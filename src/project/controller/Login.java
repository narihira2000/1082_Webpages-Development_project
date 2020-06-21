package project.controller;

import project.model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    urlPatterns={"/login"}, 
    initParams={
        @WebInitParam(name = "SUCCESS_PATH", value = "member"),
        @WebInitParam(name = "ERROR_PATH", value = "/jsp/login.jsp"),
        @WebInitParam(name = "LOCK_PATH", value = "/jsp/lock.jsp")
    }
)
public class Login extends HttpServlet {

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        Optional<String> optionalPasswd = userService.encryptedPassword(username,password);
        
        int failTime = userService.getLoginFailTime(username);

        try {
        	request.login(username, optionalPasswd.get());
            if(request.getSession(false) != null) {
                request.changeSessionId();
            }
            if(failTime==3) {
            	request.setAttribute("username", username);
            	request.getRequestDispatcher(getInitParameter("LOCK_PATH"))
                	.forward(request, response);
            	return;
            }
            userService.resetLoginFailTime(username);
            request.getSession().setAttribute("login", username);
            request.getSession().setAttribute("UserAvatar", userService.getUserAvatar(username));
            response.sendRedirect(getInitParameter("SUCCESS_PATH"));
        } catch(NoSuchElementException | ServletException e) {
        	List<String> errors = new ArrayList<>(); 
        	errors.add("Login Failed!");
            
            if(failTime!=99) {
            	String userRole = userService.getUserRole(username);
            	if(userRole.equals("member")) {
            		if(failTime==3) {
                    	request.setAttribute("username", username);
                    	request.getRequestDispatcher(getInitParameter("LOCK_PATH"))
                        	.forward(request, response);
                    	return;
                    }
                	else {
                		userService.loginFailed(username,failTime+1);
                		errors.add("You only have "+(3-failTime-1)+" chance(s) to login into "+username+".");
                	}
            	}
            	else if(userRole.equals("unverified")) {
            		errors.add("Please validate your account.");
            	}
            }
            request.setAttribute("errors", errors);
            List<Message> newest = userService.newestMessages(10);
        	request.setAttribute("newest", newest);
            request.getRequestDispatcher(getInitParameter("ERROR_PATH"))
                   .forward(request, response);
        }
        
    }
}

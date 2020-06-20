package project.controller;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import project.model.*;

@WebServlet("/reset_password")
public class ResetPassword extends HttpServlet {
	private final Pattern passwdRegex = Pattern.compile("^\\w{8,16}$");
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String token = request.getParameter("token");
        
        UserService userService =
                (UserService) getServletContext().getAttribute("userService");
        Optional<Account> optionalAcct = userService.accountByEmail(email);
        
        if(optionalAcct.isPresent()) {
            Account acct = optionalAcct.get();
            if(acct.getPassword().equals(token)) {
                request.setAttribute("acct", acct);
                request.getSession().setAttribute("token", token);
                request.getRequestDispatcher("/jsp/reset_password.jsp")
                       .forward(request, response);
                return;
            }
        }
        
        response.sendRedirect("/project");
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        String storedToken = (String) request.getSession().getAttribute("token");
        if(storedToken == null || !storedToken.equals(token)) {
            response.sendRedirect("/project");
            return;
        }
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
            
        UserService userService = (UserService) getServletContext().getAttribute("userService");

        if (!validatePassword(password, password2)) {
            Optional<Account> optionalAcct = userService.accountByEmail(email);
            request.setAttribute("errors", Arrays.asList("請確認密碼符合格式並再度確認密碼"));
            request.setAttribute("acct", optionalAcct.get());

            request.getRequestDispatcher("/jsp/reset_password.jsp")
                   .forward(request, response);
        } else {
            userService.resetPassword(email, password);
            request.getRequestDispatcher("/jsp/reset_success.jsp")
                   .forward(request, response);
        }
    }

    private boolean validatePassword(String password, String password2) {
    	return password != null && 
    		passwdRegex.matcher(password).find() && 
            password.equals(password2);
    }
}

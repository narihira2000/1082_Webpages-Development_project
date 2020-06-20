package project.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import project.model.*;

@WebServlet("/forgot")
public class Forgot extends HttpServlet {
	protected void doPost(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
        String email = request.getParameter("email");
        
        UserService userService =
                (UserService) getServletContext().getAttribute("userService");
        Optional<Account> optionalAcct = userService.accountByEmail(email);
        
        if(optionalAcct.isPresent()) {
            EmailService emailService = 
                    (EmailService) getServletContext().getAttribute("emailService");
            emailService.passwordResetLink(optionalAcct.get());
            request.setAttribute("isExist", true);
        }
        else {
        	request.setAttribute("isExist", false);
        }
        
        request.setAttribute("email", email);
        request.getRequestDispatcher("/jsp/sent_reset_mail.jsp")
               .forward(request, response);
    }
}

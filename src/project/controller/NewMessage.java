package project.controller;

import java.io.IOException;
import project.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    urlPatterns={"/new_message"}, 
    initParams={
        @WebInitParam(name = "MEMBER_PATH", value = "member")
    }
)
@ServletSecurity(
		@HttpConstraint(rolesAllowed = {"member"})
)
public class NewMessage extends HttpServlet {

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException {
            
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        
        if(content == null || content.length() == 0 || title == null || title.length() == 0 ) {
            response.sendRedirect(getInitParameter("MEMBER_PATH"));
            return;
        }        
       
        if(title.length() <= 140) {
            UserService userService = (UserService) getServletContext().getAttribute("userService");
            userService.addMessage(getUsername(request), title, content);
            response.sendRedirect(getInitParameter("MEMBER_PATH"));
        }
        else {
            request.getRequestDispatcher(getInitParameter("MEMBER_PATH")).forward(request, response);
        }
    }
    
    private String getUsername(HttpServletRequest request) {
        return  (String) request.getSession().getAttribute("login");
    }
    
}

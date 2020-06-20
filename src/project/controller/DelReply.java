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
    urlPatterns={"/del_reply"}, 
    initParams={
        @WebInitParam(name = "ARTICLE_PATH", value = "article")
    }
)
@ServletSecurity(
		@HttpConstraint(rolesAllowed = {"member"})
)
public class DelReply extends HttpServlet {    
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String millis = request.getParameter("millis");
        int ID = Integer.parseInt(request.getParameter("ID"));
        
        if(millis != null) {
            UserService userService = (UserService) getServletContext().getAttribute("userService");
            userService.deleteReply(getUsername(request), millis, ID);
        }
        
        response.sendRedirect(getInitParameter("ARTICLE_PATH"));
    }
    
    private String getUsername(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("login");
    }
}

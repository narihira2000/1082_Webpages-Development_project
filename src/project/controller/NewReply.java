package project.controller;

import java.io.IOException;
import project.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    urlPatterns={"/new_reply"}, 
    initParams={
        @WebInitParam(name = "ARTICLE_PATH", value = "article")
    }
)
public class NewReply extends HttpServlet {

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) 
                            throws ServletException, IOException {
            
        request.setCharacterEncoding("UTF-8");
        String content = request.getParameter("content");
       
        if(content.length() <= 140) {
            UserService userService = (UserService) getServletContext().getAttribute("userService");
            userService.addReply(getUsername(request), content, getID(request));
            
            response.sendRedirect(getInitParameter("ARTICLE_PATH"));
        }
        else {
            request.getRequestDispatcher(getInitParameter("ARTICLE_PATH")).forward(request, response);
        }
    }
    
    private String getUsername(HttpServletRequest request) {
        return  (String) request.getSession().getAttribute("login");
    }
    
    private int getID(HttpServletRequest request) {
        return (int) request.getSession().getAttribute("ID");
    }
    
}

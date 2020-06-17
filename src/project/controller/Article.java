package project.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
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
    urlPatterns={"/article"},
    initParams = {
    	@WebInitParam(name = "ARTICLE_PATH", value = "/jsp/article.jsp")
    }
)
@ServletSecurity(
		@HttpConstraint(rolesAllowed = {"member"})
)
public class Article extends HttpServlet {
    
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
        processRequest(request, response);
    }
   
    protected void processRequest(
                HttpServletRequest request, HttpServletResponse response) 
                        throws ServletException, IOException {

    	UserService userService = (UserService) getServletContext().getAttribute("userService");
    	Message messages = userService.messageByID(getID(request));
    	List<Reply> replies = userService.reply(getID(request));
        
        request.setAttribute("messages", messages);
        request.setAttribute("replies", replies);
        request.getRequestDispatcher(getInitParameter("ARTICLE_PATH")).forward(request, response);
    	
//    	request.setCharacterEncoding("UTF-8");
//        String title = request.getParameter("title");
//        String time = request.getParameter("time");
//        String username = request.getParameter("username");
//        String content = request.getParameter("content");
//        
//        String strID = request.getParameter("ID");
//        int ID;
//        if(strID==null) {
//        	System.out.println("enter null if");
//        	ID = (int) request.getSession().getAttribute("ID");
//        }
//        else {
//        	ID = Integer.parseInt(strID);
//        }
//        
//        System.out.println("<article>"+strID);
//        
//    	
//        UserService userService = (UserService) getServletContext().getAttribute("userService");
//        List<Reply> replies = userService.reply(ID);
//        
//        request.setAttribute("replies", replies);
//        request.setAttribute("title", title);
//        request.setAttribute("content", content);
//        request.setAttribute("ID", ID);
//        request.setAttribute("time", time);
//        request.setAttribute("username", username);
//        request.getRequestDispatcher("/jsp/article.jsp").forward(request, response);
    }
    
    private int getID(HttpServletRequest request) {
        return (int) request.getSession().getAttribute("ID");
    }
}


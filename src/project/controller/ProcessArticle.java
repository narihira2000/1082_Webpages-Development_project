package project.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import project.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    urlPatterns={"/process_article"},
    initParams = {
        @WebInitParam(name = "ARTICLE_PATH", value = "article")
    }
)
public class ProcessArticle extends HttpServlet {
    
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
    	int ID = Integer.parseInt(request.getParameter("ID"));
        
//        UserService userService = (UserService) getServletContext().getAttribute("userService");
        
        if(request.getSession(false) != null) {
            request.changeSessionId();
        }
        request.getSession().setAttribute("ID", ID);
        response.sendRedirect(getInitParameter("ARTICLE_PATH"));

        
    }
   
//    protected void processRequest(
//                HttpServletRequest request, HttpServletResponse response) 
//                        throws ServletException, IOException {

//    	request.setCharacterEncoding("UTF-8");
//        String title = request.getParameter("title");
//        String time = request.getParameter("time");
//        String username = request.getParameter("username");
//        String content = request.getParameter("content");
//        
//        String strID = request.getParameter("ID");
//        System.out.print(strID);
//        int ID = Integer.parseInt(strID);
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
//    }
}


package project.controller;

import project.model.*;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("")
public class Index extends HttpServlet {
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
        
    	UserService userService = (UserService) getServletContext().getAttribute("userService");
    	List<Message> newest = userService.newestMessages(10);
    	request.setAttribute("newest", newest);
    	
        request.getRequestDispatcher("jsp/index.jsp")
               .forward(request, response);
    }
}


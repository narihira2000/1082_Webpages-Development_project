package project.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.model.*;

@WebServlet(
    urlPatterns={"/user/*"}, 
    initParams={
        @WebInitParam(name = "USER_PATH", value = "/jsp/user.jsp")
    }
)
public class User extends HttpServlet {
    protected void doGet(
                HttpServletRequest request, HttpServletResponse response) 
                        throws ServletException, IOException {

        String username = getUsername(request);
        System.out.println(username);
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        
        request.setAttribute("username", username);
        if(userService.isUserExist(username)) {
            List<Message> messages = userService.messages(username);
            request.setAttribute("messages", messages);
        } else {
            request.setAttribute("errors", Arrays.asList(String.format("%s 還沒有發表訊息", username)));
        }
        
        request.getRequestDispatcher(getInitParameter("USER_PATH"))
               .forward(request, response);
    }

    private String getUsername(HttpServletRequest request) {
        return request.getPathInfo().substring(1);
    }
}

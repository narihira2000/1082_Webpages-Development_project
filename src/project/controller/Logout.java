package project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    urlPatterns={"/logout"}, 
    initParams={
        @WebInitParam(name = "LOGIN_PATH", value = "/project")
    }
)
public class Logout extends HttpServlet {
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response) 
                          throws ServletException, IOException {
        request.getSession().invalidate();
//    	request.logout();
        response.sendRedirect(getInitParameter("LOGIN_PATH"));
    }
}

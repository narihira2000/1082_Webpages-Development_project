package project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import project.model.*;

@WebServlet(
	urlPatterns={"/verify"}
)
public class Verify extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String token = request.getParameter("token");
	    UserService userService = (UserService) getServletContext().getAttribute("userService");
	    request.setAttribute("acct", userService.verify(email, token));
	    request.getRequestDispatcher("/jsp/verify.jsp").forward(request, response);
	}
}

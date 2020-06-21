package project.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import project.model.UserService;

@MultipartConfig
@WebServlet(
	    urlPatterns={"/setAvatar"}, 
	    initParams={
	        @WebInitParam(name = "MEMBER_PATH", value = "member")
	    }
)
@ServletSecurity(
		@HttpConstraint(rolesAllowed = {"member"})
)
public class SetAvatar extends HttpServlet {

    public SetAvatar() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    String username = request.getParameter("username");
		Part part = request.getPart("file");
	       
	    byte[] bytes = getBytes(part);
	        
	    UserService userService = (UserService) getServletContext().getAttribute("userService");
	    userService.setUserAvatar(username, bytes);
	    request.getSession().setAttribute("UserAvatar", userService.getUserAvatar(username));
	    response.sendRedirect(getInitParameter("MEMBER_PATH"));
	}
	
	private byte[] getBytes(Part part) throws IOException {
        try(InputStream in = part.getInputStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            
        	byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            return out.toByteArray();
        }
    }

}

package com.revature.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.exceptions.ConflictingUserException;
import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.service.UserService;
import com.revature.util.JWTConfig;
import com.revature.util.JWTGenerator;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UserService service = new UserService();
	private User user = new User();
	
	/**
	 * The doGet method handles login verification
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			user = service.getByCredentials(username, password);
			
			resp.setStatus(200);
			resp.addHeader(JWTConfig.HEADER, JWTConfig.PREFIX + JWTGenerator.createJwt(user));
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp.setStatus(401);
		} catch(InvalidInputException e) {
			e.printStackTrace();
			resp.setStatus(401);
		}
	}
	
	/**
	 * The doPut method handles registration verification
	 */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/****might not need this loop through the params here but maybe later??**/
		Map<String, String[]> params = req.getParameterMap();
        for (Map.Entry<String,String[]> entry : params.entrySet()) {
        	System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()[0]); 
        }
       
		
			user.setFirstName(req.getParameter("firstname"));
			user.setLastName(req.getParameter("lastname"));
			user.setUsername(req.getParameter("username"));
			user.setPassword(req.getParameter("inputPassword"));
			user.setEmail(req.getParameter("inputEmail"));
		
		
			try {
				user = service.add(user);
				resp.setStatus(200);
				resp.addHeader(JWTConfig.HEADER, JWTConfig.PREFIX + JWTGenerator.createJwt(user));
			} catch (ConflictingUserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				resp.setStatus(400);
			} catch (InvalidInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				resp.setStatus(400);
			}
			
			
	        
	}

}

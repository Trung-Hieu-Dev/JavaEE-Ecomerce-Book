package com.ray.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ray.dao.UserDAO;
import com.ray.entity.User;
import com.ray.service.UserService;


@WebServlet("/admin/manage_user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private UserService userService; 
       
    public UserController() {
        super();
        this.userDAO = new UserDAO();
        this.userService = new UserService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		
		if (command == null) {
			command = "LIST";
		}
		
		switch (command) {
			case "LIST":
				getUserList(request, response);
				break;
			case "NEW":
				showNewForm(request, response);
				break;
			case "LOAD":
				showFormEdit(request, response);
				break;
	
			default:
				getUserList(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		
		if (command == null) {
			command = "INSERT";
		}
		
		switch (command) {
			case "INSERT":
				insertUser(request, response);
				break;
			case "UPDATE":
				updateUser(request, response);
				break;
	
			default:
				insertUser(request, response);
				break;
		}
		
	}
	
	private void getUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("theUser", null);
		
		// get data
		List<User> userList = userDAO.finAll();
		
		// pass data to JSP file
		request.setAttribute("userList", userList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user_list.jsp");
		dispatcher.forward(request, response);	
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("theUser", null);
		
		response.sendRedirect("user_form.jsp");
	}
	
	private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		
		User userToUpdate = this.userService.getUserById(userId);
		session.setAttribute("theUser", userToUpdate);
		
		response.sendRedirect("user_form.jsp");
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// read parameter
		request.setAttribute("message", null);
		
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		
		User newUser = new User(email, password, fullName);
//				this.userDAO.insert(newUser);
		
		String errorMessage = this.userService.createUser(newUser);
		if (errorMessage != null) {
			request.setAttribute("message", errorMessage);
			request.setAttribute("theUser", newUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user_form.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		// redirect to user list
		response.sendRedirect("manage_user?command=LIST"); // set parameter
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setAttribute("message", null);
		
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		
		User updatedUser = new User(userId, email, password, fullName);
		
		String errorMessage = this.userService.updateUser(updatedUser);
		if (errorMessage != null) {
			request.setAttribute("message", errorMessage);
			request.setAttribute("theUser", updatedUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user_form.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		
		// redirect to user list
		response.sendRedirect("manage_user?command=LIST"); // set parameter
	}

}

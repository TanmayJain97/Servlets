package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(
		description = "LoginServlet",
		urlPatterns = {"/Login"},
		initParams = {
				@WebInitParam(name= "User",value = "Tanmay"),
				@WebInitParam(name = "Pass",value = "password")
		}
	)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
//    public LoginServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("User");
		String pass = request.getParameter("Password");
		String userID = getServletConfig().getInitParameter("User");
		String password = getServletConfig().getInitParameter("Pass");
		String nameRegex = "^[A-Z]{1}.{2,}$";
		String passRegex = "^(?=.*\\d)(?=.*[A-Z])(?=.*\\W)(?!.*\\W\\w*\\W)(?!.*\\s).{8,}$";
		
		//Username Regex
		if (!username.matches(nameRegex)) {
			RequestDispatcher reqD = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Kindly Enter Correct USERNAME</font>");
			reqD.include(request, response);
		}
		
		//Password Regex
		if (!password.matches(passRegex)) {
			RequestDispatcher reqD = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Kindly Enter Correct PASSWRD</font>");
			reqD.include(request, response);
		}
		
		if(username.equals(userID) && pass.equals(password)) {
			request.setAttribute("User Name", username);
			request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
		}
		else {
			RequestDispatcher reqD = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Incorrect Credentials</font>");
			reqD.include(request, response);
		}
	}
}

package org.esgi.web.framework.module;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.dao.UserDAO;
import org.esgi.web.framework.entity.User;

public class HomeAction implements IAction {

	@Override
	public int setPriority(int priority) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addCredential(String role) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean needsCredentials() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasCredential(String[] roles) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void proceed(IContext context) {
		if (context._getRequest().getMethod().equals("GET")) {
			try {
				// context._getResponse().sendRedirect("index.jsp");
				context._getResponse().setContentType("text/html");
				context._getResponse().getWriter()
						.println("<h1>Bienvenue sur jwf</h1>");
				context._getResponse().getWriter()
						.println("<form action=\"\" method=\"post\">");
				context._getResponse()
						.getWriter()
						.println(
								"<label for=\"login\">Login: </label> <input name=\"login\"> <br>");
				context._getResponse()
						.getWriter()
						.println(
								"<label for=\"password\">Password: </label> <input name=\"password\" type=\"password\"> <br>");
				context._getResponse().getWriter()
						.println("<input type=\"submit\" value=\"Connexion\">");
				context._getResponse().getWriter().println("</form>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (context._getRequest().getMethod().equals("POST")) {
			try {
				
				UserDAO userDao = new UserDAO();
				String login = context._getRequest().getParameter("login");
				String password = context._getRequest().getParameter("password");
				/*Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/jwf", "root", "");
				ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM user where login = '" + login + "' AND password = '" + password + "'");
				*/
				if (userDao.findByLoginAndPassword(login, password)){
					//User u = new User(rs.getString("login"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("role"));
					context._getResponse().setContentType("text/html");
					context._getResponse().getWriter()
					.println("<h1>Bienvenue</h1>");
					context._getResponse().getWriter().println("<br><a href=\"/jwf/user\">Menu</a>");
				} else {
					context._getResponse().sendRedirect("index");
					System.out.println("non connecté");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

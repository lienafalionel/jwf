package org.esgi.web.framework.module.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class CreateAction implements IAction {

	private int priority;
	private String template;

	public CreateAction() {

	}

	@Override
	public int setPriority(int priority) {
		this.priority = priority;
		return this.priority;
	}

	@Override
	public int getPriority() {
		return this.priority;
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

	public String getTemplate() {
		return this.template;
	}
	
	public void setTemplate(String template) {
		this.template = template;
	}
	
	@Override
	public void proceed(IContext context) {
		if(context._getRequest().getMethod().equals("GET")) {
			try {
				context._getResponse().setContentType("text/html");
				context._getResponse().getWriter().println("<form action=\"\" method=\"post\">");
				context._getResponse().getWriter().println("<label for=\"login\">Login: </label> <input name=\"login\"> <br>");
				context._getResponse().getWriter().println("<label for=\"password\">Password: </label> <input name=\"password\" type=\"password\"> <br>");
				context._getResponse().getWriter().println("<input type=\"submit\" value=\"Créer\">");
				context._getResponse().getWriter().println("</form>");
				
				context._getResponse().getWriter().println("<a href=\"/jwf/user\">Menu</a>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (context._getRequest().getMethod().equals("POST")) {
			String login = context._getRequest().getParameter("login");
			String password = context._getRequest().getParameter("password");
			User u = new User(login, password);
			UserList.users.add(u);
			try {
				context._getResponse().sendRedirect("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

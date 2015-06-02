package org.esgi.web.framework.module.user;

import java.io.IOException;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.Context;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.entity.User;

public class SearchAction implements IAction {

	private int priority;

	public SearchAction() {

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
		
	@Override
	public void proceed(IContext context) {
		if(context._getRequest().getMethod().equals("GET")) {
			try {
				context._getResponse().setContentType("text/html");
				context._getResponse().getWriter().println("<form action=\"\" method=\"post\">");
				context._getResponse().getWriter().println("<label for=\"login\">Login: </label> <input name=\"login\"> <br>");
				context._getResponse().getWriter().println("<input type=\"submit\" value=\"Search\">");
				context._getResponse().getWriter().println("</form>");
				
				context._getResponse().getWriter().println("<a href=\"/jwf/user\">Menu</a>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (context._getRequest().getMethod().equals("POST")) {
			User user = null;
			String login = context._getRequest().getParameter("login");
			try {				
				for (User u : UserList.users) {
					System.out.println("User boucle : " + u.getLogin());
					if (u.getLogin().equals(login)) {
						user = u;
						break;
					}
				}

				context._getResponse().setContentType("text/html");
				if (user != null) {
					context._getResponse().getWriter()
							.println(user);
				} else {
					context._getResponse().getWriter().println("<h1>User not found! </h1>");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

}
}

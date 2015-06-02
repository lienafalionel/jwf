package org.esgi.web.framework.module.user;

import java.io.IOException;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.Context;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.entity.User;

public class RemoveAction implements IAction {

	private int priority;

	public RemoveAction() {

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
		try {
			boolean found = false;
			String login = (String) context.getParameter("login");
			for (User u : UserList.users) {
				if (u.getLogin().equals(login)) {
					UserList.users.remove(u);
					found = true;
					break;
				}
			}

			context._getResponse().setContentType("text/html");
			if (found) {
				context._getResponse().getWriter()
						.println("<h1>User removed</h1>");
			} else {
				context._getResponse().getWriter().println("<h1>User not found! </h1>");
			}
			context._getResponse().getWriter().println("<br><a href=\"/jwf/user\">Menu</a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

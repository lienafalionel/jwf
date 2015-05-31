package org.esgi.web.framework.module.user;

import java.io.IOException;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.renderer.JSONGenericRenderer;

public class ListAction implements IAction {
	
	private int priority;

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
			
			context.setAttribute("model", UserList.users);
			JSONGenericRenderer json = new JSONGenericRenderer();
			context._getResponse().getWriter().println(json.render(context));
//			context._getResponse().setContentType("text/html");
//			context._getResponse().getWriter().println("<br><a href=\"/jwf/user\">Menu</a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package org.esgi.web.framework.module.user;

import java.io.IOException;

import org.esgi.web.framework.action.interfaces.IActionRenderable;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.entity.User;
import org.esgi.web.framework.renderer.JSONGenericRenderer;
import org.esgi.web.framework.renderer.interfaces.IRenderer;

public class DisplayAction implements IActionRenderable {

	private int priority;
	private IRenderer renderer;
	private IContext context;

	public DisplayAction() {
		this.renderer = new JSONGenericRenderer();
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
		this.context = context;
		try {
			User user = null;
			String login = (String) context.getParameter("login");
			for (User u : UserList.users) {
				if (u.getLogin().equals(login)) {
					context.setAttribute("model", u);
					break;
				}
			}

			
			
			context._getResponse().setContentType("text/html");
//			if (user != null) {
//				context._getResponse().getWriter()
//						.println(user);
//			} else {
//				context._getResponse().getWriter().println("<h1>User not found! </h1>");
//			}
			
			context._getResponse().getWriter().println(render());
			
			context._getResponse().getWriter().println("<br><a href=\"/jwf/user\">Menu</a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setRenderer(IRenderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public String render() {
		return this.renderer.render(context);
	}

}

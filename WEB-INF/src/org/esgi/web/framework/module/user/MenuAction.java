package org.esgi.web.framework.module.user;

import java.io.IOException;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.action.interfaces.IActionRenderable;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.entity.User;
import org.esgi.web.framework.renderer.interfaces.IRenderer;

public class MenuAction implements IAction {

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
		try {
			context._getResponse().setContentType("text/html");
			context._getResponse().getWriter().println("<ul>");
			context._getResponse().getWriter().println("<li><a href=\"/jwf/user/create\">Create</a></li>");
			context._getResponse().getWriter().println("<li><a href=\"/jwf/user/list\">List</a></li>");
			context._getResponse().getWriter().println("<li>Display<ul>");
			for (User user : UserList.users) {
				context._getResponse().getWriter().println("<li><a href=\"/jwf/user/display/" + user.getLogin() + "\">" + user.getLogin() + "</a></li>");
			}
			context._getResponse().getWriter().println("</ul></li>");
			context._getResponse().getWriter().println("<li>Remove<ul>");
			for (User user : UserList.users) {
				context._getResponse().getWriter().println("<li><a href=\"/jwf/user/remove/" + user.getLogin() + "\">" + user.getLogin() + "</a></li>");
			}
			context._getResponse().getWriter().println("</ul></li>");
			context._getResponse().getWriter().println("<li><a href=\"/jwf/user/search\">Search</a></li>");
			context._getResponse().getWriter().println("</ul></li>");
			context._getResponse().getWriter().println("</ul>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}}

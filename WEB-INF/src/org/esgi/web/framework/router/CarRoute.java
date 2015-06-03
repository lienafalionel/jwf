package org.esgi.web.framework.router;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.esgi.web.framework.context.Context;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.router.interfaces.IRewriteRule;

public class CarRoute implements IRewriteRule{

	private Pattern regex;
	private String[] substitutions;
	private Matcher m;

	public CarRoute() {
		
		this.regex = Pattern.compile("/jwf/car/?([a-z]+)?/?([a-z]+)?");
		this.substitutions = new String[] { "method", "login" };
	}

	@Override
	public boolean matches(IContext context) {
		m = regex.matcher(context._getRequest().getRequestURI());
		return m.find();
	}

	@Override
	public void rewrite(IContext context) {
		for (int i = 0; i < substitutions.length && i < m.groupCount(); i++) {
			// ((Context)context).setParameter(substitutions[i], new String[] {
			// m.group(i + 1) });
			((Context) context).setParameter(substitutions[i], m.group(i + 1));
		}

		String method = (String) context.getParameter("method");

		if (method == null) {
			context.setActionClass("org.esgi.web.framework.module.car.MenuAction");
		} else {
			switch (method) {
			case "create":
				context.setActionClass("org.esgi.web.framework.module.car.CreateAction");
				break;
			}
		}
	}

}
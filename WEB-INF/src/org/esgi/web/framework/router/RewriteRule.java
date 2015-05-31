package org.esgi.web.framework.router;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.esgi.web.framework.context.Context;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.router.interfaces.IRewriteRule;

public class RewriteRule implements IRewriteRule {
	
	private Pattern regex;
	private String className;
	private String[] substitutions;
	private Matcher m;

	public RewriteRule(String regex, String className, String[] substitutions) {
		this.regex =  Pattern.compile(regex);
		this.className = className;
		this.substitutions = substitutions;
	}

	public RewriteRule(String regex, String className) {
		this.regex =  Pattern.compile(regex);
		this.className = className;
		this.substitutions = new String[0];
	}

	@Override
	public boolean matches(IContext context) {
		m = regex.matcher(context._getRequest().getRequestURI());
		
		return m.find();
	}

	@Override
	public void rewrite(IContext context) {
		context.setActionClass(className);
		
		for(int i = 0; i < substitutions.length && i < m.groupCount(); i++) {
			((Context)context).setParameter(substitutions[i], new String[] { m.group(i + 1) });
		}
	}
}

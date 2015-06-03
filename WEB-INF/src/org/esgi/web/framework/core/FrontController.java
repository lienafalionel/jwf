package org.esgi.web.framework.core;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.esgi.web.framework.context.Context;
import org.esgi.web.framework.core.interfaces.IFrontController;
import org.esgi.web.framework.module.user.UserList;
import org.esgi.web.framework.router.CarRoute;
import org.esgi.web.framework.router.Dispatcher;
import org.esgi.web.framework.router.RewriteRule;
import org.esgi.web.framework.router.Rewriter;
import org.esgi.web.framework.router.UserRoute;
import org.esgi.web.framework.router.interfaces.IDispatcher;
import org.esgi.web.framework.router.interfaces.IRewriter;


public class FrontController extends HttpServlet implements IFrontController {

	private static final long serialVersionUID = 1L;
	
	private static String URIroot = "/jwf";
	
	private IRewriter rewriter;
	private IDispatcher dispatcher;
	private Context c;
	
	public void init() {
		new UserList();
		rewriter = new Rewriter();
		dispatcher = new Dispatcher();

//		rewriter.addRule(new RewriteRule(URIroot + "user/([0-9]+).html", "org.esgi.web.framework.module.ActionA", new String[] { "user_id" }));
//		rewriter.addRule(new RewriteRule(URIroot + "b+", "org.esgi.web.framework.module.ActionB"));
//		rewriter.addRule(new RewriteRule(URIroot + "(?i)helloWorld", "org.esgi.web.framework.module.ActionHelloWorld"));
//		
//		rewriter.addRule(new RewriteRule(URIroot, "org.esgi.web.framework.module.DisplayDirectoryAction"));
//		rewriter.addRule(new RewriteRule(URIroot + "upload", "org.esgi.web.framework.module.UploadAction"));
		
		rewriter.addRule(new UserRoute());
		rewriter.addRule(new CarRoute());
		rewriter.addRule(new RewriteRule(URIroot, "org.esgi.web.framework.module.HomeAction"));
		rewriter.addRule(new RewriteRule(URIroot + "/", "org.esgi.web.framework.module.HomeAction"));
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		handle(request, response);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		try {
			c = new Context(request, response);
			// Do operations
			rewriter.rewrite(c);
			dispatcher.dispatch(c);
			c.removeUploadedFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

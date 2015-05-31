package org.esgi.web.framework.module;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class UploadAction implements IAction {

	private File file;
	private String requestedPath;
	private String requestedURI;
	private String baseURL;
	private static String root = "../res/";
	
	@Override
	public void proceed(IContext context) {
		
	}

	
	/**
	 * Return a file from the requested URI.
	 * 
	 * @param request The request.
	 * @return A file, or null if no files could be retrieved.
	 * @throws UnsupportedEncodingException 
	 */
	private void setRequestedFile(HttpServletRequest request) throws UnsupportedEncodingException {
		Pattern datePatt = Pattern.compile("/firstProject/?(.+)?");
		String url = request.getRequestURL().toString();
		baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";

		Matcher m = datePatt.matcher(URLDecoder.decode(request.getRequestURI(), "UTF-8"));
		if (m.matches()) {
			requestedPath = m.group(1);
			requestedURI = request.getRequestURI();
			
			if(!requestedURI.substring(requestedURI.length() - 1).equals("/"))
				requestedURI += "/";
			
			if(requestedPath == null)
				requestedPath = "/";
			
			file = new File(root + requestedPath);
		} else
			file = null;
	}
	
	
	/**
	 * Error writer.
	 * 
	 * @param response Response used to write.
	 * @param errorNumber Error number.
	 * @param message Message to write.
	 */
	private void error(HttpServletResponse response, int errorNumber, String message) {
		try {
			PrintWriter out = response.getWriter( );

			response.setContentType("text/html");
			response.setStatus(errorNumber);
			out.print("Error : " + message);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


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
}

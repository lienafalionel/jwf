package org.esgi.web.framework.module;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class DisplayDirectoryAction implements IAction {

	private File file;
	private String requestedPath;
	private String requestedURI;
	private String baseURL;
	private static String root = "../res/";

	@Override
	public void proceed(IContext context) {
		// Set file
		try {
			setRequestedFile(context._getRequest());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			// Detect if exist
			if (file != null && file.exists()) {
				if (file.isDirectory()) {
					context._getResponse().setContentType("text/html");
					// List the directory
					PrintWriter out = context._getResponse().getWriter();
					out.print("<h1>Directory " + createHeader() + "</h1>");
					out.print("<ul>");

					// Directories
					if (requestedPath.length() > 1)
						out.print("<li><a href=\"" + requestedURI
								+ "..\">..</a></li>");

					for (File f : file.listFiles(new FileFilter() {

						@Override
						public boolean accept(File f) {
							return f.isDirectory();
						}
					})) {
						out.print("<li><a href=\"" + requestedURI + f.getName()
								+ "\">" + f.getName() + "</a> (directory)</li>");
					}

					// Files
					for (File f : file.listFiles(new FileFilter() {

						@Override
						public boolean accept(File f) {
							return !f.isDirectory();
						}
					})) {
						out.print("<li><a href=\"" + requestedURI + f.getName()
								+ "\">" + f.getName() + "</a> (" + f.length()
								+ " bytes)</li>");
					}

					out.print("</ul>");
					out.print("<form method=\"POST\" enctype=\"multipart/form-data\"> <input type=\"submit\" value=\"upload\" /> <input type=\"file\" name=\"file\" /></form>");

				} else {
					// Send the file
					OutputStream out = context._getResponse().getOutputStream();
					FileInputStream in = new FileInputStream(file);
					byte[] buffer = new byte[4096];
					int length;
					while ((length = in.read(buffer)) > 0) {
						out.write(buffer, 0, length);
					}
					in.close();
					out.flush();
				}
			} else {
				// Error 404
				error(context._getResponse(), 404, "the ressource "
						+ requestedPath + " does not exist");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Return a file from the requested URI.
	 * 
	 * @param request
	 *            The request.
	 * @return A file, or null if no files could be retrieved.
	 * @throws UnsupportedEncodingException
	 */
	private void setRequestedFile(HttpServletRequest request)
			throws UnsupportedEncodingException {
		Pattern datePatt = Pattern.compile("/jwf/?(.+)?");
		String url = request.getRequestURL().toString();
		baseURL = url.substring(0, url.length()
				- request.getRequestURI().length())
				+ request.getContextPath() + "/";

		Matcher m = datePatt.matcher(URLDecoder.decode(request.getRequestURI(),
				"UTF-8"));
		if (m.matches()) {
			requestedPath = m.group(1);
			requestedURI = request.getRequestURI();

			if (!requestedURI.substring(requestedURI.length() - 1).equals("/"))
				requestedURI += "/";

			if (requestedPath == null)
				requestedPath = "/";

			file = new File(root + requestedPath);
		} else
			file = null;
	}

	/**
	 * Create a html header.
	 * 
	 * @return The created html header.
	 */
	private String createHeader() {
		String result = "<a href=\"" + baseURL + "\">/</a>";
		String path = "";

		for (String part : requestedPath.split("/")) {
			path += part + "/";
			result += "<a href=\"" + baseURL + path + "\">" + part + "</a>/";
		}

		return result;
	}

	/**
	 * Error writer.
	 * 
	 * @param response
	 *            Response used to write.
	 * @param errorNumber
	 *            Error number.
	 * @param message
	 *            Message to write.
	 */
	private void error(HttpServletResponse response, int errorNumber,
			String message) {
		try {
			PrintWriter out = response.getWriter();

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

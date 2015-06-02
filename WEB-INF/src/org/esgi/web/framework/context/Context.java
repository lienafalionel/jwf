package org.esgi.web.framework.context;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.context.interfaces.IHtmlContext;
import org.esgi.web.framework.entity.User;
import org.esgi.web.framework.error.JwfErrorHandler;

public class Context implements IContext {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private String actionClass;

	private Map<String, Object> properties;
	private Map<String, File> files;
	
	private static File tmpFolder = new File("../res");
	
	/**
	 * Constructor.
	 * 
	 * @param request Request.
	 * @param response Response.
	 * @throws IOException
	 */
	public Context(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.request = request;
		this.response = response;
		
		init();
	}
	
	private void init() {
		try {

			// Normal
			properties = new HashMap<String, Object>(request.getParameterMap());
			files = new HashMap<String, File>();
			
			// Multi-part
			if(ServletFileUpload.isMultipartContent(request)) {
			    ServletFileUpload uploader = new ServletFileUpload(new DiskFileItemFactory());
			    List<FileItem> items = uploader.parseRequest(request);
			      
			    for(FileItem item : items) {
			    	if(item.isFormField()) {
			    		// Form item
			    		properties.put(item.getFieldName(), appendToArray((String[])properties.get(item.getFieldName()), item.getString()));
			    	} else {
			    		// File
			    		File f = new File(tmpFolder.getAbsolutePath() + item.getName() + new Date());
						item.write(f);
			    		files.put(item.getName(), f);
			    	}
			    }
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
			JwfErrorHandler.displayError(this, 500, "Error while uploading file : " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			JwfErrorHandler.displayError(this, 500, "Error while retreiving sended values : " + e.getMessage());
		}
	}
	
	
	/**
	 * Relocate an array.
	 * 
	 * @param tab Tab to reallocate
	 * @param toAdd String to add.
	 * @return The reallocated string.
	 */
	private String[] appendToArray(String[] tab, String toAdd) {
		
		if(tab == null)
			return new String[] { toAdd };
		
		String[] newtab = new String[tab.length + 1];
		int i = 0;
		
		for(; i < tab.length; i++)
			newtab[i] = tab[i];
		newtab[i] = toAdd;
		
		return newtab;
	}
	
	@Override
	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	@Override
	public String getActionClass() {
		return actionClass;
	}

	@Override
	public HttpServletRequest _getRequest() {
		return request;
	}

	@Override
	public HttpServletResponse _getResponse() {
		return response;
	}

	@Override
	public Object getParameter(String key) {
		return properties.get(key);
	}
	
	public Object setParameter(String key, Object parameter) {
		return properties.put(key, parameter);
	}
	
	public void setParameter(String key, String[] value) {
		properties.put(key, value);
	}

	@Override
	public String[] getParameterKeys() {
		String[] tmp = new String[properties.keySet().size()];
		int i = 0;
		
		for(String key : properties.keySet()) {
			tmp[i++] = key;
		}
		
		return tmp;
	}

	@Override
	public void setAttribute(String key, Object value) {
		request.setAttribute(key, value);
	}

	@Override
	public Object getAttribute(String key) {
		return request.getAttribute(key);
	}
	
	@Override
	public boolean resetSession() {
		request.getSession().invalidate();
		return true;
	}

	@Override
	public IHtmlContext toHtmlContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File[] getUploadedFiles() {
		File[] tmp = new File[files.size()];
		int i = 0;
		
		for(File f : files.values()) {
			tmp[i++] = f;
		}
		return tmp;
	}
	
	/**
	 * Remove all temporary files.
	 */
	public void removeUploadedFiles() {
		for(File f : files.values()) {
			f.delete();
		}
		
		files = new HashMap<String, File>();
	}

	@Override
	public void setSessionAttribute(String key, Object value) {
		request.getSession().setAttribute(key, value);
	}

	@Override
	public String getSessionAttribute(String key) {
		return (String) request.getSession().getAttribute(key);
	}

	@Override
	public String[] getUserCredentials() {
		// TODO Auto-generated method stub
		return null;
	}
	

}

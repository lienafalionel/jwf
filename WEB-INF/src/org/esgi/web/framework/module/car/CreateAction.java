package org.esgi.web.framework.module.car;

import java.io.IOException;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.dao.CarDAO;
import org.esgi.web.framework.entity.Car;

public class CreateAction implements IAction{

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
		if (context._getRequest().getMethod().equals("GET")) {
			try {
				context._getResponse().sendRedirect("createCar");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (context._getRequest().getMethod().equals("POST")) {
			String name = context._getRequest().getParameter("name");
			float price = Float.parseFloat(context._getRequest().getParameter("price"));
			String year = context._getRequest().getParameter("year");
			String type_fuel = context._getRequest().getParameter("type_fuel");
			String gearbox_type = context._getRequest().getParameter("gearbox_type");
			String description = context._getRequest().getParameter("description");
			String telephone_number = context._getRequest().getParameter("telephone_number");
			String email = context._getRequest().getParameter("email");
			Car c = new Car(name, price, year, type_fuel, gearbox_type, description, telephone_number, email);
			CarDAO carDao = new CarDAO();
			carDao.create(c);
		}
		
	}

}

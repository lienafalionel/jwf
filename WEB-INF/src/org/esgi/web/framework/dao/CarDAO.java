package org.esgi.web.framework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.esgi.web.framework.entity.Car;
import org.esgi.web.framework.entity.User;

import com.mysql.jdbc.PreparedStatement;

public class CarDAO extends DAO<Car>{

	@Override
	public Car find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Car obj) {
		try {			
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                	ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate("INSERT INTO car (name, price, year, type_fuel, gearbox_type, description, telephone_number, email)"+
                                            			"VALUES('"+obj.getName()+"', '"+obj.getPrice()+"', '"+obj.getType_fuel()+"', '"+obj.getGearbox_type()+"', '"+obj.getDescription()+"', '"+obj.getTelephone_number()+"', '"+obj.getEmail()+"')"
                 );

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
		
	}

	@Override
	public void update(Car obj) {
		try {
			this .connect
			.createStatement(
			    	ResultSet.TYPE_SCROLL_INSENSITIVE, 
			    	ResultSet.CONCUR_UPDATABLE
			     ).executeUpdate(
			       	" UPDATE car SET name = '"+ obj.getName() +"', price = '" + obj.getPrice()
			       	+ "', type_fuel = '" + obj.getType_fuel() + "', gearbox_type = '" + obj.getGearbox_type()
			       	+ "', description = '" + obj.getDescription() + "', telephone_number = '" + obj.getTelephone_number() 
			       	+ "', email = '" + obj.getEmail()	+ " WHERE id = " + obj.getId()
			       );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Car obj) {
		try {			
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                	ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"DELETE FROM car WHERE id = " + obj.getId()
                 );

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }	
		
	}

}

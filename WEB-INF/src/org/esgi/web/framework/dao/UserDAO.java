package org.esgi.web.framework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.esgi.web.framework.entity.User;

import com.mysql.jdbc.PreparedStatement;

public class UserDAO extends DAO<User>{

	@Override
	public User find(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean findByLoginAndPassword(String login, String password) {
		
		boolean trouve = false;
		try {			
			ResultSet result = this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeQuery(
                	"SELECT * FROM user WHERE login = '" +  login + "' AND password = '" + password +"'"
                 );
                 
                 int size = 0;
                 
                 while (result.next())
                 {
                	 size++;
                 }
                 if (size > 0)
                 {
                	 trouve = true;
                 }
                 

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    return trouve;
		
	}

	@Override
	public void create(User obj) {
		try {			
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                	ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate("INSERT INTO user (login, password, lastname, firstname, role)"+
                                            			"VALUES("+obj.getLogin()+", "+obj.getPassword()+", "+obj.getLastName()+", "+obj.getFirstName()+","+obj.getRole()+")"
                 );

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	}

	@Override
	public void update(User obj) {
		try {
			this .connect
			.createStatement(
			    	ResultSet.TYPE_SCROLL_INSENSITIVE, 
			    	ResultSet.CONCUR_UPDATABLE
			     ).executeUpdate(
			       	" UPDATE user SET login = '"+ obj.getLogin() +"', password = '" + obj.getPassword()
			       	+ "', lastname = '" + obj.getLastName() + "', firstname = '" + obj.getFirstName()
			       	+ "', role = '" + obj.getRole() + " WHERE id = " + obj.getId()
			       );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(User obj) {
		try {			
			this.connect	
                .createStatement(
                	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                	ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                	"DELETE FROM user WHERE id = " + obj.getId()
                 );

	    } catch (SQLException e) {
	            e.printStackTrace();
	    }		
	}

}

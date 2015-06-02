package org.esgi.web.framework.module.user;

import java.util.ArrayList;
import java.util.List;

import org.esgi.web.framework.entity.User;

public class UserList {

	public static List<User> users = new ArrayList<User>();
	
	public UserList() {
		users.add(new User("abc", "BCD"));
		users.add(new User("aze", "BCD"));
	}
}

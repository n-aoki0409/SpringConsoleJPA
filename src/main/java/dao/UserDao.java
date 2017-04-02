package dao;

import model.User;

public interface UserDao {

	User getUser(String name);

	User getUser(Integer id);

	void updateUser(User user);

	void addUser(User user);
}

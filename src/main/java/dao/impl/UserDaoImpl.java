package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.User;
import dao.UserDao;

public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User getUser(String name) {
		// ユーザー名でユーザーを取得
		List<User> userList = em.createQuery(" FROM User AS user WHERE user.name = :name ", User.class).setParameter(
				"name", name).getResultList();

		if (userList.isEmpty()) {
			return null;
		}

		return userList.get(0);
	}

	@Override
	public User getUser(Integer id) {
		// IDでユーザーを取得
		return em.find(User.class, id);
	}

	@Override
	public void updateUser(User user) {
		// ユーザーを更新
		em.merge(user);
	}

	@Override
	public void addUser(User user) {
		em.persist(user);
	}
}

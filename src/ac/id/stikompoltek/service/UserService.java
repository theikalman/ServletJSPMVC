package ac.id.stikompoltek.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ac.id.stikompoltek.dao.UserDao;
import ac.id.stikompoltek.dto.User;

public class UserService {
	
	private UserDao userDao;
	private Connection connection;
	
	public void setConnection(Connection connection) {
		try {
			this.connection = connection;
			this.userDao = new UserDao();
			this.userDao.setConnection(this.connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Save user data, if you want to save it as new data, set idUser to 0
	 * 
	 * @param user
	 * @return
	 */
	public User save(User user) {
		try {
			this.connection.setAutoCommit(false);
			this.userDao.save(user);
			this.connection.commit();
			this.connection.setAutoCommit(true);
			return user;
		} catch (SQLException e) {
			try {
				this.connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		}
		
	}
	
	/**
	 * Delete user dari database
	 * 
	 * @param user
	 * @return User - Mengembalikan user yang berhasil di delete
	 */
	public User delete(User user) {
		try {
			this.connection.setAutoCommit(false);
			this.userDao.delete(user);
			this.connection.commit();
			this.connection.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				this.connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		}
		return user;
	}
	
	/**
	 * Mengambil semua data User yang ada dalam database
	 * 
	 * @return List : User - Mengembalikan semua data user
	 */
	public List<User> getAll() {
		try {
			return this.userDao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}

	/**
	 * Mengambil user berdasarkan id-nya
	 * 
	 * @param idUser
	 * @return User - Mengembalikan user yang terpilih
	 */
	public User getById(int idUser) {
		try {
			return this.userDao.getById(idUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Mengambil user berdasarkan username-nya
	 * 
	 * @param idUser
	 * @return User - Mengembalikan user yang terpilih
	 */
	public User getByUsername(String username) {
		try {
			return this.userDao.getByUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

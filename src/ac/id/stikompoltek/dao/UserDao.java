package ac.id.stikompoltek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ac.id.stikompoltek.dto.User;

public class UserDao {
	
	private Connection connection; 
	
	private PreparedStatement insertStatement, updateStatement, deleteStatement, selectAllStatement, selectByIdStatement, selectByUsernameStatement;
	
	private final String insertQuery = "INSERT INTO user(username_user, password_user, nama_lengkap_user) VALUES(?,?,?)";
	private final String updateQuery = "UPDATE INTO user SET username_user=?, password_user=?, nama_lengkap_user=? WHERE id_user=?";
	private final String deleteQuery = "DELETE FROM user WHERE id_user=?";
	private final String selectAllQuery = "SELECT * FROM user";
	private final String selectByIdQuery = "SELECT * FROM user WHERE id_user=?";
	private final String selectByUsernameQuery = "SELECT * FROM user WHERE username_user=?";
	
	/**
	 * Setting koneksi
	 * @throws SQLException 
	 */
	public void setConnection(Connection connection) throws SQLException {
		
		this.connection = connection;
		
		this.insertStatement = this.connection.prepareStatement(this.insertQuery, Statement.RETURN_GENERATED_KEYS); // flag generate auto increment key
		this.updateStatement = this.connection.prepareStatement(this.updateQuery);
		this.deleteStatement = this.connection.prepareStatement(this.deleteQuery);
		this.selectAllStatement = this.connection.prepareStatement(this.selectAllQuery);
		this.selectByIdStatement = this.connection.prepareStatement(this.selectByIdQuery);
		this.selectByUsernameStatement = this.connection.prepareStatement(this.selectByUsernameQuery);
		
	}
	
	
	/**
	 * Save user data, set idUser to 0 if you want to save it as new data
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public User save(User user) throws SQLException {
		
		if(user.getIdUser() == 0) {
			
			// Insert
			this.insertStatement.setString(1, user.getUsername());
			this.insertStatement.setString(2, user.getPassword());
			this.insertStatement.setString(3, user.getNamaLengkap());
			
			int idUser = this.insertStatement.executeUpdate();
			user.setIdUser(idUser);
			
			return user;
			
		} else {
			
			// Update
			this.updateStatement.setString(1, user.getUsername());
			this.updateStatement.setString(2, user.getPassword());
			this.updateStatement.setString(3, user.getNamaLengkap());
			this.updateStatement.setInt(4, user.getIdUser());
			
			this.updateStatement.executeUpdate();
			
			return user;
			
		}
		
	}
	
	/**
	 * Hapus user dari database
	 * 
	 * @param user
	 * @return User - Mengembalikan user yang berhasil dihapus
	 * @throws SQLException 
	 */
	public User delete(User user) throws SQLException {
		
		this.deleteStatement.setInt(1, user.getIdUser());
		
		this.deleteStatement.executeUpdate();
		
		return user;
		
	}
	
	/**
	 * Mengambil semua user
	 * 
	 * @return List : Users - Mengembalikan semua user yang ada dalam database
	 * @throws SQLException 
	 */
	public List<User> getAll() throws SQLException {
		
		List<User> usersList = new ArrayList<>();
		
		ResultSet resultSet = this.selectAllStatement.executeQuery();
		
		while(resultSet.next()) {
			
			User user = new User();
			
			user.setIdUser(resultSet.getInt("id_user"));
			user.setUsername(resultSet.getString("username_user"));
			user.setPassword(resultSet.getString("password_user"));
			user.setNamaLengkap(resultSet.getString("nama_lengkap_user"));
			
			usersList.add(user);
			
		}
		
		return usersList;
		
	}
	
	/**
	 * Mengambil user berdasarkan id-nya
	 * 
	 * @return User - Mengembalikan user yang dipilih berdasarkan Id-nya
	 * @throws SQLException 
	 */
	public User getById(int idUser) throws SQLException {
		
		this.selectByIdStatement.setInt(1, idUser);
		
		ResultSet resultSet = this.selectByIdStatement.executeQuery();
		
		if(resultSet.next()) {
			
			User user = new User();
			
			user.setIdUser(resultSet.getInt("id_user"));
			user.setUsername(resultSet.getString("username_user"));
			user.setPassword(resultSet.getString("password_user"));
			user.setNamaLengkap(resultSet.getString("nama_lengkap_user"));
			
			return user;
			
		}
		
		return null;
		
	}
	
	/**
	 * Mengambil user berdasarkan username-nya
	 * 
	 * @return User - Mengembalikan user yang dipilih berdasarkan Username-nya
	 * @throws SQLException 
	 */
	public User getByUsername(String username) throws SQLException {
		
		this.selectByUsernameStatement.setString(1, username);
		
		ResultSet resultSet = this.selectByUsernameStatement.executeQuery();
		
		if(resultSet.next()) {
			
			User user = new User();
			
			user.setIdUser(resultSet.getInt("id_user"));
			user.setUsername(resultSet.getString("username_user"));
			user.setPassword(resultSet.getString("password_user"));
			user.setNamaLengkap(resultSet.getString("nama_lengkap_user"));
			
			return user;
			
		}
		
		return null;
		
	}

}

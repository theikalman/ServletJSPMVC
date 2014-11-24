package ac.id.stikompoltek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ac.id.stikompoltek.dto.Tag;

public class TagDao {
	
	private Connection connection; 
	
	private PreparedStatement insertStatement, updateStatement, deleteStatement, selectAllStatement, selectByIdStatement, selectByURLStatement;
	
	private final String insertQuery = "INSERT INTO tag(nama, seo_url, ket) VALUES(?,?,?)";
	private final String updateQuery = "INSERT INTO tag SET nama=?, seo_url=?, ket=? WHERE id=?";
	private final String deleteQuery = "DELETE FROM tag WHERE id=?";
	private final String selectAllQuery = "SELECT * FROM tag";
	private final String selectByIdQuery = "SELECT * FROM tag WHERE id=?";
	private final String selectByURLQuery = "SELECT * FROM tag WHERE seo_url=?";
	
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
		this.selectByURLStatement = this.connection.prepareStatement(this.selectByURLQuery);
		
	}

	/**
	 * Insert tag kedalam database
	 * 
	 * @param tag
	 * @return Tag - Mengembalikan tag yang berhasil di insert
	 * @throws SQLException 
	 */
	public Tag insert(Tag tag) throws SQLException {
		
		this.insertStatement.setString(1, tag.getNama());
		this.insertStatement.setString(2, tag.getSeoUrl());
		this.insertStatement.setString(3, tag.getKet());
		
		int idTag = this.insertStatement.executeUpdate();
		tag.setIdTag(idTag);
		
		return tag;
		
	}
	
	/**
	 * Update tag di database
	 * 
	 * @param tag
	 * @return Tag - Mengembalikan tag yang berhasil di update
	 * @throws SQLException 
	 */
	public Tag update(Tag tag) throws SQLException {
		
		this.updateStatement.setString(1, tag.getNama());
		this.updateStatement.setString(2, tag.getSeoUrl());
		this.updateStatement.setString(3, tag.getKet());
		this.updateStatement.setInt(4, tag.getIdTag());
		
		this.updateStatement.executeUpdate();
		
		return tag;
		
	}
	
	/**
	 * Hapus tag dari database
	 * 
	 * @param tag
	 * @return Tag - Mengembalikan tag yang berhasil dihapus
	 * @throws SQLException 
	 */
	public Tag delete(Tag tag) throws SQLException {
		
		this.deleteStatement.setInt(1, tag.getIdTag());
		
		this.deleteStatement.executeUpdate();
		
		return tag;
		
	}
	
	/**
	 * Mengambil semua tag
	 * 
	 * @return List : Tags - Mengembalikan semua tag yang ada dalam database
	 * @throws SQLException 
	 */
	public List<Tag> getAll() throws SQLException {
		
		List<Tag> tagsList = new ArrayList<>();
		
		ResultSet resultSet = this.selectAllStatement.executeQuery();
		
		while(resultSet.next()) {
			
			Tag tag = new Tag();
			
			tag.setIdTag(resultSet.getInt("id"));
			tag.setNama(resultSet.getString("nama"));
			tag.setSeoUrl(resultSet.getString("seo_url"));
			tag.setKet(resultSet.getString("ket"));
			
			tagsList.add(tag);
			
		}
		
		return tagsList;
		
	}
	
	/**
	 * Mengambil tag berdasarkan id-nya
	 * 
	 * @return Tag - Mengembalikan tag yang dipilih berdasarkan Id-nya
	 * @throws SQLException 
	 */
	public Tag getById(int idTag) throws SQLException {
		
		this.selectByIdStatement.setInt(1, idTag);
		
		ResultSet resultSet = this.selectByIdStatement.executeQuery();
		
		if(resultSet.next()) {
			
			Tag tag = new Tag();
			
			tag.setIdTag(resultSet.getInt("id"));
			tag.setNama(resultSet.getString("nama"));
			tag.setSeoUrl(resultSet.getString("seo_url"));
			tag.setKet(resultSet.getString("ket"));
			
			return tag;
			
		}
		
		return null;
		
	}
	
	/**
	 * Mengambil tag berdasarkan seo_url-nya
	 * 
	 * @return Tag - Mengembalikan tag yang dipilih berdasarkan seo_url-nya
	 * @throws SQLException
	 */
	public Tag getByURL(String url) throws SQLException {
		
		this.selectByURLStatement.setString(1, url);
		
		ResultSet resultSet = this.selectByURLStatement.executeQuery();
		
		if(resultSet.next()) {
			
			Tag tag = new Tag();
			
			tag.setIdTag(resultSet.getInt("id"));
			tag.setNama(resultSet.getString("nama"));
			tag.setSeoUrl(resultSet.getString("seo_url"));
			tag.setKet(resultSet.getString("ket"));
			
			return tag;
			
		}
		
		return null;
		
	}

}

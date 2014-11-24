package ac.id.stikompoltek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ac.id.stikompoltek.dto.Kategori;

public class KategoriDao {
	
	private Connection connection; 
	
	private PreparedStatement insertStatement, updateStatement, deleteStatement, selectAllStatement, selectByIdStatement, selectByURLStatement;
	
	private final String insertQuery = "INSERT INTO kategori(nama, seo_url, ket) VALUES(?,?,?)";
	private final String updateQuery = "INSERT INTO kategori SET nama=?, seo_url=?, ket=? WHERE id=?";
	private final String deleteQuery = "DELETE FROM kategori WHERE id=?";
	private final String selectAllQuery = "SELECT * FROM kategori";
	private final String selectByIdQuery = "SELECT * FROM kategori WHERE id=?";
	private final String selectByURLQuery = "SELECT * FROM kategori WHERE seo_url=?";
	
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
	 * Insert kategori kedalam database
	 * 
	 * @param kategori
	 * @return User - Mengembalikan kategori yang berhasil di insert
	 * @throws SQLException 
	 */
	public Kategori insert(Kategori kategori) throws SQLException {
		
		this.insertStatement.setString(1, kategori.getNama());
		this.insertStatement.setString(2, kategori.getSeoUrl());
		this.insertStatement.setString(3, kategori.getKet());
		
		int idKategori = this.insertStatement.executeUpdate();
		kategori.setIdKategori(idKategori);
		
		return kategori;
		
	}
	
	/**
	 * Update kategori di database
	 * 
	 * @param kategori
	 * @return Kategori - Mengembalikan kategori yang berhasil di update
	 * @throws SQLException 
	 */
	public Kategori update(Kategori kategori) throws SQLException {
		
		this.updateStatement.setString(1, kategori.getNama());
		this.updateStatement.setString(2, kategori.getSeoUrl());
		this.updateStatement.setString(3, kategori.getKet());
		this.updateStatement.setInt(4, kategori.getIdKategori());
		
		this.updateStatement.executeUpdate();
		
		return kategori;
		
	}
	
	/**
	 * Hapus kategori dari database
	 * 
	 * @param kategori
	 * @return Kategori - Mengembalikan kategori yang berhasil dihapus
	 * @throws SQLException 
	 */
	public Kategori delete(Kategori kategori) throws SQLException {
		
		this.deleteStatement.setInt(1, kategori.getIdKategori());
		
		this.deleteStatement.executeUpdate();
		
		return kategori;
		
	}
	
	/**
	 * Mengambil semua kategori
	 * 
	 * @return List : Kategoris - Mengembalikan semua kategori yang ada dalam database
	 * @throws SQLException 
	 */
	public List<Kategori> getAll() throws SQLException {
		
		List<Kategori> kategorisList = new ArrayList<>();
		
		ResultSet resultSet = this.selectAllStatement.executeQuery();
		
		while(resultSet.next()) {
			
			Kategori kategori = new Kategori();
			
			kategori.setIdKategori(resultSet.getInt("id"));
			kategori.setNama(resultSet.getString("nama"));
			kategori.setSeoUrl(resultSet.getString("seo_url"));
			kategori.setKet(resultSet.getString("ket"));
			
			kategorisList.add(kategori);
			
		}
		
		return kategorisList;
		
	}
	
	/**
	 * Mengambil kategori berdasarkan id-nya
	 * 
	 * @return Kategori - Mengembalikan kategori yang dipilih berdasarkan Id-nya
	 * @throws SQLException 
	 */
	public Kategori getById(int idKategori) throws SQLException {
		
		this.selectByIdStatement.setInt(1, idKategori);
		
		ResultSet resultSet = this.selectByIdStatement.executeQuery();
		
		if(resultSet.next()) {
			
			Kategori kategori = new Kategori();
			
			kategori.setIdKategori(resultSet.getInt("id"));
			kategori.setNama(resultSet.getString("nama"));
			kategori.setSeoUrl(resultSet.getString("seo_url"));
			kategori.setKet(resultSet.getString("ket"));
			
			return kategori;
			
		}
		
		return null;
		
	}
	
	/**
	 * Mengambil kategori berdasarkan seo_url-nya
	 * 
	 * @return Kategori - Mengembalikan kategori yang dipilih berdasarkan seo_url-nya
	 * @throws SQLException 
	 */
	public Kategori getByURL(String url) throws SQLException {
		
		this.selectByURLStatement.setString(1, url);
		
		ResultSet resultSet = this.selectByURLStatement.executeQuery();
		
		if(resultSet.next()) {
			
			Kategori kategori = new Kategori();
			
			kategori.setIdKategori(resultSet.getInt("id"));
			kategori.setNama(resultSet.getString("nama"));
			kategori.setSeoUrl(resultSet.getString("seo_url"));
			kategori.setKet(resultSet.getString("ket"));
			
			return kategori;
			
		}
		
		return null;
		
	}

}

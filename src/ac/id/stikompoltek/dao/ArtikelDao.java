package ac.id.stikompoltek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ac.id.stikompoltek.dto.Artikel;
import ac.id.stikompoltek.dto.Kategori;
import ac.id.stikompoltek.dto.User;
import ac.id.stikompoltek.service.KategoriService;
import ac.id.stikompoltek.service.UserService;

public class ArtikelDao {
	
	private Connection connection; 
	
	private PreparedStatement insertStatement, updateStatement, deleteStatement, selectAllStatement, selectByIdStatement, selectByURLStatement;
	
	private final String insertQuery = "INSERT INTO artikel(judul, seo_url, isi, ket, kategori_id, user_id) VALUES(?, ?, ?, ?, ?, ?)";
	private final String updateQuery = "UPDATE artikel SET judul=?, seo_url=?, isi=?, ket=?, kategori_id=?, user_id=? WHERE id=?";
	private final String deleteQuery = "DELETE FROM artikel WHERE id=?";
	private final String selectAllQuery = "SELECT * FROM artikel";
	private final String selectByIdQuery = "SELECT * FROM artikel WHERE id=?";
	private final String selectByURLQuery = "SELECT * FROM artikel WHERE seo_url=?";
	
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
	 * Save artikel, jika id artikel sudah ada dalam database
	 * maka secara otomatis akan melakukan update
	 * 
	 * @param artikel - Artikel yang akan disimpan (baru) atau update
	 * @return Artikel - mengembalikan artikel yang berhasil di insert atau update
	 * @throws SQLException 
	 */
	public Artikel save(Artikel artikel) throws SQLException {
		
		int idArtikel = artikel.getIdArtikel();
		
		if(idArtikel == 0) { // insert
			
			this.insertStatement.setString(1, artikel.getJudul());
			this.insertStatement.setString(2, artikel.getSeoUrl());
			this.insertStatement.setString(3, artikel.getIsi());
			this.insertStatement.setString(4, artikel.getKet());
			this.insertStatement.setInt(5, artikel.getKategori().getIdKategori());
			this.insertStatement.setInt(6, artikel.getUser().getIdUser());
			
			int idUser = this.insertStatement.executeUpdate();
			artikel.setIdArtikel(idUser);
			
		} else { // update

			this.updateStatement.setString(1, artikel.getJudul());
			this.updateStatement.setString(2, artikel.getSeoUrl());
			this.updateStatement.setString(3, artikel.getIsi());
			this.updateStatement.setString(4, artikel.getKet());
			this.updateStatement.setInt(5, artikel.getKategori().getIdKategori());
			this.updateStatement.setInt(6, artikel.getUser().getIdUser());
			this.updateStatement.setInt(7, artikel.getIdArtikel());
			
			this.updateStatement.executeUpdate();
			
		}
		
		return artikel;
		
	}
	
	/**
	 * Hapus artikel dari database
	 * 
	 * @param artikel
	 * @return Artikel - Mengembalikan artikel yang berhasil dihapus
	 * @throws SQLException 
	 */
	public Artikel delete(Artikel artikel) throws SQLException {
		
		this.deleteStatement.setInt(1, artikel.getIdArtikel());
		
		this.deleteStatement.executeUpdate();
		
		return artikel;
		
	}
	
	/**
	 * Mengambil semua user
	 * 
	 * @return List : Users - Mengembalikan semua user yang ada dalam database
	 * @throws SQLException 
	 */
	public List<Artikel> getAll() throws SQLException {
		
		List<Artikel> artikelsList = new ArrayList<>();
		
		ResultSet resultSet = this.selectAllStatement.executeQuery();
		
		while(resultSet.next()) {
			
			Artikel artikel = new Artikel();
			
			// Kategori
			KategoriService kategoriService = new KategoriService();
			kategoriService.setConnection(this.connection);
			Kategori kategori = kategoriService.getById(resultSet.getInt("kategori_id"));
			
			// User
			UserService userService = new UserService();
			userService.setConnection(this.connection);
			User user = userService.getById(resultSet.getInt("user_id"));
			
			
			artikel.setIdArtikel(resultSet.getInt("id"));
			artikel.setJudul(resultSet.getString("judul"));
			artikel.setSeoUrl(resultSet.getString("seo_url"));
			artikel.setIsi(resultSet.getString("isi"));
			artikel.setKet(resultSet.getString("ket"));
			artikel.setKategori(kategori);
			artikel.setUser(user);
			
			artikelsList.add(artikel);
			
		}
		
		return artikelsList;
		
	}
	
	/**
	 * Mengambil artikel berdasarkan id-nya
	 * 
	 * @return Artikel - Mengembalikan artikel yang dipilih berdasarkan Id-nya
	 * @throws SQLException 
	 */
	public Artikel getById(int idArtikel) throws SQLException {
		
		this.selectByIdStatement.setInt(1, idArtikel);
		
		ResultSet resultSet = this.selectByIdStatement.executeQuery();
		
		if(resultSet.next()) {
			
			Artikel artikel = new Artikel();
			
			// Kategori
			KategoriService kategoriService = new KategoriService();
			kategoriService.setConnection(this.connection);
			Kategori kategori = kategoriService.getById(resultSet.getInt("kategori_id"));
			
			// User
			UserService userService = new UserService();
			userService.setConnection(this.connection);
			User user = userService.getById(resultSet.getInt("user_id"));
			
			artikel.setIdArtikel(resultSet.getInt("id"));
			artikel.setJudul(resultSet.getString("judul"));
			artikel.setSeoUrl(resultSet.getString("seo_url"));
			artikel.setIsi(resultSet.getString("isi"));
			artikel.setKet(resultSet.getString("ket"));
			artikel.setKategori(kategori);
			artikel.setUser(user);
			
			return artikel;
			
		}
		
		return null;
		
	}
	
	
	/**
	 * Mengambil artikel berdasarkan seo_url-nya
	 * 
	 * @return Artikel - Mengembalikan artikel yang dipilih berdasarkan seo_url-nya
	 * @throws SQLException 
	 */
	public Artikel getByURL(String url) throws SQLException {
		
		this.selectByURLStatement.setString(1, url);
		
		ResultSet resultSet = this.selectByURLStatement.executeQuery();
		
		if(resultSet.next()) {
			
			// User
			UserService userService = new UserService();
			userService.setConnection(this.connection);
			User user = userService.getById(resultSet.getInt("user_id"));
			
			// Kategori
			KategoriService kategoriService = new KategoriService();
			kategoriService.setConnection(this.connection);
			Kategori kategori = kategoriService.getById(resultSet.getInt("kategori_id"));
			
			Artikel artikel = new Artikel();
			
			artikel.setIdArtikel(resultSet.getInt("id"));
			artikel.setJudul(resultSet.getString("judul"));
			artikel.setSeoUrl(resultSet.getString("seo_url"));
			artikel.setIsi(resultSet.getString("isi"));
			artikel.setKet(resultSet.getString("ket"));
			artikel.setUser(user);
			artikel.setKategori(kategori);
			
			return artikel;
			
		}
		
		return null;
		
	}
	
}

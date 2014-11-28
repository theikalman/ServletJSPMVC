package ac.id.stikompoltek.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ac.id.stikompoltek.dao.ArtikelDao;
import ac.id.stikompoltek.dto.Artikel;

public class ArtikelService {
	
	private ArtikelDao artikelDao;
	private Connection connection;
	
	public void setConnection(Connection connection) {
		try {
			this.connection = connection;
			this.artikelDao = new ArtikelDao();
			this.artikelDao.setConnection(this.connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Simpan artikel, jika id artikel tidak kosong (0), maka akan secara otomatis mengupdate
	 * 
	 * @param artikel
	 * @return Artikel - mengembalikan artikel yang berhasil di simpan
	 */
	public Artikel save(Artikel artikel) {
		try {
			this.connection.setAutoCommit(false);
			this.artikelDao.save(artikel);
			this.connection.commit();
			this.connection.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				this.connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		}
		return artikel;
	}
	
	/**
	 * Delete artikel dari database
	 * 
	 * @param artikel
	 * @return Artikel - Mengembalikan artikel yang berhasil di delete
	 */
	public Artikel delete(Artikel artikel) {
		try {
			this.connection.setAutoCommit(false);
			this.artikelDao.delete(artikel);
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
		return artikel;
	}
	
	/**
	 * Mengambil semua data Artikel yang ada dalam database
	 * 
	 * @return List : Artikel - Mengembalikan semua data artikel
	 */
	public List<Artikel> getAll() {
		try {
			return this.artikelDao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}

	/**
	 * Mengambil artikel berdasarkan id-nya
	 * 
	 * @param idArtikel
	 * @return Artikel - Mengembalikan artikel yang terpilih
	 */
	public Artikel getById(int idArtikel) {
		try {
			return this.artikelDao.getById(idArtikel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Mengambil artikel berdasarkan seo_url-nya
	 * 
	 * @param urlArtikel
	 * @return Artikel - Mengembalikan artikel yang terpilih
	 */
	public Artikel getByURL(String urlArtikel) {
		try {
			return this.artikelDao.getByURL(urlArtikel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

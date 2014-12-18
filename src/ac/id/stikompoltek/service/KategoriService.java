package ac.id.stikompoltek.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ac.id.stikompoltek.dao.KategoriDao;
import ac.id.stikompoltek.dto.Kategori;

public class KategoriService {
	
	private KategoriDao kategoriDao;
	private Connection connection;
	
	public void setConnection(Connection connection) {
		try {
			this.connection = connection;
			this.kategoriDao = new KategoriDao();
			this.kategoriDao.setConnection(this.connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Save kategori, set idKategori to 0 if you want to insert it as new data
	 * 
	 * @param kategori
	 * @return
	 */
	public Kategori save(Kategori kategori) {
		
		try {
			this.connection.setAutoCommit(false);
			this.kategoriDao.save(kategori);
			this.connection.commit();
			this.connection.setAutoCommit(true);
			return kategori;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		}
		
	}
	
	/**
	 * Delete kategori dari database
	 * 
	 * @param kategori
	 * @return Kategori - Mengembalikan kategori yang berhasil di delete
	 */
	public Kategori delete(Kategori kategori) {
		try {
			this.connection.setAutoCommit(false);
			this.kategoriDao.delete(kategori);
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
		return kategori;
	}
	
	/**
	 * Mengambil semua data Kategori yang ada dalam database
	 * 
	 * @return List : Kategori - Mengembalikan semua data kategori
	 */
	public List<Kategori> getAll() {
		try {
			return this.kategoriDao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}

	/**
	 * Mengambil kategori berdasarkan id-nya
	 * 
	 * @param idKategori
	 * @return Kategori - Mengembalikan kategori yang terpilih
	 */
	public Kategori getById(int idKategori) {
		try {
			return this.kategoriDao.getById(idKategori);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Mengambil kategori berdasarkan seo_url-nya
	 * 
	 * @param urlKategori
	 * @return Kategori - Mengembalikan kategori yang terpilih
	 */
	public Kategori getByURL(String urlKategori) {
		try {
			return this.kategoriDao.getByURL(urlKategori);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

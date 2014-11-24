package ac.id.stikompoltek.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ac.id.stikompoltek.dao.TagDao;
import ac.id.stikompoltek.dto.Tag;

public class TagService {
	
	private TagDao tagDao;
	private Connection connection;
	
	public void setConnection(Connection connection) {
		try {
			this.connection = connection;
			this.tagDao = new TagDao();
			this.tagDao.setConnection(this.connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Simpan tag baru
	 * 
	 * @param tag
	 * @return Tag - Mengembalikan tag yang berhasil di insert
	 */
	public Tag insert(Tag tag) {
		try {
			this.connection.setAutoCommit(false);
			this.tagDao.insert(tag);
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
		return tag;
	}
	
	/**
	 * Update tag
	 * 
	 * @param tag
	 * @return Tag - Mengembalikan tag yang berhasil di update
	 */
	public Tag update(Tag tag) {
		try {
			this.connection.setAutoCommit(false);
			this.tagDao.update(tag);
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
		return tag;
	}
	
	/**
	 * Delete tag dari database
	 * 
	 * @param tag
	 * @return Tag - Mengembalikan tag yang berhasil di delete
	 */
	public Tag delete(Tag tag) {
		try {
			this.connection.setAutoCommit(false);
			this.tagDao.delete(tag);
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
		return tag;
	}
	
	/**
	 * Mengambil semua data Tag yang ada dalam database
	 * 
	 * @return List : Tag - Mengembalikan semua data tag
	 */
	public List<Tag> getAll() {
		try {
			return this.tagDao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}

	/**
	 * Mengambil tag berdasarkan id-nya
	 * 
	 * @param idTag
	 * @return Tag - Mengembalikan tag yang terpilih
	 */
	public Tag getById(int idTag) {
		try {
			return this.tagDao.getById(idTag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Mengambil tag berdasarkan seo_url-nya
	 * 
	 * @param urlTag
	 * @return Tag - Mengembalikan tag yang terpilih
	 */
	public Tag getByURL(String urlTag) {
		try {
			return this.tagDao.getByURL(urlTag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

package ac.id.stikompoltek.dto;

/**
 * User Model untuk tabel Kategori
 * @author aji
 *
 */
public class Kategori {
	
	private int idKategori;
	private String nama;
	private String seoUrl;
	private String ket;
	
	public int getIdKategori() { return idKategori; }
	
	public void setIdKategori(int idKategori) { this.idKategori = idKategori; }
	
	public String getNama() { return nama; }
	
	public void setNama(String nama) { this.nama = nama; }
	
	public String getSeoUrl() { return seoUrl; }
	
	public void setSeoUrl(String seoUrl) { this.seoUrl = seoUrl; }
	
	public String getKet() { return ket; }
	
	public void setKet(String ket) { this.ket = ket; }	

}

package ac.id.stikompoltek.dto;


/**
 * User Model untuk tabel Artikel
 * @author aji
 *
 */
public class Artikel {
	
	private int idArtikel;
	private String judul;
	private String seoUrl;
	private String isi;
	private String ket;
	private Kategori kategori;
	private User user;
	
	public int getIdArtikel() { return idArtikel; }
	
	public void setIdArtikel(int idArtikel) { this.idArtikel = idArtikel; }
	
	public String getJudul() { return judul; }
	
	public void setJudul(String judul) { this.judul = judul; }
	
	public String getSeoUrl() { return seoUrl; }
	
	public void setSeoUrl(String seoUrl) { this.seoUrl = seoUrl; }
	
	public String getIsi() { return isi; }
	
	public void setIsi(String isi) { this.isi = isi; }
	
	public String getKet() { return ket; }
	
	public void setKet(String ket) { this.ket = ket; }
	
	public Kategori getKategori() { return kategori; }
	
	public void setKategori(Kategori kategori) { this.kategori = kategori; }
	
	public User getUser() { return user; }
	
	public void setUser(User user) { this.user = user; }

}

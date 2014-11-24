package ac.id.stikompoltek.dto;

/**
 * User Model untuk tabel Tag
 * @author aji
 *
 */
public class Tag {
	
	private int idTag;
	private String nama;
	private String seoUrl;
	private String ket;
	
	public int getIdTag() { return idTag; }
	
	public void setIdTag(int idTag) { this.idTag = idTag; }
	
	public String getNama() { return nama; }
	
	public void setNama(String nama) { this.nama = nama; }
	
	public String getSeoUrl() { return seoUrl; }
	
	public void setSeoUrl(String seoUrl) { this.seoUrl = seoUrl; }
	
	public String getKet() { return ket; }
	
	public void setKet(String ket) { this.ket = ket; }	

}

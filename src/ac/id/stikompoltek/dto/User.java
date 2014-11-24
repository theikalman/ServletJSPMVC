package ac.id.stikompoltek.dto;

/**
 * User Model untuk tabel User
 * @author aji
 *
 */
public class User {
	
	private int idUser;
	private String username;
	private String password;
	private String namaLengkap;
	
	public int getIdUser() { return idUser; }
	
	public void setIdUser(int idUser) { this.idUser = idUser; }
	
	public String getUsername() { return username; }
	
	public void setUsername(String username) { this.username = username; }
	
	public String getPassword() { return password; }
	
	public void setPassword(String password) { this.password = password; }
	
	public String getNamaLengkap() { return namaLengkap; }
	
	public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }

}

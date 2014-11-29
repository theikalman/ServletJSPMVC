package ac.id.stikompoltek;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ac.id.stikompoltek.dto.Artikel;
import ac.id.stikompoltek.dto.Kategori;
import ac.id.stikompoltek.dto.User;
import ac.id.stikompoltek.service.ArtikelService;
import ac.id.stikompoltek.service.KategoriService;
import ac.id.stikompoltek.service.UserService;

/**
 * Servlet implementation class Testong
 */
@WebServlet(description = "Kelas untuk testing", urlPatterns = { "/testong" })
public class Testong extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connection = (Connection) request.getServletContext().getAttribute("mysqlConnection");
		
		// Test
		UserService userService = new UserService();
		userService.setConnection(connection);
		User user = userService.getById(1);
		
		KategoriService kategoriService = new KategoriService();
		kategoriService.setConnection(connection);
		Kategori kategori = kategoriService.getById(1);
		
		
		ArtikelService artikelService = new ArtikelService();
		artikelService.setConnection(connection);
		
		Artikel artikel = artikelService.getById(4);
		artikel.setJudul("Atit");
		artikel.setSeoUrl("atit");
		artikel.setIsi("Isi atit bro :)");
		artikel.setKet("Ket");
		artikel.setUser(user);
		artikel.setKategori(kategori);
		
		artikelService.save(artikel);
		
	}

}

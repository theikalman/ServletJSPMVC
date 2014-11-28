package ac.id.stikompoltek;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class Artikel
 */
@WebServlet("/artikel")
public class ArtikelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Check login
		boolean isLoggedIn = (request.getSession().getAttribute("userLoggedIn") == null) ? false : true;
		
		if(isLoggedIn) {
			
			String act = request.getParameter("act");
			
			if(act != null) {
				
				int idArtikel = Integer.valueOf(request.getParameter("id-artikel"));
				
				Connection connection = (Connection) request.getServletContext().getAttribute("mysqlConnection");
				
				// Kategori
				KategoriService kategoriService = new KategoriService();
				kategoriService.setConnection(connection);
				Kategori kategori = kategoriService.getById(Integer.valueOf(request.getParameter("id-kategori")));
				
				// User
				UserService userService = new UserService();
				userService.setConnection(connection);
				User user = (User) request.getSession().getAttribute("userLoggedIn");
				
				// Artikel
				ArtikelService artikelService = new ArtikelService();
				artikelService.setConnection(connection);
				
				Artikel artikel = artikelService.getById(idArtikel);
				
				artikel.setJudul(request.getParameter("judul-artikel"));
				artikel.setKategori(kategori);
				artikel.setUser(user);
				artikel.setKet(request.getParameter("ket-artikel"));
				artikel.setIsi(request.getParameter("isi-artikel"));
				
				// Execute save
				artikelService.save(artikel);
				
				response.sendRedirect(request.getServletContext().getInitParameter("BASE_URL") + "/artikel");
				
			}
			
		} else {
			response.sendRedirect(request.getServletContext().getInitParameter("BASE_URL") + "/login");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connection = (Connection) request.getServletContext().getAttribute("mysqlConnection");
		
		ArtikelService artikelService = new ArtikelService();
		KategoriService kategoriService = new KategoriService();
		
		artikelService.setConnection(connection);
		kategoriService.setConnection(connection);
		
		// Check http parameter
		String action = (String) request.getParameter("act");
		String artikelUrl = (String) request.getParameter("url");
		
		if(action != null && artikelUrl != null) {
			
			Artikel artikel = artikelService.getByURL(artikelUrl);
			List<Kategori> kategoris = kategoriService.getAll();
			
			switch (action) {
			case "edit":
				// Get selected artikel
				request.setAttribute("selectedArtikel", artikel);
				request.setAttribute("listKategori", kategoris);
				RequestDispatcher dispatcher = request.getRequestDispatcher("admin/artikel_edit.jsp");
				dispatcher.forward(request, response);
				return;
				
			case "delete":
				// Get selected artikel
				artikelService.delete(artikel);
				// redirect to artikel list
				response.sendRedirect(request.getServletContext().getInitParameter("BASE_URL") + "/artikel");
				return;

			default:
				break;
			}
		}
		
		// Get artikel data
		List<Artikel> artikelList = artikelService.getAll();
		request.setAttribute("artikelList", artikelList);
		
		// Forward to the view
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin/artikel.jsp");
		dispatcher.forward(request, response);
		
	}

}

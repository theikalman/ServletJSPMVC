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
				
				// Get post data
				String idArtikelPost = request.getParameter("id-artikel");
				String idKategoriPost = request.getParameter("id-kategori");
				String judulArtikelPost = request.getParameter("judul-artikel");
				// TODO You have to add label handler here...
				String ketKategoriPost = request.getParameter("ket-artikel");
				String isiArtikelPost = request.getParameter("isi-artikel");
				
				// Set advanced data
				int idArtikel = (idArtikelPost != null) ? Integer.valueOf(request.getParameter("id-artikel")) : 0;
				String seoUrl = judulArtikelPost.toLowerCase().replace(" ", "-");
				
				// Get connection
				Connection connection = (Connection) request.getServletContext().getAttribute("mysqlConnection");
				
				// Get Kategori from database
				KategoriService kategoriService = new KategoriService();
				kategoriService.setConnection(connection);
				Kategori kategori = kategoriService.getById(Integer.valueOf(idKategoriPost));
				
				// Get User from database
				UserService userService = new UserService();
				userService.setConnection(connection);
				User currentUser = (User) request.getSession().getAttribute("userLoggedIn");
				
				// Artikel
				ArtikelService artikelService = new ArtikelService();
				artikelService.setConnection(connection);
				
				Artikel artikel = new Artikel();
				
				// If idArtikel != 0 update data, otherwise insert as new record
				if(idArtikel != 0) {					
					artikel = artikelService.getById(idArtikel);
				}
				
				artikel.setIdArtikel(idArtikel);
				artikel.setJudul(judulArtikelPost);
				artikel.setSeoUrl(seoUrl);
				artikel.setKategori(kategori);
				artikel.setUser(currentUser);
				artikel.setKet(ketKategoriPost);
				artikel.setIsi(isiArtikelPost);
				
				// Execute save
				artikelService.save(artikel);
				
				// Send to the view
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
		
		// Get connection from ServletContext scope
		Connection connection = (Connection) request.getServletContext().getAttribute("mysqlConnection");
		RequestDispatcher dispatcher;
		
		ArtikelService artikelService = new ArtikelService();
		KategoriService kategoriService = new KategoriService();
		
		// Set connection for every Service
		artikelService.setConnection(connection);
		kategoriService.setConnection(connection);
		
		// Check http parameter
		String action = (String) request.getParameter("act");
		String artikelUrl = (String) request.getParameter("url");
		
		if(action != null) {
			
			Artikel artikel = artikelService.getByURL(artikelUrl);
			List<Kategori> kategoris = kategoriService.getAll();
			
			switch (action) {
			case "add":
				request.setAttribute("listKategori", kategoris);
				dispatcher = request.getRequestDispatcher("admin/artikel_new.jsp");
				dispatcher.forward(request, response);
				return;
				
			case "edit":
				if(artikelUrl != null){
					// Get selected artikel
					request.setAttribute("selectedArtikel", artikel);
					request.setAttribute("listKategori", kategoris);
					dispatcher = request.getRequestDispatcher("admin/artikel_edit.jsp");
					dispatcher.forward(request, response);
					return;
				}
				
			case "delete":
				if(artikelUrl != null) {
					// Get selected artikel
					artikelService.delete(artikel);
					// redirect to artikel list
					response.sendRedirect(request.getServletContext().getInitParameter("BASE_URL") + "/artikel");
					return;
				}

			default:
				break;
			}
		} 
		
		
		// When no action on http parameter, then show all articles
		
		// Get artikel data
		List<Artikel> artikelList = artikelService.getAll();
		request.setAttribute("artikelList", artikelList);
		
		// Forward to the view
		dispatcher = request.getRequestDispatcher("admin/artikel.jsp");
		dispatcher.forward(request, response);
		
	}

}

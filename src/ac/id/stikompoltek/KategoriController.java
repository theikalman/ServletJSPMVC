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
import ac.id.stikompoltek.service.ArtikelService;
import ac.id.stikompoltek.service.KategoriService;

@WebServlet("/admin/kategori")
public class KategoriController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// Check login
		boolean isLoggedIn = (request.getSession().getAttribute("userLoggedIn") == null) ? false : true;

		if (isLoggedIn) {
			
			// Connection
			Connection connection = (Connection) request.getServletContext().getAttribute("mysqlConnection");
			
			// Kategori Service
			KategoriService kategoriService = new KategoriService();
			kategoriService.setConnection(connection);
			
			// Get action on post parameter
			String act = request.getParameter("act");
			
			if(act != null) {
				switch (act) {
				case "save":
					Kategori kategori = new Kategori();
					int idKategori = (request.getParameter("id-kategori") != null) ? Integer.parseInt(request.getParameter("id-kategori")) : 0 ;
					kategori.setIdKategori(idKategori);
					kategori.setNama(request.getParameter("nama-kategori"));
					kategori.setSeoUrl(kategori.getNama().toLowerCase().replace(" ", "-"));
					kategori.setKet(request.getParameter("ket-kategori"));
					kategoriService.save(kategori);
					break;

				default:
					break;
				}
			}
			
			// Send to the view
			response.sendRedirect(request.getServletContext().getInitParameter("BASE_URL") + "/admin/kategori");
			
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Dispatcher for forwading
		RequestDispatcher dispatcher;

		// Connection
		Connection connection = (Connection) request.getServletContext().getAttribute("mysqlConnection");

		// Kategori Service
		KategoriService kategoriService = new KategoriService();
		kategoriService.setConnection(connection);

		// Check login
		boolean isLoggedIn = (request.getSession().getAttribute("userLoggedIn") == null) ? false : true;

		if (isLoggedIn) {
			// Check http parameter
			String act = request.getParameter("act");
			String url = request.getParameter("url");
			if (act != null) {
				switch (act) {
				case "add":
					dispatcher = request.getRequestDispatcher("kategori_new.jsp");
					dispatcher.forward(request, response);
					return;

				case "edit":
					if(url != null) {
						// Get selected kategori
						Kategori kategori = kategoriService.getByURL(url);
						// Set data for view
						request.setAttribute("kategoriSelected", kategori);
						dispatcher = request.getRequestDispatcher("kategori_edit.jsp");
						dispatcher.forward(request, response);
						return;
					}
					break;

				case "delete":
					// Artikel Service
					ArtikelService artikelService = new ArtikelService();
					artikelService.setConnection(connection);
					
					if(url != null) {
						
						Kategori kategori = kategoriService.getByURL(url);
						
						// Get artikels who use this kategori
						List<Artikel> artikels = artikelService.getByIdKategori(kategori.getIdKategori());
						// Delete if no one artikel that who use this artikel
						if(artikels.isEmpty()) {
							kategoriService.delete(kategori);							
						}
						
					}
					break;

				default:
					break;
				}
			}

			// Show for all kategori if no act value on http parameter
			dispatcher = request.getRequestDispatcher("kategori.jsp");

			// Get all kategori data
			List<Kategori> kategoris = kategoriService.getAll();

			// Set data for view
			request.setAttribute("kategoriList", kategoris);

			// Forward to the view
			dispatcher.forward(request, response);
			return;

		}

		// Go to login page when no logged in user is
		response.sendRedirect(request.getServletContext().getInitParameter("BASE_URL") + "/admin/login");
	}

}

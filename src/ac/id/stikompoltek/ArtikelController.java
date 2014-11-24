package ac.id.stikompoltek;

import java.io.IOException;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ac.id.stikompoltek.dto.Artikel;
import ac.id.stikompoltek.service.ArtikelService;

/**
 * Servlet implementation class Artikel
 */
@WebServlet("/artikel")
public class ArtikelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connection = (Connection) request.getServletContext().getAttribute("mysqlConnection");
		ArtikelService artikelService = new ArtikelService();
		artikelService.setConnection(connection);
		
		// Check http parameter
		String action = (String) request.getParameter("act");
		String artikelUrl = (String) request.getParameter("url");
		if(action != null && artikelUrl != null) {
			Artikel artikel = artikelService.getByURL(artikelUrl);
			switch (action) {
			case "edit":
				// Get selected artikel
				request.setAttribute("selectedArtikel", artikel);
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

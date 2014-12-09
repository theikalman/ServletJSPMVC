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

import ac.id.stikompoltek.dto.Kategori;
import ac.id.stikompoltek.service.KategoriService;

@WebServlet("/kategori")
public class KategoriController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

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
					dispatcher = request.getRequestDispatcher(request.getServletContext().getInitParameter("BASE_URL") + "/admin/kategori_add.jsp");
					dispatcher.forward(request, response);
					return;

				case "update":
					if(url != null) {
						// Get selected kategori
						Kategori kategori = kategoriService.getByURL(url);
						// Set data for view
						request.setAttribute("kategoriSelected", kategori);
						dispatcher = request.getRequestDispatcher(request.getServletContext().getInitParameter("BASE_URL") + "/admin/kategori_edit.jsp");
						dispatcher.forward(request, response);
						return;
					}
					break;

				case "delete":
					if(url != null) {
						Kategori kategori = kategoriService.getByURL(url);
						kategoriService.delete(kategori);
					}
					break;

				default:
					break;
				}
			}

			// Show for all kategori if no act value on http parameter
			dispatcher = request.getRequestDispatcher(request.getServletContext().getInitParameter("BASE_URL") + "/admin/kategori.jsp");

			// Get all kategori data
			List<Kategori> kategoris = kategoriService.getAll();

			// Set data for view
			request.setAttribute("kategoriList", kategoris);

			// Forward to the view
			dispatcher.forward(request, response);
			return;

		}

		// Go to login page when no logged in user is
		response.sendRedirect(request.getServletContext().getInitParameter("BASE_URL") + "/login");
	}

}

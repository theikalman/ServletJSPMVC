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

import ac.id.stikompoltek.dto.Tag;
import ac.id.stikompoltek.service.TagService;

/**
 * Servlet implementation class TagController
 */
@WebServlet(description = "Tag Controller", urlPatterns = { "/admin/tag" })
public class TagController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check login
		boolean isLoggedIn = (request.getSession().getAttribute("userLoggedIn") == null) ? false : true;
		if(isLoggedIn) {
			// For redirect to the view
			RequestDispatcher dispatcher;
			// Connection and service
			Connection connection = (Connection) request.getServletContext().getAttribute("mysqlConnection");
			TagService tagService = new TagService();
			tagService.setConnection(connection);
			
			Tag tag = new Tag();
			tag = tagService.getByURL(request.getParameter("url"));
			
			// Check request parameter
			String act = (request.getParameter("act") != null) ? request.getParameter("act") : "" ;
			switch(act) {
			case "add":
				dispatcher = request.getRequestDispatcher("tag_new.jsp");
				dispatcher.forward(request, response);
				return;
			case "edit":
				dispatcher = request.getRequestDispatcher("tag_edit.jsp");
				request.setAttribute("tagSelected", tag);
				dispatcher.forward(request, response);
				return;
			case "delete":
				tagService.delete(tag);
				response.sendRedirect(request.getServletContext().getInitParameter("BASE_URL") + "/admin/tag");
				return;
			default:
				List<Tag> tags = tagService.getAll();
				dispatcher = request.getRequestDispatcher("tag.jsp");
				request.setAttribute("tags", tags);
				dispatcher.forward(request, response);
				break;
			}
			
		} else {			
			response.sendRedirect(request.getServletContext().getInitParameter("BASE_URL") + "/admin/login");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Check login
		boolean isLoggedIn = (request.getSession().getAttribute("userLoggedIn") == null) ? false : true;
		if(isLoggedIn) {
			// Connection and service
			Connection connection = (Connection) request.getServletContext().getAttribute("mysqlConnection");
			TagService tagService = new TagService();
			tagService.setConnection(connection);
			// Get post data
			String act = (request.getParameter("act") != null) ? request.getParameter("act") : "";
			Tag tag = new Tag();
			tag.setIdTag((request.getParameter("id-tag") != null) ? Integer.parseInt(request.getParameter("id-tag")) : 0);
			tag.setNama(request.getParameter("nama-tag"));
			tag.setSeoUrl(tag.getNama().replace(" ", "-").toLowerCase());
			tag.setKet(request.getParameter("ket-tag"));
			// Debugging
			System.out.println(request.getParameter("id-tag"));
			System.out.println("Id : " + tag.getIdTag());
			System.out.println("Nama : " + tag.getNama());
			System.out.println("Seo URL : " + tag.getSeoUrl());
			System.out.println("Ket : " + tag.getKet());
			// Switch action
			switch(act) {
			case "save":
				tagService.save(tag);
				response.sendRedirect(request.getServletContext().getInitParameter("BASE_URL") + "/admin/tag");
				return;
			default:
				break;
			}
		} else {			
			response.sendRedirect(request.getServletContext().getInitParameter("BASE_URL") + "/admin/login");
		}
	}

}

package ac.id.stikompoltek;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ac.id.stikompoltek.dto.User;
import ac.id.stikompoltek.service.UserService;

/**
 * Servlet implementation class Login
 */
@WebServlet(description="Deskripsi :)", urlPatterns="/admin/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forward to the view
		RequestDispatcher dispathcer = request.getRequestDispatcher("login.jsp");
		dispathcer.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection connection = (Connection) request.getServletContext().getAttribute("mysqlConnection");
		
		String username, password;
		username = request.getParameter("username");
		password = request.getParameter("password");
		
		User user = new User();
		
		UserService userService = new UserService();
		userService.setConnection(connection);
		user = userService.getByUsername(username);
		
		// autentikasi
		if(user != null && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
			// daftarkan di session
			HttpSession session = request.getSession();
			session.setAttribute("userLoggedIn", user);
			// Redirect to get method
			response.sendRedirect(request.getServletContext().getInitParameter("BASE_URL") + "/admin/dashboard");
		} else {
			// Redirect to get method
			response.sendRedirect(request.getRequestURL().toString());
		}
		
	}

}

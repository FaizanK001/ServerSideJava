package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.FilmDAO;

/**
 * Servlet implementation class AddFilm
 */
@WebServlet("/AddFilm")
public class AddFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFilm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub
	 * response.getWriter().append("Served at: ").append(request.getContextPath());
	 * }
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// Class FilmDAO Object
			FilmDAO dao = new FilmDAO();

			// Request the parameter and then pass in the database.
			String id = request.getParameter("fid");
			String title = request.getParameter("ftitle");
			String year = request.getParameter("fyear");
			String director = request.getParameter("fdirector");
			String stars = request.getParameter("fstars");
			String review = request.getParameter("freview");
			// change the string value into int
			int filmid = Integer.parseInt(id);
			int filmyear = Integer.parseInt(year);

			dao.addFilm(filmid, title, filmyear, director, stars, review);
			PrintWriter out = response.getWriter();
			out.println("Film Added");

		} catch (Exception e) {
			e.printStackTrace();
		}
		// doGet(request, response);

	}

}

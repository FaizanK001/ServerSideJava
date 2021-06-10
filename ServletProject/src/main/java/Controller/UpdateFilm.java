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
 * Servlet implementation class UpdateFilm
 */
@WebServlet("/UpdateFilm")
public class UpdateFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateFilm() {
		super();

		FilmDAO dao = new FilmDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			FilmDAO dao = new FilmDAO();
			// Get the parameter and update the movie
			String id = request.getParameter("updateid");
			String title = request.getParameter("updatetitle");
			String year = request.getParameter("updateyear");
			String director = request.getParameter("updatedirector");
			String stars = request.getParameter("updatestars");
			String review = request.getParameter("updatereview");

			int filmid = Integer.parseInt(id);
			int filmyear = Integer.parseInt(year);
			// recall the updatefilm method from FilmDAO class to update the movie
			dao.updateFilm(filmid, title, filmyear, director, stars, review);
			PrintWriter out = response.getWriter();
			out.println("updated Added");

		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}

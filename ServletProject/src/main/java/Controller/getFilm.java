package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Model.Film;
import Model.FilmDAO;
import Model.FilmStore;

/**
 * Servlet implementation class getFilm
 */
@WebServlet("/getFilm")
public class getFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getFilm() {
		super();
		// TODO Auto-generated constructor stub
	}

	String add;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		// Parameter used get film by id and name
		String strId = request.getParameter("getFilm");
		String strName = request.getParameter("getFilm");

		// parameter used to change the datatype
		String strFormat = request.getParameter("format");
		// parameter used for film and and name
		String strData = request.getParameter("data");

		// library used to convert string into json
		Gson gson = new Gson();
		// empty variable
		String outputpage = null;
		// Film class Object
		Film f = new Film();
		// FilmDAO clas object
		FilmDAO fd = new FilmDAO();
		// Filmstore class Object
		FilmStore filmstore = new FilmStore();

		// if and else condition if the user choice filmid from combo box search film by
		// id else by name
		if ("filmid".equals(strData)) {

			f = fd.getFilmByID(Integer.parseInt(strId));
		} else if ("filmname".equals(strData)) {

			f = fd.getFilmByNAME(strName);

		}

		// if and else condition for json, xml and string format
		if ("json".equals(strFormat)) {

			String Json = gson.toJson(f);
			request.setAttribute("json", Json);
			response.setContentType("application/json");
			outputpage = "View/json.jsp";

		} else if ("xml".equals(strFormat)) {

			request.setAttribute("film", f);
			response.setContentType("text/xml");
			outputpage = "View/xml-film.jsp";

		} else if ("text".equals(strFormat)) {

			response.setContentType("text/plain");
			request.setAttribute("film", f);
			outputpage = "View/String.jsp";

		} else if ("xml".equals(strFormat)) {
			outputpage = "View/String.jsp";
			response.setContentType("text/plain");
			request.setAttribute("film", f);

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(outputpage);
		dispatcher.forward(request, response);

		// System.out.println("Film retrieve");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		add = request.getParameter("postFilm");

	}

}

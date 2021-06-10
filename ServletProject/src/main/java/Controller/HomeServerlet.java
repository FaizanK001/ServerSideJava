package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;

import Model.Film;
import Model.FilmDAO;
import Model.FilmStore;

/**
 * Servlet implementation class HomeServerlet
 */
@WebServlet("/HomeServlet")
public class HomeServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String format;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServerlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Cache-Control", "no-cache");
		
		
		

		PrintWriter	pw = response.getWriter();	
		format = request.getParameter("data-format");
		System.out.println(format);
		
		// FilmDao object
		FilmDAO dao= new FilmDAO();
		//Connect to the Database to get all  Films
		ArrayList<Film> films = dao.getAllFilms();
		//Film Store object
		FilmStore fs = new FilmStore();
		
	 // Convert Arraylist to json
		Gson gson = new Gson();
		
		// address to jsp file in view folder
		String data = "", address="";
		
	if(format.equals("json")) {
		
			data = gson.toJson(films);
			address = "json";
			response.setContentType("application/json");
			request.setAttribute("json",data);

			RequestDispatcher dispatcher = request.getRequestDispatcher("View/"+address+".jsp");
			dispatcher.forward(request, response);
				
		}
		
	else if(format.equals("xml")){
			try {
				JAXBContext con = JAXBContext.newInstance(FilmStore.class);
				Marshaller marshaller= con.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				fs.setAllFilm(films);
				
				marshaller.marshal(fs, pw);
			}catch(Exception e) {
				System.out.print(e);
			}	
		}
		
    else if(format.equals("text")) {
			address = "String";
		
			response.setContentType("text/plain");
			request.setAttribute("film",films);
			

			RequestDispatcher dispatcher = request.getRequestDispatcher("View/"+address+".jsp");
			dispatcher.forward(request, response);
				
			
		}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		doGet(request, response);
	}

}

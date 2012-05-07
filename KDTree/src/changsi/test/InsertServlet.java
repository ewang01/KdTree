package changsi.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
 // test string {"x":5, "y":4, "id":"123456"}
    private KdNode parse_param(String json) throws Exception{
    	JSONParser parser=new JSONParser();
    	Object obj=parser.parse(json);
    	JSONObject object=(JSONObject)obj;
    	double x = Double.valueOf(String.valueOf(object.get("x")));
		double y = Double.valueOf(String.valueOf(object.get("y")));
		String id = String.valueOf(object.get("id"));
		KdNode node = new KdNode( x, y, id);
    	return node;

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			ServletContext app = request.getServletContext();
			KdTree tree =  (KdTree) app.getAttribute("kdTree");
	    	KdNode node = parse_param(request.getParameter("data"));
	    	tree.insert(node);
	        PrintWriter out = response.getWriter();
	 		out.println("true");
	 		
		}catch (Exception e){
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

package changsi.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import changsi.test.KdNode;;

/**
 * Servlet implementation class SetHashMap
 */
@WebServlet("/build")
public class BuildKdTree extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuildKdTree() {
        super();
        // TODO Auto-generated constructor stub
    }
    // test string [{"longitude":5, "latitude":4, "location_id":"123456"},{"longitude":9, "latitude":6, "location_id":"1234567"},{"longitude":4, "latitude":7, "location_id":"12345678"},{"longitude":8, "latitude":1, "location_id":"123456789"}]
    private ArrayList<KdNode> parse_param(String json) throws Exception{
    	ArrayList<KdNode> list = new ArrayList<KdNode> ();
    	JSONParser parser=new JSONParser();
    	
    	Object obj=parser.parse(json);
    	JSONArray array=(JSONArray)obj;
    	for(int i=0; i<array.size();i++){
    		JSONObject tmp = (JSONObject)array.get(i);
    		double x = Double.valueOf(String.valueOf(tmp.get("longitude")));
    		double y = Double.valueOf(String.valueOf(tmp.get("latitude")));
    		String id = String.valueOf(tmp.get("location_id"));
    		KdNode node = new KdNode( x, y, id);
    		list.add(node);
    	}
    	
    	return list;

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String data = (String) request.getParameter("data");
		try{
			ServletContext app = request.getServletContext();

			KdTree tree =  (KdTree) app.getAttribute("kdTree");
			
			//System.out.println( "Starting program" );
			//System.out.println(request.getParameter("data"));
			
	    	ArrayList<KdNode> list = parse_param(request.getParameter("data"));
	    	
	    	tree.build(list);

	        PrintWriter out = response.getWriter();
	 		out.println("1");
	 		
		}catch (Exception e){
			
		}
		
		
    	

        
       
		
		//rintWriter out = response.getWriter();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{
			ServletContext app = request.getServletContext();

			KdTree tree =  (KdTree) app.getAttribute("kdTree");
			
			//System.out.println( "Starting program" );
			System.out.println(request.getParameter("data"));
			
	    	ArrayList<KdNode> list = parse_param(request.getParameter("data"));
	    	
	    	tree.build(list);

	        PrintWriter out = response.getWriter();
	 		out.println("finish building kd tree");
	 		
		}catch (Exception e){
			
		}
	}

}

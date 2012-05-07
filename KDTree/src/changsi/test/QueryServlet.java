package changsi.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



@SuppressWarnings("serial")
public class QueryServlet extends HttpServlet {
	
	 // test string {"low_x":4, "low_y":3, "high_x":8, "high_y":6}
    private Double[][] parse_param(String json) throws Exception{
    	Double[][] list = new Double[2][2];
    	JSONParser parser=new JSONParser();
    	
    	Object obj=parser.parse(json);
    	JSONObject query=(JSONObject)obj;
    	list[0][0] = Double.valueOf(String.valueOf(query.get("low_x")));
    	list[0][1] = Double.valueOf(String.valueOf(query.get("low_y")));
    	list[1][0] = Double.valueOf(String.valueOf(query.get("high_x")));
    	list[1][1] = Double.valueOf(String.valueOf(query.get("high_y")));
    	return list;

    }
    
    private String generate_result(ArrayList<KdNode> result){
    	if(result.size()==0 || result == null){
    		return "[]";
    	}
    	StringBuffer json = new StringBuffer();
    	json.append("[");
    	for(int i=0; i<result.size()-1; i++){
    		json.append(result.get(i).toString());
    		json.append(",");
    	}
    	json.append(result.get(result.size()-1).toString());
    	json.append("]");
    	return json.toString();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String data = (String) request.getParameter("data");
		//System.out.println(data);
		Double[][] query;
		try {
			query = this.parse_param(data);
			ServletContext app = request.getServletContext();

			KdTree tree =  (KdTree) app.getAttribute("kdTree");
			ArrayList<KdNode> result = tree.rangeQuery( query[0], query[1] );
			
			PrintWriter out = response.getWriter();
			String json = generate_result(result);
			out.println(json);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	    
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

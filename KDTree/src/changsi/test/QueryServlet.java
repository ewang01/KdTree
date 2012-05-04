package changsi.test;

import java.io.IOException;
import java.io.PrintWriter;
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



@SuppressWarnings("serial")
public class QueryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Double low1 = Double.valueOf((String) request.getParameter("low1"));
		Double low2 = Double.valueOf((String) request.getParameter("low2"));
		Double high1 = Double.valueOf((String) request.getParameter("high1"));
		Double high2 = Double.valueOf((String) request.getParameter("high2"));
		Double [] low = new Double[2];
		Double [] high = new Double[2];
		low[0] = low1;
		low[1] = low2;
		high[0] = high1;
		high[1] = high2;
		ServletContext app = request.getServletContext();

		KdTree tree =  (KdTree) app.getAttribute("kdTree");
		String result = tree.printRange( low, high );
		
		PrintWriter out = response.getWriter();
		out.println(result);
	    
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

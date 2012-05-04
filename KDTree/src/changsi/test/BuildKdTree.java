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

import changsi.test.KdTree.KdNode;

/**
 * Servlet implementation class SetHashMap
 */
@WebServlet("/buildtree")
public class BuildKdTree extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuildKdTree() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String data = (String) request.getParameter("data");

		ServletContext app = request.getServletContext();

		KdTree tree =  (KdTree) app.getAttribute("kdTree");
		
		System.out.println( "Starting program" );

    	ArrayList<KdNode> list = new ArrayList<KdNode> ();
    	
    	KdNode node = new KdNode(2,3,"n1");
    	list.add(node);
    	node = new KdNode(5,4,"n2");
    	list.add(node);
    	node = new KdNode(9,6,"n3");
    	list.add(node);
    	node = new KdNode(4,7,"n4");
    	list.add(node);
    	node = new KdNode(8,1,"n5");
    	list.add(node);
    	node = new KdNode(7,2,"n6");
    	list.add(node);
        
        tree.build(list);

        PrintWriter out = response.getWriter();
		out.println("true");
		
		//rintWriter out = response.getWriter();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

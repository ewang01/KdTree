package changsi.test;

//import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;




@SuppressWarnings("serial")
public class SetUpServlet extends HttpServlet {

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void init() throws ServletException {

		KdTree tree = new KdTree( );
		ServletContext app = getServletContext();
		app.setAttribute("kdTree", tree);
		System.out.println("Finish setting up!");

	}

}

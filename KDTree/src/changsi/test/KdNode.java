/**
 * 
 */
package changsi.test;


/**
 * @author changsi
 * 
 */
public class KdNode {

	double x = 0.0;
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public KdNode getLeft() {
		return left;
	}

	public void setLeft(KdNode left) {
		this.left = left;
	}

	public KdNode getRight() {
		return right;
	}

	public void setRight(KdNode right) {
		this.right = right;
	}

	double y = 0.0;
	String id = null;
	KdNode left = null;
	KdNode right = null;

	public KdNode(double x, double y, String id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public KdNode(){
		
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("{\"longitude\":"+this.x+",");
		buffer.append("\"latitude\":"+this.y+",");
		buffer.append("\"location_id\":"+this.id+"}");
		return buffer.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

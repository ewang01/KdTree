/**
 * 
 */
package changsi.test;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author changsi
 *
 */
public class test {
	 // test string [{"x":5, "y":4, "id":"123456"},{"x":9, "y":6, "id":"1234567"},{"x":4, "y":7, "id":"12345678"},{"x":8, "y":1, "id":"123456789"}]
    public static ArrayList<KdNode> parse_param(String json) throws Exception{
    	ArrayList<KdNode> list = new ArrayList<KdNode> ();
    	JSONParser parser=new JSONParser();
    	Object obj=parser.parse(json);
    	JSONArray array=(JSONArray)obj;
    	for(int i=0; i<array.size();i++){
    		JSONObject tmp = (JSONObject)array.get(i);
    		double x = Double.valueOf(String.valueOf(tmp.get("x")));
    		double y = Double.valueOf(String.valueOf(tmp.get("y")));
    		String id = String.valueOf(tmp.get("id"));
    		KdNode node = new KdNode( x, y, id);
    		list.add(node);
    	}
    	return list;

    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "[{\"x\":5, \"y\":4, \"id\":\"123456\"},{\"x\":9, \"y\":6, \"id\":\"1234567\"},{\"x\":4, \"y\":7, \"id\":\"12345678\"},{\"x\":8, \"y\":1, \"id\":\"123456789\"}]";
		try{
			ArrayList <KdNode> list = test.parse_param(s);
			for(int i=0; i<list.size(); i++){
				System.out.println(list.get(i));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}

package changsi.test;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class KdTree
{
    public static class KdNode
    {
        double x;
        double y;
        String id = null;
        KdNode left = null;
        KdNode right = null;
        
        public KdNode( double x, double y, String id )
        {
        	this.x = x;
        	this.y = y;
        	this.id = id;
        }
    }
    
    public static double xmedian( ArrayList<KdNode> arl )
    {
    	double [ ] arr;
    	int l;
        int size = arl.size();  
    	arr = new double[ size ];        
        for ( l = 0; l < size; l ++ )
        	arr [ l ] = arl.get( l ).x; 
        	
        Arrays.sort( arr );
        
        if ( size % 2 == 1 )
        	return arr [ (size-1)/2 ];
        else return arr [ size/2 ];
    }
    
    public static double ymedian( ArrayList<KdNode> arl )
    {
    	double [ ] arr;
    	int l;
        int size = arl.size();  
    	arr = new double[ size ];        
        for ( l = 0; l < size; l ++ )
        	arr [ l ] = arl.get( l ).y; 
        	
        Arrays.sort( arr );
        
        if ( size % 2 == 1 )
        	return arr [ (size-1)/2 ];
        else return arr [ size/2 ];
    }
    
    private KdNode root;
    
    public KdTree( )
    {
        root = null;
    }
    
    public void build( ArrayList<KdNode> al )
    {
    	root = insert( al, root, 0 );
    }

    private KdNode insert( ArrayList<KdNode> al, KdNode t, int depth )
    {
    	ArrayList<KdNode> al1 = new ArrayList<KdNode> ();
    	ArrayList<KdNode> al2 = new ArrayList<KdNode> ();
    	
    	if ( al.size() == 1 ) t = al.get(0);
    	else
    	{
    		if ( depth % 2 == 0 )
        	{
        		for ( int k = 0; k < al.size(); k++ )
        		{
        			double xm = xmedian( al );
        			if ( al.get( k ).x < xm)
        				al1.add( al.get( k ) );
        			else if ( al.get( k ).x > xm)
        				al2.add( al.get( k) );
        			else t = al.get( k );
        		}
        	}
        	else
        	{
        		for ( int k = 0; k < al.size(); k++ )
        		{
        			double ym = ymedian( al );
        			if ( al.get( k ).y < ym )
        				al1.add( al.get( k ) );
        			else if ( al.get( k ).y > ym )
        				al2.add( al.get( k ) );
        			else t = al.get( k );
        		}
        		//System.out.println(al1);
        		//System.out.println(al2);
        	}
	    	if ( al1.size() != 0 )
	    		t.left = insert ( al1, t.left, depth + 1 );
	    	if ( al2.size() != 0 )
	    		t.right = insert ( al2, t.right, depth + 1 );
	    }
    	return t;    	
    }

    public String printRange( Double [ ] low, Double [ ] high )
    {
    	StringBuffer output = new StringBuffer();
        printRange( low, high, root, 0, output);
        return output.toString();
    }

    private void printRange( Double [ ] low, Double [ ] high, KdNode t, int level, StringBuffer output )
    {
        if( t != null )
        {
            if( low[ 0 ].compareTo( t.x ) <= 0 &&
                        low[ 1 ].compareTo( t.y ) <= 0 &&
                       high[ 0 ].compareTo( t.x ) >= 0 &&
                       high[ 1 ].compareTo( t.y ) >= 0 )
                System.out.println(( t.id + "(" + t.x + "," + t.y + ")" ));

            if( ( level == 0 && low[ level ].compareTo( t.x ) <= 0 ) || ( level == 1 && low[ level ].compareTo( t.y ) <= 0 ) )
                printRange( low, high, t.left, 1 - level, output );
            //else
            if( ( level == 0 && high[ level ].compareTo( t.x ) >= 0 ) || ( level == 1 && high[ level ].compareTo( t.y ) >= 0 ) )
                {//System.out.println(t.left.id);
                printRange( low, high, t.right, 1 - level, output );
                }
           
        }
        
    }

    
    public static void main( String [ ] args )
    {
        KdTree t = new KdTree( );
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
    	        
        t.build(list);
        
        
//        String str="[{\"low1\":\"0.0\",\"low2\":\"1.0\",\"high1\":\"10.0\",\"high2\":\"2.5\"}]";
        
        JSONObject json;
		try {
			json = (JSONObject)new JSONParser().parse("{\"name\":\"MyNode\", \"width\":200, \"height\":100}");
			System.out.println("name=" + json.get("name"));
	        System.out.println("width=" + json.get("width"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println( "Starting program" );
        Double [ ] a = { 0.0, 1.0 };
        Double [ ] b = { 10.0, 2.5 };

        t.printRange( a, b );

    }
}

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class ctest {
	public static void main(String[] args) throws FileNotFoundException  {
	
		  File myObj = new File("C:\\Users\\Lenovo\\eclipse-workspace\\Test\\src\\a.txt");
	      Scanner myReader = new Scanner(myObj);
	      String first = myReader.nextLine();
	      String[] parts = first.split(" ");
	      int streets = Integer.parseInt(parts[2]);
	      int cars = Integer.parseInt(parts[3]);
	      List<Intersection> intersections = new ArrayList<Intersection>();
	      List<Path> paths = new ArrayList<Path>();
	      System.out.println(streets);
	      System.out.println(cars);
	      for(int i=0; i<streets && myReader.hasNextLine(); i++) {
	        	String data = myReader.nextLine();
	        	//System.out.println(data);
	        	String[] sparts = data.split(" ");
	        	//System.out.println(sparts[2]);
	        	//System.out.println(sparts[3]);
	        	paths.add(new Path(sparts[2],Integer.parseInt(sparts[3])));
	        	int i1 = Integer.parseInt(sparts[0]);
	        	int i2 = Integer.parseInt(sparts[1]);
	        	
	        	int index1 = containsId(intersections,i1);
	        	int index2 = containsId(intersections,i2);
	        	
	        	if(index1!=-1) {
	        		Intersection x = intersections.get(index1);
	        		int y = Integer.parseInt(sparts[3]);
	        		Path z = new Path(sparts[2],y);
	        		x.exitPaths.add(z);
	        	} else {
	        		Intersection x = new Intersection(i1);
	        		intersections.add(x);
	        		int y = Integer.parseInt(sparts[3]);
	        		Path z = new Path(sparts[2],y);
	        		x.exitPaths.add(z);
	        	}
	        	
	        	
	        	if(index2!=-1) {
	        		Intersection x = intersections.get(index2);
	        		int y = Integer.parseInt(sparts[3]);
	        		Path z = new Path(sparts[2],y);
	        		x.enterPaths.add(z);
	        	} else {
	        		Intersection x = new Intersection(i2);
	        		intersections.add(x);
	        		int y = Integer.parseInt(sparts[3]);
	        		Path z = new Path(sparts[2],y);
	        		x.enterPaths.add(z);
	        	}
	        	
	           
	      }
	      
	      for(int j=0; j<cars && myReader.hasNextLine(); j++) {
		       	String data = myReader.nextLine();
		       	String[] cparts = data.split(" ");
		       	List<String> pathsx = new ArrayList<String>();
		       	for(int i = 1; i<Integer.parseInt(cparts[0]); i++) {
		       		pathsx.add(cparts[i]);
		       	}
		       	
		      // 	System.out.println(data);
		    
	      }
	        
	      
	      for(int i=0; i<paths.size(); i++) {
	    	  paths.get(i).printPath();
	      }
	      
	      for(int i=0; i<intersections.size(); i++) {
	    	  intersections.get(i).printIntersection();
	      }
	      
	      int time = 0;
	      while(true) {
	    	  for(int i =0; i<intersections.size(); i++) {
	    		  List<Path> pathsCurrent = intersections.get(i).enterPaths;
	    		  int max = pathsCurrent.get(i).cars.size();
	    		  Path path = null;
	    		  for(int z = 0; z<pathsCurrent.size(); z++) {
	    			  if(pathsCurrent.get(z).cars.size()>max) {
	    				  max = pathsCurrent.get(z).cars.size();
	    				  path = pathsCurrent.get(z);
	    			  }
	    		  }
	    		  TrafficLamb lamb = new TrafficLamb(intersections.get(i).id,max,path);
	    	  }
	    	  
	    	  
	    	  
	      }
	      
	      
	      
	      
	        
	      }
	





	public static int containsId(List<Intersection> list, long id) {
		int i = 0;
	    for (Intersection object : list) {
	        if (object.id == id) {
	            return i;
	        }
	        i++;
	    }
	    return -1;
	}
		
		
	}
  
  
  
  
  
  
  
  
  import java.util.ArrayList;
import java.util.List;

public class Car {
	int index = 0;
	List<String> way;
	
	Car(List<String> _way) {
		way = _way;
	}

}





import java.util.ArrayList;
import java.util.List;

public class Intersection {
	int id;
	List<Path> exitPaths = new ArrayList<Path>();
    List<Path> enterPaths = new ArrayList<Path>();
  
     
    Intersection(int _id) 
    { 
        id = _id;
    } 
	
    void printIntersection() {
    	System.out.println(this.id + " " + String.valueOf(exitPaths.size()) + " " + String.valueOf(enterPaths.size()) );
    }
}






import java.util.ArrayList;
import java.util.List;

public class Path {
	String name;  
    int duration;
    TrafficLamb lamb = null;
    List<Car> cars = new ArrayList<Car>();
  
     
    Path(String _name, int _duration ) 
    { 
    	duration = _duration;
        name = _name;
    } 
    
    void printPath() {
    	System.out.println(this.name + " " + String.valueOf(this.duration));
    }
}







public class TrafficLamb {
	int intersection;
	int duration;
	Path path;
	
	TrafficLamb(int _intersection, int _duration, Path _path) 
    { 
         duration = _duration;
         path = _path;
         intersection = _intersection;
    } 
}

public class printAsterix {											// Define the class
	
	public static String repeatString(String s, int n) { 			// Define the repeatString function
		
		   StringBuilder sb = new StringBuilder(s.length() * n);	// Use StringBuilder
		   
		   for (int i=0; i<n; i++)
			   
		      sb.append(s); 
		   
		   return sb.toString(); 									// Return string
		   
		}
	
	
	public static void main(String[] args) {						// Define class
		
		for(int i=1; i<=10; i++)									// Loop 10 times
								
            System.out.println(repeatString("*", i));				// Print each line
              
	}

}

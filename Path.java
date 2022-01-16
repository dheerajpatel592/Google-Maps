package googleMap;
import java.util.*;

public class Path {
	
	 Set<List<String>> set = new HashSet<>();
	
	public void allValidPaths(List <String> s , int l , int r) {
		if(l == r) {
			List <String > x = new ArrayList<>();
			List <String > y = new ArrayList<>();
			for(String v : s) 
				x.add(v);
			
			int z = s.size()-1 ;
			for(int i = z ; i >= 0 ; i--) {
				y.add( x.get(i));
			}
			
			if(set.contains(y))
				return ;
			
			set.add(x);
//			System.out.println(s);
			return ;
		}
		
		for(int i = l ; i<= r ; i++) {
			s = interchangeString(s , l , i ) ;
			allValidPaths(s , l+1 , r);
			s = interchangeString(s , l , i);
		}
	}
	
	public  List<String> interchangeString (List<String> s , int a , int b) {

		String temp = s.get(a);
		s.set(a , s.get(b));
		s.set(b , temp);
		return s;
	}

	
	
}

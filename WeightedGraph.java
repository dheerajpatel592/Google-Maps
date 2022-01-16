package googleMap;
import java.util.* ;

public class WeightedGraph {
	Trie trie = new Trie();
	Scanner sc = new Scanner (System.in);

	private class Node {
		String label ;
		
		public Node(String label) {
			this.label = label ;
		}
		
		private List <Edge> edges = new ArrayList<>() ;
		
		@Override
		public String toString() {
			return label;
		}
		
		public void addEdge(Node to , int weight) {
			edges.add(new Edge(this , to , weight));
		}
		
		public List<Edge> getEdges() {
			return edges ;
		}
	}
	
	private class Edge {
	
		private Node from ;
		private Node to ;
		private int weight ;
		
		public Edge(Node from , Node to , int weight) {
			this.from = from ;
			this.to = to ;
			this.weight = weight ;
		}

		@Override
		public String toString() {
			return from + " --> " + to ;
		}
	}
	
	Map <String, Node> nodes  = new HashMap<>();
	
	public void addNode (String label) {
		nodes.putIfAbsent(label, new Node(label));
		trie.insert (label);
	}
	
	public void addEdge(String from , String to , int weight) {
		Node fromNode = nodes.get(from);
		if(from == null)
			throw new IllegalArgumentException();
		
		Node toNode = nodes.get(to);
		if(to == null)
			throw new IllegalArgumentException();
		
		fromNode.addEdge(toNode, weight);
		toNode.addEdge(fromNode, weight);
		
	}
	
	public void print() {
		for(var node : nodes.values() ) {
			var edges = node.getEdges();
			if(!edges.isEmpty())
				System.out.println(node + " is connected to " + edges);
		}
	}
	
	public class NodeEntry {
		private Node node ;
		private int priority ;
		
		public NodeEntry(Node node, int priority) {
			this.node = node;
			this.priority = priority;
		}
	}
	
	public PathEntry getShortestPath(String from , String to ) {
		Node fromNode = nodes.get(from);
		if(fromNode == null)
			throw new IllegalArgumentException();
		
		Node toNode = nodes.get(to);
		if(toNode == null)
			throw new IllegalArgumentException();
		
		Map <Node , Integer> distances = new HashMap<>();
		for(Node node : nodes.values())
			distances.put(node , Integer.MAX_VALUE);
		
		distances.replace(fromNode, 0);
		
		Map<Node , Node> previousNodes = new HashMap<>() ;
		
		Set<Node> visited = new HashSet<>();
		
		PriorityQueue <NodeEntry> queue = new PriorityQueue<>(
				Comparator.comparingInt(ne -> ne.priority));
		
		queue.add(new NodeEntry(fromNode , 0));
		
		while(!queue.isEmpty()) {
			Node current = queue.remove().node ;
			visited.add(current);
			
			for(Edge edge : current.getEdges()) {
				if(visited.contains(edge.to))
					continue ;
				
				var newDistance = distances.get(current) + edge.weight ;
				
				if(newDistance < distances.get(edge.to)) {
					distances.replace(edge.to, newDistance);
					previousNodes.put(edge.to , current);
					queue.add(new NodeEntry(edge.to , newDistance));
				}
			}
		}
		
	    var stack = buildPath(previousNodes , distances ,toNode);
	    List<String> list = new ArrayList<>();
	    while(!stack.isEmpty()) {
	    	list.add(stack.pop().label);
	    }
	    int distance = getDistance(distances , toNode);
		return new PathEntry(list , distance) ;
	}
	
	List<String> petrolPump = new ArrayList<>();
	List<String> hotels = new ArrayList<>();
	
	private int getDistance( Map<Node , Integer> distances , Node to) {
		return distances.get(to);
	}
	
//	private void printShortestPath(String from , String to) {
//		List<String> list = getShortestPath(from , to ).path;
//		for( String item : list) {
//			System.out.println(item + " --> ");
//		}
//		
//		while(stack.size() != 1) {
//			System.out.print(stack.pop()+ " --> ");
//		}
//		System.out.print(stack.pop());
//	}
	
	private Stack<Node> buildPath(Map<Node , Node> previousNodes ,  Map<Node , Integer> distances ,Node toNode) {
		
//		int totalDistance = 0 ;
		Stack<Node>  stack = new Stack<>();
		stack.push(toNode);
		
//		ArrayDeque <Node> queue = new ArrayDeque<>();
		Node previous = previousNodes.get(toNode);
//		totalDistance += distances.get(toNode);
		
		while(previous != null) {
			stack.push(previous);
//			totalDistance += distances.get(previous);
			previous = previousNodes.get(previous);
		}
//		System.out.println(totalDistance);
		
//		Path path = new Path();
//		List<String> path = new ArrayList<>();
		
//		while(!stack.isEmpty()) 
//			path.add(stack.pop().label);
		
//		return path ;
		return stack ;
	}
	
	private class PathEntry {
		List<String> path ;
		int priority ;
		
		public PathEntry(List<String> path, int priority) {
			this.path = path;
			this.priority = priority;
		}

		@Override
		public String toString() {
			return path + "  " + " Distance = " + priority + " km";
		}
	}
	
	private void costAndTime(float distance , int choice) {
		
		// average speed of car = 40 Km/hr
		// average speed of motorcycle = 60 Km/hr
		// average walking speed = 2.5 Km/hr
		
		System.out.print("Time taken = ");
		switch(choice) {
			case 1 : System.out.printf(" %.2f hr \n", (distance/40) );
				break ;
			case 2 : System.out.printf(" %.2f hr \n", (distance/60));
				break ;
			case 3 : System.out.printf(" %.2f hr \n", (distance/2.5));
				break ;
		}
		
		// mileage of car = 25 Km/L
		// mileage of motorcycle = 60 Km/L
		// cost of 1L petrol = 90 
		if(choice != 3) {
			System.out.print("Total cost of journey = ");
			switch(choice) {
				case 1 : System.out.printf(" %.2f rupees", (distance/25)*90);
					break ;
				case 2 : System.out.printf(" %.2f rupees", (distance/60)*90);
					break ;
			}
		}
	}
	
	private PriorityQueue<PathEntry> findPetrolPumps(String location){
		PriorityQueue<PathEntry> queue = new PriorityQueue<>(Comparator.comparingInt(pe -> pe.priority));
		for (String pump : petrolPump ) {
			queue.add(getShortestPath(location , pump));
		}
		return queue ;
	}
	
	public void getAllDistances(List<String> list) {
		Path permu = new Path ();
//		Set < Path > = new HashSet <>();
		Map <String , Integer> map1 = new HashMap<>();
		Map <String , List <String >> map2 = new HashMap<>();
		permu.allValidPaths(list, 1, list.size()-2);
		PriorityQueue<PathEntry> queue = new PriorityQueue<>(Comparator.comparingInt(pe -> pe.priority));
		for(var newList : permu.set) {
			System.out.println(newList);
			int i = 0 ;
			int j = 1 ;
			int distance = 0 ;
			PathEntry path ;
			ArrayList <String> list2 = new ArrayList<>();
			while(j != newList.size()) {
				String newString1 = newList.get(i)+ newList.get(j) ;
				String newString2 = newList.get(j) + newList.get(i) ;
				
				if (map1.containsKey(newString1)) {
					distance += map1.get(newString1);
					System.out.println("map used");
					for (int z = 0 ; z < map2.get(newString1).size() -1 ; z++ ) {
						list2.add( map2.get(newString1).get(z));
					}
				}
				
				else if(map1.containsKey(newString2)) {
					distance += map1.get(newString2);
					System.out.println("map used");
					for (int z = map2.get(newString2).size() -1; z > 0; z-- ) {
						list2.add( map2.get(newString2).get(z));
					}
				}
				else {
					path = getShortestPath(newList.get(i), newList.get(j));
					map1.put(newString1,path.priority );
					map1.put(newString2,path.priority );
					map2.put(newString1, path.path);
					List <String > list4 = new ArrayList<>();
					for(int z = path.path.size() - 1 ; z >= 0 ; z--)
						list4.add(path.path.get(z));
					map2.put(newString2, list4);
					System.out.println("map isn't used");
					for (int z = 0 ; z < path.path.size()-1 ;z++)
						list2.add(path.path.get(z));
					distance += path.priority;
				}
					
				
//				path = getShortestPath(newList.get(i), newList.get(j));
//				for(int z = 0 ; z < path.path.size()-1 ;z++)
//					list2.add(path.path.get(z));
				//list2.addAll(path.path);
				//System.out.println(path.path);
//				distance += path.priority;
				i++ ;
				j++ ;
			}
			list2.add(list.get(0));
			//System.out.println(list2);
			for(int s = 0 ; s < list2.size()-1 ; s++)
				System.out.print(list2.get(s) + " --> ");
			System.out.print(list2.get(list2.size()-1));
			System.out.println();
			System.out.println();
			queue.add(new PathEntry(newList , distance));
		}
		
		while(!queue.isEmpty()) {
			System.out.println(queue.remove());
		}
	}
	
	private String getLocation(String x) {
		System.out.println(x);
		String location = sc.nextLine().toUpperCase();
//		if(!nodes.containsKey(location)) {
//			System.out.println("No such place");
//			System.out.println("Try Again");
//			return getLocation(x);
//		}
		var words = trie.findWords(location);
		for(int i = 0; i< words.size() ;i++) {
			System.out.print(i+1 + ". " + words.get(i) + "   ");
		}
		System.out.println();
		System.out.println("Enter index number ");
		System.out.println("Or press 0 to search again");
		int index = sc.nextInt();
		sc.nextLine();
		if(index < 0 || index > words.size()) {
			System.out.println("Try Again");
			return getLocation(x);
		}
		else if (index == 0)
			return getLocation(x);
		else 
			location = words.get(index - 1);
		
		return location ;
	}
	
	public void methodCaller () {
		System.out.println("WELCOME");
		System.out.println("Press any key to continue :");
		sc.nextLine();
		System.out.println("Choose Mode ");
		System.out.println("1 : Normal Mode");
		System.out.println("2 : Delivery Mode");
		int x = sc.nextInt();
		if(x == 1) {
			System.out.println("What would you like me to do ?");
			System.out.println("1 : Find route to a place ");
			System.out.println("2 : Find Petrol Pump near me");
			System.out.println("3 : Find Hotel near me ");
			int y = sc.nextInt();
//			System.out.println("Enter your current location ");
			sc.nextLine();
			String location = getLocation("Enter your current location ");
			
			switch(y) {
	
				case 1 : 
					//System.out.println("Where do you want to go?");
					String destination = getLocation("Where do you want to go?");
					System.out.println("How do you want to go to " + destination + " ?");
					System.out.println("1. By Car    2.By Motorcycle    3.Walking");
					int choice = sc.nextInt();
					int distance = 0 ;
					if(choice == 1 || choice ==2) {
						System.out.println("Would you like to have a petrol pump en route for refuelling ?  yes/no");
						sc.nextLine();
						String pumpEnRoute = sc.nextLine().toLowerCase();
						if(pumpEnRoute.equals("yes")) {
							PriorityQueue <PathEntry> queue3 = findPetrolPumps(location);
							var first = queue3.remove();
							var second = getShortestPath(first.path.get(first.path.size()-1) , destination);
//							System.out.println(first);
//							System.out.println(second);
							for(int k = 1 ; k< second.path.size(); k++) {
								first.path.add(second.path.get(k));
							}
							System.out.println(first.path);
							distance = first.priority + second.priority;
							System.out.println("Distance = " + distance);
						}
						else if(pumpEnRoute.equals("no")) {
							var path = getShortestPath(location, destination);
							System.out.println(path);
							distance = path.priority ;
						}
					}
					else if (choice == 3 ) {
						var path = getShortestPath(location, destination);
						System.out.println(path);
						distance = path.priority ;
						
					}
					costAndTime(distance , choice);
					break ;
				case 2 :
//					Map <String , Integer> pumpMap = new HashMap<>();
//					for(String pump : petrolPump) {
//						pumpMap.put(getShortestPath(location , "P").toString(), 5);
//					}
//					for(var item : pumpMap.values().toArray())
//						System.out.println(pumpMap.get);
					PriorityQueue <PathEntry> queue1 = findPetrolPumps(location);
					while(!queue1.isEmpty()) {
						System.out.println(queue1.remove());
					}
					break ;
				case 3 :
					PriorityQueue<PathEntry> queue2 = new PriorityQueue<>(Comparator.comparingInt(pe -> pe.priority));
					for (String hotel : hotels ) {
						queue2.add(getShortestPath(location , hotel));
					}
					while(!queue2.isEmpty()) {
						System.out.println(queue2.remove());
					}
					break ;
			}
		}
		else if(x == 2) {
			System.out.println("Feature :");
			System.out.println("Find shortest distance to visit multiple places on a single trip");
			System.out.println("Press any key to choose this feature");
			sc.nextLine();
			sc.nextLine();
			System.out.println("Enter your current location ");
			String location = sc.nextLine().toUpperCase();
			System.out.println("How many places do you want to visit on your trip");
			int num = sc.nextInt();
			System.out.println("Enter places you want to visit on your trip");
			List<String> list = new ArrayList<>();
			list.add(location);
			sc.nextLine();
			for(int i = 0 ;i< num ; i++) {
				list.add(sc.nextLine().toUpperCase());
			}
			list.add(location);
			getAllDistances(list);
		}
		sc.close();
	}
	
}

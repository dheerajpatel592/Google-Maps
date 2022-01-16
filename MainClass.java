package googleMap;

public class MainClass {

	public static void main(String[] args) {
		WeightedGraph graph = new WeightedGraph();
		
//		graph.addNode("P1");
//		graph.addNode("A");
//		graph.addNode("B");
//		graph.addNode("CHANDNI CHOWK");
//		graph.addNode("CHAWRI BAZAR");
//		graph.addNode("P2");
//		graph.addNode("P3");
//		graph.addNode("E");
//		graph.addNode("F");
//		graph.addNode("H1");
//		graph.addNode("H2");
//		graph.addEdge("A" ,"B", 1);
//		graph.addEdge("A" ,"CHANDNI CHOWK" , 10);
//		graph.addEdge("B", "CHANDNI CHOWK", 2);
//		graph.addEdge("B", "P1", 1);
//		graph.addEdge("P1", "CHANDNI CHOWK", 3);
//		graph.addEdge("P2", "CHANDNI CHOWK", 1);
//		graph.addEdge("P3", "CHAWRI BAZAR", 3);
//		graph.addEdge("CHAWRI BAZAR", "CHANDNI CHOWK", 3);
//		graph.addEdge("A" ,"E", 5);
//		graph.addEdge("E" ,"H1", 2);
//		graph.addEdge("H1" ,"F", 3);
//		graph.addEdge("CHAWRI BAZAR" ,"H2", 4);
//		graph.addEdge("B", "F", 7);
//		graph.petrolPump.add("P1");
//		graph.petrolPump.add("P2");
//		graph.petrolPump.add("P3");
//		graph.hotels.add("H1");
//		graph.hotels.add("H2");
////		System.out.println(graph.trie.findWords("P"));
//		graph.methodCaller();

		
		graph.addNode("AKSHARDHAM MANDIR");
		graph.addNode("AGRASEN KI BAOLI");
		graph.addNode("ART MUSEUM");
		graph.addNode("B");
		graph.addNode("CHANDNI CHOWK");
		graph.addNode("CHAWRI BAZAR");
		graph.addNode("CHHATARPUR MANDIR");
		graph.addNode("D");
		graph.addNode("E");
		graph.addNode("F");
		graph.addNode("G");
		graph.addNode("P1");
		graph.addNode("P2");
		graph.addNode("P3");
		graph.addNode("I");
		graph.addNode("J");
		graph.addNode("H1");
		graph.addNode("H2");
		graph.addNode("H3");
		graph.addNode("K");
		graph.addNode("L");
		
		graph.addEdge("P1" ,"CHANDNI CHOWK" , 6);
		graph.addEdge("H3", "CHANDNI CHOWK", 7);
		graph.addEdge("D", "P1", 4);
		graph.addEdge("B", "P1", 5);
		graph.addEdge("B", "D", 3);
		graph.addEdge("D", "F", 5);
		graph.addEdge("F", "H3", 3);
		graph.addEdge("B", "CHAWRI BAZAR", 7);
		graph.addEdge("CHAWRI BAZAR", "P2", 7);
		graph.addEdge("E", "P2", 8);
		graph.addEdge("E", "D", 3);
		graph.addEdge("H3", "P3", 3);
		graph.addEdge("F", "G", 4);
		graph.addEdge("G", "P3", 2);
		graph.addEdge("E", "H1", 2);
		graph.addEdge("G", "H1", 9);
		graph.addEdge("AKSHARDHAM MANDIR", "P3", 1);
		graph.addEdge("AKSHARDHAM MANDIR", "I", 3);
		graph.addEdge("ART MUSEUM", "P2", 9);
		graph.addEdge("ART MUSEUM", "L", 6);
		graph.addEdge("G", "I", 5);
		graph.addEdge("L", "H1", 4);
		graph.addEdge("L", "K", 8);
		graph.addEdge("G", "K", 7);
		graph.addEdge("K", "J", 1);
		graph.addEdge("J", "H2", 1);
		graph.addEdge("H2", "CHHATARPUR MANDIR", 2);
		graph.addEdge("CHHATARPUR MANDIR", "I", 2);
		graph.addEdge("L", "AGRASEN KI BAOLI", 11);
		graph.addEdge("AGRASEN KI BAOLI", "J", 7);
		graph.petrolPump.add("P1");
		graph.petrolPump.add("P2");
		graph.petrolPump.add("P3");
		graph.hotels.add("H1");
		graph.hotels.add("H2");
		graph.hotels.add("H3");

		graph.methodCaller();
		
		
//		graph.addNode("A");
//		graph.addNode("B");
//		graph.addNode("C");
//		graph.addNode("D");
//		graph.addNode("X");
//		graph.addEdge("A", "B", 4);
//		graph.addEdge("A", "C", 5);
//		graph.addEdge("A", "X", 8);
//		graph.addEdge("B", "C", 10);
//		graph.addEdge("C", "X", 7);
//		graph.addEdge("X", "B", 1);
//		graph.addEdge("B", "D", 2);
//		graph.addEdge("X", "D", 3);
//		graph.addEdge("D", "C", 6);
//		graph.addEdge("A", "D", 9);
//		graph.methodCaller();
//		graph.getAllDistances(list);

	}

}

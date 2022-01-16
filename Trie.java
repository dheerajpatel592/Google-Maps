package googleMap;
import java.util.* ;

public  class Trie {
	
	private class Node {
		private char value ;
		private HashMap <Character , Node> children = new HashMap<>();
		private boolean isEndOfWord ;
		
		public Node (char value) {
			this.value = value ; 
		}
		
		@Override
		public String toString() {
			return "value = " + value ;
		}
		
		public boolean hasChild(char ch) {
			return children.containsKey(ch) ;
		}
		
		public void addChild(char ch) {
			children.put(ch,new Node(ch));
		}
		
		public Node getChild(char ch ) {
			return children.get(ch);
		}
		
		public Node[] getChildren() {
			return children.values().toArray(new Node[0]);
		}
		
		public boolean hasChildren() {
			return !children.isEmpty();
		}
		
		public void removeChild(char ch) {
			children.remove(ch);
		}
	}
	
	private Node root = new Node(' ');
	
	public void traverse() {
		traverse(root) ;
	}
	
	private void traverse(Node root) {
		System.out.println(root.value);
		
		for (Node child : root.getChildren())
			traverse(child);
	}
	
	public void remove (String word) {
		remove (root , word , 0);
	}
	
	private void remove(Node root , String word , int index) {
		if(word == null)
			return ; 
		
		if(index == word.length()) {
			root.isEndOfWord = false ;
			return ;
		}
		char ch = word.charAt(index);
		Node child = root.getChild(ch);
		
		remove(child, word , index+1);
		
		if(!child.hasChildren() && !child.isEndOfWord)
			root.removeChild(ch);
	}
	
	public boolean contains(String word) {
		if(word == null)
			return false;
		
		var current = root ;
		for(var ch : word.toCharArray()) {
			if(!current.hasChild(ch))
				return false ;
			current = current.getChild( ch);
		}
		return current.isEndOfWord ;
	}
	
	public void insert (String word) {
		var current = root ;
		for(char ch : word.toCharArray()) {
		
			if(!current.hasChild(ch))
				current.addChild(ch);
			current = current.getChild(ch) ;
		}
		current.isEndOfWord = true ;
	}
	
	public List <String> findWords(String prefix){
		Node lastNode = findLastNodeOf(prefix);
		List <String> words = new ArrayList<>();
		findWords(lastNode , prefix , words);
		return words ;
	}
	
	private void findWords(Node root , String prefix , List <String> words){
		if(root == null)
			return ;
		
		if(root.isEndOfWord)
			words.add(prefix);
		
		for(Node child : root.getChildren())
			findWords(child , prefix + child.value , words);
	}
	
	private Node findLastNodeOf(String prefix) {
		if(prefix == null)
			return null;
		Node current = root ;
		for(char ch : prefix.toCharArray()) {
			Node child = current.getChild(ch);
			if(child == null)
				return null ;
			current = child ;
		}
		return current ;
	}
	
}

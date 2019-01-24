import java.util.ArrayList;
import java.util.*;
class Node implements Comparable<Node>{
	public int h;
	public int g;
	public int f;
	public int cost = 1;
	public int x;
	public int y;
	public int n;
	public Node parent;
	public ArrayList<Node> neighbours = new ArrayList<Node>();
	public Node(int i, int j, int n2) {
		// TODO Auto-generated constructor stub
		x = i;
		y = j;
		n = n2;
	}
	public String toString(){
		return x*n + y + "";
	}
	public int compareTo(Node node){
		return Double.compare(h, node.h);
	}
}
//AStar 
public class aStar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node [][] graph = new Node[4][4];
		int n = graph[0].length;
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph[i].length; j++){
				graph[i][j] = new Node(i,j,n);
			}
		}
		graph[1][1] = null;
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph[i].length; j++){
				if(graph[i][j] != null){
					if(i == 0){
						if(j==0){
							graph[i][j].neighbours.add(graph[i+1][j]);
							graph[i][j].neighbours.add(graph[i][j+1]);
						}
						else if(j == graph[0].length - 1){
							graph[i][j].neighbours.add(graph[i][j-1]);
							graph[i][j].neighbours.add(graph[i+1][j]);
						}
						else {
							graph[i][j].neighbours.add(graph[i][j+1]);
							graph[i][j].neighbours.add(graph[i+1][j]);
							graph[i][j].neighbours.add(graph[i][j-1]);
						}
					}
					else if(i == graph.length - 1){
						if(j==0){
							graph[i][j].neighbours.add(graph[i-1][j]);
							graph[i][j].neighbours.add(graph[i][j+1]);
						}
						else if(j == graph[0].length - 1){
							graph[i][j].neighbours.add(graph[i][j-1]);
							graph[i][j].neighbours.add(graph[i-1][j]);
						}
						else {
							graph[i][j].neighbours.add(graph[i][j+1]);
							graph[i][j].neighbours.add(graph[i-1][j]);
							graph[i][j].neighbours.add(graph[i][j-1]);
						}
					}
					else {
						if(j==0){
							graph[i][j].neighbours.add(graph[i-1][j]);
							graph[i][j].neighbours.add(graph[i+1][j]);
							graph[i][j].neighbours.add(graph[i][j+1]);
						}
						else if(j == graph[0].length - 1){
							graph[i][j].neighbours.add(graph[i][j-1]);
							graph[i][j].neighbours.add(graph[i+1][j]);
							graph[i][j].neighbours.add(graph[i-1][j]);
						}
						else {
							graph[i][j].neighbours.add(graph[i][j+1]);
							graph[i][j].neighbours.add(graph[i+1][j]);
							graph[i][j].neighbours.add(graph[i-1][j]);
							graph[i][j].neighbours.add(graph[i][j-1]);
						}
					}
				}
			}
		}
		Node destination = graph[3][3];
		ArrayList<Node> path = astar(graph[0][0], destination);
		System.out.println(path);
	}
	
	public static ArrayList<Node> astar(Node start, Node goal) {
			start.g = 0;
			start.h = estimateDistance(start,goal);
			start.f = start.h;
			PriorityQueue<Node> path = new PriorityQueue<Node>();
			path.add(start);
			HashSet<Node> closed = new HashSet<Node>();
			
			while(!path.isEmpty()){
				Node n = path.poll();
				if(n == goal)
					break;
				closed.add(n);
				for(Node neighbour : n.neighbours){
					if(neighbour == null)
						continue;
					int nextG = n.g + neighbour.cost;
					if(nextG < neighbour.g){
						path.remove(neighbour);
						closed.remove(neighbour);
					}
					if(!closed.contains(neighbour) && !path.contains(neighbour)){
						neighbour.g = nextG;
						neighbour.h = estimateDistance(neighbour,goal);
						neighbour.f = neighbour.g + neighbour.h;
						neighbour.parent = n;
						path.add(neighbour);
					}
				}
			}
	    ArrayList<Node> nodes = new ArrayList<Node>();
	    Node current = goal;
	    while (current.parent != null) {
	        nodes.add(current);
	        current = current.parent;
	    }
	    nodes.add(start);
	    Collections.reverse(nodes);
	    return nodes;
	}

	public static int estimateDistance(Node node1, Node node2) {
	    return Math.abs(node1.x - node2.x) + Math.abs(node1.y - node2.y);
	}
}

/*ways to represent graphs 
classes
2D array
*/

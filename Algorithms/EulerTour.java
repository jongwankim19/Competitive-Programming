import java.util.*;
public class EulerTour {
	
	public static String tour = "";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] graph = {{0,1,0,1,0,1,1},
						 {1,0,1,0,0,0,0},
						 {0,1,0,1,1,1,0},
						 {1,0,1,0,0,0,0},
						 {0,0,1,0,0,1,0},
						 {1,0,1,0,1,0,1},
						 {1,0,0,0,0,1,0}};
		
		//Vertices in Eulerian graphs with Eulerian cycles must have an even degree
		int count = 0;
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph.length; j++){
				if(graph[i][j] != 0){
					count++;
				}
			}
			if(count % 2 != 0){
				System.out.println("Not an Eulerian graph");
				System.exit(0);
			}
			count = 0;
		}
		Hierholzer(graph,0);
		System.out.println(tour);
	}

	public static void Hierholzer(int[][] graph, int start){
		Stack<Integer> stack = new Stack<Integer>();
		do{
			int newVertex = hasNeighbour(graph[start], start);
			if(newVertex == -1){
				tour = start + tour;
				start = stack.pop();
			}
			else {
				stack.push(start);
				graph[start][newVertex] = 0;
				graph[newVertex][start] = 0;
				start = newVertex;
			}
		}while(!stack.isEmpty());
		tour = start + tour;
	}
	
	public static int hasNeighbour(int[] graph, int vertex){
		for(int i = 0; i < graph.length; i++){
			if(graph[i] != 0)
				return i;
		}
		return -1;
	}
	
	//Eulerian Path: Add a dummy edge between the two odd-degreed vertices and 
	///start from that dummy edge and remove it at end
	
	//Chinese Postman Problem (Route Inspection)
	//If the graph is not Eulerian, there are still even number of odd degree vertices
	//Using those vertices, get the minimum weight perfect matching
	//The minimum cost + total cost = answer
}

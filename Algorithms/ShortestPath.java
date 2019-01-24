import java.util.*;
public class ShortestPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] graph = {{0,0,6,3,0},
						 {3,0,0,0,0},
						 {0,0,0,2,0},
						 {0,1,1,0,0},
						 {0,4,0,2,0}
						};
		int[][] path = BellmanFord(graph,4);
		System.out.println(path[0][0]);
		
		ShortestPathFaster(graph,4);
		
		FloydWarshall(graph);
		
		//backtracking
		ArrayList<Integer> backtracking = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		while(!stack.isEmpty()){
			int a = stack.pop();
			backtracking.add(a);
			if(a != 4){
				stack.add(path[1][a]);
			}
		}
		Collections.reverse(backtracking);
		System.out.println(backtracking);
	}

	public static int[][] BellmanFord(int[][] graph, int start){
		int[][] path = new int[2][graph.length];
		//Relax all distances
		for(int i = 0; i < path[0].length; i++){
			path[0][i] = Integer.MAX_VALUE/2;
		}
		path[0][start] = 0;
		path[1][start] = start;
		//Get distances O(VE) per one cycle of check
		for(int i = 0; i < graph.length-1; i++){
			for(int j = 0; j < graph.length; j++){
				for(int k = 0; k < graph.length; k++){
					if(graph[j][k] != 0){
						if(path[0][k] > path[0][j] + graph[j][k]){
							path[0][k] = path[0][j] + graph[j][k];
							path[1][k] = j;
						}
					}
				}
			}
		}
		//Negative Cycles
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph.length; j++){
				if(graph[i][j] != 0){
					if(path[0][j] > path[0][i] + graph[i][j]){
						System.out.println("Negative Cycle");
						System.exit(0);
					}
				}
			}
		}
		return path;
	}
	
	public static void ShortestPathFaster(int[][] graph, int start){
		int[][] path = new int[2][graph.length];
		//Relax all distances
		for(int i = 0; i < path[0].length; i++){
			path[0][i] = Integer.MAX_VALUE/2;
		}
		path[0][start] = 0;
		
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		queue.add(start);
		while(!queue.isEmpty()){
			int newV = queue.poll();
			for(int i = 0; i < graph.length; i++){
				if(graph[newV][i] != 0){
					if(path[0][i] > path[0][newV] + graph[newV][i]){
						path[0][i] = path[0][newV] + graph[newV][i];
						if(!queue.contains(i))
							queue.add(i);
					}
				}			
			}
		}
		System.out.println(path[0][0]);
	}
	
	public static void FloydWarshall(int[][] graph){
		int[][] newGraph = new int[graph.length][graph.length];
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph.length; j++){
				newGraph[i][j] = graph[i][j];
				if(graph[i][j] == 0)
					newGraph[i][j] = Integer.MAX_VALUE/2;
			}
		}
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph.length; j++){
				for(int k = 0; k < graph.length; k++){
					newGraph[j][k] = Math.min(newGraph[j][k], newGraph[j][i] + newGraph[i][k]);
				}
			}
		}
		System.out.println(newGraph[4][0]);
	}
}

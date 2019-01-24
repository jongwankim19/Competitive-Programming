import java.util.*;
public class MST {
	public static HashMap<Integer, String> map;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[][] graph = new int[6][6];
		int[] visited = new int[6];
		map = new HashMap<Integer, String>();
		map.put(0,"Tokyo");
		map.put(1,"Korea");
		map.put(2,"USA");
		map.put(3,"China");
		map.put(4,"Russia");
		map.put(5, "Mexico");
		
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph[i].length; j++){
				if(graph[i][j] == 0){
					System.out.println("cell for " + map.get(i) + " to " + map.get(j));
					graph[i][j] = sc.nextInt();
					if(graph[i][j] == 0){
						graph[i][j] = Integer.MAX_VALUE;
					}
					graph[j][i] = graph[i][j];
				}
			}
		}
		System.out.println("Start from which number?");
		Prims(graph,visited,sc.nextInt());
		sc.close();
	}
	
	public static void Prims(int[][] graph, int[] visited, int a){
		 int min = 0;
		 visited[a] = 1;
		 int row = 0;
		 int col = 0;
		 int total = 0;
		 int[][] mst = new int[graph.length][graph.length];
		 for(int i = 0; i < graph.length-1; i++){
			 min = Integer.MAX_VALUE;
			 for(int k = 0; k < graph.length; k++){
				 if(visited[k] == 1){
					 for(int j = 0; j < graph.length; j++){
						 if(visited[j] == 0){
							 if(min > graph[k][j]){
								 min = graph[k][j];
								 row = k;
								 col = j;
							 }
						 }
					 }
				 }
			 }
			 visited[col] = 1;
			 total += min;
			 graph[row][col] = Integer.MAX_VALUE;
			 mst[row][col] = min;
			 mst[col][row] = min;
			 System.out.println(map.get(row) + "->" + map.get(col) + " weight with " + min);
		 }
		 System.out.println("total weight is " + total);
		 display(mst);
	}
	
	public static void display(int[][] graph){
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph[i].length; j++){
				System.out.print(graph[i][j] + " " );
			}
			System.out.println();
		}
	}
	
	//minimum spanning tree for directed graph (Chu-Liu Algorithm O(VE), but can be optimized to O(E+VlogV))
	public static void EdmondsChuLiu(int[][] graph){
		
	}
}

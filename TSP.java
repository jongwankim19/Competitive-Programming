/* 
 * Held Karp Algorithm
 * 	- Every subpath of a path of minimum distance is itself of minimum distance.
 * Christofides Algorithm
 * 	- provides a 1.5-approximation
 * 		- Form a minimum spanning tree
 * 		- Minimum cost perfect matching for odd degree vertices in MST
 * 		- Union of MST and matching
 * 		- Form an Euler tour 
 * 		- Skip repeated vertices and make the graph into Hamiltonian circuit.
 */
import java.util.*;
public class TSP {

	public final static int INF = Integer.MAX_VALUE/2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] graph = {{0,1,15,6},
						 {2,0,7,3},
						 {9,6,0,12},
						 {10,4,8,0}};
		
		HeldKarp(graph);
	}
	
	//Held Karp using bitmasking
	/*What's the meaning of (mask & (1 << lastNode), (mask & (1 << nextNode), mask | (1 << nextNode)?
	/*The ith bit is set in mask if and only if ith node is already in the path. 
	 *The first two expressions check if the bit is set. The last one sets the 
	 *bit that corresponds to the nextNode.
	 */
	public static void HeldKarp(int[][] graph){
		int n = graph.length; 
		int[][] dp = new int[n][1 << n];
	    for (int i = 0; i < dp.length; i++)
	        Arrays.fill(dp[i], INF);
	    dp[0][1] = 0;
	    for (int i = 1; i < (1 << n); i++) {
	        for (int j = 0; j < n; j++) {
	            if ((i & (1 << j)) == 0)
	                continue; 
	            for (int k = 0; k < n; k++) {
	                if ((i & (1 << k)) != 0)
	                    continue;
	                dp[k][i | (1 << k)] = Math.min(dp[k][i | (1 << k)], dp[j][i] + graph[j][k]);
	            }
	        }   
	    }
	    int res = INF;
	    for (int j = 0; j < n; j++){
	    	res = (int) Math.min(res, graph[j][0] + dp[j][(1 << n) - 1]);
	    }
	    System.out.println(res);
	}
}
/* 
 * output
 * (21, [1,3,4,2,1])
 */

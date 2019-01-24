import java.util.*;
public class MaxFlow {

   public static void main(String[] args) {
   	// TODO Auto-generated method stub 	
      int[][] capacity = {{0,20,15,0,0},
         			 {0,0,18,0,8},
         			 {0,0,0,10,3},
         			 {0,0,0,0,12},
         			 {0,0,0,0,0}};
   	
      int maxflow = EdmondsKarp(capacity, 0, 4);
      System.out.println(maxflow);
   }

   public static int EdmondsKarp(int[][] capacity, int s, int t){
      int n = capacity.length;
      int[][] flow = new int[n][n];
      while(true){
      	//parent table
         int[] parent = new int[n];
         Arrays.fill(parent, -1);
         parent[s] = s;
      	
      	//path table
         int[] path = new int[n];
         path[s] = Integer.MAX_VALUE;
      	
         Queue<Integer> queue = new LinkedList<Integer>();
         queue.add(s);
      	
         loop:
         while(!queue.isEmpty()){
            int v = queue.poll();
            for(int i = 0; i < n; i++){
            	if(capacity[v][i] - flow[v][i] > 0 && parent[i] == -1){
            		parent[i] = v;
            		path[i] = Math.min(path[v], capacity[v][i] - flow[v][i]);
            		if(i != t){
            			queue.add(i);
            		}
            		else {
            			while(parent[i] != i){
            				v = parent[i];
            				flow[v][i] += path[t];
            				i = v;
            			}
            			break loop;
            		}
            	}
            }
         }
         if(parent[t] == -1){
            int sum = 0;
            for(int i = 0; i < n; i++){
               sum += flow[i][t];
            }
            return sum;
         }
      }
   }
}
//Strongly Connected Components using adjacency  ArrayList O(V + E)
import java.util.*;
public class SCC {

   @SuppressWarnings("unchecked")
   public static void main(String[] args) {
   	// TODO Auto-generated method stub
      ArrayList<Integer>[] graph = new ArrayList[5];
      for(int i = 0; i < 5; i++){
         graph[i] = new ArrayList<Integer>();
      }
      graph[0].add(2);
      graph[0].add(3);
      graph[1].add(0);
      graph[2].add(1);
      graph[3].add(4);
      
      Kosaraju(graph);
   }
   
   public static void dfs(ArrayList<Integer>[] graph, int v, boolean[] visited, Stack<Integer> comp) 
   {
      visited[v] = true;
      for (int i = 0; i < graph[v].size(); i++)
         if (!visited[graph[v].get(i)])
            dfs(graph, graph[v].get(i), visited, comp);
      comp.push(v);
   }
   
   public static void Kosaraju(ArrayList<Integer>[] graph)
   {
      int V = graph.length;
      boolean[] visited = new boolean[V];
      Stack<Integer> order = fillOrder(graph, visited);       
     
      ArrayList<Integer>[] reverseGraph = getReverse(graph);        

      visited = new boolean[V];
   
      ArrayList<Stack<Integer>> SCComp = new ArrayList<Stack<Integer>>();
      while(!order.isEmpty())
      {
         int v = order.pop();
         if (!visited[v]) 
         {
            Stack<Integer> comp = new Stack<Integer>();
            dfs(reverseGraph, v, visited, comp);
            SCComp.add(comp);
         }
      }
      System.out.println(SCComp);
   }	
   
   public static Stack<Integer> fillOrder(ArrayList<Integer>[] graph, boolean[] visited) 
   {        
      int V = graph.length;
      Stack<Integer> order = new Stack<Integer>();
      for (int i = 0; i < V; i++){
         if (!visited[i])
            dfs(graph, i, visited, order);
      }
      return order;
   }
   
   @SuppressWarnings("unchecked")
   public static ArrayList<Integer>[] getReverse(ArrayList<Integer>[] graph)
   {
      int V = graph.length;
      ArrayList<Integer>[] g = new ArrayList[V];
      for (int i = 0; i < V; i++){
         g[i] = new ArrayList<Integer>();
      }
      for (int v = 0; v < V; v++){
         for (int i = 0; i < graph[v].size(); i++){
            g[graph[v].get(i)].add(v);
         }
      }
      return g;
   }
}

/*
import java.util.*;
public class SCC {

   public static void main(String[] args) {
      int[][] graph = {{0,0,1,1,0},
                       {1,0,0,0,0},
                       {0,1,0,0,0},
                       {0,0,0,0,1},
                       {0,0,0,0,0}};
                       
      Kosaraju(graph);
   }
   
   public static void dfs(int[][] graph, int v, boolean[] visited, Stack<Integer> comp) 
   {
      visited[v] = true;
      for (int i = 0; i < graph[v].length; i++){
         if (!visited[i] && graph[v][i] != 0)
            dfs(graph, i, visited, comp);
      }
      comp.push(v);
   }
   
   public static void Kosaraju(int[][] graph)
   {
      boolean[] visited = new boolean[graph.length];
      Stack<Integer> order = fillOrder(graph, visited);       
      
      int[][] reverseGraph = getReverse(graph);        
   
      visited = new boolean[graph.length];
   
      ArrayList<Stack<Integer>> SCComp = new ArrayList<Stack<Integer>>();
      while(!order.isEmpty())
      {
         int v = order.pop();
         if (!visited[v]) 
         {
            Stack<Integer> comp = new Stack<Integer>();
            dfs(reverseGraph, v, visited, comp);
            SCComp.add(comp);
         }
      }
      System.out.println(SCComp);
   }	
   
   public static Stack<Integer> fillOrder(int[][] graph, boolean[] visited) 
   {        
      Stack<Integer> order = new Stack<Integer>();
      for (int i = 0; i < graph.length; i++){
         if (!visited[i])
            dfs(graph, i, visited, order);
      }
      return order;
   }
   
   public static int[][] getReverse(int[][] graph)
   {
      int[][] g = new int[graph.length][graph.length];
      for(int i = 0; i < g.length; i++){
         for(int j = 0; j < g.length; j++){
            g[i][j] = graph[j][i];
         }
      }
      return g;
   }
}
*/

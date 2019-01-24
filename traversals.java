import java.util.*;

class Vertex implements Comparable<Vertex> {
   public double minDistance = Double.POSITIVE_INFINITY;
   public boolean visited;
   public String name;
   public Edge[] adjacencies;
   public Vertex (String name){
      this.name = name;
      visited = false;
   }
   public String toString() {
      return name;
   }
   public int compareTo (Vertex other){
      return Double.compare(minDistance, other.minDistance);
   }
}

class Edge
{
   public final Vertex target;
   public Edge(Vertex argTarget)
   { 
      target = argTarget;
   }
}

public class traversals {
	
   public static void main(String[] args) {
   	// TODO Auto-generated method stub
      Vertex A = new Vertex("A");
      Vertex B = new Vertex("B");
      Vertex C = new Vertex("C");
      Vertex D = new Vertex("D");
      Vertex E = new Vertex("E");
      Vertex F = new Vertex("F");
      Vertex G = new Vertex("G");
      Vertex H = new Vertex("H");
   	
      A.adjacencies = new Edge[] { new Edge(B), new Edge(D), new Edge(G) };
      B.adjacencies = new Edge[] { new Edge(A), new Edge(E), new Edge(F) };
      C.adjacencies = new Edge[] { new Edge(F), new Edge(H) };
      D.adjacencies = new Edge[] { new Edge(A), new Edge(F) };
      E.adjacencies = new Edge[] { new Edge(B), new Edge(G) };
      F.adjacencies = new Edge[] { new Edge(B), new Edge(C) };
      G.adjacencies = new Edge[] { new Edge(A), new Edge(E) };
      H.adjacencies = new Edge[] { new Edge(C) };
   	
      dfs(A);
      System.out.println();
      //System.out.println(bfs(A,2));
   }
	
   public static void bfs(Vertex source){
      source.minDistance = 0;
      source.visited = true;
      PriorityQueue<Vertex> path = new PriorityQueue<Vertex>();
      path.add(source);
      int distance = 0;
      System.out.print(source);
      while(!path.isEmpty()){
         Vertex v = path.poll();
         distance = distance + 1;
         for(Edge e : v.adjacencies){
            Vertex neighbor = e.target;
            if(neighbor.visited == false){
               neighbor.visited = true;
               neighbor.minDistance = distance;
               path.add(neighbor);
               System.out.print(neighbor);
            }
         }
      }
   }
	
   public static ArrayList<Vertex> bfs(Vertex source, int d){
      ArrayList<Vertex> re = new ArrayList<Vertex>();
      source.minDistance = 0;
      source.visited = true;
      PriorityQueue<Vertex> path = new PriorityQueue<Vertex>();
      path.add(source);
      int distance = 0;
      while(!path.isEmpty()){
         Vertex v = path.poll();
         distance = distance + 1;
         for(Edge e : v.adjacencies){
            Vertex neighbor = e.target;
            if(neighbor.visited == false){
               neighbor.visited = true;
               neighbor.minDistance = distance;
               if(neighbor.minDistance == d){
                  re.add(neighbor);
               }
               path.add(neighbor);
            }
         }
      }
      return re;
   }
   
   public static void dfs(Vertex source){
      Stack<Vertex> stack = new Stack<Vertex>();
      source.visited = true;
      stack.push(source);
      System.out.print(source);
      while(!stack.isEmpty()){
         Vertex v = stack.peek();
         Vertex u = check(v);
         if(u == null){
            stack.pop();
         }
         else {
            stack.push(u);
            u.visited = true;
            System.out.print(u);
         }
         
      }
   }
   public static Vertex check(Vertex v){
      for(Edge e : v.adjacencies){
         Vertex y = e.target;
         if(y.visited == false){
            return y;
         }
      }
      return null;
   }
}
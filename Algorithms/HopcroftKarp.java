/**
 ** Java Program to Implement Hopcroft-Karp Algorithm
 **/
 
import java.util.*;
 
public class HopcroftKarp
{    
    public static final int NIL = 0;
    public static final int INF = Integer.MAX_VALUE;
    public static ArrayList<Integer>[] Adj; 
    public static int[] Pair;
    public static int[] Dist;
    public static int cx, cy;
    
    public static void main (String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hopcroft Algorithm Test\n");
 
        /** Accept number of edges **/
        System.out.println("Enter number of edges\n");
        int E = sc.nextInt();
        int[] x = new int[E];
        int[] y = new int[E];
        cx = 0;
        cy = 0;
 
        System.out.println("\nEnter "+ E +" x, y coordinates \n");
        for (int i = 0; i < E; i++)
        {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
            cx = Math.max(cx, x[i]);
            cy = Math.max(cy, y[i]);
        }
        cx += 1;
        cy += 1;
        
 
        makeGraph(x, y, E);            
 
        System.out.println("\nMatches : "+ solve());       
        sc.close();
    }    
    
    public static int solve()
    {
        Pair = new int[cx + cy + 1];
        Dist = new int[cx + cy + 1];
        int matching = 0;
        while (BFS())
            for (int v = 1; v <= cx; v++)
                if (Pair[v] == NIL)
                    if (DFS(v))
                        matching = matching + 1;
        return matching;
    }
    
    @SuppressWarnings("unchecked")
	public static void makeGraph(int[] x, int[] y, int E)
    {
        Adj = new ArrayList[cx + cy + 1];
        for (int i = 0; i < Adj.length; ++i)
            Adj[i] = new ArrayList<Integer>();        
        
        for (int i = 0; i < E; ++i) 
            addEdge(x[i] + 1, y[i] + 1);    
    }
    
    public static void addEdge(int u, int v) 
    {
        Adj[u].add(cx + v);
        Adj[cx + v].add(u);
    }   
    
    public static boolean BFS() 
    {
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int v = 1; v <= cx; ++v) 
            if (Pair[v] == NIL) 
            { 
                Dist[v] = 0; 
                queue.add(v); 
            }
            else 
                Dist[v] = INF;
 
        Dist[NIL] = INF;
 
        while (!queue.isEmpty()) 
        {
            int v = queue.poll();
            if (Dist[v] < Dist[NIL]) 
                for (int u : Adj[v]) 
                    if (Dist[Pair[u]] == INF) 
                    {
                        Dist[Pair[u]] = Dist[v] + 1;
                        queue.add(Pair[u]);
                    }           
        }
        return Dist[NIL] != INF;
    }    
    
    public static boolean DFS(int v) 
    {
        if (v != NIL) 
        {
            for (int u : Adj[v]) 
                if (Dist[Pair[u]] == Dist[v] + 1)
                    if (DFS(Pair[u])) 
                    {
                        Pair[u] = v;
                        Pair[v] = u;
                        return true;
                    }               
 
            Dist[v] = INF;
            return false;
        }
        return true;
    } 
}

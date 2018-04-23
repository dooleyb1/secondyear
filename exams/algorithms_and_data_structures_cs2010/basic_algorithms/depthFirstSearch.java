// ----------------------------------------------------------
/**
 * Depth-First-Search using a Directed Graph
 *
 * ----------------------------------------------------------

 */

 public class DirectedDFS{

   //True if path from s to marked[v]
   private boolean[] marked;

   //Constructor marks vertices reachable from source
   public DirectedDFS(Digraph G, int s){
     marked = new boolean[G.V()];
     dfs(G, s);
   }

   //Recursively performs dfs
   private void dfs(Digraph G, int V){
     marked[v] = true;

     //Iterate over vertices adjacent to v and perfrom dfs
     for(int w : G.adj(v)){
       if(!marked[w])
        dfs(G, w)
     }
   }

   public boolean visited(int v){
     return marked[v];
   }
 }

import java.lang.reflect.Array;
import java.util.*;
public class CheckCycleByBFS {
    public static class Edge{
        int src;
        int nbr;
        Edge(int src, int nbr){
            this.src=src;
            this.nbr=nbr;
        }
    }
    public static boolean checkBFS(ArrayList<Edge>[] graph, int n){ ///BFS
        boolean[] visited = new boolean[n+1];
        for(int i = 1; i<=n;i++){
            if(!visited[i]){
                if(IsCycle(i, visited, graph)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean checkDFS(ArrayList<Edge>[] graph, int n){ ///DFS
        boolean[] visited= new boolean[n+1];
        for(int i = 1; i<=n; i++){
            if(!visited[i]){
                if(ISCYCLE(i, -1, visited, graph)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean IsCycle(int i, boolean[] visited, ArrayList<Edge>[] graph){ //BFS
        //visited[i] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(i);
        while(q.size()>0){
            int t = q.remove();


                if(!visited[t]) {
                    visited[t] = true;
                    for (Edge e : graph[t]) {
                        if (!visited[e.nbr]) {
                            q.add(e.nbr);
                        }
                    }
                }else{
                    return true;
                }

        }
        return false;
    }
    public static boolean ISCYCLE(int i, int prev, boolean[] visited, ArrayList<Edge>[] graph){
        visited[i] = true;
        for(Edge e: graph[i]){
            if(!visited[e.nbr]){

                boolean rres =  ISCYCLE(e.nbr,i, visited, graph);
                if(rres==true)return true;

            }else {
                if(prev!=e.nbr){
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        ArrayList<Edge>[] graph = new ArrayList[n+1];
        for(int i = 1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i<m; i++)
        {
            int a = scn.nextInt();
            int b = scn.nextInt();
            graph[a].add(new Edge(a, b));
            graph[b].add(new Edge(b, a));

        }

        System.out.println(checkDFS(graph, n)); //Cycle check with DFS
        System.out.println(checkBFS(graph, n)); //Cycle check with BFS
        System.out.println(checkBipartiteBFS(graph, n)); //graph coloring problem with BFS
        System.out.println(checkBipartiteDFS(graph, n)); //graph coloring problem with DFS


    }
    public static boolean checkBipartiteBFS(ArrayList<Edge>[] graph, int n){
        int color[] = new int[n+1];
        Arrays.fill(color, -1);
        for(int i = 1; i<=n; i++)
        {
            if(color[i]==-1){
                if(!checkBIPBFS(i, graph, color)){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean checkBIPBFS(int i, ArrayList<Edge>[] graph, int[] color){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(i);

        color[i]=1;
        while(q.size()>0){
            int t= q.remove();
           // color[t]=c;
            for(Edge e: graph[t]){
                if(color[e.nbr]==-1){



                    color[e.nbr] = 1-color[t];
                    q.add(e.nbr);

                }else{
                    if(color[e.nbr]==color[t]){
                        return false;
                    }
                }
            }

        }
        return true;
    }
    public static boolean checkBipartiteDFS(ArrayList<Edge>[] graph, int n){
        int color[] = new int[n+1];
        Arrays.fill(color, -1);
        for(int i = 1; i<=n; i++)
        {
            if(color[i]==-1){
                if(!checkBIPDFS(i, graph, color)){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean checkBIPDFS(int i, ArrayList<Edge>[] graph, int[] color){


        if(color[i]==-1) color[i]=1;
        for(Edge e: graph[i]){
            if(color[e.nbr]==-1){
                color[e.nbr] = 1-color[i];
                if(!checkBIPDFS( e.nbr, graph,  color)){
                    return false;
                }
            }else{
                if(color[i] == color[e.nbr]){
                    return false;
                }
            }
        }

        return true;
    }
}

import java.util.ArrayList;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;


public class Graph {
    private int V;
    private ArrayList<ArrayList<Integer>> adj;

    public Graph(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Integer>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public void DFS(int start) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        visited[start] = true;
        stack.push(start);

        while (!stack.empty()) {
            int current = stack.pop();
            System.out.println(current + " ");

            for (int i = 0; i < adj.get(current).size(); i++) {
                int neighbour = adj.get(current).get(i);
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    stack.push(neighbour);
                }
            }
        }
    }
    public void BFS(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int i = 0; i < adj.get(current).size(); i++) {
                int neighbour = adj.get(current).get(i);
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 4);



        System.out.println("Depth First Traversal (starting from vertex 0): ");
        g.DFS(0);
        
        Graph h = new Graph(5);
        h.addEdge(0, 1);
        h.addEdge(0, 2);
        h.addEdge(1, 3);
        h.addEdge(1, 4);
        h.addEdge(2, 4);


        System.out.println("Breadth First Traversal (starting from vertex 0): ");
        h.BFS(0);
    }
}

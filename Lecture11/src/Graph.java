import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {

    private final int                            vertices;
    private       ArrayList<LinkedList<Integer>> adj;

    public Graph(int vertices) {
        this.vertices = vertices;
        adj = new ArrayList<>(vertices);
        for (int v = 0; v < vertices; v++)
            adj.add(new LinkedList<>());
    }

    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    public int degree(int v) {
        int degree = 0;
        for (int w : adj(v))
            degree++;
        return degree;
    }

    public int vertex_cnt() {
        return vertices;
    }

    public int edges() {
        int res = 0;
        for (int i = 0; i < vertices; i++) {
            res += adj.get(i).size();
        }
        return res/2;
    }

    public Iterable<Integer> adj(int v) {
        return adj.get(v);
    }
}

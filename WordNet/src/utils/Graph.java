package utils;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {

    private final int                            vertices_cnt;
    private       ArrayList<LinkedList<Integer>> adj;

    public Graph() {
        this.vertices_cnt = 10;
        adj = new ArrayList<>(vertices_cnt);
        for (int v = 0; v < vertices_cnt; v++) {
            adj.add(new LinkedList<>());
        }
    }

    public Graph(int vertices_cnt) {
        this.vertices_cnt = vertices_cnt;
        adj = new ArrayList<>(vertices_cnt);
        for (int v = 0; v < vertices_cnt; v++)
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
        return vertices_cnt;
    }

    public int edges() {
        int res = 0;
        for (int i = 0; i < vertices_cnt; i++) {
            res += adj.get(i).size();
        }
        return res/2;
    }

    public Iterable<Integer> adj(int v) {
        return adj.get(v);
    }
}

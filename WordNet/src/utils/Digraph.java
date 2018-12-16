package utils;

import java.util.ArrayList;
import java.util.LinkedList;

public class Digraph extends Graph {
    private final int                            vertices_cnt;
    private       ArrayList<LinkedList<Integer>> adj;

    public Digraph() {
        super();
        this.vertices_cnt = 10;
        adj = new ArrayList<>(vertices_cnt);
        for (int v = 0; v < vertices_cnt; v++) {
            adj.add(new LinkedList<>());
        }
    }

    public Digraph(int vertices_cnt) {
//        super(vertices_cnt);
        this.vertices_cnt = vertices_cnt;
        adj = new ArrayList<>(vertices_cnt);
        for (int v = 0; v < vertices_cnt; v++) {
            adj.add(new LinkedList<>());
        }
    }

    public void addEdge(int v, int w) {
        try {
            adj.get(v).add(w);
        } catch (IndexOutOfBoundsException e) {
            adj.ensureCapacity(adj.size()*2);
            adj.set(v, new LinkedList<>()).add(w);
        }
    }

    public void stronglyConnect(int v, int w) {
        addEdge(v, w);
        addEdge(w, v);
    }

    public Digraph reverse() {
        Digraph reverse = this;
        for (int v = 0; v < vertices_cnt; v++) {
            for (int w : adj.get(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    public int vertex_cnt() {
        return this.vertices_cnt;
    }

    public Iterable<Integer> adj(int v) {
        return adj.get(v);
    }
}

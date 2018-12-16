package utils;

import java.util.LinkedList;

public class DepthFirstPaths implements Pathfinder {
    private       boolean marked[];
    private       int     edge_to[];
    private final int     start;

    public DepthFirstPaths(Graph g, int start) {
        this.start = start;
        this.marked = new boolean[g.vertex_cnt()];
        this.edge_to = new int[g.vertex_cnt()];
        dfs(g, start);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int new_v : g.adj(v)) {
            if (!marked[new_v]) {
                edge_to[new_v] = v;
                dfs(g, new_v);
            }
        }
    }

    public boolean has_path_to(int v) {
        return marked[v];
    }

    public Iterable<Integer> path_to(int v) {
        if (!has_path_to(v)) {
            return null;
        }
        LinkedList<Integer> path = new LinkedList<>();
        for (int i = v; i != start; i = edge_to[i])
            path.push(i);
        path.push(start);
        return path;
    }
}

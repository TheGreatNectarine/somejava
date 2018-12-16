import java.util.LinkedList;
import java.util.Stack;

public class DepthFirstPaths {


    private       boolean marked[];
    private       int     edge_to[];
    private final int     start;

    DepthFirstPaths(Digraph g, int start) {
        this.start = start;
        this.marked = new boolean[g.V()];
        this.edge_to = new int[g.V()];
        dfs(g, start);
    }

    DepthFirstPaths(Graph g, int start) {
        this.start = start;
        this.marked = new boolean[g.V()];
        this.edge_to = new int[g.V()];
        dfs(g, start);
    }

    DepthFirstPaths(Graph g, int start, boolean[] visited) {
        this.start = start;
        this.marked = new boolean[g.V()];
        this.edge_to = new int[g.V()];
        dfs(g, start);
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && marked[i]) {
                visited[i] = true;
            }
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        for (int new_v : g.adj(v)) {
            if (!marked[new_v]) {
                edge_to[new_v] = v;
                dfs(g, new_v);
            }
        }
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

    public boolean[] getMarked() {
        return marked;
    }
}

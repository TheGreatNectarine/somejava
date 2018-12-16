import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstPaths implements Pathfinder {
    private       boolean marked[];
    private       int     edge_to[];
    private       int     dist_to[];
    private final int     start;

    BreadthFirstPaths(Graph g, int start) {
        this.start = start;
        this.marked = new boolean[g.vertex_cnt()];
        this.edge_to = new int[g.vertex_cnt()];
        dist_to = new int[g.vertex_cnt()];
        bfs(g, start);
    }

    private void bfs(Graph g, int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        marked[start] = true;
        dist_to[start] = 0;
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    q.add(w);
                    marked[w] = true;
                    edge_to[w] = v;
                    dist_to[w] = dist_to[v]+1;
                }
            }
        }
    }

    @Override
    public boolean has_path_to(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> path_to(int v) {
        if (!has_path_to(v)) {
            return null;
        }
        LinkedList<Integer> path = new LinkedList<>();
        for (int i = v; i != start; i = edge_to[i]) {
            path.push(i);
        }
        path.push(start);
        return path;
    }
}

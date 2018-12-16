package utils;

public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s->v path?
    private int[]     edgeTo;      // edgeTo[v] = last edge on shortest s->v path
    private int[]     distTo;      // distTo[v] = length of shortest s->v path


    public BreadthFirstPaths(Digraph g, int s) {
        int v = g.vertex_cnt();
        marked = new boolean[v];
        distTo = new int[v];
        edgeTo = new int[v];
        for (int w = 0; w < v; w++) {
            distTo[w] = INFINITY;
        }
        bfs(g, s);
    }


    public BreadthFirstPaths(Digraph g, Iterable<Integer> sources) {
        int v = g.vertex_cnt();
        marked = new boolean[v];
        distTo = new int[v];
        edgeTo = new int[v];
        for (int w = 0; w < v; w++) {
            distTo[w] = INFINITY;
        }
        bfs(g, sources);
    }


    private void bfs(Digraph G, int s) {
        Queue<Integer> q = new Queue<>();
        marked[s] = true;
        distTo[s] = 0;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v]+1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }


    private void bfs(Digraph G, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v]+1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }


    public boolean hasPathTo(int v) {
        return marked[v];
    }


    public int distTo(int v) {
        return distTo[v];
    }


    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        int            x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }
}

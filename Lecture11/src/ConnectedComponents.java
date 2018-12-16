public class ConnectedComponents {
    private boolean[] marked;
    private int[]     id;
    private int       count;

    public ConnectedComponents(Graph g) {
        marked = new boolean[g.vertex_cnt()];
        id = new int[g.vertex_cnt()];
        for (int v = 0; v < g.vertex_cnt(); v++) {
            if (!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    /**
     * @return number of connected components
     */
    public int count() {
        return count;
    }

    /**
     * @param v vertex
     * @return root of connected component which vertex v belongs to
     */
    public int id(int v) {
        return id[v];
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }
}

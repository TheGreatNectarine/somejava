import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.LinkedList;

public class Cycles {
    private Graph               G;
    private LinkedList<Integer> pathHamilton;
    private LinkedList<Integer> pathEuler;
    private boolean             visited[];
    private boolean             hasHamilton;
    private boolean             hasEuler;

    public Cycles(Graph G) {
        this.G = G;
        pathHamilton = new LinkedList<>();
        pathEuler = new LinkedList<>();
        visited = new boolean[G.V()];
        hasHamilton = hamiltonExists(0);
        hasEuler = eulerExists();
    }

    public Iterable<Integer> euler() {
        return pathEuler;
    }

    public Iterable<Integer> hamiltonian() {
        return pathHamilton;
    }

    private boolean eulerExists() {
        int oddVertexCnt = 0;
        for (int i = 0; i < G.V(); i++) {
            if (G.degree(i)%2 == 1) {
                oddVertexCnt += 1;
            }
        }
        if (oddVertexCnt > 2) {
            return false;
        }
        boolean[] visited = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (G.degree(i) > 0) {
                DepthFirstPaths dfs = new DepthFirstPaths(G, i, visited);

            }
        }
        for (int i = 0; i < G.V(); i++) {
            if (G.degree(i) > 0 && !visited[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean hamiltonExists(int curr) {
        pathHamilton.add(curr);

        //exit from recursion
        if (pathHamilton.size() == G.V()) {
            if (G.hasEdge(0, pathHamilton.getLast())) {
                return true;
            } else {
                pathHamilton.removeLast();
                return false;
            }
        }

        visited[curr] = true;
        for (Integer next = 0; next < G.V(); next++) {
            if (G.hasEdge(curr, next) && !visited[next]) {
                if (hamiltonExists(next)) {
                    return true;
                }
            }
        }
        visited[curr] = false;

        pathHamilton.removeLast();

        return false;
    }
}

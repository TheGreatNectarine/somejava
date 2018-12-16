import edu.princeton.cs.algs4.Graph;

public class CyclesDir {
    private Digraph diG;

    public CyclesDir(Digraph G) {
        this.diG = G;
    }

    public Iterable<Integer> euler() {
    }

    public Iterable<Integer> hamiltonian() {

    }

    private boolean eulerExists() {
        if (diG.connected()) {
            for (int i = 0; i < diG.V(); i++) {
                if (diG.indegree(i) != diG.outdegree(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean hamiltonExists() {

    }
}

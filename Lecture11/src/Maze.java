public class Maze {
    public final int START = 0;
    public final int   TARGET;
    private      Graph maze;

    public Maze(Graph g, int target) {
        this.TARGET = target;
        this.maze = g;
    }

    public Iterable<Integer> solution_dfs() {
        return new DepthFirstPaths(this.maze, this.START).path_to(TARGET);
    }

    public Iterable<Integer> solution_bfs() {
        return new BreadthFirstPaths(this.maze, this.START).path_to(TARGET);
    }
}
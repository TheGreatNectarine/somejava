public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(7);
        g.addEdge(0,1);
        g.addEdge(0,4);
        g.addEdge(1,4);
        g.addEdge(2,1);
        g.addEdge(2,3);
        g.addEdge(2,5);
        g.addEdge(5,6);
        Maze m = new Maze(g,6);
        m.solution_dfs().forEach(System.out::println);
    }
}

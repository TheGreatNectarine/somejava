public interface Pathfinder {
    boolean has_path_to(int v);

    Iterable<Integer> path_to(int v);
}

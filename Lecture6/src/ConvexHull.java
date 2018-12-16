import java.util.ArrayList;

class ConvexHull {

    static ArrayList<Point> find_convex_hull(Point[] points) {
        assert points.length > 3;

        ArrayList<Point> hull = new ArrayList<>();

        int leftmost   = leftmost(points);
        int to_add_ind = leftmost;
        int current_point;

        while (true) {
            hull.add(points[to_add_ind]);
            current_point = to_add_ind + 1;

            for (int i = 0; i < points.length - 1; i++) {
                if (Point.relative_position(points[to_add_ind], points[i], points[current_point])
                        == Point.Orientation.COUNTERCLOCKWISE) {
                    current_point = i;
                }
            }

            to_add_ind = current_point;
            if (leftmost == to_add_ind)
                return hull;
        }
    }

    private static int leftmost(Point[] points) {
        int leftmost = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].x < points[leftmost].x) {
                leftmost = i;
            }
        }
        return leftmost;
    }

}

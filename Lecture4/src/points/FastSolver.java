package points;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class FastSolver implements Solver {

    @Override
    @NotNull
    public ArrayList<Line> lines(Point[] points) {
        ArrayList<Line> lines = new ArrayList<>();
        for (Point p : points) {
            Point[] in = Arrays.copyOf(points, points.length);
            lines.addAll(lines_for_point(p, in));
        }
        return lines;
    }

    private ArrayList<Line> lines_for_point(Point p, Point[] points) {
        ArrayList<Line> lines = new ArrayList<>();
        Arrays.sort(points, p.SLOPE_ORDER);

        double last_slope = p.slopeTo(points[1]);
        Line   l          = new Line(p, points[1]);
        int    counter    = 1;
        for (Point point : points) {
            if (Point.compare_slopes(last_slope, p.slopeTo(point)) == 0) {
                l.add_point(point);
                ++counter;
            } else {
                if (counter >= Line.LINE_LENGTH - 1) {
                    if (!lines.contains(l))
                        lines.add(l);
                }
                counter = 1;
                l = new Line(p, point);
                last_slope = p.slopeTo(point);
            }
        }
        if (counter >= Line.LINE_LENGTH - 1) {
            if (!lines.contains(l))
                lines.add(l);
        }
        return lines;
    }


}

package points;

import java.util.ArrayList;

@FunctionalInterface
public interface Solver {

    ArrayList<Line> lines(Point[] points);

}

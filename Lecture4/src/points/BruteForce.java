package points;

import edu.princeton.cs.algs4.StdDraw;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;

public class BruteForce implements Solver {

    @NotNull
    public ArrayList<Line> lines(Point[] points) {
        set_settings();
        ArrayList<Line> lines = new ArrayList<>();
        int len = points.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    for (int l = k + 1; l < len; l++) {
                        if (Point.equal_slopes(points[i], points[j], points[k], points[l])) {
                            Line line = new Line(points[i], points[j], points[k], points[l]);
                            System.out.println(line);
                            if (!lines.contains(line))
                                lines.add(line);
                        }
                    }
                }
            }
        }
        if (lines.size() == 0)
            throw new NullPointerException("No lines were found");
        return lines;
    }

    private static void set_settings() {
        StdDraw.setPenRadius(0.003);
        StdDraw.setPenColor(Color.RED);
    }

}
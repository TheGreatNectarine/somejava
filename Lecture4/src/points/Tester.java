package points;

import edu.princeton.cs.algs4.StdDraw;

import java.io.*;
import java.util.*;

public class Tester {

    private static final double pen_r = 0.005, scale_r = 0.001;
    private static final int canvas_w = 720, canvas_h = 720, scale = 32768;
    private static final String FILENAME = "rs1423";
    static final double ACCURACY = 0.0;

    private static final Solver BRUTE_FORCE = new BruteForce();
    private static final Solver QUICK_SOLVER = new FastSolver();

    public static void main(String[] args) {
        primary_settings();
        ArrayList<Point> points = Operator.read_points(new File(FILENAME+".txt"));
        Operator.draw_points(points.toArray(new Point[points.size()]));
//        Operator.draw_lines(points.toArray(new Point[points.size()]), BRUTE_FORCE);
        Operator.draw_lines(points.toArray(new Point[points.size()]), QUICK_SOLVER);

    }

    private static void primary_settings() {
        StdDraw.setCanvasSize(canvas_w, canvas_h);
        StdDraw.setXscale(0, scale);
        StdDraw.setYscale(0, scale);
        draw_scale();
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(pen_r);
    }

    private static void draw_scale() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(scale_r);
        for (int i = 0; i < scale; i += 1000) {
            StdDraw.line(i, 0, i, scale);
            StdDraw.line(0, i, scale, i);
        }
    }

}

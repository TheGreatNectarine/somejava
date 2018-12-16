import edu.princeton.cs.algs4.StdDraw;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;

public class Point implements Comparable<Point> {
    @Deprecated
    final Comparator<Point> SLOPE_ORDER = new SlopeOrder();
    @Deprecated
    final Comparator<Point> POLAR_ORDER = new PolarOrder();

    final int x;
    private final int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void draw() {
        StdDraw.point(x, y);
    }

    void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    @Deprecated
    static boolean equal_slopes(Point... points) {
        for (int i = 0; i < points.length - 2; i++) {
            double slope      = points[i].slopeTo(points[i + 1]);
            double slope_next = points[i + 1].slopeTo(points[i + 2]);
            if (slope != slope_next)
                return false;
        }
        return true;
    }

    private double slopeTo(Point that) {
        if (this.y == that.y) {
            if (this.x == that.x) {
                return Double.NEGATIVE_INFINITY;
            }
            return 0;
        }
        if (this.x == that.x)
            return Double.POSITIVE_INFINITY;

        return (double) (that.y - this.y) / (that.x - this.x);
    }

    private double angle_to(Point that) {
        double angle = atan2(that.y - this.y, that.x - this.x);

        if (angle < 0) {
            angle += PI;
        }

        return angle;
    }

    @Deprecated
    public int compareTo(@NotNull Point that) {
        if ((this.y < that.y) || (this.y == that.y && this.x < that.x))
            return -1;
        if (this.y == that.y && this.x == that.x)
            return 0;
        return 1;
    }

    static Orientation relative_position(Point p, Point q, Point t) {
        int determinant = (q.y - p.y) * (t.x - q.x) - (q.x - p.x) * (t.y - q.y);
        if (determinant == 0)
            return Orientation.COLINEAR;

        if (determinant > 0)
            return Orientation.CLOCKWISE;

        return Orientation.COUNTERCLOCKWISE;
    }

    private static int compare_slopes(double slope_p, double slope_q) {
        if (slope_p < slope_q - 0/*Tester.ACCURACY*/) {
            return -1;
        }
        if (slope_p > slope_q + 0/*Tester.ACCURACY*/) {
            return 1;
        }
        return 0;

    }

    private static int compare_degrees(double degree_p, double degree_q) {
        return Double.compare(degree_p, degree_q);
    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p, Point q) {
            double slope_p = Point.this.slopeTo(p);
            double slope_q = Point.this.slopeTo(q);
            return compare_slopes(slope_p, slope_q);
        }
    }

    private class PolarOrder implements Comparator<Point> {

        @Override
        public int compare(Point p, Point q) {
            double degree_p = Point.this.angle_to(p);
            double degree_q = Point.this.angle_to(q);
            return compare_degrees(degree_p, degree_q);
        }
    }

    public String toString() {
        return String.format("[%d, %d]", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point that = (Point) obj;
            return this.x == that.x && this.y == that.y;
        }
        throw new UnsupportedOperationException(
                "Can't compare Point and " + obj.getClass().getSimpleName()
        );
    }

    public enum Orientation {
        COLINEAR(-1), CLOCKWISE(0), COUNTERCLOCKWISE(1);

        final int value;

        Orientation(int value) {
            this.value = value;
        }
    }
}
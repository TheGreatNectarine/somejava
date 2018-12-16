package points;

import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;
import org.jetbrains.annotations.NotNull;

public class Point implements Comparable<Point> {

    final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    final int x;
    final int y;

    Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    @Deprecated
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    static boolean equal_slopes(Point... points) {
        for (int i = 0; i < points.length - 2; i++) {
            double slope = points[i].slopeTo(points[i + 1]);
            double slope_next = points[i + 1].slopeTo(points[i + 2]);
            if (slope != slope_next)
                return false;
        }
        return true;
    }


    double slopeTo(Point that) {
        if (this.y == that.y) {
            if (this.x == that.x) {
                return Double.NEGATIVE_INFINITY;
            }
            return 0;
        }
        if (this.x == that.x)
            return Double.POSITIVE_INFINITY;
        double res = (double) (that.y - this.y) / (that.x - this.x);

        return res;
    }

    public int compareTo(@NotNull Point that) {
        if ((this.y < that.y) || (this.y == that.y && this.x < that.x))
            return -1;
        if (this.y == that.y && this.x == that.x)
            return 0;
        return 1;
    }

    static int compare_slopes(double slope_p, double slope_q) {
        if (slope_p < slope_q - Tester.ACCURACY) {
            return -1;
        }
        if (slope_p > slope_q + Tester.ACCURACY) {
            return 1;
        }
        return 0;

    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p, Point q) {
            double slope_p = Point.this.slopeTo(p);
            double slope_q = Point.this.slopeTo(q);
            return compare_slopes(slope_p, slope_q);
        }
    }

    public String toString() {
        /* DO NOT MODIFY */
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
}
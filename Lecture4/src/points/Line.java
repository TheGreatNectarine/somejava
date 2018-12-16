package points;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class Line {

    static final int LINE_LENGTH = 4;

    private       ArrayList<Point> points;
    private       Point            first;
    private       Point            last;
    private final double           slope;

    Line(Point... points) {
        assert points.length < 2;
        this.points = new ArrayList<>();
        Arrays.sort(points, Comparator.naturalOrder());
        Collections.addAll(this.points, points);
        this.first = this.points.get(0);
        this.last = this.points.get(this.points.size() - 1);
        this.slope = this.first.slopeTo(this.last);
    }

    private boolean contains(Point p) {
        return points.contains(p);
    }

    private Point find_first() {
        Point first = this.points.get(0);
        for (Point p : this.points) {
            if (p.compareTo(first) < 0) {
                first = p;
            }
        }
        return first;
    }

    private Point find_last() {
        Point last = this.points.get(0);
        for (Point p : this.points) {
            if (p.compareTo(last) > 0) {
                last = p;
            }
        }
        return last;
    }

    void draw() {
        StdDraw.line(this.first.x, this.first.y, this.last.x, this.last.y);
    }

    void next_color() {
        StdDraw.setPenColor(random_color());
    }

    private Color random_color() {
        ThreadLocalRandom t = ThreadLocalRandom.current();
        return new Color(t.nextInt(0, 256), t.nextInt(0, 256), t.nextInt(0, 256));
    }

    void add_point(Point p) throws IllegalArgumentException {
        assert this.contains(p);
        assert p.slopeTo(first) == this.slope;
        if (p.compareTo(first) < 0)
            first = p;
        else if (p.compareTo(last) > 0)
            last = p;
        points.add(p);
    }


    public ArrayList<Point> getPoints() {
        return points;
    }

    public Point getFirst() {
        return first;
    }

    public Point getLast() {
        return last;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Line {\n");
        for (Point p : this.points) {
            sb.append(p.toString()).append(" ");
        }
        sb.append("\n}");
        return sb.toString();
    }

    void print() {
        System.out.println(this);
    }

    @Override
    public boolean equals(Object obj) {
        assert obj instanceof Line;
        Line that = (Line) obj;
        return this.first.equals(that.first) && this.last.equals(that.last);
    }
}

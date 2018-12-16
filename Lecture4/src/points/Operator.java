package points;

import edu.princeton.cs.algs4.StdDraw;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class Operator {

    static ArrayList<Point> read_points(File f) {
        ArrayList<Point> points = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            br.readLine();
            while (true) {
                line = br.readLine();
                if (line != null)
                    points.add(parse_point(line));
                else break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    @NotNull
    private static Point parse_point(String line) {
        int[] coordinates = Arrays.stream(line.split("\\s"))
                .filter(a -> !a.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
        return new Point(coordinates[0], coordinates[1]);
    }

    static void draw_points(Point[] points) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for (Point p : points) {
            p.draw();
        }
    }

    @SuppressWarnings("unchecked")
    static void draw_lines(Point[] points, @NotNull Solver solver) {
        StdDraw.setPenRadius(0.003);
        StdDraw.setPenColor(Color.RED);
        for (Line l : solver.lines(points)) {
            l.draw();
            l.next_color();
//            l.print();
//            sleep();
        }
    }

    static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

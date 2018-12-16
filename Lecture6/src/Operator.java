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

    private static ArrayList<Point> read_points(File f) {
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

    static ArrayList<Point> read_points(String filename) {
        File file = new File(filename);
        return read_points(file);
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

    static void draw_convex_hull(ArrayList<Point> points) {
        StdDraw.setPenColor(Color.RED);
        StdDraw.setPenRadius(0.005);
        for (int i = 0; i < points.size() - 1; i++) {
            points.get(i).drawTo(points.get(i + 1));
            sleep();
        }
        points.get(points.size() - 1).drawTo(points.get(0));
    }

    private static void sleep() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

import edu.princeton.cs.algs4.StdDraw;

import java.io.File;

public class Tester {

    private static final double pen_r = 0.005, scale_r = 0.001;
    private static final int canvas_w = 720, canvas_h = 720, scale = 32768;
    private static final String EXTENSION = "rs1423.txt";
    private static final String FILENAME  = "" + EXTENSION;
    private static final File   FILE      = new File(FILENAME + EXTENSION);
    static final         double ACCURACY  = 0.0;

    public static void main(String[] args) {
//        ArrayList<Point> points = Operator.read_points(FILENAME);
//        primary_settings();
//        Operator.draw_points(points.toArray(new Point[points.size()]));
//        ArrayList<Point> hull = ConvexHull.find_convex_hull(points.toArray(new Point[points.size
//                ()]));
//        Operator.draw_convex_hull(hull);
//        System.out.println("HULL: \n" + hull);
        int a = 0;
        System.out.println();
    }
    //(n)! == n * (n-1)!
    //(n)! == factorial(n)
    private static int factorial (int n){
        if (n<0){
            return -1;
        }
        if(n==0 || n==1){
            return 1;
        }
        return n*factorial(n-1);
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

package practice;

import java.io.*;
import java.util.Arrays;

public class ColorBalls {
    public static void main(String[] args) {
//        System.out.println("Hello world");
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
            FileWriter fw = new FileWriter(new File("output.txt"));
            int number_of_balls = Integer.parseInt(br.readLine());
            String[] colors = br.readLine().split(" ");
            fw.write(count_balls_to_repaint(number_of_balls, convert_array(colors)));
            br.close();
            fw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static String count_balls_to_repaint(int size, int[] colors) {
        int[] color_counter = new int[9];
        int res = 0;
        for (int i = 0; i < size; i++) color_counter[colors[i] - 1] += 1;
        Arrays.sort(color_counter);
        for (int i : color_counter) res += i;
        return Integer.toString(res - max(color_counter));
    }

    private static int max(int[] arr) {
        int biggest = 0;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] > biggest) biggest = arr[i];
        return biggest;
    }

    private static int[] convert_array(String[] origin) {
        int[] res = new int[origin.length];
        for (int i = 0; i < res.length; i++)
            res[i] = Integer.parseInt(origin[i]);
        return res;
    }
}
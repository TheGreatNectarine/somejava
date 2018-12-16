import org.jetbrains.annotations.Contract;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class QUU {
    public static void main(String[] args) {
        try {
            BufferedReader br           = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter bw           = new BufferedWriter(new FileWriter("output.txt"));
            int            num_of_boxes = Integer.parseInt(br.readLine().split("\\s")[1]);
            int[]          queue        = parse_q(br.readLine().split("\\s"));
            long[]          servers      = new long[num_of_boxes];
            int            index        = 0;
            while (queue.length > index) {
                servers[index_of_min(servers)] += queue[index] != 0 ? queue[index] : queue[index++];
                ++index;
            }
            long max = max_in_array(servers);
            bw.write(max + "");
            br.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Contract(pure = true)
    private static int[] parse_q(String[] line) {
        int[] arr = new int[line.length];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = Integer.parseInt(line[i]);
        }
        return arr;
    }

    @Contract(pure = true)
    private static int index_of_min(long[] arr) {
        int min = 0;
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] < arr[min]) {
                min = i;
            }
        }
        return min;
    }

    @Contract(pure = true)
    private static long max_in_array(long[] arr) {
        long max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}
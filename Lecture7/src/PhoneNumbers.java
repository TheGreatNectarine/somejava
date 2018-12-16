import java.io.*;

public class PhoneNumbers {

    private static String[] numbers = new String[1000000];
    private static int      size    = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
             BufferedWriter bw = new BufferedWriter(new FileWriter(new File("output.txt")))) {
            int tests = Integer.parseInt(br.readLine());
            for (int i = 0; i < tests; i++) {
                boolean pass           = false;
                int     num_of_numbers = Integer.parseInt(br.readLine());
                for (int j = 0; j < num_of_numbers; j++) {
                    String number = br.readLine();
                    if (!pass) {
                        int rank = rank(number);
                        put(number);
                        if (one_in_another(numbers[rank], number) || one_in_another(numbers[rank - 1], number)) {
                            System.out.println("NO");
                            pass = true;
                        }
                    }
                }
                if (!pass) {
                    System.out.println("YES");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void put(String number) {
        int rank = rank(number);
        System.arraycopy(numbers, rank, numbers, rank + 1, size - rank);
        numbers[rank] = number;
        size++;
    }

    private static int rank(String key) {
        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(numbers[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    private static boolean one_in_another(String s1, String s2) {
        return s1 != null && s2 != null && (s1.indexOf(s2) == 0 || s2.indexOf(s1) == 0);
    }

}

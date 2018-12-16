import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tree {

    public static int water(int layers) {
        return layers * layers + layers + 1;
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.printf("%d", water(Integer.parseInt(br.readLine())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

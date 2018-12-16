import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Rabbits {

    private static int rabbits(int months, int eaten) {
        int rabbits = 1;
        while (months > 0) {
            if (rabbits > eaten) rabbits -= eaten;
            rabbits *= 2;
            months--;
        }
        return rabbits;
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.printf("%d",rabbits(Integer.parseInt(br.readLine()),Integer.parseInt(br.readLine())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Головний {

    static ThreadLocalRandom random = ThreadLocalRandom.current();
    static byte              count  = 0;

    public static void main(String[] args) {
        StoodentKarpenkaKarogo[] arr = new StoodentKarpenkaKarogo[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new StoodentKarpenkaKarogo().name(random_name()).gay_todey(random_gey()).gay_num(random_gey_num()).diam
                    (random_diam());
        }

//        List<StoodentKarpenkaKarogo> arr = Arrays.asList(ss);
//
//        arr.forEach(student ->
//                student.name(random_name()).gay_todey(random_gey()).gay_num(random_gey_num()).diam
//                        (random_diam()));
        System.out.println(Arrays.toString(arr) + "\n----------------------\n");
//        Сортування.sort(arr, StoodentKarpenkaKarogo.ZHOP);
        System.out.println(Arrays.toString(arr) + "\n----------------------\n");
        Сортування.вибором(arr, StoodentKarpenkaKarogo.PEED);

        System.out.println(Arrays.toString(arr) + "\n----------------------\n");
        Сортування.вибором(arr, StoodentKarpenkaKarogo.NEZAR);

        System.out.println(Arrays.toString(arr) + "\n----------------------\n");

    }

    private static String random_name() {
        String[] names = {"Стасік", "КмаКодєр", "Пані Горка", "Денис Василенко", "Маргаль",
                "Ігор Шуднтра", "Пані Козопас",
                "Кірюха", "Саньок", "Прєпод"};
        return names[random.nextInt(0, names.length - 1)];
    }

    private static boolean random_gey() {
        if (count == 0) {
            count++;
            return true;
        }
        return false;
    }

    private static long random_gey_num() {
        return random.nextLong(0, Long.MAX_VALUE);
    }

    private static double random_diam() {
        return random.nextDouble(0, Double.MAX_VALUE);
    }

}

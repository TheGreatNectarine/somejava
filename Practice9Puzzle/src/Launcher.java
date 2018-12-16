public class Launcher {

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {
                        1, 2, 3
                },
                {
                        4, 5, 6
                },
                {
                        7, 8, 0
                }
        };
        Board b = new Board(arr);
        b.show();
        System.out.println((b.getBlank().x + " " + b.getBlank().y));
        b.move(b.where_is(8));
        System.out.println(b.solved());
        b.show();
        b.move(b.where_is(5));
        b.show();
    }

}

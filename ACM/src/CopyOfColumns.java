import java.awt.Color;
import s

public class CopyOfColumns extends GraphicsProgram {

    private static final int HEIGHT             = 500;
    private static final int WIDTH              = 700;
    private static final int STEPS              = 100;
    private static final int SIZE_OF_THE_SQUARE = 50;
    static final         int THRESHOLD          = 20;
    private static final int ADD_BEEPERS        = 3;


    public void run() {
        this.setSize(WIDTH, HEIGHT);
        int x      = 0;
        int y      = HEIGHT - SIZE_OF_THE_SQUARE;
        int length = 1;
        for (int i = 1; ; i++) {
            for (int j = 1; j < length; j++) {
                if (able_to_draw(x, y)) {
                    GRect square = new GRect(x, y, SIZE_OF_THE_SQUARE, SIZE_OF_THE_SQUARE);
                    square.setFilled(true);
                    if ((j % 3) == 0) {
                        square.setFillColor(Color.BLUE);
                    } else if ((j % 3) == 1) {
                        square.setFillColor(Color.YELLOW);
                    } else if ((j % 3) == 2) {
                        square.setFillColor(Color.RED);
                    }
                    add(square);
                    y = HEIGHT - (j * SIZE_OF_THE_SQUARE);
                }

                length += ADD_BEEPERS;
                x = i * (SIZE_OF_THE_SQUARE + STEPS);
                y = HEIGHT - SIZE_OF_THE_SQUARE;

            }
        }
    }

    private boolean able_to_draw(int pos_x, int pos_y) {
        return (pos_x < WIDTH - SIZE_OF_THE_SQUARE - THRESHOLD)
                && (pos_y > HEIGHT - SIZE_OF_THE_SQUARE - THRESHOLD)
    }
}
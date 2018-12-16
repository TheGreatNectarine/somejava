import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {

    public class Tile {
        int x, y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private      int[][]           tiles;
    private      int               DIMS;
    public final int[][]           FINAL_STATE;
    private      Tile              blank;
    private      LinkedList<Board> neighbours;

    public Board(int[][] blocks) {
        assert (equal_dimensions(blocks));
        this.DIMS = blocks[0].length;
        this.tiles = blocks.clone();
        FINAL_STATE = final_state(DIMS);
        blank = where_is(0);
        neighbours = new LinkedList<>();
        neighbours.add(this);
        fill_neightbours();
    }

    private void fill_neightbours() {

    }

    public int dimension() {
        return DIMS;
    }

    public int hamming() {
        int res = 0;
        for (int i = 0; i < DIMS; i++) {
            for (int j = 0; j < DIMS; j++) {
                if (this.tiles[i][j] - 1 != j + i * DIMS) {
                    ++res;
                }
            }
        }
        return res;
    }

    public int manhattan() {
        int count    = 0;
        int expected = 0;

        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                int value = tiles[row][col];
                expected++;
                if (value != 0 && value != expected) {
                    count += Math.abs(row
                            - row(FINAL_STATE, value))
                            + Math.abs(col
                            - col(FINAL_STATE, value));
                }
            }
        }
        return count;
    }

    public boolean solved() {
        return num_of_misplaced() == 0;
    }

    public boolean equals(Object y) {
        return (y instanceof Board) && (Arrays.deepEquals(((Board) y).tiles, this.tiles));
    }

    //TODO ???
    public Iterable<Board> neighbors() {
        return neighbours;
    }

    public void show() {
        System.out.println("-------------");
        for (int i = 0; i < DIMS; i++) {
            System.out.print("| ");
            for (int j = 0; j < DIMS; j++) {
                int           n = tiles[i][j];
                StringBuilder s;
                if (n > 0) {
                    s = new StringBuilder(Integer.toString(n));
                } else {
                    s = new StringBuilder();
                }
                while (s.length() < 2) {
                    s.append(" ");
                }
                System.out.print(s + "| ");
            }
            System.out.print("\n");
        }
        System.out.print("-------------\n\n");
    }

    private boolean equal_dimensions(int[][] blocks) {
        int dim = blocks[0].length;
        for (int i = 1; i < blocks.length; i++) {
            if (dim != blocks[i].length)
                return false;
        }
        return true;
    }

    private static int[][] final_state(int dim) {
        int[][] finalArray = new int[dim][dim];
        int     value      = 0;

        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                value++;
                if ((col + 1 == dim) && (row + 1 == dim)) {
                    finalArray[row][col] = 0;
                } else {
                    finalArray[row][col] = value;
                }

            }
        }

        return finalArray;
    }

    private int col(int[][] tiles, int value) {
        for (int[] tile : tiles) {
            for (int col = 0; col < tile.length; col++) {
                if (tile[col] == value) {
                    return col;
                }
            }
        }
        return -1;
    }

    private int row(int[][] tiles, int value) {
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                if (tiles[row][col] == value) {
                    return row;
                }
            }
        }
        return -1;
    }

    private int num_of_misplaced() {
        int wrong = 0;
        for (int i = 0; i < DIMS; i++) {
            for (int j = 0; j < DIMS; j++) {
                if ((tiles[i][j] > 0) && (tiles[i][j] != FINAL_STATE[i][j])) {
                    wrong++;
                }
            }
        }
        return wrong;
    }

    private List<Tile> tiles() {
        ArrayList<Tile> out = new ArrayList<>();
        for (int i = 0; i < DIMS; i++) {
            for (int j = 0; j < DIMS; j++) {
                out.add(new Tile(i, j));
            }
        }
        return out;
    }

    private int tile_by_pos(Tile p) {
        return tiles[p.x][p.y];
    }


    public Tile blank() {
        return blank;
    }


    Tile where_is(int x) {
        for (Tile p : tiles()) {
            if (tile_by_pos(p) == x) {
                return p;
            }
        }
        return null;
    }

    private boolean valid_move(Tile t) {
        if (t.x < 0 || t.y < 0 || t.x > DIMS || t.y > DIMS) {
            return false;
        }
        int dx = blank.x - t.x;
        int dy = blank.y - t.y;
        System.out.println((Math.abs(dx) + Math.abs(dy) == 1) && (dx * dy == 0));
        return (Math.abs(dx) + Math.abs(dy) == 1);
    }

    public void move(Tile t) {
        Board clone = new Board(this.tiles);
        if (valid_move(t) && tiles[blank.x][blank.y] == 0) {
            tiles[blank.x][blank.y] = tiles[t.x][t.y];
            tiles[t.x][t.y] = 0;
            blank = t;
        }
    }

    public Board after_moving(Tile t) {
        Board clone = new Board(this.tiles);
        clone.move(t);
        return clone;
    }

    public Tile getBlank() {
        return blank;
    }


}

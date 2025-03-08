package game2048logic;

import game2048rendering.Side;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c -   the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return 0.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        while (r - 1 >= minR) {
            if (board[r - 1][c] == 0) {
                board[r-1][c] = board[r][c];
                board[r][c] = 0;
                r--;
            } else {
                if (board[r - 1][c] == board[r][c]) {
                    board[r - 1][c] = 2 * board[r - 1][c];
                    board[r][c] = 0;
                    return r;
                }
                break;
            }
        }
        return 0;
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        int minR = 0;
        for (int i = 1; i < board.length; i++) {
            minR += moveTileUpAsFarAsPossible(board, i, c, minR);
        }
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            tiltColumn(board, i);
        }
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        if (side == Side.EAST) {
            boardTransformation(board, side);
            tiltUp(board);
            boardTransformation(board, Side.WEST);
        } else if (side == Side.WEST) {
            boardTransformation(board, side);
            tiltUp(board);
            boardTransformation(board, Side.EAST);;
        } else if (side == Side.SOUTH) {
            boardTransformation(board, side);
            tiltUp(board);
            boardTransformation(board, Side.SOUTH);;
        } else {
            tiltUp(board);
        }
    }

    public static void boardTransformation(int[][] board, Side side) {
        int size = board.length;
        int[][] transformedBoard = new int[size][size];
        
        // First copy to temp array
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int xVal = side.x(i, j, size);
                int yVal = side.y(i, j, size);
                transformedBoard[i][j] = board[xVal][yVal];
            }
        }
        
        // Copy back to original board
        for (int i = 0; i < size; i++) {
            System.arraycopy(transformedBoard[i], 0, board[i], 0, size);
        }
    }

    public static void main(String[] args) {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 2, 0, 4},
        };
        tilt(before, Side.WEST);
        System.out.printf(String.valueOf(before.length));

    }
}

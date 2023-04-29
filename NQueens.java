import java.util.*;
public class NQueens {
    private int[][] board;
    private int n;

    public NQueens(int n) {
        this.n = n;
        this.board = new int[n][n];
    }

    public boolean solve() {
        return solveRecursively(0);
    }

    private boolean solveRecursively(int col) {
        if (col == n) {
            return true;
        }

        for (int i = 0; i < n; i++) {
            if (isSafe(i, col)) {
                board[i][col] = 1;
                if (solveRecursively(col + 1)) {
                    return true;
                }
                board[i][col] = 0;
            }
        }

        return false;
    }

    private boolean isSafe(int row, int col) {
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public void printBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        NQueens queens = new NQueens(4);
        if (queens.solve()) {
            queens.printBoard();
        } else {
            System.out.println("No solution found!");
        }
    }
}

import java.util.Scanner;

class Main {

    static int i = 0;
    static int j = -1;

    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        int[][] matrix = new int[n][n];
        MoveDir moveDir = MoveDir.Right;
        for (int value = 1; value <= n * n; value++) {
            moveDir = MoveDir.nextDir(moveDir, matrix);
            matrix[i][j] = value;
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    enum MoveDir {
        Right, Down, Left, Up;

        static MoveDir nextDir(MoveDir moveDir, final int[][] matrix) {
            switch (moveDir) {
                case Right: j++;
                    return j + 1 >= matrix.length || matrix[i][j + 1] != 0
                        ? Down : Right;
                case Down: i++;
                    return i + 1 >= matrix.length || matrix[i + 1][j] != 0
                        ? Left : Down;
                case Left: j--;
                    return j - 1 < 0 || matrix[i][j - 1] != 0
                        ? Up : Left;
                default: i--;  // i.e. Up
                    return /*i - 1 < 0 || */ matrix[i - 1][j] != 0
                        ? Right : Up;
            }
        }
    }
}
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ii = scanner.nextInt();
        int jj = scanner.nextInt();
        int[][] matrix = new int[ii][jj];
        for (int i = 0; i < ii; i++) {
            for (int j = 0; j < jj; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        int temp;
        int row1 = scanner.nextInt();
        int row2 = scanner.nextInt();
        for (int i = 0; i < ii; i++) {
            temp = matrix[i][row1];
            matrix[i][row1] = matrix[i][row2];
            matrix[i][row2] = temp;
            for (int j = 0; j < jj; j++) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
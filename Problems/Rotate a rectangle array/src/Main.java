import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[m][n];
        for (int ni = n - 1; ni >= 0; ni--) {
            for (int mi = 0; mi < m; mi++) {
                matrix[mi][ni] = scanner.nextInt();
            }
        }
        for (int[] ar : matrix) {
            for (int value : ar) {
                System.out.printf("%d ", value);
            }
            System.out.println();
        }
    }
}
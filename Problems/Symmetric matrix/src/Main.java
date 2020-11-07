import java.util.Scanner;

class Main {

    static int size;
    static int[][] matrix;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        size = scanner.nextInt();
        matrix = new int[size][size];
        for (int[] ar : matrix) {
            for (int j = 0; j < size; j++) {
                ar[j] = scanner.nextInt();
            }
        }
        System.out.println(getResult());
    }

    private static String getResult() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return "NO";
                }
            }
        }
        return "YES";
    }
}
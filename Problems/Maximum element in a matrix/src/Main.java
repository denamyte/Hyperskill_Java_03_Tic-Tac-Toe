import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int row = 0;
        int col = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int value = scanner.nextInt();
                if (value > max) {
                    max = value;
                    row = i;
                    col = j;
                }
            }
        }
        System.out.printf("%d %d", row, col);
    }
}
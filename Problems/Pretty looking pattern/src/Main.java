import java.util.Scanner;

public class Main {
    static final int SIZE = 4;
    static String[] pattern;

    public static void main(String[] args) {
        pattern = new Scanner(System.in).tokens().limit(SIZE).toArray(String[]::new);
        System.out.println(isPretty() ? "YES" : "NO");
    }

    private static boolean isPretty() {
        for (int i = 0; i < SIZE - 1; i++) {
            for (int j = 0; j < SIZE - 1; j++) {
                if (pattern[i].charAt(j) == pattern[i].charAt(j + 1) &&
                        pattern[i + 1].charAt(j) == pattern[i + 1].charAt(j + 1) &&
                        pattern[i].charAt(j) == pattern[i + 1].charAt(j + 1)) {
                    return false;
                }
            }
        }
        return true;
    }
}
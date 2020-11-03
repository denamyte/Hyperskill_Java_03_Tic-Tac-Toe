import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    int smallSize;
    int size;
    int[][] matrix;

    public static void main(String[] args) {
        System.out.println(new Main().checkSolved() ? "YES" : "NO");
    }

    Main() {
        inputSudoku();
    }

    final void inputSudoku() {
        Scanner scanner = new Scanner(System.in);
        smallSize = scanner.nextInt();
        size = smallSize * smallSize;
        matrix = new int[size][size];
        for (int[] row : matrix) {
            for (int x = 0; x < size; x++) {
                row[x] = scanner.nextInt();
            }
        }
    }

    boolean checkSolved() {
        return checkVectors() && checkColumns() && checkSmallSquares();
    }

    boolean checkVectors() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            set.clear();
            for (int j = 0; j < size; j++) {
                set.add(matrix[i][j]);
            }
            if (setFailed(set)) {
                return false;
            }
        }
        return true;
    }

    boolean checkColumns() {
        Set<Integer> set = new HashSet<>();
        for (int j = 0; j < size; j++) {
            set.clear();
            for (int i = 0; i < size; i++) {
                set.add(matrix[i][j]);
            }
            if (setFailed(set)) {
                return false;
            }
        }
        return true;
    }

    boolean checkSmallSquares() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < size; i += smallSize) {
            for (int j = 0; j < size; j += smallSize) {
                set.clear();
                for (int iSmall = i; iSmall < i + smallSize; iSmall++) {
                    for (int jSmall = j; jSmall < j + smallSize; jSmall++) {
                        set.add(matrix[iSmall][jSmall]);
                    }
                }
                if (setFailed(set)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean setFailed(Set<Integer> set) {
        return set.size() != size
                || set.stream().min(Integer::compare).orElseThrow() != 1
                || set.stream().max(Integer::compare).orElseThrow() != size;
    }
}
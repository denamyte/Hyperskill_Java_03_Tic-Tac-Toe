package previous;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main3 {
    public static final int X_SUM = 264;
    public static final int O_SUM = 237;
    // Obtained
    private final char[] data;
    // Derived
    private final Map<Integer, Integer> sumsMap = new HashMap<>() {{
        put(X_SUM, 0);
        put(O_SUM, 0);
    }};
    private final Map<Character, Integer> countsMap = new HashMap<>() {{
        put('X', 0);
        put('O', 0);
    }};

    Main3(char[] data) {
        this.data = data;
    }

    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------\n");
        for (int row = 0; row < 3; row++) {
            bodyLine(sb, row * 3);
        }
        sb.append("---------\n");
        sb.append(chooseGameState()).append('\n');

        return sb.toString();
    }

    private void bodyLine(StringBuilder sb, int shift) {
        sb.append("| ");
        for (int x = 0; x < 3; x++) {
            sb.append(data[shift + x]).append(' ');
        }
        sb.append("|\n");
    }

    private String chooseGameState() {
        countXO();
        countSums();
        if (Math.abs(countsMap.get('X') - countsMap.get('O')) >= 2
                || sumsMap.get(X_SUM) + sumsMap.get(O_SUM) > 1) {
            return "Impossible";
        }
        if (sumsMap.get(X_SUM) == 1) {
            return "X wins";
        }
        if (sumsMap.get(O_SUM) == 1) {
            return "O wins";
        }
        if (countsMap.get('X') + countsMap.get('O') == 9) {
            return "Draw";
        }
        return "Game not finished";
    }

    private void countXO() {
        for (char field : data) {
            countsMap.compute(field, (k, v) -> v == null ? 1 : v + 1);
        }
    }

    /** Counts sums of char indices in diagonals, rows and columns */
    private void countSums() {
        incSumsMap(data[0] + data[4] + data[8]);  // main diagonal
        incSumsMap(data[2] + data[4] + data[6]);  // secondary diagonal
        for (int i = 0; i < 3; i++) {
            incSumsMap(data[i * 3] + data[i * 3 + 1] + data[i * 3 + 2]);  // rows
            incSumsMap(data[i] + data[3 + i] + data[6 + i]);  // columns
        }
    }

    private void incSumsMap(int key) {
        sumsMap.compute(key, (k, value) -> value == null ? 1 : value + 1);
    }

    public static void main(String[] args) {
        System.out.print("Enter cells: ");
        Main3 tic = new Main3(new Scanner(System.in).nextLine().toCharArray());
        System.out.println(tic.render());
    }
}

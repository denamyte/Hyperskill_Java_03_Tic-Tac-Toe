package tictactoe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static final int X_SUM = 264;  // 'X' + 'X' + 'X'
    public static final int O_SUM = 237;  // 'O' + 'O' + 'O'
    public static final String ENTER = "Enter the coordinates: ";
    public static final String ERROR_OCCUPIED = "This cell is occupied! Choose another one!\n";
    public static final String ERROR_NOT_NUMBERS = "You should enter numbers!\n";
    public static final String ERROR_NOT_1_3 = "Coordinates should be from 1 to 3!\n";
    // Derived
    private final Map<Integer, Integer> sumsMap = new HashMap<>() {{
        put(X_SUM, 0);
        put(O_SUM, 0);
    }};
//    private final Map<Character, Integer> countsMap = new HashMap<>() {{
//        put('X', 0);
//        put('O', 0);
//    }};

    // Obtained
    private char[] data;
    private int movesCount;
    private boolean xsTurn = true;
    private String additionalString;  // one of three errors | draw message | win message
    private int[] crd;
    @Deprecated
    private GameState state = GameState.START;
    private GameState2 state2 = GameState2.ENTER_CELLS;

    public Main() {
        data = new char[9];
        Arrays.fill(data, ' ');
    }

    public boolean notFinished() {
        System.out.print(render());
        return state2 != GameState2.FINISHED;
    }

    public void input(String input) {
        parseAndCheckCrd(input);

        // TODO: 11/13/20 Define state here

    }

    private void parseAndCheckCrd(String input) {
        state2 = GameState2.ERROR;
        if (!input.matches("\\d +\\d")) {
            additionalString = ERROR_NOT_NUMBERS;
        } else if (!input.matches("[1-3] +[1-3]")) {
            additionalString = ERROR_NOT_1_3;
        } else {
            crd = Arrays.stream(input.split(" +")).mapToInt(Integer::parseInt).toArray();
            if (getFieldByCrd(crd) != ' ') {
                additionalString = ERROR_OCCUPIED;
            } else {
                state2 = GameState2.ENTER_CELLS;
            }
        }
    }

    private char getFieldByCrd(int[] crd) {
        return data[flattenCrd(crd)];
    }

    private void setFieldByCrd(char field, int[] crd) {
        data[flattenCrd(crd)] = field;
    }

    private int flattenCrd(int[] crd) {
        return 8 - 3 * crd[1] + crd[0];
    }

    public String render() {
        StringBuilder sb = new StringBuilder();
        if (state2 != GameState2.ERROR) {
            renderTable(sb);
        }
        if (state2 != GameState2.ENTER_CELLS){
            sb.append(additionalString);
        }
        if (state2 != GameState2.FINISHED) {
            sb.append(ENTER);
        }
        return sb.toString();
    }

    private void renderTable(StringBuilder sb) {
        sb.append("---------\n");
        for (int row = 0; row < 3; row++) {
            bodyLine(sb, row * 3);
        }
        sb.append("---------");
    }

    private void bodyLine(StringBuilder sb, int shift) {
        sb.append("| ");
        for (int x = 0; x < 3; x++) {
            sb.append(data[shift + x]).append(' ');
        }
        sb.append("|\n");
    }

    private String chooseGameState() {
//        countXO();
        countSums();
//        if (Math.abs(countsMap.get('X') - countsMap.get('O')) >= 2
//                || sumsMap.get(X_SUM) + sumsMap.get(O_SUM) > 1) {
//            return "Impossible";
//        }
        if (sumsMap.get(X_SUM) == 1) {
            return "X wins";
        }
        if (sumsMap.get(O_SUM) == 1) {
            return "O wins";
        }
        if (movesCount == 9) {
            return "Draw";
        }
        return "Game not finished";
    }

//    private void countXO() {
//        for (char field : data) {
//            countsMap.compute(field, (k, v) -> v == null ? 1 : v + 1);
//        }
//    }

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

    enum GameState {
        START,
        ENTER_CELLS,
        ENTER_CRD,

        DONE
    }

    enum GameState2 {
        ENTER_CELLS,
        ERROR,
        FINISHED
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main ticTac = new Main();
        while (ticTac.notFinished()) {
            ticTac.input(scanner.nextLine());
        }
    }
}

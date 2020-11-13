package tictactoe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static final int X_SUM = 264;
    public static final int O_SUM = 237;
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
    private int errorString;
    @Deprecated
    private GameState state = GameState.START;
    private GameState2 state2 = GameState2.ENTER_CELLS;

    public Main() {
        data = new char[9];
        Arrays.fill(data, '_');
    }

    public void manage(String input) {
        render(true);

        switch (state) {
            case START:
                turnOnState(GameState.ENTER_CELLS, "Enter cells: ");
                break;
            case ENTER_CELLS:
                enterCells(input);
                turnOnEnterCrd();
                break;
            case ENTER_CRD:
                manageEnteringCoordinates(input);
                break;
        }
    }

    @Deprecated
    private void turnOnEnterCrd() {
        turnOnState(GameState.ENTER_CRD, "Enter the coordinates: ");
    }

    private void turnOnState(GameState state, String msg) {
        this.state = state;
        if (msg != null) {
            System.out.print(msg);
        }
    }

    private void enterCells(String input) {
        data = input.toCharArray();
        System.out.println(render(false));
    }

    private void manageEnteringCoordinates(String input) {
        int[] crd = parseAndCheckCrd(input);
        if (crd != null) {
            setFieldByCrd('X', crd);
            turnOnState(GameState.DONE, render(false) + '\n');
        } else {
            turnOnEnterCrd();
        }
    }

    private int[] parseAndCheckCrd(String input) {
        if (!input.matches("\\d +\\d")) {
            System.out.println("You should enter numbers!");
            return null;
        }
        // Here the input is 2 numbers in range [0, 9]

        if (!input.matches("[1-3] +[1-3]")) {
            System.out.println("Coordinates should be from 1 to 3!");
            return null;
        }
        // Here, the input is 2 numbers in range [1, 3]

        int[] crd = Arrays.stream(input.split(" +")).mapToInt(Integer::parseInt).toArray();
        if (isFieldByCrdOccupied(crd)) {
            return null;
        }
        return crd;
    }

    private boolean isFieldByCrdOccupied(int[] crd) {
        char field = getFieldByCrd(crd);
        if (field == 'X' || field == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            return true;
        }
        return false;
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

    public String render(boolean includeGameState) {
        // TODO: 11/13/20 render should include errors
        StringBuilder sb = new StringBuilder();
        sb.append("---------\n");
        for (int row = 0; row < 3; row++) {
            bodyLine(sb, row * 3);
        }
        sb.append("---------");

        if (includeGameState) {
            sb.append(chooseGameState()).append('\n');
        }
        return sb.toString();
    }

    private void bodyLine(StringBuilder sb, int shift) {
        sb.append("| ");
        for (int x = 0; x < 3; x++) {
            char field = data[shift + x];
            sb.append(field == '_' ? ' ' : field).append(' ');
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
        ERROR
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main ticTac = new Main();
        while (ticTac.state != GameState.DONE) {
            ticTac.manage(scanner.nextLine());
        }
    }
}

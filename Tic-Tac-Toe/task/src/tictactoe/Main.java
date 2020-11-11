package tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static final int X_SUM = 264;
    public static final int O_SUM = 237;
    // Obtained
    private char[] data;
    // Derived
    private final Map<Integer, Integer> sumsMap = new HashMap<>() {{
        put(X_SUM, 0);
        put(O_SUM, 0);
    }};
    private final Map<Character, Integer> countsMap = new HashMap<>() {{
        put('X', 0);
        put('O', 0);
    }};

    private GameState state = GameState.START;

    public Main() {
        manage(null);
    }

    public void manage(String input) {
        switch (state) {
            case START:
                turnOnState(GameState.ENTER_CELLS, "Enter cells: ");
                break;
            case ENTER_CELLS:
                enterCells(input);
                turnOnState(GameState.ENTER_CRD, "Enter the coordinates: ");
                break;
            case ENTER_CRD:
                manageEnteringCoordinates(input);
                break;
            case DONE:
                break;
        }
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
        if (coordinatesEntered(input)) {
            render(false);
//            turnOnState(
//                    // TODO: 11/11/20 Go on herein
//            );
        }
    }
    
    private boolean coordinatesEntered(String input) {
        // TODO: 11/11/20
        //  Check the pattern: /\d +\d/
        //  Check the numbers range: [1..3]
        //  Check if the specified cell is occupied

        return false;
    }

    public String render(boolean includeGameState) {
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

    enum GameState {
        START,
        ENTER_CELLS,
        ENTER_CRD,

        DONE
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main ticTac = new Main();
        while (ticTac.state != GameState.DONE) {
            ticTac.manage(scanner.nextLine());
        }
    }
}

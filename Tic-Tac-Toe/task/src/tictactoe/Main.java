package tictactoe;

import java.util.*;

import static tictactoe.Main.GameState.*;

public class Main {
    public static final int X_SUM = 264;  // 'X' + 'X' + 'X'
    public static final int O_SUM = 237;  // 'O' + 'O' + 'O'
    public static final Map<GameState, String> infoMap = Map.of(
            ENTER_CELLS, "Enter the coordinates: ",
            ERROR_NOT_NUMBERS, "You should enter numbers!\n",
            ERROR_OUT_OF_RANGE, "Coordinates should be from 1 to 3!\n",
            ERROR_ALREADY_OCCUPIED, "This cell is occupied! Choose another one!\n",
            X_WINS, "X wins\n",
            O_WINS, "O wins\n",
            DRAW, "Draw\n"
    );

    private final Map<Integer, Integer> sumsMap = new HashMap<>() {{
        put(X_SUM, 0);
        put(O_SUM, 0);
    }};

    private final char[] data;
    private int movesCount;
    private boolean xsTurn = true;
    private int[] crd;
    private GameState state = ENTER_CELLS;

    public Main() {
        data = new char[9];
        Arrays.fill(data, ' ');
    }

    public boolean notFinished() {
        return !FINISHED_SET.contains(state);
    }

    public void input(String input) {
        state = ENTER_CELLS;
        parseAndCheckCrd(input);
        if (!ERROR_SET.contains(state)) {
            setFieldByCrd(xsTurn ? 'X' : 'O', crd);
            xsTurn = !xsTurn;
            movesCount++;
            checkIfGameFinished();
        }
    }

    private void parseAndCheckCrd(String input) {
        if (!input.matches("\\d +\\d")) {
            state = ERROR_NOT_NUMBERS;
        } else if (!input.matches("[1-3] +[1-3]")) {
            state = ERROR_OUT_OF_RANGE;
        } else {
            crd = Arrays.stream(input.split(" +")).mapToInt(Integer::parseInt).toArray();
            if (getFieldByCrd(crd) != ' ') {
                state = ERROR_ALREADY_OCCUPIED;
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
        if (!ERROR_SET.contains(state)) {
            renderTable(sb);
        }
        if (state != ENTER_CELLS){
            sb.append(infoMap.get(state));
        }
        if (!FINISHED_SET.contains(state)) {
            sb.append(infoMap.get(ENTER_CELLS));
        }
        return sb.toString();
    }

    private void renderTable(StringBuilder sb) {
        sb.append("---------\n");
        for (int row = 0; row < 3; row++) {
            bodyLine(sb, row * 3);
        }
        sb.append("---------\n");
    }

    private void bodyLine(StringBuilder sb, int shift) {
        sb.append("| ");
        for (int x = 0; x < 3; x++) {
            sb.append(data[shift + x]).append(' ');
        }
        sb.append("|\n");
    }

    private void checkIfGameFinished() {
        countSums();
        if (sumsMap.get(X_SUM) == 1) {
            state = X_WINS;
        } else if (sumsMap.get(O_SUM) == 1) {
            state = O_WINS;
        } else if (movesCount == 9) {
            state = DRAW;
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
        ENTER_CELLS, ERROR_NOT_NUMBERS, ERROR_OUT_OF_RANGE, ERROR_ALREADY_OCCUPIED, X_WINS, O_WINS, DRAW;
        static EnumSet<GameState> FINISHED_SET = EnumSet.of(X_WINS, O_WINS, DRAW);
        static EnumSet<GameState> ERROR_SET = EnumSet.of(ERROR_NOT_NUMBERS, ERROR_OUT_OF_RANGE, ERROR_ALREADY_OCCUPIED);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main ticTac = new Main();
        while (true) {
            System.out.print(ticTac.render());
            if (ticTac.notFinished()) {
                ticTac.input(scanner.nextLine());
            } else {
                break;
            }
        }
    }
}

package tictactoe;

import java.util.Map;
import java.util.Scanner;

public class Main {
//    static final int X_IND = 0, O_IND = 1;
//    static final char[] players = new char[]{'X', 'O'};
//    static final char FILLER = '_';
    static final Map<Character, Integer> IND = Map.of('O', 0, 'X', 1);
    static final Map<Character, Integer> CHAR_TO_INT = Map.of('_', -1, 'O', 0, 'X', 1);
    // Obtained
    private final char[] data;
    // Derived
    private int[] counts = new int[2];  // numbers of the 'O's and 'X's (indices 0 and 1 respectively)
    private int[] wins = new int[2];  // numbers of wins of the 'O's and 'X's (indices 0 and 1 respectively)

    Main(char[] data) {
        this.data = data;
        String state = chooseGameState();
    }

    public void render() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------\n");
        for (int row = 0; row < 3; row++) {
            bodyLine(sb, row * 3);
        }
        sb.append("---------\n");
        // TODO: 11/8/20 Add the status text

        // Debug
        sb.append(String.format("Xs: %d; Os: %d %n", counts[CHAR_TO_INT.get('X')], counts[CHAR_TO_INT.get('O')]));

        System.out.println(sb.toString());
    }

    private void bodyLine(StringBuilder sb, int shift) {
        sb.append("| ");
        for (int x = 0; x < 3; x++) {
            sb.append(data[shift + x]).append(' ');
        }
        sb.append("|\n");
    }

    private String chooseGameState() {
        // todo
        //  The order of checks:
        //   - "Impossible":
        //     - A lot more Xs than 0s or vice versa
        //     - Wins counts are > 0 for both Xs and Os
        //   - "X wins" or "O wins"
        //   - "Draw" state
        //   - "Game not finished" otherwise
        countXO();
        if (Math.abs(counts[0] - counts[1]) >= 2) {
            return "Impossible";
        }
        countWins();
        if (wins[0] + wins[1] > 1) {
            return "Impossible";
        }

        return "";
    }

    private void countXO() {
        for (char field : data) {
            if (field != '_') {
                counts[CHAR_TO_INT.get(field)]++;
            }
        }
    }

    private void countWins() {
        countDiagonalWin();

        // TODO: 11/8/20 Finish counting wins
    }

    private void countDiagonalWin() {
        if (data[4] == '_') {
            return;
        }
        if (data[0] == data[4] && data[4] == data[8]) {
            wins[CHAR_TO_INT.get(data[4])]++;
        }
        if (data[2] == data[4] && data[4] == data[6]) {
            wins[CHAR_TO_INT.get(data[4])]++;
        }
    }

    private int countWinsOf(char player) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.print("Enter cells: ");
        Main tic = new Main(new Scanner(System.in).nextLine().toCharArray());
        tic.render();
    }
}

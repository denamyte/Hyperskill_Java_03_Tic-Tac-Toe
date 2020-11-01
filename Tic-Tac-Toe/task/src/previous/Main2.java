package previous;

import java.util.Scanner;

public class Main2 {
    private final char[] data;
    StringBuilder sb;  // contract: initialize in render(), fill, print, forget

    Main2(char[] data) {
        this.data = data;
    }

    public void render() {
        sb = new StringBuilder();
        horzBorder();
        for (int i = 0; i < 3; i++) {
            bodyLine(i);
        }
        horzBorder();
        System.out.println(sb.toString());
    }

    private void horzBorder() {
        sb.append("---------\n");
    }

    private void bodyLine(int y) {
        sb.append("| ");
        int shift = y * 3;
        for (int x = 0; x < 3; x++) {
            sb.append(data[shift + x]).append(' ');
        }
        sb.append("|\n");
    }

    public static void main(String[] args) {
        System.out.print("Enter cells: ");
        Main2 tic = new Main2(new Scanner(System.in).nextLine().toCharArray());
        tic.render();
    }
}

import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int size = new Scanner(System.in).nextInt();
        char[][] matrix = new char[size][size];
        // fill
        for (char[] ar: matrix) {
            Arrays.fill(ar, '.');
        }
        int middleIndex = size / 2;
        // middle row
        Arrays.fill(matrix[middleIndex], '*');
        // middle column
        for (int i = 0; i < size; i++) {
            matrix[i][middleIndex] = '*';
        }
        // main and secondary diagonals
        for (int i = 0; i < size; i++) {
            matrix[i][i] = matrix[i][size - 1 - i] =  '*';
        }
        // show up
        for (char[] ar : matrix) {
            for (char value : ar) {
                System.out.printf("%c ", value);
            }
            System.out.println();
        }
    }
}
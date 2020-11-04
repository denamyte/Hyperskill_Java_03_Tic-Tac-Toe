import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println(
                    Stream.iterate(-i, prev -> prev + 1)
                            .limit(n)
                            .map(Math::abs)
                            .map(String::valueOf)
                            .collect(Collectors.joining(" ")));
        }
        // or
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.printf("%d ", Math.abs(i - j));
//            }
//            System.out.println();
//        }
    }
}
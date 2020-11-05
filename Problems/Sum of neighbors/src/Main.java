import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        // INPUT
        Scanner scanner = new Scanner(System.in);
        List<int[]> data = Stream.generate(scanner::nextLine)
                .takeWhile(Predicate.not("end"::equals))
                .map(s -> Arrays.stream(s.split(" "))
                        .mapToInt(Integer::valueOf)
                        .toArray())
                .collect(Collectors.toList());
        // SOLUTION
        int n = data.size();  // row count
        int m = data.get(0).length;  // column count
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%d ", data.get(i)[(j + m - 1) % m]  // to the left
                        + data.get(i)[(j + 1) % m]  // to the right
                        + data.get((i + n - 1) % n)[j]  // up
                        + data.get((i + 1) % n)[j]);  // down
            }
            System.out.println();
        }
    }
}
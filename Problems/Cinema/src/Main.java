import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        // INPUT
        Scanner scanner = new Scanner(System.in);
        int rowCount = scanner.nextInt();
        scanner.nextLine(); // Skipping the remaining part of the first line, ignoring seats per row count
        // Generating a string representing the cinema
        String cinema = Stream.generate(scanner::nextLine).limit(rowCount).collect(Collectors.joining(""));
        int adjacentCount = scanner.nextInt();

        // SOLUTION
        // Generating a string representing n adjacent seats
        String seats = Stream.generate(() -> "0").limit(adjacentCount).collect(Collectors.joining(" "));
        // Searching the row
        int index = cinema.indexOf(seats);
        System.out.println(index == -1 ? 0 : index * rowCount / cinema.length() + 1);
    }
}
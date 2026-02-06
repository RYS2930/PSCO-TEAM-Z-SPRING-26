import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Dice Game Menu ===");
            System.out.println("1. Dice Patterns Challenge");
            System.out.println("2. Dice Grid Puzzle");
            System.out.println("3. Dice Codebreaker");
            System.out.println("4. Dice Battle");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> new DicePatternGame().play();
                case 2 -> new DiceGridGame().play();
                case 3 -> new DiceCodebreakerGame().play();
                case 4 -> new DiceBattle().play();
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}

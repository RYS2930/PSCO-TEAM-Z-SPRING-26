import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = sc.nextLine();
        UserProfile user = UserManager.loadOrCreateUser(username);

        while (true) {
            System.out.println("\n=== Dice Game Menu ===");
            System.out.println("1. Dice Patterns Challenge");
            System.out.println("2. Dice Grid Puzzle");
            System.out.println("3. Dice Codebreaker");
            System.out.println("4. Dice Battle");
            System.out.println("5. View Player Stats");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = readIntInRange(sc, 0, 5);

            switch (choice) {
                case 1 -> {
                    DicePatternGame g = new DicePatternGame(); 
                    g.play();
                    user.updatePatternScore(g.calculateScore());
                    user.incrementGamesPlayed();
                }
                case 2 -> {
                    DiceGridGame g = new DiceGridGame(); 
                    g.play();
                    user.updateGridScore(g.calculateScore());
                    user.incrementGamesPlayed();
                }
                case 3 -> {
                    DiceCodebreakerGame g = new DiceCodebreakerGame(); 
                    g.play();
                    user.updateCodebreakerScore(10);
                    user.incrementGamesPlayed();
                }
                case 4 -> {
                    DiceBattle g = new DiceBattle();
                    g.play();
                    user.updateBattleScore(20);
                    user.incrementGamesPlayed();
                }
                case 5 -> System.out.println(user);
                case 0 -> {
                    UserManager.saveUser(user);
                    System.out.println("Bye!");
                    return;
                }
            }

            UserManager.saveUser(user);
        }
    }

    
    private static int readIntInRange(Scanner sc, int min, int max) {
        while (true) {
            if (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter a number (" + min + "-" + max + "): ");
                sc.next(); 
                continue;
            }
            int x = sc.nextInt();
            if (x < min || x > max) {
                System.out.print("Out of range! Enter (" + min + "-" + max + "): ");
                continue;
            }
            return x;
        }
    }
}

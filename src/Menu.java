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
                    DiceCodebreakerGame g = new DiceCodebreakerGame(sc);
                    DiceCodebreakerGame.CodebreakerResult r = g.play();
                    user.updateCodebreaker(r.won() ? r.guessesUsed() : -1);
                    user.incrementGamesPlayed();
                }
                case 4 -> {
                    DiceBattle g = new DiceBattle();
                    DiceBattle.BattleResult result = g.play();
                    user.updateBattleResult(result.playerWon(), result.playerScore(), result.computerScore());
                    user.incrementGamesPlayed();
                }
                case 5 -> viewStatsMenu(sc, user);
                case 0 -> {
                    UserManager.saveUser(user);
                    System.out.println("Bye!");
                    System.out.println("================");
                    System.out.print("Enter username (Quit the game, type -1): ");
                    sc.nextLine();
                    username = sc.nextLine();
                    if (username.equals("-1")) {
                        System.out.println("Good Bye!");
                        return;
                    }
                    user = UserManager.loadOrCreateUser(username);
                }
            }

            UserManager.saveUser(user);
        }
    }

    private static void viewStatsMenu(Scanner sc, UserProfile user) {
        while (true) {
            System.out.println("\n=== View Player Stats ===");
            System.out.println("1. Dice Pattern");
            System.out.println("2. Dice Grid");
            System.out.println("3. Dice Codebreaker");
            System.out.println("4. Dice Battle");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            int choice = readIntInRange(sc, 0, 4);
            switch (choice) {
                case 1 -> {
                    System.out.println(user.getPatternStats());
                    pauseAfterViewingStats(sc);
                }
                case 2 -> {
                    System.out.println(user.getGridStats());
                    pauseAfterViewingStats(sc);
                }
                case 3 -> {
                    System.out.println(user.getCodebreakerStats());
                    pauseAfterViewingStats(sc);
                }
                case 4 -> {
                    System.out.println(user.getBattleStats());
                    pauseAfterViewingStats(sc);
                }
                case 0 -> {
                    return;
                }
            }
        }
    }

    /** Lets the player read the stats; press Enter to return to the stats submenu. */
    private static void pauseAfterViewingStats(Scanner sc) {
        sc.nextLine(); // newline left after readIntInRange
        System.out.print("\nPress Enter to return to stats menu... ");
        sc.nextLine();
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

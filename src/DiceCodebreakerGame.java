import java.util.*;

/**
 * Profile stats record <b>which guess</b> solved the code (1–10), or a failed run if all 10 tries miss.
 */
public class DiceCodebreakerGame {
    private static final int MAX_ATTEMPTS = 10;

    private int[] secret = new int[4];
    private final Random rand = new Random();
    private final Scanner sc;

    public DiceCodebreakerGame() {
        this(new Scanner(System.in));
    }

    public DiceCodebreakerGame(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Play one game and return result for profile / stats.
     */
    public CodebreakerResult play() {
        generateCode();

        int guessesUsed = 0;
        while (guessesUsed < MAX_ATTEMPTS) {
            guessesUsed++;
            int[] guess = readFourDigitsOrSeparated();

            int correctPos = 0;
            int correctNum = 0;
            boolean[] usedSecret = new boolean[4];
            boolean[] usedGuess = new boolean[4];

            for (int i = 0; i < 4; i++) {
                if (guess[i] == secret[i]) {
                    correctPos++;
                    usedSecret[i] = usedGuess[i] = true;
                }
            }

            for (int i = 0; i < 4; i++) {
                if (usedGuess[i]) continue;
                for (int j = 0; j < 4; j++) {
                    if (!usedSecret[j] && guess[i] == secret[j]) {
                        correctNum++;
                        usedSecret[j] = true;
                        break;
                    }
                }
            }

            System.out.println("Correct position: " + correctPos);
            System.out.println("Correct number (wrong position): " + correctNum);

            if (correctPos == 4) {
                System.out.println("You win! Solved in " + guessesUsed + " guesses.");
                return new CodebreakerResult(true, guessesUsed);
            }
        }

        System.out.println("Could not solve in 10 tries. Secret was: " + Arrays.toString(secret));
        return new CodebreakerResult(false, 0);
    }

    /**
     * Accepts either four digits together ({@code 2334}) or separated ({@code 2 3 3 4}).
     * Old code used {@code nextInt()} four times, so {@code 2334} was read as one integer 2334 → "out of range".
     */
    private int[] readFourDigitsOrSeparated() {
        while (true) {
            System.out.print("Enter 4 numbers (1-6), e.g. 2334 or 2 3 3 4: ");
            String line;
            do {
                line = sc.nextLine().trim();
            } while (line.isEmpty());

            int[] guess = tryParseFour(line);
            if (guess != null) {
                return guess;
            }
            System.out.println("Invalid input. Need exactly four digits, each 1-6 (e.g. 2334 or 2 3 3 4).");
        }
    }

    /** Returns null if the line is not a valid guess. */
    private static int[] tryParseFour(String line) {
        int[] guess = new int[4];

        if (line.matches("[1-6]{4}")) {
            for (int i = 0; i < 4; i++) {
                guess[i] = line.charAt(i) - '0';
            }
            return guess;
        }

        String[] parts = line.trim().split("\\s+");
        if (parts.length != 4) {
            return null;
        }
        try {
            for (int i = 0; i < 4; i++) {
                guess[i] = Integer.parseInt(parts[i]);
                if (guess[i] < 1 || guess[i] > 6) {
                    return null;
                }
            }
            return guess;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void generateCode() {
        for (int i = 0; i < 4; i++) {
            secret[i] = rand.nextInt(6) + 1;
        }
    }

    /** When {@code won} is false, {@code guessesUsed} is unused (0). */
    public record CodebreakerResult(boolean won, int guessesUsed) {}
}

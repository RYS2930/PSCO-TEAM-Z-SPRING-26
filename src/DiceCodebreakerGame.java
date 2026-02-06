import java.util.*;

public class DiceCodebreakerGame {
    private int[] secret = new int[4];
    private Random rand = new Random();
    private Scanner sc = new Scanner(System.in);

    public void play() {
        generateCode();
        int attempts = 10;

        while (attempts-- > 0) {
            int[] guess = new int[4];
            System.out.print("Enter 4 numbers (1-6): ");
            for (int i = 0; i < 4; i++) guess[i] = sc.nextInt();

            int correctPos = 0, correctNum = 0;
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
                System.out.println("You win!");
                return;
            }
        }
        System.out.println("Game over. Secret was: " + Arrays.toString(secret));
    }

    private void generateCode() {
        for (int i = 0; i < 4; i++)
            secret[i] = rand.nextInt(6) + 1;
    }
}

import java.util.*;

public class DicePatternGame {
    private final int[] dice = new int[5];
    private final Random rand = new Random();
    private final Scanner sc;

    
    public DicePatternGame() {
        this(new Scanner(System.in));
    }

    
    public DicePatternGame(Scanner sc) {
        this.sc = sc;
    }

    public void play() {
        rollAll();
        System.out.println("Initial roll: " + Arrays.toString(dice));

        for (int round = 0; round < 2; round++) {
            System.out.print("Re-roll? (y/n): ");
            String ans = sc.next();
            if (!ans.equalsIgnoreCase("y")) break;

            System.out.print("Enter indices to reroll (0-4), separated by space, end with -1: ");

            boolean[] rerolled = new boolean[5]; 
            while (true) {
                Integer idx = readIntSafe();
                if (idx == null) {
                    System.out.print("Invalid input. Enter 0-4 or -1 to stop: ");
                    continue;
                }

                if (idx == -1) break;

                if (idx < 0 || idx > 4) {
                    System.out.print("Out of range! Enter 0-4 or -1 to stop: ");
                    continue;
                }

                if (rerolled[idx]) {
                    System.out.print("Index " + idx + " already rerolled this round. Choose another or -1: ");
                    continue;
                }

                dice[idx] = rollDie();
                rerolled[idx] = true;
            }

            System.out.println("Current dice: " + Arrays.toString(dice));
        }

        int score = calculateScore();
        System.out.println("Final score: " + score);
    }

    private Integer readIntSafe() {
        if (!sc.hasNextInt()) {
            sc.next(); 
            return null;
        }
        return sc.nextInt();
    }

    private void rollAll() {
        for (int i = 0; i < 5; i++) dice[i] = rollDie();
    }

    private int rollDie() {
        return rand.nextInt(6) + 1;
    }

    public int calculateScore() {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int d : dice) freq.put(d, freq.getOrDefault(d, 0) + 1);

        boolean straight =
                freq.keySet().containsAll(Arrays.asList(1, 2, 3, 4, 5)) ||
                freq.keySet().containsAll(Arrays.asList(2, 3, 4, 5, 6));

        if (freq.containsValue(5)) return 50;
        if (freq.containsValue(4)) return 40;
        if (freq.containsValue(3) && freq.containsValue(2)) return 35;
        if (straight) return 30;
        if (freq.containsValue(3)) return 25;

        int pairs = 0;
        for (int v : freq.values()) if (v == 2) pairs++;
        if (pairs == 2) return 20;
        if (pairs == 1) return 10;

        return 0;
    }
}

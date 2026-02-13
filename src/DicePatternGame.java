import java.util.*;

public class DicePatternGame {
    private int[] dice = new int[5];
    private Random rand = new Random();
    private Scanner sc = new Scanner(System.in);

    public void play() {
        rollAll();
        System.out.println("Initial roll: " + Arrays.toString(dice));

        for (int i = 0; i < 2; i++) {
            System.out.print("Re-roll? (y/n): ");
            if (!sc.next().equalsIgnoreCase("y")) break;

            System.out.print("Enter indices to reroll (0-4), separated by space, end with -1: ");
            while (true) {
                if (!sc.hasNextInt()) {
                    sc.next();
                    continue;
                }
                int idx = sc.nextInt();
                if (idx == -1) break;
                if (idx >= 0 && idx < 5) dice[idx] = rollDie();
            }
            System.out.println("Current dice: " + Arrays.toString(dice));
        }

        int score = calculateScore();
        System.out.println("Final score: " + score);
    }

    private void rollAll() {
        for (int i = 0; i < 5; i++) dice[i] = rollDie();
    }

    private int rollDie() {
        return rand.nextInt(6) + 1;
    }

    private int calculateScore() {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int d : dice) freq.put(d, freq.getOrDefault(d, 0) + 1);

        boolean straight = freq.keySet().containsAll(Arrays.asList(1,2,3,4,5)) ||
                freq.keySet().containsAll(Arrays.asList(2,3,4,5,6));

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

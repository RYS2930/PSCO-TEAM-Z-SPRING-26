import java.util.*;

public class DiceGridGame {
    private int[][] grid = new int[3][3];
    private Random rand = new Random();
    private Scanner sc = new Scanner(System.in);

    public void play() {
        for (int turn = 0; turn < 9; turn++) {
            int die = rollDie();
            printGrid();
            System.out.println("Rolled: " + die);

            while (true) {
                System.out.print("Enter row col (0-2): ");
                int r = sc.nextInt();
                int c = sc.nextInt();
                if (grid[r][c] == 0) {
                    grid[r][c] = die;
                    break;
                }
                System.out.println("Cell occupied.");
            }
        }

        printGrid();
        System.out.println("Final score: " + calculateScore());
    }

    private int rollDie() {
        return rand.nextInt(6) + 1;
    }

    private int calculateScore() {
        int score = 0;
        for (int i = 0; i < 3; i++) {
            score += scoreLine(grid[i]);
            score += scoreLine(new int[]{grid[0][i], grid[1][i], grid[2][i]});
        }
        return score;
    }

    private int scoreLine(int[] line) {
        Arrays.sort(line);
        if (line[0] == line[2]) return 15;
        if (line[0]+1 == line[1] && line[1]+1 == line[2]) return 12;
        if (line[0] == line[1] || line[1] == line[2]) return 8;
        return 5;
    }

    private void printGrid() {
        for (int[] row : grid)
            System.out.println(Arrays.toString(row));
    }
}

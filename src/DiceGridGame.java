import java.util.*;

public class DiceGridGame {
    private int[][] grid = new int[3][3];
    private Random rand = new Random();
    private Scanner sc;


    public DiceGridGame() {
        this(new Scanner(System.in));
    }

    
    public DiceGridGame(Scanner sc) {
        this.sc = sc;
    }

    public void play() {
        resetGrid();

        for (int turn = 0; turn < 9; turn++) {
            int die = rollDie();
            printGrid();
            System.out.println("Rolled: " + die);

            while (true) {
                System.out.print("Enter row col (0-2): ");

                Integer r = readIntSafe();
                Integer c = readIntSafe();

                if (r == null || c == null) {
                    System.out.println("Invalid input. Please enter TWO numbers (0-2).");
                    continue;
                }

                if (r < 0 || r > 2 || c < 0 || c > 2) {
                    System.out.println("Out of range! Row and col must be 0-2.");
                    continue;
                }

                if (grid[r][c] != 0) {
                    System.out.println("Cell occupied. Choose another.");
                    continue;
                }

                grid[r][c] = die;
                break;
            }
        }

        printGrid();
        System.out.println("Final score: " + calculateScore());
    }

    private void resetGrid() {
        for (int r = 0; r < 3; r++) {
            Arrays.fill(grid[r], 0);
        }
    }

    private Integer readIntSafe() {
        if (!sc.hasNextInt()) {
            sc.next(); 
            return null;
        }
        return sc.nextInt();
    }

    private int rollDie() {
        return rand.nextInt(6) + 1;
    }

    public int calculateScore() {
        int score = 0;

        
        for (int r = 0; r < 3; r++) {
            score += scoreLine(grid[r]);
        }

        
        for (int c = 0; c < 3; c++) {
            score += scoreLine(new int[]{grid[0][c], grid[1][c], grid[2][c]});
        }

        return score;
    }

    private int scoreLine(int[] line) {
        int[] sorted = line.clone();  
        Arrays.sort(sorted);

        if (sorted[0] == sorted[2]) return 15; 
        if (sorted[0] + 1 == sorted[1] && sorted[1] + 1 == sorted[2]) return 12; 
        if (sorted[0] == sorted[1] || sorted[1] == sorted[2]) return 8; 
        return 5; 
    }

    private void printGrid() {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }
}

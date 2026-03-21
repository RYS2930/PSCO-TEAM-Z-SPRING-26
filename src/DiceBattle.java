import java.util.*;

public class DiceBattle {

    private BattlePlayer player;
    private BattlePlayer computer;
    private Random rand = new Random();
    private Scanner sc = new Scanner(System.in);

    public DiceBattle() {
        player = new BattlePlayer(20);
        computer = new BattlePlayer(20);
    }

    public BattleResult play() {
        System.out.println("\n=== Dice Battle ===");

        while (player.isAlive() && computer.isAlive()) {

            System.out.println("\nPlayer HP: " + player.getHp()
                    + " | Computer HP: " + computer.getHp());

            Action playerAction = playerChooseAction();
            Action computerAction = computerChooseAction();

            int playerAttack = 0, computerAttack = 0;
            int playerHeal = 0, computerHeal = 0;

            if (playerAction == Action.ATTACK) {
                int dice = rand.nextInt(3) + 1;
                int[] rolls = rollDice(dice);
                playerAttack = sum(rolls);
                System.out.println("You attack with " + Arrays.toString(rolls));
                if (isCritical(rolls)) {
                    playerAttack *= 2;
                    System.out.println("Critical hit!");
                }
            }

            if (computerAction == Action.ATTACK) {
                int dice = rand.nextInt(3) + 1;
                int[] rolls = rollDice(dice);
                computerAttack = sum(rolls);
                System.out.println("Computer attacks with " + Arrays.toString(rolls));
                if (isCritical(rolls)) {
                    computerAttack *= 2;
                    System.out.println("Computer critical hit!");
                }
            }

            if (playerAction == Action.HEAL) {
                int[] rolls = rollDice(2);
                playerHeal = sum(rolls);
                System.out.println("You heal with " + Arrays.toString(rolls)
                        + " → +" + playerHeal);
            }

            if (computerAction == Action.HEAL) {
                int[] rolls = rollDice(2);
                computerHeal = sum(rolls);
                System.out.println("Computer heals with " + Arrays.toString(rolls)
                        + " → +" + computerHeal);
            }

            // -------- Phase 3: Resolve defend --------
            if (playerAction == Action.DEFEND) {
                player.setDefending(true);
                System.out.println("You defend this round.");
            }

            if (computerAction == Action.DEFEND) {
                computer.setDefending(true);
                System.out.println("Computer defends this round.");
            }

            // Player attacks first (player attack + computer attack: damage computer before player).
            // Player attack + computer defend: damage halved.
            if (playerAttack > 0) {
                if (computerAction == Action.DEFEND) {
                    int dmg = playerAttack / 2;
                    computer.takeDamage(dmg);
                    System.out.println("Computer defends — damage halved. Computer takes " + dmg + " damage.");
                } else {
                    computer.takeDamage(playerAttack);
                    System.out.println("Computer takes " + playerAttack + " damage.");
                }
            }

            // Computer attacks only if still alive (user has priority on simultaneous attacks).
            if (computerAttack > 0) {
                if (!computer.isAlive()) {
                    System.out.println("Computer is defeated and cannot attack!");
                } else {
                    int dmg = computerAttack;
                    if (player.isDefending()) {
                        dmg /= 2;
                        System.out.println("You defend — damage is halved!");
                    }
                    player.takeDamage(dmg);
                    System.out.println("You take " + dmg + " damage.");
                }
            }

            player.heal(playerHeal);
            computer.heal(computerHeal);

            player.clearDefend();
            computer.clearDefend();
        }

        boolean playerWon = player.isAlive();
        System.out.println(playerWon
                ? "You win the battle!"
                : "You lost the battle!");
        return new BattleResult(player.getHp(), computer.getHp(), playerWon);
    }


    private Action playerChooseAction() {
        System.out.println("\nYour action:");
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.println("3. Heal (2 dice)");
        System.out.print("Choose: ");

        int choice = sc.nextInt();
        return switch (choice) {
            case 2 -> Action.DEFEND;
            case 3 -> Action.HEAL;
            default -> Action.ATTACK;
        };
    }

    private Action computerChooseAction() {
        if (computer.getHp() <= 5 && rand.nextBoolean()) {
            return Action.HEAL;
        }
        if (computer.getHp() <= 6 && rand.nextBoolean()) {
            return Action.DEFEND;
        }
        return Action.ATTACK;
    }

    private int[] rollDice(int count) {
        int[] rolls = new int[count];
        for (int i = 0; i < count; i++) {
            rolls[i] = rand.nextInt(6) + 1;
        }
        return rolls;
    }

    private int sum(int[] arr) {
        int s = 0;
        for (int v : arr) s += v;
        return s;
    }

    private boolean isCritical(int[] rolls) {
        if (rolls.length < 2) return false;
        for (int r : rolls) {
            if (r != 6) return false;
        }
        return true;
    }

    enum Action {
        ATTACK, DEFEND, HEAL
    }

    public record BattleResult(int playerScore, int computerScore, boolean playerWon) {}
}

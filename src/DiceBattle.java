import java.util.Random;
import java.util.Scanner;

public class DiceBattle {

    private Player player;
    private Player computer;
    private Random rand = new Random();
    private Scanner sc = new Scanner(System.in);

    public DiceBattle() {
        player = new Player(20);
        computer = new Player(20);
    }

    public void play() {
        System.out.println("\n=== Dice Battle ===");

        while (player.isAlive() && computer.isAlive()) {
            System.out.println("\nPlayer HP: " + player.getHp()
                    + " | Computer HP: " + computer.getHp());

            playerTurn();
            if (!computer.isAlive()) break;

            computerTurn();
        }

        if (player.isAlive()) {
            System.out.println("You win the battle!");
        } else {
            System.out.println("You lost the battle!");
        }
    }

    private void playerTurn() {
        player.setDefending(false);
        System.out.println("\nYour turn:");
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.print("Choose: ");

        int choice = sc.nextInt();

        if (choice == 2) {
            player.setDefending(true);
            System.out.println("You are defending this turn.");
            return;
        }

        int diceCount = rand.nextInt(3) + 1;
        int damage = rollDice(diceCount);
        System.out.println("You rolled " + diceCount + " dice.");
        applyDamage(computer, damage);
    }

    private void computerTurn() {
        computer.setDefending(false);
        System.out.println("\nComputer's turn:");

        if (computer.getHp() <= 6) {
            computer.setDefending(true);
            System.out.println("Computer chooses to defend.");
            return;
        }

        int diceCount = rand.nextInt(3) + 1;
        int damage = rollDice(diceCount);
        System.out.println("Computer attacks with " + diceCount + " dice.");
        applyDamage(player, damage);
    }

    private int rollDice(int count) {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            int roll = rand.nextInt(6) + 1;
            sum += roll;
        }
        return sum;
    }

    private void applyDamage(Player target, int damage) {
        if (target.isDefending()) {
            damage /= 2;
            System.out.println("Defence activated! Damage reduced.");
        }
        target.takeDamage(damage);
        System.out.println("Damage dealt: " + damage);
    }
}

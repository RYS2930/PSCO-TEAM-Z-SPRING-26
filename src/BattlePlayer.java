public class BattlePlayer {

    private int hp;
    private boolean defending;

    public BattlePlayer(int hp) {
        this.hp = hp;
        this.defending = false;
    }

    public int getHp() {
        return hp;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public boolean isDefending() {
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public void clearDefend() {
        defending = false;
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp < 0) hp = 0;
    }

    public void heal(int amount) {
        hp += amount;
    }
}


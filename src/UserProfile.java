import java.time.LocalDateTime;

public class UserProfile {

    private String username;
    private int totalGamesPlayed;

    private int highestPatternScore;
    private int lastPatternScore;

    private int highestGridScore;
    private int lastGridScore;

    private int highestCodebreakerScore;
    private int lastCodebreakerScore;

    private int highestBattleScore;
    private int lastBattleScore;

    private LocalDateTime lastPlayed;

    public UserProfile(String username) {
        this.username = username;
        this.lastPlayed = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public void incrementGamesPlayed() {
        totalGamesPlayed++;
        lastPlayed = LocalDateTime.now();
    }

    public void updatePatternScore(int score) {
        lastPatternScore = score;
        if (score > highestPatternScore)
            highestPatternScore = score;
    }

    public void updateGridScore(int score) {
        lastGridScore = score;
        if (score > highestGridScore)
            highestGridScore = score;
    }

    public void updateCodebreakerScore(int score) {
        lastCodebreakerScore = score;
        if (score > highestCodebreakerScore)
            highestCodebreakerScore = score;
    }

    public void updateBattleScore(int score) {
        lastBattleScore = score;
        if (score > highestBattleScore)
            highestBattleScore = score;
    }

    public String toFileString() {
        return username + "," + totalGamesPlayed + ","
                + highestPatternScore + "," + lastPatternScore + ","
                + highestGridScore + "," + lastGridScore + ","
                + highestCodebreakerScore + "," + lastCodebreakerScore + ","
                + highestBattleScore + "," + lastBattleScore + ","
                + lastPlayed;
    }

    public static UserProfile fromFileString(String line) {
        String[] p = line.split(",");
        UserProfile u = new UserProfile(p[0]);
        u.totalGamesPlayed = Integer.parseInt(p[1]);
        u.highestPatternScore = Integer.parseInt(p[2]);
        u.lastPatternScore = Integer.parseInt(p[3]);
        u.highestGridScore = Integer.parseInt(p[4]);
        u.lastGridScore = Integer.parseInt(p[5]);
        u.highestCodebreakerScore = Integer.parseInt(p[6]);
        u.lastCodebreakerScore = Integer.parseInt(p[7]);
        u.highestBattleScore = Integer.parseInt(p[8]);
        u.lastBattleScore = Integer.parseInt(p[9]);
        u.lastPlayed = LocalDateTime.parse(p[10]);
        return u;
    }

    @Override
    public String toString() {
        return """
        User: %s
        Total Games: %d
        Last Played: %s

        Dice Pattern   → High: %d | Last: %d
        Dice Grid      → High: %d | Last: %d
        Codebreaker    → High: %d | Last: %d
        Dice Battle    → High: %d | Last: %d
        """.formatted(username, totalGamesPlayed, lastPlayed,
                highestPatternScore, lastPatternScore,
                highestGridScore, lastGridScore,
                highestCodebreakerScore, lastCodebreakerScore,
                highestBattleScore, lastBattleScore);
    }
}

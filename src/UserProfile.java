import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserProfile {
    private static final int MAX_RECENT = 10;

    private String username;
    private int totalGamesPlayed;

    private int highestPatternScore;
    private int lastPatternScore;

    private int highestGridScore;
    private int lastGridScore;

    /** Fewest guesses to win (1–10); 0 = never won yet. */
    private int bestCodebreakerGuesses;
    /** Last game: 1–10 = correct on that guess; -1 = none; 0 = never played Codebreaker. */
    private int lastCodebreakerRound;

    private int battleWins;
    private int battleLosses;

    private List<Integer> recentPatternScores = new ArrayList<>();
    private List<Integer> recentGridScores = new ArrayList<>();
    /** Each entry: 1–10 = guess number when correct, -1 = none (did not solve) */
    private List<Integer> recentCodebreakerOutcomes = new ArrayList<>();
    private List<String> recentBattleResults = new ArrayList<>();

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
        addRecentScore(recentPatternScores, score);
    }

    public void updateGridScore(int score) {
        lastGridScore = score;
        if (score > highestGridScore)
            highestGridScore = score;
        addRecentScore(recentGridScores, score);
    }

    /**
     * @param outcome {@code -1} = did not solve; {@code 1}–{@code 10} = correct on that guess number
     */
    public void updateCodebreaker(int outcome) {
        if (outcome == -1) {
            lastCodebreakerRound = -1;
            addRecentScore(recentCodebreakerOutcomes, -1);
            return;
        }
        if (outcome >= 1 && outcome <= 10) {
            lastCodebreakerRound = outcome;
            if (bestCodebreakerGuesses == 0 || outcome < bestCodebreakerGuesses) {
                bestCodebreakerGuesses = outcome;
            }
            addRecentScore(recentCodebreakerOutcomes, outcome);
        }
    }

    public void updateBattleResult(boolean won, int playerScore, int computerScore) {
        if (won) {
            battleWins++;
        } else {
            battleLosses++;
        }
        String playerText = won ? playerScore + "(win)" : String.valueOf(playerScore);
        String computerText = won ? String.valueOf(computerScore) : computerScore + "(win)";
        addRecentBattleResult("[Player:" + playerText + " vs Computer:" + computerText + "]");
    }

    public String getPatternStats() {
        return "Dice Pattern   -> High: " + highestPatternScore + " | Last: " + lastPatternScore
                + "\nRecent 10 times:\n" + formatRecentScoresLines(recentPatternScores);
    }

    public String getGridStats() {
        return "Dice Grid      -> High: " + highestGridScore + " | Last: " + lastGridScore
                + "\nRecent 10 times:\n" + formatRecentScoresLines(recentGridScores);
    }

    public String getCodebreakerStats() {
        return "Codebreaker    -> Best: " + formatCodebreakerBestLine()
                + " | Last: " + formatCodebreakerLastLine()
                + "\nRecent 10 times:\n" + formatRecentCodebreakerLines(recentCodebreakerOutcomes);
    }

    public String getBattleStats() {
        return "Dice Battle    -> Wins: " + battleWins + " | Losses: " + battleLosses
                + "\nRecent 10 times:\n" + formatRecentBattleLines();
    }

    public String toFileString() {
        return username + "," + totalGamesPlayed + ","
                + highestPatternScore + "," + lastPatternScore + ","
                + highestGridScore + "," + lastGridScore + ","
                + bestCodebreakerGuesses + "," + lastCodebreakerRound + ","
                + battleWins + "," + battleLosses + ","
                + lastPlayed + ","
                + joinIntList(recentPatternScores) + ","
                + joinIntList(recentGridScores) + ","
                + joinIntList(recentCodebreakerOutcomes) + ","
                + joinStringList(recentBattleResults);
    }

    public static UserProfile fromFileString(String line) {
        String[] p = line.split(",");
        UserProfile u = new UserProfile(p[0]);
        u.totalGamesPlayed = Integer.parseInt(p[1]);
        u.highestPatternScore = Integer.parseInt(p[2]);
        u.lastPatternScore = Integer.parseInt(p[3]);
        u.highestGridScore = Integer.parseInt(p[4]);
        u.lastGridScore = Integer.parseInt(p[5]);
        u.bestCodebreakerGuesses = Integer.parseInt(p[6]);
        u.lastCodebreakerRound = Integer.parseInt(p[7]);
        u.battleWins = Integer.parseInt(p[8]);
        u.battleLosses = Integer.parseInt(p[9]);
        u.lastPlayed = LocalDateTime.parse(p[10]);
        if (p.length > 11) u.recentPatternScores = parseIntList(p[11]);
        if (p.length > 12) u.recentGridScores = parseIntList(p[12]);
        if (p.length > 13) u.recentCodebreakerOutcomes = parseIntList(p[13]);
        if (p.length > 14) u.recentBattleResults = parseStringList(p[14]);

        // Old saves stored "points" (e.g. 100) instead of guess counts — reset Codebreaker stats.
        boolean legacyCodebreaker = u.bestCodebreakerGuesses > 10 || u.bestCodebreakerGuesses < 0
                || u.lastCodebreakerRound > 10 || u.lastCodebreakerRound < -1;
        if (legacyCodebreaker) {
            u.bestCodebreakerGuesses = 0;
            u.lastCodebreakerRound = 0;
            u.recentCodebreakerOutcomes = new ArrayList<>();
        }
        return u;
    }

    private void addRecentScore(List<Integer> history, int score) {
        history.add(score);
        trimToRecentLimit(history);
    }

    private void addRecentBattleResult(String result) {
        recentBattleResults.add(result);
        trimToRecentLimit(recentBattleResults);
    }

    private <T> void trimToRecentLimit(List<T> history) {
        while (history.size() > MAX_RECENT) {
            history.remove(0);
        }
    }

    /** One score per line (same layout as Battle recent). */
    private String formatRecentScoresLines(List<Integer> history) {
        if (history.isEmpty()) return "No record yet";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < history.size(); i++) {
            if (i > 0) sb.append("\n");
            sb.append(history.get(i));
        }
        return sb.toString();
    }

    private String formatRecentBattleLines() {
        if (recentBattleResults.isEmpty()) return "No record yet";
        return String.join("\n", recentBattleResults);
    }

    private String formatCodebreakerBestLine() {
        if (bestCodebreakerGuesses <= 0) return "-";
        return codebreakerLineText(bestCodebreakerGuesses);
    }

    private String formatCodebreakerLastLine() {
        if (lastCodebreakerRound == 0) return "-";
        return codebreakerLineText(lastCodebreakerRound);
    }

    /** One entry per line: failed runs show {@code Failed}, wins show {@code Correct on guess N}. */
    private String formatRecentCodebreakerLines(List<Integer> history) {
        if (history.isEmpty()) return "No record yet";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < history.size(); i++) {
            if (i > 0) sb.append("\n");
            sb.append(codebreakerLineText(history.get(i)));
        }
        return sb.toString();
    }

    /** {@code -1} → Failed; {@code 1}–{@code 10} → Correct on guess N */
    private static String codebreakerLineText(int v) {
        if (v == -1) return "Failed";
        return "Correct on guess " + v;
    }

    private static String joinIntList(List<Integer> list) {
        if (list.isEmpty()) return "-";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append("|");
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    private static String joinStringList(List<String> list) {
        if (list.isEmpty()) return "-";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append("|");
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    private static List<Integer> parseIntList(String raw) {
        List<Integer> list = new ArrayList<>();
        if (raw == null || raw.isBlank() || raw.equals("-")) return list;
        String[] parts = raw.split("\\|");
        for (String part : parts) {
            if (!part.isBlank()) {
                list.add(Integer.parseInt(part));
            }
        }
        return list;
    }

    private static List<String> parseStringList(String raw) {
        List<String> list = new ArrayList<>();
        if (raw == null || raw.isBlank() || raw.equals("-")) return list;
        String[] parts = raw.split("\\|");
        for (String part : parts) {
            if (!part.isBlank()) {
                list.add(part);
            }
        }
        return list;
    }
}

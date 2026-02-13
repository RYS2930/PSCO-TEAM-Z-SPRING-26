import java.io.*;
import java.util.*;

public class UserManager {

    private static final String FILE = "users.txt";

    public static UserProfile loadOrCreateUser(String username) {
        List<UserProfile> users = loadAll();

        for (UserProfile u : users) {
            if (u.getUsername().equalsIgnoreCase(username))
                return u;
        }

        UserProfile newUser = new UserProfile(username);
        users.add(newUser);
        saveAll(users);
        return newUser;
    }

    public static void saveUser(UserProfile user) {
        List<UserProfile> users = loadAll();
        users.removeIf(u -> u.getUsername().equalsIgnoreCase(user.getUsername()));
        users.add(user);
        saveAll(users);
    }

    private static List<UserProfile> loadAll() {
        List<UserProfile> users = new ArrayList<>();
        File f = new File(FILE);

        if (!f.exists()) return users;

        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                users.add(UserProfile.fromFileString(sc.nextLine()));
            }
        } catch (Exception e) {
            System.out.println("Error reading users file.");
        }
        return users;
    }

    private static void saveAll(List<UserProfile> users) {
        try (PrintWriter pw = new PrintWriter(FILE)) {
            for (UserProfile u : users)
                pw.println(u.toFileString());
        } catch (Exception e) {
            System.out.println("Error writing users file.");
        }
    }
}

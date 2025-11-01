import java.io.*;

public class UserService {
    private final String FILE = "data/users.txt";

    public User login(String username, String password) {
        File f = new File(FILE);
        if (!f.exists()) return null;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] creds = line.split(",");
                if (creds.length >= 2 && creds[0].equals(username) && creds[1].equals(password)) {
                    return new User(username, password);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user file.");
        }
        return null;
    }

    public void register(String username, String password) {
        try {
            File f = new File(FILE);
            f.getParentFile().mkdirs();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, true))) {
                bw.write(username + "," + password);
                bw.newLine();
            }
            System.out.println("User registered successfully!");
        } catch (IOException e) {
            System.out.println("Error writing user file.");
        }
    }
}

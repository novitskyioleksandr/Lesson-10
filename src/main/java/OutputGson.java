import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

public class OutputGson {

    private static final String RELATIVE_PATH = "src/main/resources/file/file.txt";
    private static final String ABSOLUTE_PATH = "D:\\GoIT\\Lesson-10\\Lesson-10\\src\\main\\resources\\file\\user.json";

    public static void main(String[] args) {
        File file = new File(RELATIVE_PATH);
        ArrayList<User> users = new ArrayList<User>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                if (line.equals("name age")) {
                    line = reader.readLine();
                }
                String name = line.replaceAll("\\s+", "").replaceAll("\\d+", "");
                String ageUser = line.replaceAll("\\D+", "");
                int age = Integer.parseInt(ageUser);
                User user = new User(name, age);
                users.add(user);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String userJson = gson.toJson(users);

        File fileJson = new File(ABSOLUTE_PATH);
        if (!fileJson.exists()) {
            fileJson.getParentFile().mkdirs();

            try {
                fileJson.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        try (FileWriter writer = new FileWriter(fileJson)) {
            writer.write(userJson);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class User implements Serializable {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
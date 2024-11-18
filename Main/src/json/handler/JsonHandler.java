package json.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import project.users.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonHandler {
    private static Gson gson;

     static {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        gson = builder.create();
    }

    public static void writeToJson(LinkedList<User> usersList) {
        LinkedList<User> existingUsersList = readFromJson();
        if (existingUsersList != null) {
            usersList.addAll(existingUsersList);
        }
        String payload = gson.toJson(usersList);
        try {
            Files.write(Paths.get("register.json"), payload.getBytes());
        }
        catch (IOException ex) {
            Logger.getLogger(JsonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static LinkedList<User> readFromJson() {
        String recoveryPayload = "";
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("output.json"));
            recoveryPayload = new String(bytes);
        } catch (IOException ex) {
            Logger.getLogger(JsonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        Type userListType = new TypeToken<LinkedList<User>>(){}.getType();
        return gson.fromJson(recoveryPayload, userListType);
    }
}

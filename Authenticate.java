import java.util.HashMap;
import java.util.Scanner;

public class Authenticate {

    public static void verifyCredentials(String username, String password, Scanner scan){

        // initializing Hashmap objects 
        HashMap<String, User> users = new HashMap<>();

        // Creating objects for pharmacist and assistant
        // Assigning credentials and roles directly
        Pharmacist pharmacist = new Pharmacist(01,"Elgene Reyes", "pharmacist", "elgene", "Pharmacist");
        Assistant assistant = new Assistant(02,"Michelle Martinez", "assistant", "mich", "Assistant");

        users.put(pharmacist.getUsername(), pharmacist);
        users.put(assistant.getUsername(), assistant);

        // checking credential and access level
        if (users.containsKey(username) && users.get(username).getPassword().equals(password)){                    
            
            User user = users.get(username);
            user.mainMenu(scan);
        }
        else{
            System.out.println("Invalid username or password. Please try again.");
        }
    }
}

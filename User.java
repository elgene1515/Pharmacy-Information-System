import java.util.Scanner;

public class User {
    private int id;
    private String name;
    private String username;
    private String password; 
    private String role;

    public User(){
        this.id = 000;
        this.name = "Unknown";
        this.username = "Unknown";
        this.password = "Unknown";
        this.role = "Unknown";
    }    
    public User(int id, String name, String username, String password, String role){
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public int getID(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }

    public void mainMenu(Scanner scan){
    }
}

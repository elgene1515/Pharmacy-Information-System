import java.util.Scanner;

public class Login {
    public static void main(String[] args) {

        // initialization of variables and flags
        Scanner scan = new Scanner(System.in);
        String username;
        String password;
        int option;
        boolean runningSystem = true;

        // Main login page
        while (runningSystem) {
            try {
                System.out.println("======================================");
                System.out.println("Welcome to Pharmacy Information System");
                System.out.println("======================================");
                System.out.println("1. Login");
                System.out.println("2. Exit");
                option = Integer.parseInt(scan.nextLine());
                
                switch (option) {
                    case 1:{
                        System.out.print("Enter Username: ");
                        username = scan.nextLine();
                        System.out.print("Enter Password: ");
                        password = scan.nextLine();
    
                        // authenticate
                        Authenticate.verifyCredentials(username, password, scan);
                        break;
                    }
                    case 2: {
                        System.out.println("Exiting the system. Goodbye!");
                        runningSystem = false;
                        break;
                    }
                    default:
                        System.out.println("Invalid Option. Please Try Again.");
                        break;
                }
            } catch (NumberFormatException  e) {
                System.out.println("Invalid Option. Please Try Again.");
            }
        }
        scan.close();
    }
}
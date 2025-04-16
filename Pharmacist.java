import java.util.Scanner;
public class Pharmacist extends User{

    private String name;
    private String role;

    public Pharmacist(int id, String name, String username, String password, String role){
        super(id, name, username, password, role);
        this.role = role;
        this.name = name;
    }

    @Override
    public void mainMenu(Scanner scan){
        int option;
        boolean loggedin = true;

        while (loggedin) {
            System.out.println("======================================");
            System.out.println("Main Menu (" + name + " : " + role + ")");
            System.out.println("======================================");
            System.out.println("1. Inventory Management");
            System.err.println("2. Customer Management");
            System.out.println("3. Logout");
            
            try {
                option = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Option. Please Try Again");
                continue;
            }

            switch (option) {
                case 1:InventoryManagement.inventory(scan, role);break;
                case 2:CustomerManagement.manageCustomer(scan, role);break;
                case 3:loggedin = false;break;
                default:System.out.println("Invalid Option. Please Try Again.");break;
            }
        }
    }
}

import java.util.ArrayList;

public class Customer {
    private int id;
    private String name;
    private String contact;
    private boolean prescription;
    private ArrayList<String> purchaseHistory;

    public Customer(){
        this.id = 0;
        this.name = "Unknown";
        this.contact = "Unknown";
        this.prescription = false;
        this.purchaseHistory = new ArrayList<>();
    }
    public Customer(int id, String name, String contact, boolean prescription, ArrayList<String> purchaseHistory){
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.prescription = prescription;
        this.purchaseHistory = purchaseHistory;
    }
    public int getID(){
        return id;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public void setPrescription(boolean prescription) {
        this.prescription = prescription;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public boolean getPrescription(){
        return prescription;
    }
    public ArrayList<String> getPurchaseHistory() {
        return purchaseHistory;
    }


    public String getContact() {
        return contact;
    }
    public void displayCustomer(){
        System.out.println(
            "ID: " + id + " | " +
            "NAME: " + name + " | " +
            "CONTACT: " + contact + " | " +
            "PRESCRIPTION: " + prescription + " | " +
            "PURCHASE HISTORY: " + purchaseHistory
        );
    }

}

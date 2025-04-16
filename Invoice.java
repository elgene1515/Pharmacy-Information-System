import java.util.ArrayList;

public class Invoice {
    private int invoiceID;
    private String customerName;
    private ArrayList<Integer> medicineIDs;
    private ArrayList<Integer> quantities;
    private float totalAmount;

    public Invoice(int invoiceID, String customerName, ArrayList<Integer> medicineIDs, ArrayList<Integer> quantities, float totalAmount) {
        this.invoiceID = invoiceID;
        this.customerName = customerName;
        this.medicineIDs = medicineIDs;
        this.quantities = quantities;
        this.totalAmount = totalAmount;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<Integer> getMedicineIDs() {
        return medicineIDs;
    }

    public ArrayList<Integer> getQuantities() {
        return quantities;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void displayInvoice() {
        System.out.println("======================================");
        System.out.println("Invoice ID: " + invoiceID);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Medicines:");
        for (int i = 0; i < medicineIDs.size(); i++) {
            int medicineID = medicineIDs.get(i); 
            int quantity = quantities.get(i);
    
            // Retrieve medicine details
            Medicine medicine = InventoryManagement.medicines.get(medicineID);
            if (medicine != null) {
                float subtotal = medicine.getPrice() * quantity; // Calculate subtotal
                System.out.printf("- %s x%d @ %.2f each = %.2f%n", medicine.getName(), quantity, medicine.getPrice(), subtotal);
            }
        }
        System.out.printf("Total Amount: %.2f%n", totalAmount);
        System.out.println("======================================");
    }
    
}

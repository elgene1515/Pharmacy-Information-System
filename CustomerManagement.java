import java.util.Scanner;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class CustomerManagement {

    private static HashMap<Integer, Customer> customers = new HashMap<>();
    private static Set<Integer> usedCustomerIds = new TreeSet<>();

    static{
        customers.put(1, new Customer(1, "John Doe", "09171234567", true, new ArrayList<>(Arrays.asList())));
        customers.put(2, new Customer(2, "Jane Smith", "09281234568", false, new ArrayList<>(Arrays.asList())));
        customers.put(3, new Customer(3, "Alice Brown", "09391234569", true, new ArrayList<>(Arrays.asList())));
        customers.put(4, new Customer(4, "Bob White", "09471234560", false, new ArrayList<>(Arrays.asList())));
    
        usedCustomerIds.add(1);
        usedCustomerIds.add(2);
        usedCustomerIds.add(3);
        usedCustomerIds.add(4);
    }

    public static void manageCustomer(Scanner scan, String role){
        boolean running = true;
        int option;
        
        while(running){
            System.out.println("======================================");
            System.out.println("Cusomer Management (" + role +")");
            System.out.println("======================================");
            System.out.println("1. View Customers");
            System.out.println("2. Add Customer");
            System.out.println("3. Edit Customer");
            System.out.println("4. Check Presciption Status");
            System.out.println("5. Dispense Medicine");
            System.out.println("6. View Invoice");
            System.out.println("7. Return to Main Menu");
            try {
                // Use `Integer.parseInt` to catch invalid number inputs
                option = Integer.parseInt(scan.nextLine());
    
                switch (option) {
                    case 1: 
                        viewCustomer(); 
                        break;
                    case 2: 
                        addCustomer(scan); 
                        break;
                    case 3: 
                        editCustomer(scan); 
                        break;
                    case 4: 
                        checkPrescriptionStatus(scan); 
                        break;
                    case 5: 
                        dispenseMedicine(scan); 
                        break;
                    case 6: 
                        viewInvoice(scan); 
                        break;
                    case 7: 
                        running = false; 
                        break;
                    default: 
                        System.out.println("Invalid Option. Please Try Again."); 
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Option. Please Try Again.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please Try Again.");
                e.printStackTrace(); // Optional: Print the stack trace for debugging
            }
        }
    }
    public static int findNextCustomerID(){
        int id = 1;

        while(usedCustomerIds.contains(id)){
            id++;
        }
        return id;
    }
    public static void viewCustomer(){
        if(customers.isEmpty()){
            System.out.println("======================================");
            System.out.println("No Customer Found");
            System.out.println("======================================");
        }
        else{
            System.out.println("======================================");
            System.out.println("List of Customers");
            System.out.println("======================================");
    
            for(Customer customer: customers.values()){
                // Prepare prescription status
                String prescriptionStatus = customer.getPrescription() ? "PRESCRIBED" : "NOT PRESCRIBED";
    
                // Get purchase history as a string, showing "[]" if it's empty
                String purchaseHistory = customer.getPurchaseHistory().isEmpty() ? "[]" : customer.getPurchaseHistory().toString();
    
                // Display customer details including purchase history
                System.out.println("ID: " + customer.getID() + ", Name: " + customer.getName() + ", Contact: " + customer.getContact() + ", Prescription: " + prescriptionStatus + ", Purchase History: " + purchaseHistory);
            }
        }
    }        
    public static void addCustomer(Scanner scan) {
        int id = 0;
        String name = "";
        String contact = "";
        String prescriptionInput = "";
        boolean prescription = false;
    
        try {
            System.out.println("======================================");
            System.out.println("Add Customer");
            System.out.println("======================================");
    
            // Auto-generate customer ID
            id = findNextCustomerID();
    
            // Input for Name
            System.out.print("Enter Name: ");
            name = scan.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Invalid name input! Defaulting to 'Unknown'.");
                name = "Unknown";
            }
    
            // Input for Contact
            System.out.print("Enter Contact: ");
            contact = scan.nextLine().trim();
            if (contact.isEmpty() || !contact.matches("\\d{10,12}")) { // Validate contact as 10-12 digits
                System.out.println("Invalid contact input! Defaulting to 'N/A'.");
                contact = "N/A";
            }
    
            // Input for Prescription Status
            System.out.print("Does the customer need a prescription (yes / no)? ");
            prescriptionInput = scan.nextLine().trim().toLowerCase();
            if (prescriptionInput.equals("yes")) {
                prescription = true;
            } else if (prescriptionInput.equals("no")) {
                prescription = false;
            } else {
                System.out.println("Invalid input! Defaulting to 'no prescription'.");
                prescription = false;
            }
    
            // Create the customer and add it to the collection
            Customer customer = new Customer(id, name, contact, prescription, new ArrayList<>());
            customers.put(id, customer);
            usedCustomerIds.add(id);
    
            // Display success message and customer details
            System.out.println("Customer added successfully!");
            String prescriptionStatus = prescription ? "PRESCRIBED" : "NOT PRESCRIBED";
            System.out.println("ID: " + customer.getID() 
                    + " | NAME: " + customer.getName() 
                    + " | CONTACT: " + customer.getContact() 
                    + " | PRESCRIPTION: " + prescriptionStatus 
                    + " | PURCHASE HISTORY: " + customer.getPurchaseHistory());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding the customer. Please try again.");
            e.printStackTrace(); // Optional for debugging
        }
    }        
    public static void editCustomer(Scanner scan) {
        int id;
        String name;
        String contact;
        boolean prescription;
    
        if (customers.isEmpty()) {
            System.out.println("======================================");
            System.out.println("No Customers to Edit.");
            System.out.println("======================================");
            return;
        }
    
        try {
            // Display the list of customers
            System.out.println("======================================");
            System.out.println("List of Customers");
            System.out.println("======================================");
            for (Customer customer : customers.values()) {
                String prescriptionStatus = customer.getPrescription() ? "PRESCRIBED" : "NOT PRESCRIBED";
                System.out.println("ID: " + customer.getID() + " | NAME: " + customer.getName() + " | CONTACT: " + customer.getContact() + " | PRESCRIPTION: " + prescriptionStatus + " | PURCHASE HISTORY: " + customer.getPurchaseHistory());
            }
    
            System.out.println("======================================");
            System.out.println("Edit Customer Details");
            System.out.println("======================================");
            System.out.print("Enter the ID of the Customer you want to edit: ");
            try {
                id = Integer.parseInt(scan.nextLine()); // Parsing ID input from user
            } catch (NumberFormatException e) {
                System.out.println("Invalid customer ID! Please enter a valid number.");
                return;
            }
    
            if (customers.containsKey(id)) {
                Customer customer = customers.get(id);
    
                // Display customer info before editing
                String prescriptionStatus = customer.getPrescription() ? "PRESCRIBED" : "NOT PRESCRIBED";
                System.out.println("ID: " + customer.getID() + " | NAME: " + customer.getName() + " | CONTACT: " + customer.getContact() + " | PRESCRIPTION: " + prescriptionStatus + " | PURCHASE HISTORY: " + customer.getPurchaseHistory());
    
                // Edit Name
                System.out.print("Enter New Name (leave blank to keep current): ");
                name = scan.nextLine();
                if (!name.trim().isEmpty()) {
                    customer.setName(name);
                }
    
                // Edit Contact with validation
                // Input for Contact
                System.out.print("Enter New Contact (leave blank to keep current): ");
                contact = scan.nextLine().trim();
                if (!contact.isEmpty()) {
                    if (!contact.matches("\\d{10,12}")) { // Validate contact as 10-12 digits
                        System.out.println("Invalid contact number! Contact not changed.");
                    } else {
                        customer.setContact(contact);
                    }
                }
    
                // Update Prescription Status
                System.out.print("Update Prescription Status (yes/no, leave blank to keep current): ");
                String prescriptionInput = scan.nextLine().toLowerCase();
                if (!prescriptionInput.trim().isEmpty()) {
                    if (prescriptionInput.equals("yes")) {
                        prescription = true;
                    } else if (prescriptionInput.equals("no")) {
                        prescription = false;
                    } else {
                        System.out.println("Invalid input. Prescription status set to false by default.");
                        prescription = false;
                    }
                    customer.setPrescription(prescription);
                }
    
                // After update, display the customer again with correct prescription status
                System.out.println("Customer updated successfully!");
                String updatedPrescriptionStatus = customer.getPrescription() ? "PRESCRIBED" : "NOT PRESCRIBED"; // Ensure status is correct
                System.out.println("ID: " + customer.getID() + " | NAME: " + customer.getName() + " | CONTACT: " + customer.getContact() + " | PRESCRIPTION: " + updatedPrescriptionStatus + " | PURCHASE HISTORY: " + customer.getPurchaseHistory());
            } else {
                System.out.println("Customer with ID: " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while editing the customer. Please try again.");
            e.printStackTrace(); // Optional for debugging
        }
    }
    
    // Helper method to validate the contact number                 
    public static void checkPrescriptionStatus(Scanner scan) {
        int option;
        int searchID;
    
        if (customers.isEmpty()) {
            System.out.println("======================================");
            System.out.println("No Customers to Check Prescription.");
            System.out.println("======================================");
            return;
        }
    
        try {
            System.out.println("======================================");
            System.out.println("Check Customer's Prescription Status");
            System.out.println("======================================");
            System.out.println("1. Display all prescription status");
            System.out.println("2. Check specific prescription status");
            option = Integer.parseInt(scan.nextLine());  // Parsing user input for option
    
            switch (option) {
                case 1:
                    for (Integer customer : customers.keySet()) {
                        if (customers.get(customer).getPrescription()) {
                            System.out.println(customers.get(customer).getName() + " with ID: " + customers.get(customer).getID() + " is PRESCRIBED");
                        } else {
                            System.out.println(customers.get(customer).getName() + " with ID: " + customers.get(customer).getID() + " is NOT PRESCRIBED");
                        }
                    }
                    break;
    
                case 2:
                    System.out.print("Enter the ID of customer you want to check the prescription status: ");
                    searchID = Integer.parseInt(scan.nextLine());  // Parsing user input for searchID
    
                    if (customers.containsKey(searchID)) {
                        if (customers.get(searchID).getPrescription()) {
                            System.out.println(customers.get(searchID).getName() + " with ID: " + customers.get(searchID).getID() + " is PRESCRIBED");
                        } else {
                            System.out.println(customers.get(searchID).getName() + " with ID: " + customers.get(searchID).getID() + " is NOT PRESCRIBED");
                        }
                    } else {
                        System.out.println("Customer with ID: " + searchID + " not found.");
                    }
                    break;
    
                default:
                    System.out.println("Invalid Option. Please Try Again.");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while checking the prescription status. Please try again.");
            e.printStackTrace(); // Optional for debugging purposes
        }
    }    
    public static void dispenseMedicine(Scanner scan) {
        int patientID;
    
        System.out.println("======================================");
        System.out.println("Dispense Medicines");
        System.out.println("======================================");
    
        try {
            if (customers.isEmpty()) {
                System.out.println("======================================");
                System.out.println("No Customer Found");
                System.out.println("======================================");
                return;
            }
    
            viewCustomer();
    
            System.out.println("Enter the ID of the customer to dispense medicine: ");
            patientID = Integer.parseInt(scan.nextLine());
    
            Customer selectedCustomer = customers.get(patientID);
    
            if (selectedCustomer == null) {
                System.out.println("Customer not found.");
                return;
            }
    
            selectedCustomer.displayCustomer();
    
            if (InventoryManagement.medicines.isEmpty()) {
                System.out.println("======================================");
                System.out.println("No medicines available in the inventory.");
                System.out.println("======================================");
                return;
            }
    
            ArrayList<Integer> selectedMedicineIDs = new ArrayList<>();
            ArrayList<Integer> quantities = new ArrayList<>();
            float totalCost = 0;
    
            boolean dispensing = true;
            while (dispensing) {
                InventoryManagement.viewMedicine();
    
                System.out.println("Select the ID of the medicine to add to the invoice (or type 0 to finish): ");
                int medicineID = Integer.parseInt(scan.nextLine());
    
                if (medicineID == 0) {
                    break;
                }
    
                if (!InventoryManagement.medicines.containsKey(medicineID)) {
                    System.out.println("Medicine not found.");
                    continue;
                }
    
                Medicine selectedMedicine = InventoryManagement.medicines.get(medicineID);
    
                // Check if the medicine is expired before dispensing
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
                LocalDate expiryDate = LocalDate.parse(selectedMedicine.getExpiryDate(), formatter);
                LocalDate today = LocalDate.now();
    
                if (expiryDate.isBefore(today)) {
                    System.out.println("This medicine has expired and cannot be dispensed.");
                    continue;  // Skip dispensing this expired medicine
                }
    
                // Check if prescription is required for the selected medicine
                if (selectedMedicine.getPrescription()) {
                    if (!selectedCustomer.getPrescription()) {
                        System.out.println("This medicine requires a prescription, but the selected customer does not have one.");
                        continue;  // Skip this medicine if the customer does not have a prescription
                    }
                }
    
                System.out.println("Enter quantity for " + selectedMedicine.getName() + ": ");
                int quantity = Integer.parseInt(scan.nextLine());
    
                if (selectedMedicine.getStock() < quantity) {
                    System.out.println("Insufficient stock for " + selectedMedicine.getName() + ".");
                    continue;
                }
    
                // Update stock
                selectedMedicine.setStock(selectedMedicine.getStock() - quantity);
    
                // Add to invoice details
                selectedMedicineIDs.add(medicineID);
                quantities.add(quantity);
                totalCost += selectedMedicine.getPrice() * quantity;
            }
    
            if (selectedMedicineIDs.isEmpty()) {
                System.out.println("No medicines selected.");
                return;
            }
    
            // Create Invoice
            int invoiceID = InvoiceManagement.generateInvoiceID();
            Invoice invoice = new Invoice(invoiceID, selectedCustomer.getName(), selectedMedicineIDs, quantities, totalCost);
            InvoiceManagement.addInvoice(invoice);
    
            // Add invoice ID to customer's purchase history
            selectedCustomer.getPurchaseHistory().add("Invoice ID: " + invoiceID);
    
            System.out.println("Invoice created successfully!");
            System.out.println("Invoice ID: " + invoiceID);
            System.out.println("Total Cost: " + totalCost);
    
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        } catch (NullPointerException e) {
            System.out.println("An error occurred: customer or medicine data is missing.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while dispensing medicine. Please try again.");
            e.printStackTrace(); // Optional for debugging purposes
        }
    }            
    public static void viewInvoice(Scanner scan) {
        System.out.println("======================================");
        System.out.println("View Invoices");
        System.out.println("======================================");
        System.out.println("1. View all invoices");
        System.out.println("2. View invoices for a specific customer");
        System.out.println("3. Search for a specific invoice by ID");
    
        int option;
        try {
            option = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid option. Please enter a number.");
            return;
        }
    
        switch (option) {
            case 1: // View all invoices
                try {
                    if (InvoiceManagement.getAllInvoices().isEmpty()) {
                        System.out.println("======================================");
                        System.out.println("No invoice records found.");
                        System.out.println("======================================");
                    } else {
                        System.out.println("======================================");
                        System.out.println("All Invoices:");
                        System.out.println("======================================");
                        for (Invoice invoice : InvoiceManagement.getAllInvoices()) {
                            invoice.displayInvoice();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred while retrieving invoices.");
                    e.printStackTrace();
                }
                break;
    
            case 2: // View invoices for a specific customer
                System.out.println("Enter the customer ID:");
                int customerId;
                try {
                    customerId = Integer.parseInt(scan.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid customer ID. Please enter a valid number.");
                    return;
                }
    
                try {
                    Customer customer = customers.get(customerId);
    
                    if (customer != null) {
                        if (customer.getPurchaseHistory().isEmpty()) {
                            System.out.println("======================================");
                            System.out.println("No invoice records found for " + customer.getName() + ".");
                            System.out.println("======================================");
                        } else {
                            System.out.println("Invoices for " + customer.getName() + ":");
                            for (String invoiceId : customer.getPurchaseHistory()) {
                                try {
                                    String numericId = invoiceId.replaceAll("[^0-9]", ""); // Extract numeric part
                                    int parsedInvoiceId = Integer.parseInt(numericId);
                                    Invoice invoice = InvoiceManagement.getInvoiceById(parsedInvoiceId);
    
                                    if (invoice != null) {
                                        invoice.displayInvoice();
                                    } else {
                                        System.out.println("Invoice ID " + parsedInvoiceId + " not found.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("======================================");
                                    System.out.println("Invalid invoice ID format: " + invoiceId);
                                    System.out.println("======================================");
                                }
                            }
                        }
                    } else {
                        System.out.println("======================================");
                        System.out.println("Customer not found.");
                        System.out.println("======================================");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred while retrieving customer invoices.");
                    e.printStackTrace();
                }
                break;
    
            case 3: // Search for a specific invoice by ID
                try {
                    if (InvoiceManagement.getAllInvoices().isEmpty()) {
                        System.out.println("======================================");
                        System.out.println("No existing invoice records.");
                        System.out.println("======================================");
                        return;
                    }
    
                    System.out.println("Enter the invoice ID:");
                    int invoiceId;
                    try {
                        invoiceId = Integer.parseInt(scan.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid invoice ID. Please enter a valid number.");
                        return;
                    }
    
                    Invoice invoice = InvoiceManagement.getInvoiceById(invoiceId);
    
                    if (invoice != null) {
                        System.out.println("======================================");
                        System.out.println("Invoice Details:");
                        System.out.println("======================================");
                        invoice.displayInvoice();
                    } else {
                        System.out.println("======================================");
                        System.out.println("No invoice record found for ID: " + invoiceId);
                        System.out.println("======================================");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred while searching for the invoice.");
                    e.printStackTrace();
                }
                break;
    
            default:
                System.out.println("======================================");
                System.out.println("Invalid option. Please try again.");
                System.out.println("======================================");
                break;
        }
    }                
}
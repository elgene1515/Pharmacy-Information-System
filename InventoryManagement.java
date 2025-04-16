import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.TreeSet;

public class InventoryManagement{

    public static HashMap<Integer, Medicine> medicines = new HashMap<>();
    private static Set<Integer> usedMedicinesIds = new TreeSet<>();
    
    static{
        medicines.put(1, new Medicine(1, "Aspirin", "Pain Reliever", true,  5.99f, 100, "11/30/24", "PharmaCo"));
        medicines.put(2, new Medicine(2, "Ibuprofen", "Anti-inflammatory", false,  8.49f, 150, "12/31/25", "MediCorp"));  
        medicines.put(3, new Medicine(3, "Paracetamol", "Fever Reducer", true , 3.49f, 200, "10/15/23", "HealthMax"));     
    
        usedMedicinesIds.add(1);
        usedMedicinesIds.add(2);
        usedMedicinesIds.add(3);
    };

    public static void inventory(Scanner scan, String role){
        boolean running = true;
        int option;

        while (running) {
            

            if (role.equals("Pharmacist")) {
                System.out.println("======================================");
                System.out.println("Inventory Management (" + role + ")" );
                System.out.println("======================================");
                System.out.println("1. View Medicine");    
                System.out.println("2. Add Medicine");
                System.out.println("3. Edit Medicine");
                System.out.println("4. Delete Medicine");  
                System.out.println("5. Search Medicine");
                System.out.println("6. Generate Reports(Expiration Dates)");
                System.out.println("7. Check Stock Alerts");
                System.out.println("8. Back to Main Menu");
                try {
                    option = Integer.parseInt(scan.nextLine());
                    switch (option) { 
                        case 1: 
                            viewMedicine();
                            break;
                        case 2: 
                            addMedicine(scan);
                            break;
                        case 3: 
                            editMedicine(scan);
                            break;
                        case 4: 
                            deleteMedicine(scan);
                            break;
                        case 5: 
                            searchMedicine(scan);
                            break;
                        case 6: 
                            generateReport();
                            break;
                        case 7:
                            generateLowStockReport();
                            break;
                        case 8: 
                            running = false;
                            break;
                        default: 
                            System.out.println("Invalid Option. Please Try Again.");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Option. Please Try Again.");
                } 
            }
            else if (role.equals("Assistant")) {
                System.out.println("======================================");
                System.out.println("Inventory Management (" + role + ")");
                System.out.println("======================================");
                System.out.println("1. View Medicine");      
                System.out.println("2. Search Medicine");
                System.out.println("3. Generate Reports(Expiration Dates)");
                System.out.println("4. Check Stock Alerts");
                System.out.println("5. Back to Main Menu");
                try {
                    option = Integer.parseInt(scan.nextLine());
                    switch (option) { 
                        case 1: 
                            viewMedicine();
                            break;
                        case 2: 
                            searchMedicine(scan);
                            break;
                        case 3: 
                            generateReport();
                            break;
                        case 4:
                            generateLowStockReport();
                            break;
                        case 5: 
                            running = false;
                            break;
                        default: 
                            System.out.println("Invalid Option. Please Try Again.");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Option. Please Try Again.");
                } 
            }

            
        }
    }

    public static int findNextMedicineID(){
        int id = 1;
        while(usedMedicinesIds.contains(id)){
            id++;
        }
        return id;
    }
    public static void viewMedicine() { 
        if (medicines.isEmpty()) {
            System.out.println("======================================");
            System.out.println("No Medicine Found.");
            System.out.println("======================================");
        } else {
            System.out.println("======================================");
            System.out.println("List of Medicines");
            System.out.println("======================================");
            for (Medicine medicine : medicines.values()) {
                medicine.displayMedicine(); 
            }
        }
    }
    public static void addMedicine(Scanner scan) {
        int id = 0;
        String name = "";
        String category = "";
        boolean prescription = false;
        float price = 0.0f;
        int stock = 0;
        String expiryDate = "01/01/00"; // Default expiry date
        String manufacturer = "";

        try {
            System.out.println("======================================");
            System.out.println("Add Medicine");
            System.out.println("======================================");

            id = findNextMedicineID(); // Auto-generate the ID
            
            System.out.print("Enter Name: ");
            name = scan.nextLine();
            if (name.trim().isEmpty()) {
                name = "Unknown";
            }
    
            System.out.print("Enter Category: ");
            category = scan.nextLine();
            if (category.trim().isEmpty()) {
                category = "Unknown";
            }

            System.out.print("Does it need prescription (yes / no)? ");
            String prescriptionInput = scan.nextLine().trim().toLowerCase();
            if (prescriptionInput.equals("yes")) {
                prescription = true;
            } else if (prescriptionInput.equals("no")) {
                prescription = false;
            } else {
                System.out.println("Invalid input! Defaulting to 'no'.");
                prescription = false; // Default to 'no' if the input is invalid
            }

            System.out.print("Enter Price: ");
            try {
                price = Float.parseFloat(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid price input! Defaulting to 0.0.");
                price = 0.0f; // Default to 0 if input is invalid
            }

            System.out.print("Enter Stock: ");
            try {
                stock = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid stock input! Defaulting to 0.");
                stock = 0; // Default to 0 if input is invalid
            }

            // Validate Expiry Date Format (MM/DD/YY)
            System.out.print("Enter Expiry Date (MM/DD/YY): ");
            expiryDate = scan.nextLine().trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
            try {
                // Try to parse the expiry date using the formatter
                LocalDate.parse(expiryDate, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid expiry date input! Defaulting to '01/01/00'.");
                expiryDate = "01/01/00"; // Default to "01/01/00" if the input is invalid
            }

            System.out.print("Enter Manufacturer: ");
            manufacturer = scan.nextLine();
            if (manufacturer.trim().isEmpty()) {
                manufacturer = "Unknown";
            }

            // Create and add the medicine object to the collection
            Medicine medicine = new Medicine(id, name, category, prescription, price, stock, expiryDate, manufacturer);
            medicines.put(id, medicine);
            usedMedicinesIds.add(id);

            System.out.println("Medicine added successfully!");
            medicine.displayMedicine();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding the medicine. Please try again.");
            e.printStackTrace(); // Print stack trace for debugging purposes (optional)
        }
    }          
    public static void editMedicine(Scanner scan) {
        int id;
        String name;
        String category;
        boolean prescription;
        float price;
        int stock;
        String expiryDate;
        String manufacturer;
    
        if (medicines.isEmpty()) {
            System.out.println("======================================");
            System.out.println("No Medicines to Edit.");
            System.out.println("======================================");
            return;
        }
    
        try {
            System.out.println("======================================");
            System.out.println("Edit Medicine");
            System.out.println("======================================");
            System.out.print("Enter the ID of the Medicine you want to edit: ");
            id = Integer.parseInt(scan.nextLine());
    
            if (medicines.containsKey(id)) {
                Medicine medicine = medicines.get(id);
                System.out.println("Current Details:");
                medicine.displayMedicine();
    
                System.out.print("Enter New Name (leave blank to keep current): ");
                name = scan.nextLine();
                if (!name.trim().isEmpty()) {
                    medicine.setName(name);
                }
    
                System.out.print("Enter New Category (leave blank to keep current): ");
                category = scan.nextLine();
                if (!category.trim().isEmpty()) {
                    medicine.setCategory(category);
                }
    
                System.out.print("Does it need prescription (yes/no, leave blank to keep current): ");
                String prescriptionInput = scan.nextLine().trim().toLowerCase();
                if (!prescriptionInput.isEmpty()) {
                    if (prescriptionInput.equals("yes")) {
                        prescription = true;
                        medicine.setPrescription(prescription);
                    } else if (prescriptionInput.equals("no")) {
                        prescription = false;
                        medicine.setPrescription(prescription);
                    } else {
                        System.out.println("Invalid input! Prescription status not changed.");
                    }
                }
    
                System.out.print("Enter New Price (leave blank to keep current): ");
                String priceInput = scan.nextLine();
                if (!priceInput.trim().isEmpty()) {
                    try {
                        price = Float.parseFloat(priceInput);
                        medicine.setPrice(price);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price input! Price not changed.");
                    }
                }
    
                System.out.print("Enter New Stock (leave blank to keep current): ");
                String stockInput = scan.nextLine();
                if (!stockInput.trim().isEmpty()) {
                    try {
                        stock = Integer.parseInt(stockInput);
                        medicine.setStock(stock);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid stock input! Stock not changed.");
                    }
                }
    
                // Expiry Date Validation
                System.out.print("Enter New Expiry Date (MM/DD/YY, leave blank to keep current): ");
                expiryDate = scan.nextLine();
                if (!expiryDate.trim().isEmpty()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
                    try {
                        // Validate and set the new expiry date
                        LocalDate.parse(expiryDate, formatter);
                        medicine.setExpiryDate(expiryDate);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid expiry date input! Defaulting to '01/01/00'.");
                        medicine.setExpiryDate("01/01/00");
                    }
                }
    
                System.out.print("Enter New Manufacturer (leave blank to keep current): ");
                manufacturer = scan.nextLine();
                if (!manufacturer.trim().isEmpty()) {
                    medicine.setManufacturer(manufacturer);
                }
    
                System.out.println("Medicine updated successfully!");
                medicine.displayMedicine();
            } else {
                System.out.println("Medicine with ID: " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID input! Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while editing the medicine. Please try again.");
            e.printStackTrace(); // For debugging purposes (optional)
        }
    }        
    public static void deleteMedicine(Scanner scan) {
        int id;
    
        if (medicines.isEmpty()) {
            System.out.println("======================================");
            System.out.println("No Medicine to Delete.");
            System.out.println("======================================");
            return;
        }
    
        try {
            System.out.println("======================================");
            System.out.println("Delete Medicine");
            System.out.println("======================================");
            System.out.print("Enter the ID of Medicine you want to Delete: ");
            id = Integer.parseInt(scan.nextLine());
    
            if (medicines.containsKey(id)) {
                medicines.remove(id);
                usedMedicinesIds.remove(id); // If you have this map for used IDs
                System.out.println("Medicine with ID: " + id + " has been successfully deleted.");
            } else {
                System.out.println("Medicine with ID: " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID input! Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while deleting the medicine. Please try again.");
            e.printStackTrace(); // Optional for debugging purposes
        }
    }    
    public static void searchMedicine(Scanner scan) {
        int id;
    
        if (medicines.isEmpty()) {
            System.out.println("======================================");
            System.out.println("No Medicine To Search.");
            System.out.println("======================================");
            return;
        }
    
        try {
            System.out.println("======================================");
            System.out.println("Search Medicine");
            System.out.println("======================================");
            System.out.print("Enter the ID of Medicine you want to Search: ");
            id = Integer.parseInt(scan.nextLine());
    
            if (medicines.containsKey(id)) {
                medicines.get(id).displayMedicine();
            } else {
                System.out.println("Medicine with ID: " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID input! Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while searching for the medicine. Please try again.");
            e.printStackTrace(); // Optional for debugging purposes
        }
    }    
    public static void generateReport() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate today = LocalDate.now();

        if (medicines.isEmpty()) {
            System.out.println("======================================");
            System.out.println("No Medicine Found.");
            System.out.println("======================================");
            return;
        }

        try {
            System.out.println("======================================");
            System.out.println("Report");
            System.out.println("======================================");

            for (Medicine medicine : medicines.values()) {
                try {
                    LocalDate expiry = LocalDate.parse(medicine.getExpiryDate(), formatter);

                    if (expiry.isBefore(today)) {
                        System.out.println("ID: " + medicine.getId() + " | NAME: " + medicine.getName() + " | STATUS: EXPIRED.");
                    } else if (expiry.isBefore(today.plusMonths(3))) {
                        System.out.println("ID: " + medicine.getId() + " | NAME: " + medicine.getName() + " | STATUS: EXPIRING SOON.");
                    } else {
                        System.out.println("ID: " + medicine.getId() + " | NAME: " + medicine.getName() + " | STATUS: NOT EXPIRED.");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid expiry date format for medicine ID: " + medicine.getId() + " | NAME: " + medicine.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while generating the report.");
            e.printStackTrace(); // Optional for debugging purposes
        }
    }
    public static void generateLowStockReport() {
        int lowStockThreshold = 5; // Define the threshold for low stock
    
        if (medicines.isEmpty()) {
            System.out.println("======================================");
            System.out.println("No Medicine Found.");
            System.out.println("======================================");
        } else {
            boolean lowStockFound = false;
            System.out.println("======================================");
            System.out.println("Low Stock Report");
            System.out.println("======================================");
            for (Medicine medicine : medicines.values()) {
                if (medicine.getStock() < lowStockThreshold) { // Check if stock is below the threshold
                    if (!lowStockFound) {
                        lowStockFound = true; // Flag to indicate at least one low-stock medicine was found
                    }
                    System.out.println("Medicine: " + medicine.getName());
                    System.out.println("Stock: " + medicine.getStock());
                }
            }
    
            if (!lowStockFound) {
                System.out.println("No medicines are low on stock.");
            }
        }
    }         
}

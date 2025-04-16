import java.util.ArrayList;
import java.util.HashMap;

public class InvoiceManagement {
    public static HashMap<Integer, Invoice> invoices = new HashMap<>();
    private static int currentInvoiceID = 1;

    public static int generateInvoiceID() {
        return currentInvoiceID++;
    }

    public static void addInvoice(Invoice invoice) {
        invoices.put(invoice.getInvoiceID(), invoice);
    }
    public static void viewInvoices() {
        if (invoices.isEmpty()) {
            System.out.println("No invoices found.");
        } else {
            for (Invoice invoice : invoices.values()) {
                invoice.displayInvoice();
            }
        }
    }
    public static ArrayList<Invoice> getAllInvoices() {
        return new ArrayList<>(invoices.values());
    }
    public static Invoice getInvoiceById(int invoiceID) {
        return invoices.get(invoiceID); // Returns null if invoiceID is not found
    }
}

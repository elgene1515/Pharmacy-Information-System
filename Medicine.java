public class Medicine {
    private int id;
    private String name;
    private String category;
    private boolean prescription;
    private float price;
    private int stock;
    private String expiryDate;
    private String manufacturer; 

    public Medicine() {
        this.id = 0;
        this.name = "Unknown";
        this.category = "Unknown";
        this.price = 0; 
        this.stock = 0;
        this.expiryDate = "01/01/00";
        this.manufacturer = "Unknown";
    }

    public Medicine(int id, String name, String category, boolean prescription, float price, int stock, String expiryDate, String manufacturer) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.prescription = prescription;
        this.price = price;
        this.stock = stock;
        this.expiryDate = expiryDate;
        this.manufacturer = manufacturer; 
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public int getStock() {
        return stock; 
    }

    public boolean getPrescription() {
        return prescription;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrescription(boolean prescription) {
        this.prescription = prescription;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void displayMedicine() {
        // Prepare prescription status
        String prescriptionStatus = prescription ? "PRESCRIBED" : "NOT PRESCRIBED";

        // Display medicine details
        System.out.println(
            "ID: " + id + " | " +
            "NAME: " + name + " | " +
            "CATEGORY: " + category + " | " +
            "PRESCRIPTION: " + prescriptionStatus + " | " +
            "PRICE: " + price + " | " +
            "STOCK: " + stock + " | " +
            "EXPIRATION DATE (MM/DD/YY): " + expiryDate + " | " +
            "MANUFACTURER: " + manufacturer
        );
    }
}
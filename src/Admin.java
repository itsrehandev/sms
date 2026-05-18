public class Admin {
    private final String adminId;
    private String adminName;
    private String shopName;
    private String email;
    private static int totalAdmins = 0;

    // Constructor 1: Default
    public Admin() {
        this.adminId = "ADMIN000";
        this.adminName = "Unknown";
        this.shopName = "Unknown Shop";
        this.email = "unknown@email.com";
        totalAdmins++;
    }

    // Constructor 2: Basic info
    public Admin(String adminId, String adminName, String shopName) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.shopName = shopName;
        this.email = "admin@email.com";
        totalAdmins++;
    }

    // Constructor 3: Complete info
    public Admin(String adminId, String adminName, String shopName, String email) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.shopName = shopName;
        this.email = email;
        totalAdmins++;
    }

    // Getters
    public String getAdminId() {
        return adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getShopName() {
        return shopName;
    }

    public String getEmail() {
        return email;
    }

    public static int getTotalAdmins() {
        return totalAdmins;
    }

    // Setters
    public void setAdminName(String adminName) {
        if (!adminName.isEmpty()) {
            this.adminName = adminName;
        } else {
            System.out.println("Admin name cannot be empty!");
        }
    }

    public void setShopName(String shopName) {
        if (!shopName.isEmpty()) {
            this.shopName = shopName;
        } else {
            System.out.println("Shop name cannot be empty!");
        }
    }

    public void setEmail(String email) {
        if (!email.isEmpty()) {
            this.email = email;
        } else {
            System.out.println("Email cannot be empty!");
        }
    }

    // Display admin info
    public void displayAdminInfo() {
        System.out.println("\n========== ADMIN INFO ==========");
        System.out.println("Admin ID: " + adminId);
        System.out.println("Admin Name: " + adminName);
        System.out.println("Shop Name: " + shopName);
        System.out.println("Email: " + email);
        System.out.println("================================\n");
    }

    // CSV format
    public String toCSV() {
        return adminId + "," + adminName + "," + shopName + "," + email;
    }
}


public class Admin {
    private final String adminId;
    private String adminName;
    private String shopName;
    private String email;
    private static int totalAdmins = 0;

    public Admin() {
        this.adminId = "null";
        this.adminName = "Unknown";
        this.shopName = "Unknown Shop";
        this.email = "abc@gmail.com";
        totalAdmins++;
    }

    public Admin(String adminId, String adminName, String shopName) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.shopName = shopName;
        this.email = "admin@email.com";
        totalAdmins++;
    }

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
            this.adminName = adminName;
    }

    public void setShopName(String shopName) {
            this.shopName = shopName;
    }

    public void setEmail(String email) {
            this.email = email;
    }

    public String toCSV() {
        return adminId + "," + adminName + "," + shopName + "," + email;
    }
}


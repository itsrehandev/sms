import java.util.ArrayList;

public class InputValidator {
    
    // Validate if string is not empty
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
    
    // Validate if string is valid ID format (alphanumeric)
    public static boolean isValidId(String input) {
        return isNotEmpty(input) && input.matches("^[A-Za-z0-9]+");
    }
    
    // Validate if product ID exists in inventory
    public static boolean productExists(String productId, Inventory inventory) {
        return inventory.searchProduct(productId) != null;
    }
    
    // Validate sufficient stock
    public static boolean hasSufficientStock(String productId, int requiredQty, Inventory inventory) {
        Product p = inventory.searchProduct(productId);
        return p != null && p.getQuantity() >= requiredQty;
    }

    // Validate discount percentage (0-100)
    public static boolean isValidDiscount(double discount) {
        return discount >= 0 && discount <= 100;
    }
}


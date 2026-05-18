import java.util.ArrayList;

public class InputValidator {
    
    // Validate if string is not empty
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
    
    // Validate if string is valid ID format (alphanumeric)
    public static boolean isValidId(String input) {
        return isNotEmpty(input) && input.matches("^[A-Za-z0-9]+$");
    }
    
    // Validate if number is positive
    public static boolean isPositive(double number) {
        return number > 0;
    }
    
    // Validate if integer is non-negative
    public static boolean isNonNegative(int number) {
        return number >= 0;
    }
    
    // Validate if quantity is valid (positive integer)
    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }
    
    // Validate if price is valid (positive double)
    public static boolean isValidPrice(double price) {
        return price > 0;
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

    // Validate email format
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Validate discount percentage (0-100)
    public static boolean isValidDiscount(double discount) {
        return discount >= 0 && discount <= 100;
    }
}


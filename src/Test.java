import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        
        System.out.println("Welcome to Sales Management System");
        Product shirt = new Product("P001", "Shirt", 300, 500, 50, "Clothing");
        Product pants = new Product("P002", "Pants");
        pants.setSellingPrice(800);
        System.out.println("Current Stock");
        shirt.printProductInfo();
        pants.printProductInfo();
// I am mujahid here
// Testing Github
    }
}
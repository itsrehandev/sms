import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        System.out.println("Welcome to Sales Management System");

        // PRODUCT TESTING
        Product shirt = new Product("P001","Shirt",300,500,50,"Clothing");

        Product pants = new Product("P002", "Pants");
        pants.setSellingPrice(800);

        System.out.println("\nCurrent Stock");
        shirt.printProductInfo();
        pants.printProductInfo();

        // STOCK METHODS
        shirt.increaseStock(10);

        boolean sold = shirt.decreaseStock(20);

        if(sold) {
            System.out.println("Stock Reduced Successfully");
        }
        else {
            System.out.println("Not Enough Stock");
        }

        shirt.printProductInfo();

        // CUSTOMER TESTING
        Customer customer1 = new Customer(
                "C001",
                "Ali",
                "03001234567",
                "Rawalpindi"
        );

        customer1.printCustomerInfo();

        customer1.setAddress("Islamabad");

        System.out.println("\nAfter Address Update:");
        customer1.printCustomerInfo();

        // CSV TEST
        System.out.println("\nProduct CSV:");
        System.out.println(shirt.toCSV());

        System.out.println("\nCustomer CSV:");
        System.out.println(customer1.toCSV());
    }
}
// For future : Like Shop owners can register regular customer and manage their
// records like a khata system. where customer pays
// at the end of the month or in advance and they buy for "free" throughout the month.
public class Customer
{
    private final String customerId;
    private String name;
    private String phoneNumber;
    private String address;
    private static int totalCustomers = 0;

    public Customer()
    {
        customerId = "C000";
        name = "Unknown";
        phoneNumber = "";
        address = "";
        totalCustomers++;
    }

    public Customer(String customerId, String name, String phoneNumber, String address)
    {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        totalCustomers++;
    }

    public String getCustomerId()
    {
        return customerId;
    }
    public String getName()
    {
        return name;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getAddress()
    {
        return address;
    }
    public static int getTotalCustomer()
    {
        return totalCustomers;
    }

    public void setName(String name)
    {
        if(!name.isEmpty())
        {
            this.name = name;
        }
        else
        {
            System.out.println("Name cannot be empty");
        }
        
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public void printCustomerInfo()
    {
        System.out.println("\n========== Customer INFO ==========");
        System.out.println("Customer ID: "+customerId);
        System.out.println("Customer Name: "+name);
        System.out.println("Customer Phone Number: "+phoneNumber);
        System.out.println("Customer Address: "+address);
        System.out.println("Total Customers: "+totalCustomers);
        System.out.println("==================================\n");
    }

    public String toCSV()
    {
        return customerId+ "," +name+ "," +phoneNumber+ "," +address;
    }


    


}

public class Customer 
{
    private final String customerId;
    private String name;
    private String phoneNnumber;
    private String address;
    private static int totalCustomers = 0;

    public Customer()
    {
        customerId = "C000";
        name = "Unknown";
        phoneNnumber = "";
        address = "";
        totalCustomers++;
    }

    public Customer(String customerId, String name, String phoneNumber, String address)
    {
        this.customerId = customerId;
        this.name = name;
        this.phoneNnumber = phoneNumber;
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
        return phoneNnumber;
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
        this.phoneNnumber = phoneNumber;
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
        System.out.println("Customer Phone Number: "+phoneNnumber);
        System.out.println("Customer Address: "+address);
        System.out.println("Total Customers: "+totalCustomers);
        System.out.println("==================================\n");
    }

    public String toCSV()
    {
        return customerId+ "," +name+ "," +phoneNnumber+ "," +address;
    }


    


}

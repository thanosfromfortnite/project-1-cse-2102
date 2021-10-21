import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class CustomerProfInterface {
    private String fileName;
    private CustomerProfDB database;

    public CustomerProfInterface(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        database = new CustomerProfDB(this.fileName);
    }

    public boolean getUserChoice() throws IOException {
        System.out.println("What is your admin ID?");
        Scanner scan = new Scanner(System.in);
        String id = scan.nextLine(); // input for ID
        
        System.out.println("Using ID " + id);
        
        System.out.println("Here are your choices:\n" +
                "1. Enter a New CustomerProf\n" +
                "2. Delete a customer by Name and adminID\n" +
                "3. Find and display a CustomerProf by name and adminID\n" +
                "4. CustomerProf Modifications\n" +
                "5. Display all profiles\n" +
                "6. Write to database\n" +
                "0. Exit\n");

        String choice = scan.nextLine();
        
        
        
        
        if (choice.equals("1"))
            // enter a new customer profile
            database.insertNewProfile(createNewCustomerProf());
        else if (choice.equals("2"))
            // delete customer profile
            deleteCustomerProf();
        else if (choice.equals("3"))
            // find profile
            findCustomerProf();
        else if (choice.equals("4"))
            // customer prof modifications
            updateCustomerProf();
        else if (choice.equals("5"))
            // display all profiles
            displayAllCustomerProf();
        else if (choice.equals("6"))
            // write to database
            writeToDB();
        if (choice.equals("0"))
        	return false;
        return true;
    }

    public CustomerProf createNewCustomerProf() {
        System.out.println("Please write the admin ID:");
        Scanner scan = new Scanner(System.in);
        String adminID = scan.nextLine();

        System.out.println("Please write the first name:");
        String firstName = scan.nextLine();

        System.out.println("Please write the last name:");
        String lastName = scan.nextLine();

        System.out.println("Please write the address:");
        String address = scan.nextLine();

        System.out.println("Please write the phone number:");
        String phone = scan.nextLine();

        System.out.println("Please write the status:");
        String status = scan.nextLine();

        System.out.println("Please write the use:");
        String use = scan.nextLine();

        System.out.println("Please write the income:");
        float income = scan.nextFloat();
        
        VehicleInfo vehicle = createNewVehicleInfo();
        

        return new CustomerProf(adminID, firstName, lastName, address, phone, income, status, use, vehicle);

    }

    public VehicleInfo createNewVehicleInfo() {
        System.out.println("Please write the model:");
        Scanner scan = new Scanner(System.in);
        String model = scan.nextLine();

        System.out.println("Please write the type:");
        String type = scan.nextLine();

        System.out.println("Please write the year:");
        String year = scan.nextLine();

        System.out.println("Please write the method of obtaining:");
        String method = scan.nextLine();
        
        

        return new VehicleInfo(model, year, type, method);
    }

    public void deleteCustomerProf() {
        System.out.println("Please write the last name:");
        Scanner scan = new Scanner(System.in);
        String lastName = scan.nextLine();

        System.out.println("Please write the admin ID:");
        String adminID = scan.nextLine();
        
        

        boolean success = database.deleteProfile(adminID, lastName);
        
        
        if (success)
            System.out.println("Successfully deleted profile.");
        else
            System.out.println("Could not delete profile.");
    }

    public void findCustomerProf() {
        System.out.println("Please write the last name:");
        Scanner scan = new Scanner(System.in);
        String lastName = scan.nextLine();

        System.out.println("Please write the admin ID:");
        String adminID = scan.nextLine();
        
        

        CustomerProf found = database.findProfile(adminID, lastName);
        if (found == null)
            System.out.println("Could not find profile.");
        else
            displayCustomerProf(found);
    }

    public void displayCustomerProf(CustomerProf input) {
        System.out.printf("Name: %s %s\nAddress: %s\nPhone Number: %s\nStatus: %s\nUse: %s\nIncome: %f\n",
                input.getFirstName(), input.getLastName(), input.getAddress(), input.getPhone(), input.getStatus(), input.getUse(), input.getIncome());
        VehicleInfo vehicle = input.getVehicleInfo();
        System.out.printf("Vehicle Model: %s\nVehicle Type: %s\nMethod of obtaining: %s\nYear of production: %s\n\n",
                vehicle.getModel(), vehicle.getType(), vehicle.getMethod(), vehicle.getYear());
    }

    public void updateCustomerProf() {
        System.out.println("Please write the last name:");
        Scanner scan = new Scanner(System.in);
        String lastName = scan.nextLine();

        System.out.println("Please write the admin ID:");
        String adminID = scan.nextLine();

        CustomerProf update = database.findProfile(lastName, adminID);

        System.out.println("Please select which one to modify:\n" +
                "1. Address\n" +
                "2. Phone\n" +
                "3. Use\n" +
                "4. Status\n" +
                "5. Vehicle Model\n" +
                "6. Vehicle Year\n" +
                "7. Vehicle Type\n" +
                "8. Vehicle Method of Obtaining\n");
        String choice = scan.nextLine();
        
        switch (choice) {
            case "1":
                System.out.println("Enter new address:");
                update.updateAddress(scan.nextLine());
            case "2":
                System.out.println("Enter new phone number:");
                update.updatePhone(scan.nextLine());
            case "3":
                System.out.println("Enter new use:");
                update.updateUse(scan.nextLine());
            case "4":
                System.out.println("Enter new status:");
                update.updateStatus(scan.nextLine());
            case "5":
                System.out.println("Enter new vehicle model:");
                update.getVehicleInfo().updateModel(scan.nextLine());
            case "6":
                System.out.println("Enter new vehicle year:");
                update.getVehicleInfo().updateYear(scan.nextLine());
            case "7":
                System.out.println("Enter new vehicle type:");
                update.getVehicleInfo().updateType(scan.nextLine());
            case "8":
                System.out.println("Enter new vehicle method:");
                update.getVehicleInfo().updateMethod(scan.nextLine());
        }
        
    }

    public void displayAllCustomerProf() {
        System.out.println("Please write the ID:");
        Scanner scan = new Scanner(System.in);
        String adminID = scan.nextLine();
        
        
        CustomerProf current = database.findNextProfile();
        while (current != null) {
            if (current.getAdminID().equals(adminID)) {
                displayCustomerProf(current);
            }
            current = database.findNextProfile();
        }
        
    }

    public void writeToDB() throws IOException {
        database.writeAllCustomerProf();
    }
}

import java.util.*;
import java.io.*;
public class CustomerProfDB {
    private List<CustomerProf> profiles;
    private int profileCounter;
    private int numCustomers;
    String fileName;

    public CustomerProfDB(String fileName) throws FileNotFoundException {
        profiles = new ArrayList<>();
        profileCounter = 0;
        this.fileName = fileName;
        initializeDB(fileName);
        numCustomers = profiles.size();
    }

    public void initializeDB(String filename) throws FileNotFoundException {
    	Scanner scan = new Scanner(new File(filename));
    	while (scan.hasNextLine()) { // while there is still data left to be read in the file
    		String[] vars = scan.nextLine().split(",");
    		// creates an array of all of the values separated by commas in the file.
    		// this assumes the file is formatted correctly 
    		CustomerProf newCustomer = new CustomerProf(
    				vars[0], // adminID
    				vars[1], // first name
    				vars[2], // last name
    				vars[3], // address
    				vars[4], // phone number
    				Float.parseFloat(vars[5]), // income (float)
    				vars[6], // status
    				vars[7], // use
    				new VehicleInfo(
    						vars[8], // model
    						vars[9], // year
    						vars[10], // type
    						vars[11]) // method
    				);
    		if (findProfile(newCustomer.getAdminID(), newCustomer.getLastName()) == null) { // if profile does not exist
    			insertNewProfile(newCustomer);
    		}
    		else {
    			// do nothing
    		}
    	}
    }

    public void insertNewProfile(CustomerProf insert) { 
        profiles.add(insert);
        numCustomers ++;
    }

    public CustomerProf findProfile(String adminID, String lastName) {
        for (CustomerProf i: profiles) {
            if (i.getAdminID().equals(adminID) && i.getLastName().equals(lastName)) {
                return i;
            }
        }
        return null;
    }

    public boolean deleteProfile(String adminID, String lastName) {
        CustomerProf x = findProfile(adminID, lastName);
        if (x == null) {
            return false;
        }
        else {
            profiles.remove(x);
            numCustomers --;
            return true;
        }
    }

    public CustomerProf findFirstProfile() {
        if (profiles.isEmpty()) {
            return null;
        }
        return profiles.get(0);
    }

    public CustomerProf findNextProfile() {
        if (profiles.isEmpty()) {
            return null;
        }
        if (profileCounter < numCustomers) {
            CustomerProf output = profiles.get(profileCounter);
            profileCounter ++;
            return output;
        }
        return null;
    }

    public void writeAllCustomerProf() throws IOException {
    	FileWriter fw = new FileWriter(fileName);
    	File file = new File(fileName);
    	// THIS WILL CLEAR THE FILE before writing to it again!
		if (file.exists() && file.isFile()) {
			file.delete();
		}
		file.createNewFile();
		
    	for (CustomerProf i: profiles) {
    		String output = ""; // creates a string to compile all of the data into a CSV format
    		output += i.getAdminID() + ",";
    		output += i.getFirstName() + ",";
    		output += i.getLastName() + ",";
    		output += i.getAddress() + ",";
    		output += i.getPhone() + ",";
    		output += i.getIncome() + ",";
    		output += i.getStatus() + ",";
    		output += i.getUse() + ",";
    		output += i.getVehicleInfo().getModel() + ",";
    		output += i.getVehicleInfo().getYear() + ",";
    		output += i.getVehicleInfo().getType() + ",";
    		output += i.getVehicleInfo().getMethod();
    		
    		
    		fw.write(output); // writes the output to the file
    	}
    	fw.close();
    }



}

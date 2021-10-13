import java.util.*;
public class CustomerProfDB  {
    private List<CustomerProf> profiles;
    private int profileCounter;
    private int numCustomers;
    String filename;

    public CustomerProfDB(String filename) {
        profiles = new ArrayList<>();
        profileCounter = 0;
        this.filename = filename;
        initializeDB(filename);
        numCustomers = profiles.size();
    }

    public void initializeDB(String filename) {

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

    public void writeAllCustomerProf() {

    }



}

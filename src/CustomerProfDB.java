import java.util.*;
public class CustomerProfDB  {
    private List<CustomerProf> profiles;
    private int profileCounter;

    public CustomerProfDB(String filename) {
        profiles = new ArrayList<>();
        profileCounter = 0;
        initializeDB(filename);
    }

    public void initializeDB(String filename) {

    }

    public void insertNewProfile(CustomerProf insert) {
        profiles.add(insert);
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
        if (profileCounter < profiles.size()) {
            CustomerProf output = profiles.get(profileCounter);
            profileCounter ++;
            return output;
        }
        return null;
    }


}

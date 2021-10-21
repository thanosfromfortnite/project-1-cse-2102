import java.io.IOException;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		String directory = "D:\\eclipse\\workspace\\project-1-cse-2102\\src\\";
		String fileName = "customers.txt";
		CustomerProfInterface inter = new CustomerProfInterface(directory + fileName);
		
		while (inter.getUserChoice()) {
			
		}
		
	}
}

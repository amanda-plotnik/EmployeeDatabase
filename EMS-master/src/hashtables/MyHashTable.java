package hashtables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.File;
import java.util.*;

public class MyHashTable {

	public ArrayList<EmployeeInfo>[] buckets;
	private int k;

	public MyHashTable(int numBuckets) {
		k = numBuckets;
		buckets = new ArrayList[k];
		for (int i = 0; i < k; i++) {
			buckets[i] = new ArrayList<EmployeeInfo>();
		}
	}

	//Main methods (add, remove, search)
	public void addEmployee(EmployeeInfo toAdd) {
		int buck = toAdd.getEmpNumber() % k;
		buckets[buck].add(toAdd);
	}

	public boolean removeEmployee(EmployeeInfo toSack) {
		int buck = toSack.getEmpNumber() % k;
		for (int i = 0; i < buckets[buck].size(); i++) {
			if (buckets[buck].get(i).getEmpNumber() == toSack.getEmpNumber()) {
				buckets[buck].remove(i);
			}
		}
		return true;
	}

	public EmployeeInfo search(int toFind) {
		int buck = toFind % k;
		EmployeeInfo found = null;
		int position = -1;
		for (int i = 0; i < buckets[buck].size(); i++) {
			if (buckets[buck].get(i).getEmpNumber() == toFind) {
				position = i;
				found = buckets[buck].get(i);
			}
		}
		System.out.println("Employee Number " + toFind + " can be found in bucket " + buck + ", position " + position);
		return found;
	}

	//Other methods (display, read/write from file)
	public void displayContents() {

		// Print the employee numbers for the employees stored in each bucket's ArrayList, starting with bucket 0, then bucket 1, and so on.
		for (int i = 0; i < buckets.length; i++) {

			// For the current bucket, print out the employee number for each item in its ArrayList.
			System.out.println("\nExamining the ArrayList for bucket " + i);
			int listSize = buckets[i].size();
			if (listSize == 0) {
				System.out.println("  Nothing in its ArrayList!");
			} else {
				for (int j = 0; j < listSize; j++) {
					int theEmpNum = buckets[i].get(j).getEmpNumber();
					System.out.println("  Employee " + theEmpNum);
				}
			}

		}

	}

	public void readFromFile() {
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("dataTextFile.txt"));

			String line;
			while ((line = reader.readLine()) != null) {
				if (line.equals("Part Time Employee")) {
					line = reader.readLine(); // reads employee number
					int empNum = Integer.parseInt(line);
					line = reader.readLine(); // reads first name
					String firstName = line;
					line = reader.readLine(); // reads last name
					String lastName = line;
					line = reader.readLine(); // reads deduct rate
					Double deductRate = Double.parseDouble(line);
					line = reader.readLine(); // reads gender
					Gender gender = Gender.valueOf(line);
					line = reader.readLine(); // reads location
					Location location = Location.valueOf(line);
					line = reader.readLine(); // reads hourly wage
					Double hourlyWage = Double.parseDouble(line);
					line = reader.readLine(); // reads hours worked per week
					Double hoursPerWeek = Double.parseDouble(line);
					line = reader.readLine(); // reads weeks worked per year
					Double weeksPerYear = Double.parseDouble(line);
					EmployeeInfo theEmployee = new PartTimeEmployee(empNum, firstName, lastName, gender, location,
							deductRate, hourlyWage, hoursPerWeek, weeksPerYear);
					addEmployee(theEmployee);
				} else if (line.equals("Full Time Employee")) {
					line = reader.readLine(); // reads employee number
					int empnum = Integer.parseInt(line);
					line = reader.readLine(); // reads first name
					String firstname = line;
					line = reader.readLine(); // reads last name
					String lastname = line;
					line = reader.readLine(); // reads deduct rate
					double deductrate = Double.parseDouble(line);
					line = reader.readLine(); // reads gender
					Gender gender = Gender.valueOf(line); // converts the string value gender into the Gender enum
					line = reader.readLine(); // reads location
					Location location = Location.valueOf(line); // converts the string value location into the Location enum
					line = reader.readLine();
					double yearlySalary = Double.parseDouble(line);
					EmployeeInfo theEmployee = new FullTimeEmployee(empnum, firstname, lastname, gender, location,
							deductrate, yearlySalary);
					addEmployee(theEmployee);

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToFile() {
		try {
			PrintWriter writer = new PrintWriter("dataTextFile.txt");
			for (int i = 0; i < buckets.length; i++) {
				int listSize = buckets[i].size();
				if (listSize == 0) {
					System.out.println("  Nothing in its ArrayList!");
				} else if (listSize != 0) {
					for (int j = 0; j < listSize; j++) {
						EmployeeInfo someEmployee = buckets[i].get(j); // Gets employee
						if (someEmployee instanceof PartTimeEmployee) {
							writer.println("Part Time Employee"); // write PART TIME EMPLOYEE
						} else if (someEmployee instanceof FullTimeEmployee) {
							writer.println("Full Time Employee");
						}
						writer.println(someEmployee.getEmpNumber()); // Write EMPLOYEE NUMBER
						writer.println(someEmployee.getFirstName()); // Writes FIRST NAME
						writer.println(someEmployee.getLastName()); // Writes LAST NAME
						writer.println(someEmployee.getDeductRate()); // Writes DEDUCT RATE
						writer.println(someEmployee.getGender()); // Writes GENDER
						writer.println(someEmployee.getLocation()); // Writes LOCATION
						if (someEmployee instanceof PartTimeEmployee) {
							writer.println(((PartTimeEmployee) someEmployee).getHourlyWage()); // Writes HOURLY WAGE
							writer.println(((PartTimeEmployee) someEmployee).getHoursPerWeek()); // Writes HOURS WORKED PER WEEK
							writer.println(((PartTimeEmployee) someEmployee).getWeeksPerYear()); // Writes WEEKS WORKED PER WEEK
						} else if (someEmployee instanceof FullTimeEmployee) {
							writer.println(((FullTimeEmployee) someEmployee).getYearlySalary()); // Writes YEARLY SALARY
						}
					}
				}
			}
			writer.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "File Not Found");
		}
	}

	public void displayToTable(ArrayList<EmployeeInfo> b) {
		for (int i = 0; i < buckets.length; i++) {
			int listSize = buckets[i].size();
			if (listSize == 0) {
				System.out.println("  Nothing in its ArrayList!");
			} else {
				for (int j = 0; j < listSize; j++) {
					EmployeeInfo someEmployee = buckets[i].get(j);
					System.out.println(someEmployee.getEmpNumber());
					b.add(someEmployee); // f
				}
			}
		}
	}

}

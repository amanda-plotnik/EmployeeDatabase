package hashtables;

public class HashTableTest {

	public static void main(String[] args) {

		MyHashTable testTable = new MyHashTable(2);
		EmployeeInfo someEmp;

		someEmp = new EmployeeInfo(111111, "Ahmad", "Almawi", Gender.MALE, Location.MISSISSAUGA, 0.25);
		testTable.addEmployee(someEmp);

		someEmp = new EmployeeInfo(222222, "Amanda", "Plotnik", Gender.FEMALE, Location.MISSISSAUGA, 0.25);
		testTable.addEmployee(someEmp);

		someEmp = new EmployeeInfo(186484, "Lola", "Bunny", Gender.FEMALE, Location.BRAMPTON, 0.20);
		testTable.addEmployee(someEmp);

		someEmp = new EmployeeInfo(888885, "Bugs", "Bunny", Gender.MALE, Location.TORONTO, 0.21);
		testTable.addEmployee(someEmp);

		someEmp = new EmployeeInfo(795306, "Marvin", "Martian", Gender.OTHER, Location.MISSISSAUGA, 0.15);
		testTable.addEmployee(someEmp);

		someEmp = new EmployeeInfo(390217, "Sylvester", "Cat", Gender.MALE, Location.BRAMPTON, 0.27);
		testTable.addEmployee(someEmp);

		testTable.displayContents();
		testTable.search(222222);
		testTable.search(111111);
		testTable.removeEmployee(someEmp);
		testTable.displayContents();

	}

}
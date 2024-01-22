import java.io.IOException;
import java.util.*;

class UserDAO extends SerializationAbstract{

	private static final String jsonFilePath = "userData.json";
	private ArrayList<ArrayList<String>> database;
	static int count;
	{
		try {
			database = deserializeFromFile(jsonFilePath, ArrayList.class);
		} catch (IOException | ClassCastException | ClassNotFoundException a) {
			database = new ArrayList<>();
			System.out.println("Database down");
		}
    }
	public void create(String name, String surname, String email, String dob, String ageCalculate, String id) {

        int ageOutput = AgeCalculator.calculate(String.valueOf(ageCalculate));
        String age = String.valueOf(ageOutput);
        count++;
		id = String.valueOf(count);
		ArrayList<String> row = (ArrayList<String>) Arrays.asList(name, surname, email, dob, ageCalculate, id);
        database.add(row);

        System.out.print("Hello " + name + " " + surname + " your details have been saved to the database.");
        System.out.println();

        trySerialization(database ,jsonFilePath ,name);

    }

	public void update(String email, String name, String surname, String dob) {

		boolean updated = false;
        for (ArrayList<String> strings : database) {
            if (strings != null && strings.size() > 2 && email.equals(strings.get(2))) {
                strings.set(0, name);
                strings.set(1, surname);
                strings.set(3, dob);
                updated = true;
                break;
            }
        }
		if (updated) {
			serialization(database, jsonFilePath);
		} else {
			System.out.println("User not found in the database.");
		}
    }

	public void delete(String email) {

		for (int i = 0; i < database.size(); i++) {
			if ((database.get(i) != null && database.get(i).size() > 2) && (email.equals(database.get(i).get(2)))) {
				try {
					database.remove(i);
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Invalid input");
				}
				try {
					serialiseToFile(database, jsonFilePath);
				} catch (IOException a) {
					System.out.println();
				}
				return;
			}
		}
		System.out.println("User not found in the database.");
	}

	public ArrayList<ArrayList<String>> findAll() {

		ArrayList<ArrayList<String>> tableData = new ArrayList<>();
		List<String> headerRow = Arrays.asList("Name","Surname","Email","Date of Birth","Age","ID");
		tableData.add((ArrayList<String>) headerRow);

		for (ArrayList<String> user : database) {
			if (user != null) {
				tableData.add(new ArrayList<>(user));
			}
		}
		return tableData;
	}

	public ArrayList<String> getUserByEmail(String email)
	{
		for (ArrayList<String> user : database)
		{
			if (user != null && user.size() > 2 && email.equals(user.get(2))) {
				return user;
			}
		}
		return null;
	}
}

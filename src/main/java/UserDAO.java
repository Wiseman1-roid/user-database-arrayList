import java.io.IOException;
import java.util.*;

class UserDAO extends SerializationAbstract{

	private static final String jsonFilePath = "userData.json";
	private static int position = 0;
	private ArrayList<ArrayList<String>> database;

	{
		try {
			ArrayList<ArrayList<String>> database = deserializeFromFile(jsonFilePath, ArrayList.class);
		} catch (IOException e) {
			database = new ArrayList<>();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
        }
    }
	public void create(String name, String surname, String email, String dob, String ageCalculate, String id) {

		int ageOutput = AgeCalculator.calculate(ageCalculate);
		String age = String.valueOf(ageOutput);
		ArrayList<String> row = new ArrayList<>();
		row.add(name);
		row.add(surname);
		row.add(email);
		row.add(dob);
		row.add(age);
		row.add(id);

		database.add(row);
		position++;

		System.out.print("Hello " + name + " " + surname + " your details have been saved to the database.");
		System.out.println();

		try {
			serialiseToFile(database, jsonFilePath);
			System.out.println("User " + name + " has been saved to " + jsonFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			try {
				serialiseToFile(database, jsonFilePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("User not found in the database.");
		}
	}

	public void delete(String email) {

		for (int i = 0; i < database.size(); i++) {
			if ((database.get(i) != null && database.get(i).size() > 2) && (email.equals(database.get(i).get(2)))) {
				database.remove(i);
				try {
					serialiseToFile(database, jsonFilePath);
					System.out.println("User successfully deleted");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		System.out.println("User not found in the database.");
	}

	public ArrayList<ArrayList<String>> findAll() {

		int userCount = 0;
		for (ArrayList<String> user : database) {
			if (user != null) {
				userCount++;
			}
		}

		ArrayList<ArrayList<String>> tableData = new ArrayList<>();
		int currentIndex = 0;

		ArrayList<String> headerRow = new ArrayList<>();
		headerRow.add("Name");
		headerRow.add("Surname");
		headerRow.add("Email");
		headerRow.add("Date of Birth");
		headerRow.add("Age");
		headerRow.add("ID");

		tableData.add(headerRow);

		currentIndex++;

		for (ArrayList<String> user : database) {
			if (user != null) {
				tableData.add(new ArrayList<>(user));
				currentIndex++;
			}
		}
		return tableData;
	}

	public ArrayList<String> getUserByEmail(String email) {

		for (ArrayList<String> user : database) {
			if (user != null && user.size() > 2 && email.equals(user.get(2))) {
				return user;
			}
		}
		return null;
	}
}

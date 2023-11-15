import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.util.*;

public class UserDAO implements DAOInterface {

	private static String filePath = "fhulu.txt";
	private static String jsonFilePath = "userData.json";
	private static int position = 0;
	private static ArrayList<ArrayList<String>> database;

    static {
        database = new ArrayList<>();
        // Initialize the database array by deserializing from the file on class
        try {
            database = (ArrayList<ArrayList<String>>) SerializationHelper.deserializeFromFile("fhulu.txt");
        } catch (IOException | ClassNotFoundException e) {
            database = new ArrayList<>(); // Create a new database if deserialization fails
            e.printStackTrace();
        }
    }

	{
		try {
			ArrayList<ArrayList<String>> database = JSONSerializationHelper.deserializeFromFile(jsonFilePath, ArrayList.class);
		} catch (IOException e) {
			database = new ArrayList<>();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
	public static void create(String name, String surname, String email, String dob, String ageCalculate, String id) {

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
			SerializationHelper.serialiseToFile(database, filePath);
			System.out.println("User " + name + " has been saved to " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			JSONSerializationHelper.serialiseToFile(database, jsonFilePath);
			System.out.println("User " + name + " has been saved to " + jsonFilePath);
		} catch(JsonGenerationException | JsonMappingException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void update(String email, String name, String surname, String dob) {

		boolean updated = false;

		for (int i = 0; i < database.size(); i++) {
			if (database.get(i) != null && database.get(i).size() > 2 && email.equals(database.get(i).get(2))) {

				ArrayList<String> user = database.get(i);
				user.set(0, name);
				user.set(1, surname);
				user.set(3, dob);

				updated = true;
				break;
			}
		}
		if (updated) {

			try {
				SerializationHelper.serialiseToFile(database, filePath);
				System.out.println("User details updated successfully.");
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				JSONSerializationHelper.serialiseToFile(database, jsonFilePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("User not found in the database.");
		}

	}

	public static void delete(String email) {

		for (int i = 0; i < database.size(); i++) {
			if (database.get(i) != null && database.get(i).size() > 2 && email.equals(database.get(i).get(2))) {

				database.remove(i);

				try {
					SerializationHelper.serialiseToFile(database, filePath);
					System.out.println("User successfully deleted");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		System.out.println("User not found in the database.");
	}

	public static ArrayList<ArrayList<String>> findAll() {

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

	public static ArrayList<String> getUserByEmail(String email) {

		for (ArrayList<String> user : database) {
			if (user != null && user.size() > 2 && email.equals(user.get(2))) {
				return user;
			}
		}
		return null;
	}
}

import java.util.*;
import java.io.IOException;
import java.util.*;

public class MenuPrinter {

    private static int maxAttempts = 3;
    private static int attempts = 0;
    private static int lastAttempts = 3;
    private static int initialAttempt = 0;
    private static String filePath = "fhulu.txt";
    private static int count;
    static int userID() {
        try {
            count++;
            SerializationHelper.serialiseToFile(count, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void print() {

        while (true) {
            String test = Prompter.prompt(
                    "\nHello, welcome to the user database\nSelect an option:\n\n1. Add user\n2. Update user\n3. Delete user\n4. List users\n5. List user\n6. Exit");

            if (MenuValidator.validate(test)) {
                switch (test) {
                    case "1":
                        createUser();
                        break;

                    case "2":
                        updateUser();
                        break;

                    case "3":
                        deleteUser();
                        break;

                    case "4":
                        listUsers();
                        break;

                    case "5":
                        listUser();
                        break;

                    case "6":
                        System.exit(0);
                        break;
                }
            } else {
                System.out.println("You have selected an invalid option.\nPlease select from the options below:");
                print();
            }
        }
    }

    static void createUser() {

        String name = Prompter.prompt("Enter name:");
        String surname = Prompter.prompt("Enter surname:");
        String email = Prompter.prompt("Enter email:");

        String toInt = String.valueOf(userID());

        if (EmailValidator.validateEmail(email)) {

            String dateOfBirth = Prompter.prompt("Enter your date of birth:");
            if (DateValidator.isValid(dateOfBirth)) {
                UserDAO.create(name, surname, email, dateOfBirth, dateOfBirth, toInt);
                MenuPrinter.print();
            } else {
                int maxAttempts = 3;
                int attempts = 0;
                while (!DateValidator.isValid(dateOfBirth) && attempts < maxAttempts) {
                    System.out.println("Invalid Date of Birth,");
                    dateOfBirth = Prompter.prompt("please input the correct Date of Birth:");
                    if (DateValidator.isValid(dateOfBirth)) {
                        UserDAO.create(name, surname, email, dateOfBirth, dateOfBirth, toInt);
                        MenuPrinter.print();
                    } else {
                        attempts++;
                        if (attempts >= maxAttempts) {
                            System.out.println("Maximum number of attempts reached.");
                            MenuPrinter.print();
                        }
                    }
                }
            }
        } else {
            while (!EmailValidator.validateEmail(email) && attempts < maxAttempts) {
                System.out.println("Invalid Email,");
                email = Prompter.prompt("please input the correct Email:");

                if (EmailValidator.validateEmail(email)) {
                    String dateOfBirth = Prompter.prompt("Enter your date of birth:");

                    while (!DateValidator.isValid(dateOfBirth) && attempts < maxAttempts) {
                        System.out.println("Invalid Date of Birth,");
                        dateOfBirth = Prompter.prompt("please input the correct Date of Birth.");
                        initialAttempt++;
                        if (DateValidator.isValid(dateOfBirth)) {
                            UserDAO.create(name, surname, email, dateOfBirth, dateOfBirth, toInt);
                            print();
                        } else if (initialAttempt == lastAttempts) {

                            System.out.println("Maximum number of attempts reached.");
                            print();
                        }
                    }
                } else {
                    attempts++;
                    if (attempts >= maxAttempts) {
                        System.out.println("Maximum number of attempts reached.");
                        MenuPrinter.print();
                    }
                }
            }
        }
    }

    static void updateUser() {
        int maxAttempts = 3;
        int attempts = 0;
        String emailToUpdate = Prompter.prompt("Enter email to update user.");
        ArrayList<String> userEmail = UserDAO.getUserByEmail(emailToUpdate);

        if (userEmail != null) {
            String newName = Prompter.prompt("Enter new name:");
            String newSurname = Prompter.prompt("Enter new surname:");
            String newDob = Prompter.prompt("Enter new date of birth:");

            if (DateValidator.isValid(newDob)) {
                UserDAO.update(emailToUpdate, newName, newSurname, newDob);
                MenuPrinter.print();
            } else {
                attempts++;
                while (!DateValidator.isValid(newDob) && attempts < maxAttempts) {
                    System.out.println("Invalid Date of Birth, please input the correct Date of Birth.");
                    newDob = Prompter.prompt("Enter your date of birth:");
                    if (DateValidator.isValid(newDob)) {
                        UserDAO.update(emailToUpdate, newName, newSurname, newDob);
                        MenuPrinter.print();
                    } else if (attempts >= maxAttempts) {
                        System.out.println("Maximum number of attempts reached.");
                        MenuPrinter.print();
                    }
                }
            }
        } else {
            System.out.println("User not found in the database.");
        }
    }

    static void deleteUser() {

        String emailToDelete = Prompter.prompt("Enter email to delete user:");
        UserDAO.delete(emailToDelete);
    }

    static void listUsers() {

        ArrayList<ArrayList<String>> allUsers = UserDAO.findAll();
        if (allUsers != null && allUsers.size() > 0) {
            System.out.println("User Data:");

            for (ArrayList<String> user : allUsers) {
                for (String data : user) {
                    System.out.printf("%-20s", data);
                }
                System.out.println();
            }
        } else {
            System.out.println("No users found in the database.");
        }
    }

    static void listUser() {

        String emailToSearch = Prompter.prompt("Enter email to search for a user:");
        ArrayList<String> user = UserDAO.getUserByEmail(emailToSearch);

        if (user != null) {
            System.out.println("User found:");
            System.out.println("Name: " + user.get(0));
            System.out.println("Surname: " + user.get(1));
            System.out.println("Email: " + user.get(2));
            System.out.println("Date of Birth: " + user.get(3));
            System.out.println("ID: " + user.get(4));

        } else {
            System.out.println("User not found in the database.");
        }

    }
}

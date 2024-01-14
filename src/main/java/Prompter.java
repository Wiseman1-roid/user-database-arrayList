import java.util.Scanner;

public class Prompter{
	public static String prompt(String getInput){

		Scanner input = new Scanner(System.in);
		System.out.println(getInput);
		return input.nextLine();
	}
}
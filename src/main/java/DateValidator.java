public class DateValidator {
	public static boolean isValid(String validate) {

		String[] separate = validate.split("/");
		if (validate.length() == 10 && separate.length == 3 && validate.contains("/")) {
			return true;
		}
		return false;
	}
}
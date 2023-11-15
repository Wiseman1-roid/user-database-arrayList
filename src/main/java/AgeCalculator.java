import java.time.LocalDate;

public class AgeCalculator {
	public static int calculate(String age) {

		LocalDate currentDate = LocalDate.now();
		int currentYear = currentDate.getYear();
		int currentMonth = currentDate.getMonthValue();
		int currentDay = currentDate.getDayOfMonth();

		String years = age.split("/")[2];
		int birthYear = Integer.valueOf(years);

		String month = age.split("/")[1];
		int birthMonth = Integer.valueOf(month);

		String day = age.split("/")[0];
		int birthDay = Integer.valueOf(day);

		int yearsOld = currentYear - birthYear - 1;

		if (birthMonth < currentMonth) {
			return yearsOld + 1;
		} else if (birthMonth == currentMonth && birthDay >= currentDay) {
			return yearsOld + 1;
		}

		return yearsOld;
	}
}
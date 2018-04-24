import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;


public class Util {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	private static SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

	public static boolean validateInputInt(String property){
		return StringUtils.isNumeric(property) && !property.equals("0");
	}

	public static Date parseDate(Date date) {
		if (date != null) {
			String property = formatter1.format(date);
			try {
				date = java.sql.Date.valueOf(LocalDate.parse(property, formatter));
			} catch (Exception e) {

			}	
		}
		return date;
	}
}
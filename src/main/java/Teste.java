import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Teste {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		DateFormat brtFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ-2");
		Date d = df.parse("Fri Jun 13 10:24:01 BRT 2014");
		System.out.println(brtFormat.format(d)); // output: 2014-06-13T12:24:01+0200
		
	}

}

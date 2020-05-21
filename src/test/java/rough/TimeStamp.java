package rough;

import java.util.Date;

public class TimeStamp {

	public static void main(String[] args) {
		Date d = new Date();
		String screenshotname=d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		System.out.println(screenshotname);

	}

}

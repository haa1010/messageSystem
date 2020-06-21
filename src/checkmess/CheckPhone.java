package checkmess;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPhone {
	private String phone;

	public String getPhone() {
		return phone;
	}

	public CheckPhone(String x) {
		this.phone = x;
	}

	public boolean isValidE123() {
		Pattern p = Pattern.compile("(09)?[0-9]{8}");

		Matcher m = p.matcher(this.phone);
		return (m.find() && m.group().equals(this.phone));
	}

}

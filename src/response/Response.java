package response;

import graph.Database;

import java.util.ArrayList;

/**
 * @author Minh Thong- 20176881
 * This class is used to interact with user (response to user's requests)
 */
public class Response {

	/**
	 * This is function for checking if user is in database or not
	 * @param phoneNum user's phone number
	 * @return  user's state equals -1 if user is not in database
	 * 			user's information if user is valid
	 */
	public ArrayList<String> checkUser(String phoneNum) {
		if (Database.checkUser(phoneNum).isEmpty()) {
			ArrayList<String> result = new ArrayList<String>();
			result.add("");
			result.add("");
			result.add("-1");
			return result;
		} else {
			return Database.checkUser(phoneNum);
		}
	}

	/**
	 * This function is used to return 3 phone numbers to user
	 * @param phoneNum user's phone number
	 * @return 3 numbers that user requested
	 */
	public ArrayList<String> returnNumber(String phoneNum) {
		return Database.addSentList(phoneNum);
	}
}